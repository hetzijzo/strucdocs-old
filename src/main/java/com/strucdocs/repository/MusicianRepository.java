package com.strucdocs.repository;

import com.strucdocs.domain.Musician;
import com.strucdocs.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "musicians", path = "musicians")
public interface MusicianRepository
        extends PagingAndSortingRepository<Musician, Long> {

    Musician findByUser(User user);

    Musician findByName(String name);

}