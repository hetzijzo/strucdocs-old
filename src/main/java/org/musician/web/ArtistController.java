package org.musician.web;

import org.musician.domain.Artist;
import org.musician.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;

	@RequestMapping
	public Artist addArtist() {

//		Artist artist = new Artist();
//		artist.setName("Lady Gaga");
//		Song song = new Song();
//		song.setArtist(artist);
//		song.setTitle("Poker Face");
//		artist.addSong(song);
//
//		artistRepository.save(artist);

		Artist artist1 = artistRepository.findByName("Lady Gaga");
		System.out.println(artist1);

		return artist1;
	}
}
