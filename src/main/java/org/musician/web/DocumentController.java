package org.musician.web;

import com.itextpdf.text.DocumentException;
import org.musician.component.document.DocumentComponent;
import org.musician.domain.song.Song;
import org.musician.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private DocumentComponent documentComponent;

	@RequestMapping(value = "/songs/{songId}", method = RequestMethod.GET)
	public ResponseEntity<Void> getDocument(HttpServletResponse servletResponse, @PathVariable("songId") Long songId)
			throws DocumentException, IOException {
		Song song = songRepository.findById(songId);
		if (song == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		documentComponent.generateDocumentToOutputStream(servletResponse.getOutputStream(), song);

		servletResponse.setContentType("application/pdf");
		servletResponse.setHeader("Content-Disposition", String.format("inline; filename=\"%s.pdf\"", song.getTitle()));

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
