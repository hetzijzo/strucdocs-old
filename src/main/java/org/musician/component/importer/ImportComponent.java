package org.musician.component.importer;

import org.musician.domain.song.Song;
import org.musician.domain.song.SongLine;

import java.io.IOException;

public interface ImportComponent {

	Song importSong(String songUrl)
			throws IOException;

	SongLine createSongLine(String songLine)
			throws IOException;
}
