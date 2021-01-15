package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllBySenderUserId(Long id);

    List<Message> findAllByReceiverUserId(Long id);

}
