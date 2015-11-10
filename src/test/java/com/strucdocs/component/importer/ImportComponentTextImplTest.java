package com.strucdocs.component.importer;

import com.strucdocs.domain.song.Song;
import org.junit.Test;

public class ImportComponentTextImplTest {

    private ImportComponent importComponent = new ImportComponentTextImpl();

    @Test
    public void testImportSong()
            throws Exception {

        Song song = importComponent.importSong("song_text.txt");
        System.out.println(song);

    }
}