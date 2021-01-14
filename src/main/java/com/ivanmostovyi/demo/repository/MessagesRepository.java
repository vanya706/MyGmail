package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.Messages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, Long> {

    List<Messages> findAllByUserId(Long id);

    List<Messages> findAllByReceiverId(Long id);

}
