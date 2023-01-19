package com.example.ds2022_30241_fariseu_teodora.repository;

import com.example.ds2022_30241_fariseu_teodora.entity.Chat;
import com.example.ds2022_30241_fariseu_teodora.entity.Message;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MessageRepo extends JpaRepository<Message, String> {
    Message findFirstByDiscussionOrderBySentDateDesc(Chat discussion);
    List<Message> findAllByDiscussionOrderBySentDateDesc(Chat discussion);
    Set<Message> findAllByAuthor(SiteUser siteUser);
    @Query(
            value = "SELECT COUNT(*) FROM message \n" +
                    "WHERE message.discussion_id = ?1 \n" +
                    "AND message.read = false" ,
            nativeQuery = true
    )
    Integer getUnreadMessagesAmount(String discussionId);

    List<Message> findAllByDiscussionAndSentDateBeforeOrSentDateEquals(Chat discussion, Double sentDate, Double sentDate2);
}
