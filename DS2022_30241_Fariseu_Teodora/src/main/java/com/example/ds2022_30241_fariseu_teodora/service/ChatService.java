package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.chat.ChatDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Chat;
import com.example.ds2022_30241_fariseu_teodora.entity.Message;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import com.example.ds2022_30241_fariseu_teodora.repository.ChatRepo;
import com.example.ds2022_30241_fariseu_teodora.repository.MessageRepo;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatService {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    MessageRepo messageRepo;
    public void newMultiChat(List<String> recipients, AuthenticateDTO starter) {
        Chat newChat = new Chat();
        newChat.setParticipants(new HashSet<>());
        SiteUser author = new SiteUser();
        author.setId(starter.getId());

        newChat.getParticipants().add(author);
        log.info("Recipients "+recipients.size());
        newChat.getParticipants().addAll(recipients.stream().map(id -> {
            SiteUser recipient = new SiteUser();
            recipient.setId(id);
            return recipient;
        }).collect(Collectors.toList()));
        chatRepo.save(newChat);
    }
    public List<ChatDTO> userChats(String userId) {
        return chatRepo.chatsForUser(userId).stream().map(chat -> {
                    ChatDTO chatDTO = ChatDTO.builder()
                            .id(chat.getId()).build();
                    chatDTO.setName(chat.getParticipants().stream().filter(participant-> !participant.getId().equals(userId)).map(p-> p.getUsername()).collect(Collectors.joining(",")));
                    Message preview = messageRepo.findFirstByDiscussionOrderBySentDateDesc(chat);
                    if(preview==null) chatDTO.setPreviewMessage("");
                    else chatDTO.setPreviewMessage(preview.getMessage());
                    chatDTO.setUnreadMessages(messageRepo.getUnreadMessagesAmount(chat.getId()));
                    return chatDTO;
                })
                .collect(Collectors.toList());
    }
}
