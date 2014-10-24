package org.musician.component.transposer;

import org.musician.domain.chord.Chord;
import org.musician.domain.song.Song;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransposerComponent {

	public Song transposeSong(Song song, int steps);

	public Chord transposeChord(Chord chord, int steps);
}
