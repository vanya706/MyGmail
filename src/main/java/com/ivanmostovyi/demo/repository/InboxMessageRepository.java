package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.InboxMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InboxMessageRepository extends CrudRepository<InboxMessage, Long> {

    List<InboxMessage> findAllByReceiverUserId(Long id, Pageable pageable);

    List<InboxMessage> findAllByReceiverUserIdAndTitleContaining(Long senderId, String title, Pageable pageable);

    long countAllByReceiverUserIdAndTitleContaining(Long senderId, String title);

    long countAllByReceiverUserId(Long id);

}
