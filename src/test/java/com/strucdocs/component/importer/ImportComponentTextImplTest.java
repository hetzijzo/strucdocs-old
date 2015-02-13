package com.strucdocs.component.importer;

import org.junit.Test;
import com.strucdocs.domain.song.Song;

public class ImportComponentTextImplTest {

    private ImportComponent importComponent = new ImportComponentTextImpl();

    @Test
    public void testImportSong()
            throws Exception {

        Song song = importComponent.importSong("song_text.txt");
        System.out.println(song);

    }
}