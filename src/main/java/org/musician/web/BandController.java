package org.musician.web;

import org.musician.domain.Band;
import org.musician.domain.Instrument;
import org.musician.domain.Musician;
import org.musician.domain.Repertoire;
import org.musician.repository.BandRepository;
import org.musician.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bands")
public class BandController {

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private MusicianRepository musicianRepository;

	@RequestMapping
	@ResponseBody
	public Band getBand() {
		Band band = bandRepository.findByName("Make My Day");

		if (band == null) {
			band = new Band();
			band.setName("Make My Day");
			band = bandRepository.save(band);

			Musician willem = new Musician();
			willem.setName("Willem Cheizoo");
			willem.setUsername("willem");
			willem.playedIn(band, Instrument.PIANIST);
			willem = musicianRepository.save(willem);

			Musician merel = new Musician();
			merel.setName("Merel Bakker");
			merel.setUsername("merel");
			merel.playedIn(band, Instrument.VOCALIST);
			merel = musicianRepository.save(merel);
		}

		musicianRepository.findByName("Willem Cheizoo");

		System.out.println(band);
		return band;
	}
}
