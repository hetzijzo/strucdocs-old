package com.strucdocs.repository;

import com.strucdocs.StrucDocsApplication;
import com.strucdocs.domain.Artist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StrucDocsApplication.class)
@WebAppConfiguration("src/main/resources")
@IntegrationTest("server.port:0")
@DirtiesContext
public class ArtistRepositoryTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testFindAll()
            throws Exception {
        System.out.println("Running on port " + port);
        ResponseEntity<Artist> artist = new TestRestTemplate().getForEntity("http://localhost:" + port + "/artists/1", Artist.class);
        System.out.println("Got reply " + artist);

    }
}
