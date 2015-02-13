package com.strucdocs.component.transpose;

import com.strucdocs.domain.chord.Chord;
import com.strucdocs.domain.chord.Scale;
import com.strucdocs.domain.song.Song;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TransposeComponent {

	Song transposeSong(Song song, Scale scale, int steps);

	Chord transposeChord(Chord chord, Scale scale, int steps);
}
