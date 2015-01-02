package org.musician.repository;

import org.musician.domain.Musician;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "musicians", path = "musicians")
public interface MusicianRepository
		extends PagingAndSortingRepository<Musician, Long> {

	Musician findById(Long id);

	Musician findByName(String name);

}
