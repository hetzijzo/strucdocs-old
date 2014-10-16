package org.musician.web;

import org.musician.domain.Artist;
import org.musician.domain.song.Song;
import org.musician.repository.ArtistRepository;
import org.musician.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private ArtistRepository artistRepository;

	@RequestMapping
	@ResponseBody
	public List<Song> getSongs() {
		Artist artist = new Artist();
		artist.setName("Lady Gaga");
		artist = artistRepository.save(artist);

		Song song = new Song();
		song.setTitle("Poker Face");
		song.setArtist(artist);
		songRepository.save(song);

		return songRepository.findByTitle("Poker Face");
	}
}
