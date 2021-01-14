package com.ivanmostovyi.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("message")
public class Messages {

    @Id
    private Long id;

    private boolean read;

    private boolean marked;

    private String title;

    private String body;

    private Long receiverId;

    private Long userId;

}
