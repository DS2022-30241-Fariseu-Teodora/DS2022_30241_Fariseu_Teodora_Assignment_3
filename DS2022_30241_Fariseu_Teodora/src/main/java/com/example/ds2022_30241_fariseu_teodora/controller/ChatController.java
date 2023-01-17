package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import com.example.ds2022_30241_fariseu_teodora.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/chat")
public class ChatController {
    @Autowired
    private ChatService service;

    @GetMapping("/myChats")
    private ResponseEntity<?> allChats() {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(service.userChats(session.getId()));
    }

    @PostMapping("/newChat")
    private ResponseEntity<?> newChat(@RequestBody ArrayList<String> participantsId) {
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.newMultiChat(participantsId, session );
        return ResponseEntity.ok("Chat created");
    }
}
