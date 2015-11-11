package com.strucdocs.component.importer;

import com.strucdocs.domain.song.Song;
import com.strucdocs.domain.song.SongLine;

import java.io.IOException;

public interface ImportComponent {

	Song importSong(String songUrl)
			throws IOException;

	SongLine createSongLine(String songLine)
			throws IOException;
}
