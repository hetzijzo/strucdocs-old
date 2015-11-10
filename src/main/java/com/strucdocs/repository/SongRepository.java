package com.strucdocs.repository;

import com.strucdocs.domain.song.Song;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "songs", path = "songs")
public interface SongRepository
		extends PagingAndSortingRepository<Song, Long> {

	List<Song> findByTitle(String title);

}
