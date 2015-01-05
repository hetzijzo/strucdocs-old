package org.musician.component.transpose;

import org.musician.domain.chord.Chord;
import org.musician.domain.chord.Scale;
import org.musician.domain.song.Song;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransposeComponent {

	Song transposeSong(Song song, Scale scale, int steps);

	Chord transposeChord(Chord chord, Scale scale, int steps);
}
