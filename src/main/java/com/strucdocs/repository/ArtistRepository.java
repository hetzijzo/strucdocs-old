package com.strucdocs.repository;

import com.strucdocs.domain.Artist;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository
		extends PagingAndSortingRepository<Artist, Long> {

	Artist findById(Long id);

	Artist findByName();

}
