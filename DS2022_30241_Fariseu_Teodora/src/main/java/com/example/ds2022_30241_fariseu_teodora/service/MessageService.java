package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.chat.MessageDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.NewMessageDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Chat;
import com.example.ds2022_30241_fariseu_teodora.entity.Message;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import com.example.ds2022_30241_fariseu_teodora.repository.MessageRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.SiteUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    protected SiteUserRepo userRepo;
    public Message newMessage(NewMessageDTO message) {
        try {
            log.info("Inserting message " +message.getMessage()+" on conversation "+message.getChatId()+" for author "+message.getUserId());
            Message newMessage = new Message();
            newMessage.setMessage(message.getMessage());
            log.info("Timestamp: "+message.getSentDate());
            newMessage.setSentDate(message.getSentDate());
            newMessage.setRead(false);
            Chat chat = new Chat();
            chat.setId(message.getChatId());
            chat.setMessages(new HashSet<>(messageRepo.findAllByDiscussionOrderBySentDateDesc(chat)));
            newMessage.setDiscussion(chat);
            SiteUser author = userRepo.findById(message.getUserId()).orElse(null);
            newMessage.setAuthor(author);
            return messageRepo.save(newMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<MessageDTO> acknowledge(Double untilDate, String chatId, String seenerId) {
        try {
            Chat chat = new Chat();
            chat.setId(chatId);
            log.info("Messages to update "+messageRepo.findAllByDiscussionAndSentDateBeforeOrSentDateEquals(chat, untilDate, untilDate).size());
            List<Message> msg = messageRepo.findAllByDiscussionAndSentDateBeforeOrSentDateEquals(chat, untilDate, untilDate).stream().map(m -> {
                Message mi = messageRepo.findById(m.getId()).orElse(null);
                SiteUser siteUser = new SiteUser();
                siteUser.setId(seenerId);
                if(mi.getSeenBy() == null) {
                    mi.setSeenBy(new HashSet<SiteUser>());
                }
                mi.getSeenBy().add(siteUser);
                log.info("Seeners:"+siteUser.getSeenBy().size());
                return mi;
            }).toList();
            messageRepo.saveAll(msg);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public List<String> getSeen(String chatId) {
        Chat c = new Chat();
        c.setId(chatId);
        return messageRepo.findFirstByDiscussionOrderBySentDateDesc(c).getSeenBy().stream().map(
                m-> m.getUsername()
        ).collect(Collectors.toList());
    }
    public List<MessageDTO> conversationMessages(String discussionId, String sessionUserId) {
        Chat chat = new Chat();
        chat.setId(discussionId);
        return messageRepo.findAllByDiscussionOrderBySentDateDesc(chat).stream().map(
                message -> {
                    MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
                    messageDTO.setUsername(message.getAuthor().getUsername());
                    messageDTO.setSenderId(message.getAuthor().getId());
                    messageDTO.setSentDate(message.getSentDate());
                    messageDTO.setWhoSaw(message.getSeenBy().stream().map(i->i.getUsername())
                            .collect(Collectors.toList()));
                    return messageDTO;
                }
        ).collect(Collectors.toList());
    }
}
