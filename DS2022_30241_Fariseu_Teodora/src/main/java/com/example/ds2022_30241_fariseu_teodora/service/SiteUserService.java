package com.example.ds2022_30241_fariseu_teodora.service;

import com.example.ds2022_30241_fariseu_teodora.dto.mappers.ProfileMapper;
import com.example.ds2022_30241_fariseu_teodora.dto.user.*;
import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import com.example.ds2022_30241_fariseu_teodora.security.AuthenticateDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.ds2022_30241_fariseu_teodora.repository.SiteUserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SiteUserService implements UserDetailsService {
    @Autowired
    protected SiteUserRepo repo;
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected ProfileMapper profileMapper;
    public void registerAccount(RegisterDTO account) {
        try {
            SiteUser user = modelMapper.map(account, SiteUser.class);
            user.setRole(Role.USER);
            repo.save(user);
        } catch (Exception e){
            throw e;
        }
    }
    public UserDetails login(LoginDTO user) throws Exception {
        return loadUserByUsername(user.getUsername());
    }
    public SessionDTO initSession(LoginDTO user) throws Exception{
        SiteUser userDAO = repo.findOneByUsername(user.getUsername()).orElse(null);
        if(userDAO == null)
            throw new Exception("Username does not exist");
        if(userDAO.getPassword().equals(user.getPassword()))
            return modelMapper.map(userDAO, SessionDTO.class);
        throw new Exception("Password is incorrect");
    }
    public List<UserProfileDTO> allUsers() {
        return repo.findAllByRole(Role.USER).stream().map(user -> modelMapper.map(user, UserProfileDTO.class)).collect(Collectors.toList());
    }

    public void deleteAccount(UserProfileDTO account) throws Exception {
        Optional<SiteUser> desiredUser = repo.findOneByUsername(account.getUsername());
        try {
            repo.delete(desiredUser.get());
        }catch (Exception e) {
            throw e;
        }
    }
    public List<UserProfileDTO> findByUsername(String username, Role role){
        return repo.findByUsernameContainingAndRole(username,role).stream().map(user -> modelMapper.map(user, UserProfileDTO.class)).collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<SiteUser> user = repo.findOneByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new AuthenticateDTO(user.get());
    }
    public UserProfileDTO userProfile(String userID) {
        SiteUser user = repo.findById(userID).orElse(null);
        if(user != null)
            return modelMapper.map(user, UserProfileDTO.class);
        return null;
    }
    public Boolean editUser(EditProfileDTO user) {
        SiteUser user1 = repo.findById(user.getId()).orElse(null);
        if(!user.getUsername().equals(""))
            user1.setUsername(user.getUsername());
        if(!user.getEmail().equals(""))
            user1.setEmail(user.getEmail());
        try{
            repo.save(user1);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
