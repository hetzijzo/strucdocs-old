package com.strucdocs.repository;

import com.strucdocs.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
