package com.ivanmostovyi.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage {

    @Id
    private Long id;

    private String title;

    private String body;

    private LocalDateTime date;

    private boolean marked;

    private String[] receiverUsernames;

    private Long senderUserId;

}
