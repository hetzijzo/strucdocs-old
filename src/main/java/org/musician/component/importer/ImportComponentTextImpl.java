package org.musician.component.importer;

import org.musician.domain.song.Song;
import org.musician.domain.song.SongComponent;
import org.musician.domain.song.SongLine;
import org.musician.domain.song.SongPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ImportComponentTextImpl
		implements ImportComponent {

	@Override
	public Song importSong(String songUrl)
			throws IOException {
		InputStream inputStream = ImportComponentTextImpl.class.getResourceAsStream("/" + songUrl);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		bufferedReader.lines()
				.map(line -> detectTypeOfLine(line)).collect(Collectors.toList());
		//.forEach(line -> detectTypeOfLine(line));


		return null;
	}

	private Class<? extends SongComponent> detectTypeOfLine(String line) {
		Class<? extends SongComponent> componentClass = null;

		Pattern titlePattern = Pattern.compile("^Title:\\s*(\\w*)\\s*$");
		Predicate<String> titlePredicate = titlePattern.asPredicate();
		if (titlePredicate.test(line)) {
			componentClass = Song.class;
		}

		Pattern artistPattern = Pattern.compile("^Artist:\\s*(\\w*)\\s*$");
		Predicate<String> artistPredicate = artistPattern.asPredicate();
		if (artistPredicate.test(line)) {
			componentClass = Song.class;
		}


		Pattern partPattern = Pattern.compile("^.*Intro|Verse|Chorus|Solo|Outro\\s*(.*):*\\s*$");
		Predicate<String> partPredicate = partPattern.asPredicate();
		if (partPredicate.test(line)) {
			componentClass = SongPart.class;
		}

		return componentClass;
	}

	@Override
	public SongLine createSongLine(String songLine)
			throws IOException {
		return null;
	}
}
