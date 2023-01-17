package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.chat.MessageDTO;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import com.example.ds2022_30241_fariseu_teodora.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/lastMessages")
    public ResponseEntity<List<MessageDTO>> lastMessages(@RequestParam String discussion) {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(messageService.conversationMessages(discussion,session.getId()));
    }
}
