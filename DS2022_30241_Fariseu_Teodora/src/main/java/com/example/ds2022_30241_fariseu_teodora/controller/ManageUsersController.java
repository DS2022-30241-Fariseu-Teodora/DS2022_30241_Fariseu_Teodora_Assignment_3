package com.example.ds2022_30241_fariseu_teodora.controller;

import com.example.ds2022_30241_fariseu_teodora.dto.device.DeviceDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.EditProfileDTO;
import com.example.ds2022_30241_fariseu_teodora.dto.user.UserProfileDTO;
import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import com.example.ds2022_30241_fariseu_teodora.service.SiteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/users")
public class ManageUsersController {
    @Autowired
    protected SiteUserService service;

    @GetMapping("/")
    public ResponseEntity<List<UserProfileDTO>> getAccounts() {
        return new ResponseEntity<>(service.allUsers(),HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(String userID){
        return ResponseEntity.ok(service.userProfile(userID));
    }
    @GetMapping("/searchUsers")
    public ResponseEntity<List<UserProfileDTO>> findBy(@RequestParam String username, @RequestParam Boolean isAdmin) {
        Role role;
        if(isAdmin) role = Role.ADMIN;
        else  role = Role.USER;
        return ResponseEntity.ok(service.findByUsername(username,role));
    }

    @PostMapping("/associateDevice")
    public ResponseEntity<String> associateDevice(DeviceDTO deviceDTO) {
        return ResponseEntity.ok("Device associated");
    }

    @PutMapping("/editUser")
    public ResponseEntity<Boolean> editUser(@RequestBody EditProfileDTO profileDTO) {
        return ResponseEntity.ok(service.editUser(profileDTO));
    }

    @DeleteMapping("/deleteUser/{profile}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String profile) {
        try {
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            userProfileDTO.setId(profile);
            service.deleteAccount(userProfileDTO);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}
