package org.musician.repository;

import org.musician.domain.song.Song;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface SongRepository
		extends GraphRepository<Song> {

	Song findById(Long id);

	List<Song> findByTitle(String title);

}
