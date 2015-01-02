package org.musician.repository;

import org.musician.domain.song.Song;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository
		extends PagingAndSortingRepository<Song, Long> {

	Song findById(Long id);

	List<Song> findByTitle(String title);

}
