package com.ivanmostovyi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessageDto {

    private Long id;

    private List<String> receiverUsernames;

    private String senderUsername;

    private boolean read;

    private boolean marked;

    private String title;

    private String body;

    private LocalDateTime date;

}
