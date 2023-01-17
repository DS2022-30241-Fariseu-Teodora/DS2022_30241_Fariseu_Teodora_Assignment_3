package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteUserRepo extends JpaRepository<SiteUser, String> {
    boolean existsByUsername(String username);
    Optional<SiteUser> findOneByUsername(String username);
    List<SiteUser> findAllByRole(Role role);
    List<SiteUser> findByUsernameContainingAndRole(String username, Role role);
}
