package com.strucdocs;

import com.strucdocs.domain.*;
import com.strucdocs.domain.chord.Note;
import com.strucdocs.domain.song.Song;
import com.strucdocs.repository.ArtistRepository;
import com.strucdocs.repository.BandRepository;
import com.strucdocs.repository.MusicianRepository;
import com.strucdocs.repository.SongRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class StrucDocsApplication {

    @Autowired
    public MusicianRepository musicianRepository;

    @Autowired
    public BandRepository bandRepository;

    @Autowired
    public ArtistRepository artistRepository;

    @Autowired
    public SongRepository songRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createData() {
        String name = "willem";
        Musician musician = musicianRepository.findByName(name);
        if (musician == null) {

            Artist artist = Artist.builder()
                    .name("Anouk").build();

            Song song = Song.builder()
                    .title("Nobody's Wife")
                    .artist(artist)
                    .key(Note.EFlat)
                    .build();
            songRepository.save(song);

            Repertoire repetoire = new Repertoire();
            repetoire.setSongs(Collections.singleton(song));

            Band band = Band.builder()
                    .name("Make My Day").repertoire(repetoire)
                    .build();
            bandRepository.save(band);


            User user = new User();
            user.setUsername(name);
            user.setPassword(passwordEncoder.encode("password"));
            musician = Musician.builder()
                    .name(name)
                    .user(user)
                    .birthDate(new LocalDate(1983, 6, 2))
                    .city("Tilburg")
                    .country("Netherlands")
                    .email("w.cheizoo@gmail.com")
                    .build();
            musician.playsIn(band, Instrument.PIANIST);
            musicianRepository.save(musician);
        }
    }

    public static void main(String[] args)
            throws Exception {
        SpringApplication.run(StrucDocsApplication.class, args);
    }
}