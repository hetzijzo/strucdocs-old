package org.musician.repository;

import org.musician.domain.Band;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BandRepository
		extends GraphRepository<Band> {

	Band findById(Long id);

	Band findByName(String name);

}
