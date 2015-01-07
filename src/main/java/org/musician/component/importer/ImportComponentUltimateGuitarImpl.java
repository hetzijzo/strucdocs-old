package org.musician.component.importer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.musician.domain.song.Song;
import org.musician.domain.song.SongLine;

import java.io.IOException;

public class ImportComponentUltimateGuitarImpl
		implements ImportComponent {

	private final String baseUrl = "http://tabs.ultimate-guitar.com/print/";

	@Override
	public Song importSong(String songUrl)
			throws IOException {

		Document doc = Jsoup.connect(baseUrl + songUrl).get();


		return null;
	}

	@Override
	public SongLine createSongLine(String songLine)
			throws IOException {
		return null;
	}
}
