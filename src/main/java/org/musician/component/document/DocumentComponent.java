package org.musician.component.document;

import com.itextpdf.text.DocumentException;
import org.musician.domain.song.Song;

import java.io.OutputStream;

public interface DocumentComponent {

	void generateDocumentToOutputStream(OutputStream outputStream, Song song)
			throws DocumentException;
}
