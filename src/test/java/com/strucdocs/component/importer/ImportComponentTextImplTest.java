package com.strucdocs.component.importer;

import com.strucdocs.component.document.DocumentComponent;
import com.strucdocs.component.document.DocumentComponentImpl;
import com.strucdocs.domain.song.Song;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class ImportComponentTextImplTest {

    private ImportComponent importComponent = new ImportComponentTextImpl();

    private DocumentComponent documentComponent = new DocumentComponentImpl();

    @Test
    public void testImportSong()
            throws Exception {

        Song song = importComponent.importSong("song_text.txt");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        documentComponent.generateDocumentToOutputStream(outputStream, song);

        System.out.println(outputStream.toString());
    }
}