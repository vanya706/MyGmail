package com.ivanmostovyi.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    private Long id;

    private boolean read;

    private boolean marked;

    private String title;

    private String body;

    private Long receiverUserId;

    private Long senderUserId;

}
