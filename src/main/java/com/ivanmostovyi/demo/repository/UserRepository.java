package com.ivanmostovyi.demo.repository;


import com.ivanmostovyi.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long aLong);

    Optional<User> findByUsername(String username);

    List<User> findAll();

}
