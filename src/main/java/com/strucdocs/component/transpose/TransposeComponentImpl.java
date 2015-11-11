package com.strucdocs.component.transpose;

import com.strucdocs.domain.chord.Chord;
import com.strucdocs.domain.chord.Scale;
import com.strucdocs.domain.song.Song;
import org.springframework.stereotype.Service;

@Service
class TransposeComponentImpl
		implements TransposeComponent {

	@Override
	public Song transposeSong(Song song, Scale scale, int steps) {
		song.transpose(scale, steps);
		return song;
	}

	@Override
	public Chord transposeChord(Chord chord, Scale scale, int steps) {
		chord.transpose(scale, steps);
		return chord;
	}
}