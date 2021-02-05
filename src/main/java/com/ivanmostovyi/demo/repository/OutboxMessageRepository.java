package com.ivanmostovyi.demo.repository;

import com.ivanmostovyi.demo.domain.OutboxMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutboxMessageRepository extends CrudRepository<OutboxMessage, Long> {

    List<OutboxMessage> findAllBySenderUserId(Long id, Pageable pageable);

    long countAllBySenderUserId(Long id);

}
