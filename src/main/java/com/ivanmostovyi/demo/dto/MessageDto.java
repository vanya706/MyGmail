package com.ivanmostovyi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private String receiverUsername;

    private String senderUsername;

    private boolean read;

    private boolean marked;

    private String title;

    private String body;

    private LocalDateTime date;

}