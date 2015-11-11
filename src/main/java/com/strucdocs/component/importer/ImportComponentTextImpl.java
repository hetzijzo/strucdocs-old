package com.strucdocs.component.importer;

import com.strucdocs.domain.Artist;
import com.strucdocs.domain.chord.Chord;
import com.strucdocs.domain.song.Lyric;
import com.strucdocs.domain.song.Song;
import com.strucdocs.domain.song.SongComponent;
import com.strucdocs.domain.song.SongLine;
import com.strucdocs.domain.song.SongPart;
import com.strucdocs.domain.song.SongPartType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Import;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportComponentTextImpl
        implements ImportComponent {

    @Data
    @RequiredArgsConstructor
    private class ImportSongBuilder {

        private final Song song;
        private SongPart currentPart;
        private SongLine currentSongLine;
    }

    @Override
    public Song importSong(String songUrl)
            throws IOException {
        InputStream inputStream = ImportComponentTextImpl.class.getResourceAsStream("/" + songUrl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        ImportSongBuilder importSongBuilder = new ImportSongBuilder(new Song());

        bufferedReader.lines().forEach(line -> detectTypeOfLine(importSongBuilder, line));

        return importSongBuilder.getSong();
    }

    private void detectTypeOfLine(ImportSongBuilder importSongBuilder, String line) {

        if (StringUtils.isBlank(line)) {
            return;
        }

        Song song = importSongBuilder.getSong();

        Pattern titlePattern = Pattern.compile("^Title:\\s*(\\w*)\\s*$");
        Matcher titleMatcher = titlePattern.matcher(line);
        if (titleMatcher.matches()) {
            MatchResult result = titleMatcher.toMatchResult();
            song.setTitle(result.group(1));
            return;
        }

        Pattern artistPattern = Pattern.compile("^Artist:\\s*(\\w*)\\s*$");
        Matcher artistMatcher = artistPattern.matcher(line);
        if (artistMatcher.matches()) {
            MatchResult result = artistMatcher.toMatchResult();
            Artist artist = new Artist();
            artist.setName(result.group(1));
            song.setArtist(artist);
            return;
        }


        Pattern partPattern = Pattern.compile("^.*(Intro|Verse|Chorus|Solo|Outro)\\s*(.*):*\\s*$");
        Predicate<String> partPredicate = partPattern.asPredicate();
        if (partPredicate.test(line)) {
            SongPart songPart = SongPart.builder().build();
            System.out.println(line);
            songPart.setType(SongPartType.INTRO);
            song.addPart(songPart);
            importSongBuilder.setCurrentPart(songPart);
            importSongBuilder.setCurrentSongLine(null);
            return;
        }

        if (importSongBuilder.getCurrentSongLine() == null) {
            System.out.println("New SongLine");
            SongLine songLine = new SongLine();
            String[] split = StringUtils.split(line, ' ');
            Arrays.asList(split).forEach(chordString -> {
                Chord chord = Chord.fromString(chordString);
                if (chord != null)
                {
                    System.out.println("add chord");
                    songLine.addChord(chord);
                }
            });

            System.out.println(songLine);
            importSongBuilder.getCurrentPart().addLine(songLine);
            importSongBuilder.setCurrentSongLine(songLine);
        } else {
            SongLine songLine = importSongBuilder.getCurrentSongLine();
            songLine.setLyric(new Lyric(line));

            System.out.println(songLine);
            importSongBuilder.setCurrentSongLine(null);
        }

    }

    @Override
    public SongLine createSongLine(String songLine)
            throws IOException {
        return null;
    }
}
