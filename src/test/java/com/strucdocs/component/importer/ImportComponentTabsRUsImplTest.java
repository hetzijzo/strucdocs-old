package com.strucdocs.component.importer;

import com.strucdocs.domain.chord.Chord;
import com.strucdocs.domain.song.Song;
import com.strucdocs.domain.song.SongLine;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ImportComponentTabsRUsImplTest {

	private ImportComponent importComponent = new ImportComponentTabsRUsImpl();

	@Test
	public void testImportSong()
			throws Exception {
		Song song = importComponent.importSong("O1f/Thinking_out_Loud.html");
	}

	@Test
	public void createSongLine()
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("Eb7     Cm7");
		sb.append("\n");
		sb.append("This should be translated to lyric.");

		SongLine songLine = importComponent.createSongLine(sb.toString());
		assertThat(songLine, notNullValue());
		Set<Chord> chords = songLine.getChords();
		assertThat(chords, notNullValue());
		assertThat(chords.size(), is(2));

		assertThat(songLine.getLyric(), notNullValue());
		assertThat(songLine.getLyric().getText(), equalTo("This should be translated to lyric."));
	}
}