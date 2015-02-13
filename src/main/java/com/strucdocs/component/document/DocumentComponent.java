package com.strucdocs.component.document;

import com.itextpdf.text.DocumentException;
import com.strucdocs.domain.song.Song;

import java.io.OutputStream;

public interface DocumentComponent {

	void generateDocumentToOutputStream(OutputStream outputStream, Song song)
			throws DocumentException;
}
