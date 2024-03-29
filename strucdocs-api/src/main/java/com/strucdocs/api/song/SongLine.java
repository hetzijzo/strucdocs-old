package com.strucdocs.api.song;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.strucdocs.api.Transposable;
import com.strucdocs.api.chord.Chord;
import com.strucdocs.api.chord.Scale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongLine
    implements SongComponent, Transposable, Serializable {

    private static final long serialVersionUID = 379697678705188851L;

    private Long id;

    @NotNull
    @JsonUnwrapped
    private final Set<Chord> chords = new LinkedHashSet<>();

    @JsonUnwrapped
    private Lyric lyric;

    public Set<Chord> getChords() {
        return Collections.unmodifiableSet(chords);
    }

    public void addChord(Chord chord) {
        chords.add(chord);
    }

    @Override
    public void transpose(Scale scale, int steps) {
        getChords().parallelStream().forEach(chord -> chord.transpose(scale, steps));
    }

    @Override
    public boolean isTransposed() {
        return getChords().stream().anyMatch(Transposable::isTransposed);
    }

    public static SongLine fromString(String stringValue)
        throws IOException {

        LineNumberReader lnReader = new LineNumberReader(new StringReader(stringValue));

        String[] chordStrings = StringUtils.split(lnReader.readLine(), ' ');
        String lyricsLine = lnReader.readLine();

        SongLine songLine = SongLine.builder()
            .lyric(new Lyric(lyricsLine))
            .build();
        Arrays.asList(chordStrings)
            .forEach(chordString -> songLine.addChord(Chord.fromString(chordString)));

        return songLine;
    }
}
