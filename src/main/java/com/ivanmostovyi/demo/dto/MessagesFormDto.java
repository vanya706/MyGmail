package com.ivanmostovyi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagesFormDto {

    private String title;

    private String body;

    private String receiverUsername;

    private Long userId;

}