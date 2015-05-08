package com.strucdocs;

import com.strucdocs.domain.Musician;
import com.strucdocs.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StrucDocsApplication {

    @Autowired
    public MusicianRepository musicianRepository;

    @PostConstruct
    public void createData() {
        String name = "willem";
        Musician musician = musicianRepository.findByUsername(name);
        if (musician == null) {
            musician = Musician.builder()
                    .name(name)
                    .username(name)
                    .build();
            musicianRepository.save(musician);
        }
    }

    public static void main(String[] args)
            throws Exception {
        SpringApplication.run(StrucDocsApplication.class, args);
    }
}