package org.musician.web;

import org.musician.domain.Artist;
import org.musician.domain.chord.Chord;
import org.musician.domain.chord.ChordNote;
import org.musician.domain.song.*;
import org.musician.repository.ArtistRepository;
import org.musician.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private ArtistRepository artistRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Song> getSongs() {
		Artist artist = new Artist();
		artist.setName("Lady Gaga");
		artist = artistRepository.save(artist);

		Song song = new Song();
		song.setTitle("Poker Face");
		song.setArtist(artist);

		SongPart songPart1 = new SongPart();
		songPart1.setType(SongPartType.INTRO);
		SongLine line1 = new SongLine();
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.C);
		line1.setChord(chord);
		Lyric lyric = new Lyric();
		lyric.setText("Mijn zanglijn op een c akkorrd");
		line1.setLyric(lyric);
		songPart1.addLine(line1);
		song.addSongPart(songPart1);

		songRepository.save(song);

		return songRepository.findByTitle("Poker Face");
	}
}
