package org.musician.domain.song;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.Transposable;
import org.musician.domain.chord.Chord;
import org.musician.domain.chord.Scale;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongLine
		implements Transposable, Serializable {

	private static final long serialVersionUID = 379697678705188851L;

	@GraphId
	private Long id;

	@NotNull
	@RelatedTo(type = "CONTAINS_CHORD", direction = Direction.OUTGOING)
	@Fetch
	@JsonUnwrapped
	private final Set<Chord> chords = new LinkedHashSet<>();

	@RelatedTo(type = "CONTAINS_LYRIC", direction = Direction.OUTGOING)
	@Fetch
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
