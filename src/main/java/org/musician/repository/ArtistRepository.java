package org.musician.repository;

import org.musician.domain.Artist;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "artists", path = "artists")
public interface ArtistRepository
		extends PagingAndSortingRepository<Artist, Long> {

	Artist findById(Long id);

	Artist findByName();

}
