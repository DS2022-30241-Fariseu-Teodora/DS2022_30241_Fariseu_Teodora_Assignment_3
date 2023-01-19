package com.example.ds2022_30241_fariseu_teodora.components;

import com.example.ds2022_30241_fariseu_teodora.dto.chat.MessageDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.NewMessageDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.SeenerListDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.TypingDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Message;
import com.example.ds2022_30241_fariseu_teodora.service.ChatService;
import com.example.ds2022_30241_fariseu_teodora.service.MessageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class GrpcHatesMyExistence {
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    @MessageMapping("/messageSent")
    public Boolean receiveMessage(@Payload NewMessageDTO message) {
        try {
            messageService.newMessage(message);
            template.convertAndSend("/topic/conversation/"+message.getChatId(), message);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @MessageMapping("/messageSeen/{id}")
    public void ackMessage(@PathParam ("id") String chatId, @Payload SeenerListDTO until) {
        try {
            log.info("Messages :"+until.getUntil()+" "+until.getUser());
            messageService.acknowledge( until.getUntil(),chatId,until.getUser());
            template.convertAndSend("/topic/conversation/seen/"+chatId,"aaa");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @MessageMapping("/typingStatus")
    public void typingStatus(@Payload TypingDTO status) {
        template.convertAndSend("/topic/conversation/typeStatus/"+status.getChatId(), status.getIsTyping());
    }

    @SendTo("/topic/conversation/seen/{id}")
    public List<String> ackmMessage(@PathParam ("id") String chatId, @Payload String aa) {
        log.info("seen by"+messageService.getSeen(chatId).stream().collect(Collectors.joining(",")));
        return messageService.getSeen(chatId);
    }
    @SendTo("/topic/conversation/{id}")
    public MessageDTO broadcastMessage(@Payload MessageDTO message) {
        log.info("Sending message back...");
        return message;
    }

    @SendTo("/topic/conversation/typeStatus/{id}")
    public Boolean typeStatus(@Payload Boolean status) {
        return status;
    }
}
