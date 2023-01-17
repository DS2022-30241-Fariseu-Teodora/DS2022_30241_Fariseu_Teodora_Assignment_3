package com.example.ds2022_30241_fariseu_teodora.dto.user;

import com.example.ds2022_30241_fariseu_teodora.dto.user.SimpleUserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO extends SimpleUserDTO {

    private String email;
    @Setter(AccessLevel.NONE)
    private String joinedAt;

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm").format(joinedAt);
    }

    public LocalDateTime parseJoinedAt() {
        return LocalDateTime.parse(joinedAt,DateTimeFormatter.ofPattern("dd.MM.yyyy, hh:mm"));
    }
}
