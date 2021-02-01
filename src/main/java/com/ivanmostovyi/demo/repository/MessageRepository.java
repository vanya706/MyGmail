package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllBySenderUserId(Long id, Pageable pageable);

    List<Message> findAllByReceiverUserId(Long id, Pageable pageable);

    List<Message> findAllByReceiverUserIdAndTitleContainingOrSenderUserIdAndTitleContaining(
            Long receiverId, String title1, Long senderId, String title2, Pageable pageable
    );

    long countAllByReceiverUserIdAndTitleContainingOrSenderUserIdAndTitleContaining(
            Long receiverId, String title1, Long senderId, String title2
    );

    long countAllBySenderUserId(Long id);

    long countAllByReceiverUserId(Long id);

}
