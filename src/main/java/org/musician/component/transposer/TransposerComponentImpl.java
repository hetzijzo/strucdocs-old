package org.musician.component.transposer;

import org.musician.domain.chord.Chord;
import org.musician.domain.song.Song;
import org.springframework.stereotype.Service;

@Service
class TransposerComponentImpl
		implements TransposerComponent {

	@Override
	public Song transposeSong(Song song, int steps) {
		song.getSongParts().parallelStream().forEach(
				part -> part.getLines().parallelStream().forEach(
						line -> transposeChord(line.getChord(), steps)
				)
		);

		return song;
	}

	@Override
	public Chord transposeChord(Chord chord, int steps) {
		chord.transpose(steps);
		return chord;
	}
}