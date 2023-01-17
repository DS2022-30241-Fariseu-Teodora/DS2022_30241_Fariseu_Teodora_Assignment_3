package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.security.JwtTokenUtil;
import com.example.ds2022_30241_fariseu_teodora.dto.privileges.HomeOptionsDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.privileges.NavOption;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.LoginDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.RegisterDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.SessionDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.ds2022_30241_fariseu_teodora.service.SiteUserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    protected SiteUserService userService;

    @PostMapping(value = "/registerAccount")
    public ResponseEntity<String> addUser(@Valid @RequestBody RegisterDTO newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            userService.registerAccount(newUser);
            return new ResponseEntity<>("Account registered successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            return new ResponseEntity<>("User disabled",HttpStatus.FORBIDDEN);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials",HttpStatus.FORBIDDEN);
        }
        try {
            AuthenticateDTO userDetails = (AuthenticateDTO) userService.login(credentials);
            return ResponseEntity.ok()
                    .body(jwtTokenUtil.generateTokenFromUsername(userDetails.getUsername()));
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    @GetMapping("/getSessionDetails")
    public ResponseEntity<SessionDTO> getSession(){
        try {
            AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setUsername(session.getUsername());
            sessionDTO.setRole(session.getRole());
            sessionDTO.setId(session.getId());
            return ResponseEntity.ok(sessionDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/homeRoles")
    public ResponseEntity<List<HomeOptionsDTO>> getOptions(){
        AuthenticateDTO session = (AuthenticateDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(session == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<HomeOptionsDTO> options = new ArrayList<>();
        if(session.getRole() == Role.ADMIN)  {
            options.add(HomeOptionsDTO.builder().optionName("Manage Users")
                    .description("View and manage all users from the database")
                    .redirectURL("/users").build());
            options.add(HomeOptionsDTO.builder().optionName("Manage Devices")
                    .description("View and manage all possible energy metering devices")
                    .redirectURL("/models").build());
            options.add(HomeOptionsDTO.builder().optionName("Chats")
                    .description("Chat with users about their energy consumption")
                    .redirectURL("/chats").build());
        } else {
            options.add(HomeOptionsDTO.builder().optionName("Notifications")
                    .description("View notifications about devices")
                    .redirectURL("/notifications").build());
            options.add(HomeOptionsDTO.builder().optionName("My Devices")
                    .description("View and manage all your devices")
                    .redirectURL("/devices").build());
            options.add(HomeOptionsDTO.builder().optionName("Daily Consumption")
                    .description("View the total amount of energy consumed today of your metering devices")
                    .redirectURL("/energy").build());
            options.add(HomeOptionsDTO.builder().optionName("Chats")
                    .description("Chat with an admin about your energy consumption")
                    .redirectURL("/chats").build());
        }
        return ResponseEntity.ok(options);
    }
    @PostMapping("/logout")
    public void logout() {
        SecurityContextHolder.setContext(null);
    }
}
