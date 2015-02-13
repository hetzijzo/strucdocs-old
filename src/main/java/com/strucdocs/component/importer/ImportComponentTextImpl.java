package com.strucdocs.component.importer;

import com.strucdocs.domain.Artist;
import com.strucdocs.domain.song.Song;
import com.strucdocs.domain.song.SongLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportComponentTextImpl
        implements ImportComponent {

    @Override
    public Song importSong(String songUrl)
            throws IOException {
        InputStream inputStream = ImportComponentTextImpl.class.getResourceAsStream("/" + songUrl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        Song song = new Song();
        bufferedReader.lines().forEach(line -> detectTypeOfLine(song, line));

        return song;
    }

    private void detectTypeOfLine(Song song, String line) {
        Pattern titlePattern = Pattern.compile("^Title:\\s*(\\w*)\\s*$");
        Matcher titleMatcher = titlePattern.matcher(line);
        if (titleMatcher.matches()) {
            MatchResult result = titleMatcher.toMatchResult();
            song.setTitle(result.group(1));
        }

        Pattern artistPattern = Pattern.compile("^Artist:\\s*(\\w*)\\s*$");
        Matcher artistMatcher = artistPattern.matcher(line);
        if (artistMatcher.matches()) {
            MatchResult result = artistMatcher.toMatchResult();
            Artist artist = new Artist();
            artist.setName(result.group(1));
            song.setArtist(artist);
        }


        Pattern partPattern = Pattern.compile("^.*Intro|Verse|Chorus|Solo|Outro\\s*(.*):*\\s*$");
        Predicate<String> partPredicate = partPattern.asPredicate();
        if (partPredicate.test(line)) {
        }
    }

    @Override
    public SongLine createSongLine(String songLine)
            throws IOException {
        return null;
    }
}
