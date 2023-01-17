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

    public List<MessageDTO> acknowledge(List<MessageDTO> messages) {
        try {/*
            List<Message> msg = messages.stream().map();
            return messageRepo.saveAllAndFlush(messages);*/
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    public List<MessageDTO> conversationMessages(String discussionId, String sessionUserId) {
        Chat chat = new Chat();
        chat.setId(discussionId);
        return messageRepo.findAllByDiscussionOrderBySentDateDesc(chat).stream().map(
                message -> {
                    MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
                    messageDTO.setIsFromMe(message.getAuthor().getId().equals(sessionUserId));
                    return messageDTO;
                }
        ).collect(Collectors.toList());
    }
}
