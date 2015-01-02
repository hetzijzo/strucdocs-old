package org.musician.component.generatedocument;

import com.itextpdf.text.DocumentException;
import org.musician.domain.song.Song;

import java.io.OutputStream;

public interface GenerateDocumentComponent {

	void generateDocumentToOutputStream(OutputStream outputStream, Song song)
			throws DocumentException;
}
