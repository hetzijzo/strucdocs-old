package org.musician.repository;

import org.musician.domain.Artist;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArtistRepository
		extends GraphRepository<Artist> {

	Artist findById(Long id);

	Artist findByName(String name);

}
