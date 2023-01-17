package com.example.ds2022_30241_fariseu_teodora.components;

import com.example.ds2022_30241_fariseu_teodora.dto.chat.MessageDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.NewMessageDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.chat.TypingDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.consumption.ConsumptionDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Message;
import com.example.ds2022_30241_fariseu_teodora.service.ChatService;
import com.example.ds2022_30241_fariseu_teodora.service.MessageService;
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
import java.util.List;

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
            log.info("Received message " +message.getMessage());
            messageService.newMessage(message);
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setRead(false);
            messageDTO.setMessage(message.getMessage());
            template.convertAndSend("/topic/conversation/"+message.getChatId(), messageDTO);
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @SendTo("/topic/conversation/seen/{id}")
    @MessageMapping("/messageSeen/{id}")
    public List<MessageDTO> ackMessage(@PathParam ("id") String chatId,@Payload List<MessageDTO> messages) {
        messageService.acknowledge(messages);
        return messages;
    }

    @MessageMapping("/typingStatus")
    public void typingStatus(@Payload TypingDTO status) {
        template.convertAndSend("/topic/conversation/typeStatus/"+status.getChatId(), status.getIsTyping());
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
