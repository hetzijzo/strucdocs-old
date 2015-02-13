package com.strucdocs.repository;

import com.strucdocs.domain.Band;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bands", path = "bands")
public interface BandRepository
		extends PagingAndSortingRepository<Band, Long> {

	Band findById(Long id);

	Band findByName(String name);

}
