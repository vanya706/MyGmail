package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllBySenderUserId(Long id);

    List<Message> findAllByReceiverUserId(Long id);

}
