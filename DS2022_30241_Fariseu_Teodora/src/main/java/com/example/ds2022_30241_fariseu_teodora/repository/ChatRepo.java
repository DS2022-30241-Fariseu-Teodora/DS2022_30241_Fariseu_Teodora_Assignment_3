package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, String> {
    @Query(
            value = "SELECT * FROM chat\n" +
                    "INNER JOIN chat_participant c ON c.chat_id = chat.chat_id WHERE c.user_id = ?1",
            nativeQuery = true
    )
    List<Chat> chatsForUser(String userID);
}
