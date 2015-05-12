package com.strucdocs;

import com.strucdocs.domain.Musician;
import com.strucdocs.domain.User;
import com.strucdocs.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StrucDocsApplication {

    @Autowired
    public MusicianRepository musicianRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createData() {
        String name = "willem";
        Musician musician = musicianRepository.findByName(name);
        if (musician == null) {
            User user = new User();
            user.setUsername(name);
            user.setPassword(passwordEncoder.encode("password"));

            musician = Musician.builder()
                    .name(name)
                    .user(user)
                    .build();
            musicianRepository.save(musician);
        }
    }

    public static void main(String[] args)
            throws Exception {
        SpringApplication.run(StrucDocsApplication.class, args);
    }
}