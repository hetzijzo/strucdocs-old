package com.strucdocs.repository;

import com.strucdocs.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository
        extends GraphRepository<User> {

    User findByUsername(String username);

}
