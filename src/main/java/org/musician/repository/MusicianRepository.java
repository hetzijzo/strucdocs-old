package org.musician.repository;

import org.musician.domain.Band;
import org.musician.domain.Musician;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface MusicianRepository
		extends GraphRepository<Musician> {

	Musician findById(Long id);

	Musician findByName(String name);

}
