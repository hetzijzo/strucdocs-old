package org.musician.component.importer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.musician.domain.song.Song;
import org.musician.domain.song.SongLine;

import java.io.IOException;

public class ImportComponentTabsRUsImpl
		implements ImportComponent {

	private final String baseUrl = "http://www.tabsrus.nl/Tablature/Download/";


	@Override
	public Song importSong(String songUrl)
			throws IOException {
		Document doc = Jsoup.connect(baseUrl + songUrl).get();
		Element tablatureContainer = doc.getElementById("tablatureContainer");

		tablatureContainer.getAllElements().get(0);

		Song song = new Song();
		return song;
	}

	public SongLine createSongLine(String songLineString)
			throws IOException {
		return SongLine.fromString(songLineString);
	}

}
