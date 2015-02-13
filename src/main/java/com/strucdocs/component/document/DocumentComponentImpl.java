package com.strucdocs.component.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.strucdocs.domain.song.Song;
import com.strucdocs.domain.song.SongPart;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class DocumentComponentImpl
		implements DocumentComponent {

	private static final Chunk TAB = new Chunk(new VerticalPositionMark(), 200, true);

	private ObjectMapper objectMapper;

	@Override
	public void generateDocumentToOutputStream(OutputStream outputStream, Song song)
			throws DocumentException {
		PdfDocument document = new PdfDocument();
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.getTabs();
		document.open();

		String songTitle = song.getTitle();
		document.addCreationDate();
		document.addProducer();
		document.addTitle(songTitle);

		BarcodeQRCode qrcode = new BarcodeQRCode("http://localhost:8080/documents/songs/" + song.getId(), 50, 50, null);

		document.add(generateTitleParagraph(songTitle, song.getArtist().getName()));

		addEmptyLine(new Paragraph(), 2);

		for (SongPart part : song.getParts()) {
			document.add(new Paragraph(part.getType().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
			part.getLines().stream().forEach(l -> {
				try {
					document.add(new Paragraph(l.getChords().toArray().toString()));
					document.add(new Paragraph(l.getLyric().getText()));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			});
		}

		document.add(qrcode.getImage());
		document.newPage();

		document.close();
	}

	private Paragraph generateTitleParagraph(String title, String artistName) {
		Paragraph titleParagraph = new Paragraph(title, new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
		titleParagraph.add(new Chunk(" - "));
		titleParagraph.add(new Chunk(artistName));
		titleParagraph.add(new Chunk(TAB));
		return titleParagraph;
	}

	private Chapter generateSongPartChapter(SongPart part) {
//		Chapter chapter = new Chapter(new Paragraph(part.getType().toString()));
//		document.add(new Paragraph(part.getType().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));
//		part.getLines().stream().forEach(l -> {
//			try {
//				document.add(new Paragraph(l.getChord().toString()));
//				document.add(new Paragraph(l.getLyric().getText()));
//			} catch (DocumentException e) {
//				e.printStackTrace();
//			}
//		});
		return new Chapter(1);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
