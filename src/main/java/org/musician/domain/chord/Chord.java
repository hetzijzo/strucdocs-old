package org.musician.domain.chord;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.apache.commons.lang3.StringUtils;
import org.musician.converter.ChordJsonDeserializer;
import org.musician.converter.ChordJsonSerializer;
import org.musician.domain.Transposable;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@NodeEntity(partial = true)
@JsonSerialize(using = ChordJsonSerializer.class)
@JsonDeserialize(using = ChordJsonDeserializer.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"transposedNote", "transposedGroundNote"})
public class Chord
		implements Serializable, Transposable {

	public static final String GROUNDNOTE_SEPERATOR = "/";

	private static final long serialVersionUID = -5788157528932128761L;

	@GraphId
	private Long id;

	@NotNull
	@GraphProperty
	private Note note;

	@GraphProperty
	private Note groundNote;

	@GraphProperty
	private final Set<Interval> additions = new LinkedHashSet<>();

	/* Transient fields */
	private Note transposedNote;
	private Note transposedGroundNote;

	public Set<Interval> getAdditions() {
		return Collections.unmodifiableSet(additions);
	}

	public void addChordAddition(Interval interval) {
		this.additions.add(interval);
	}

	public Note getTransposedNote() {
		return transposedNote == null ? note : transposedNote;
	}

	public Note getTransposedGroundNote() {
		return transposedGroundNote == null ? groundNote : transposedGroundNote;
	}

	@Override
	public void transpose(Scale scale, int steps) {
		transposedNote = getTransposedNote().transpose(scale, steps);

		if (getTransposedGroundNote() != null) {
			transposedGroundNote = getTransposedGroundNote().transpose(scale, steps);
		}
	}

	@Override
	public boolean isTransposed() {
		return getTransposedNote() != note;
	}

	public static Chord fromString(String chordString) {
		Note groundNote;
		if (StringUtils.contains(chordString, GROUNDNOTE_SEPERATOR)) {
			String groundNoteString = StringUtils.substringAfter(chordString, "/");
			groundNote = getHighestMatchingScore(Note.class, groundNoteString);
			chordString = StringUtils.substringBefore(chordString, "/");
		} else {
			groundNote = null;
		}

		Note note = getHighestMatchingScore(Note.class, chordString);

		Chord chord = Chord.builder()
				.note(note)
				.groundNote(groundNote)
				.build();

		String chordAdditionalString = StringUtils.substringAfter(chordString, note.notation);
		while (!chordAdditionalString.isEmpty()) {
			Interval addition = getHighestMatchingScore(Interval.class, chordAdditionalString);
			chord.addChordAddition(addition);
			chordAdditionalString = StringUtils.substringAfter(chordAdditionalString, addition.notation);
		}

		return chord;
	}

	private static <T extends Enum> T getHighestMatchingScore(Class<T> itemsClass, final String stringValue) {
		return getMatchingScores(itemsClass, stringValue).entrySet()
				.stream()
				.filter(e -> stringValue.indexOf(e.getKey().toString()) == 0)
				.max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
				.get()
				.getKey();
	}

	private static <T extends Enum> Map<T, Double> getMatchingScores(Class<T> itemsClass, final String stringValue) {
		return Arrays.asList(itemsClass.getEnumConstants())
				.stream()
				.parallel()
				.filter(item -> stringValue.contains(item.toString()))
				.sequential()
				.collect(Collectors.toMap(
						item -> item,
						item -> StringUtils.getJaroWinklerDistance(item.toString(), stringValue)
				));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTransposedNote().notation);
		getAdditions().forEach(addition -> sb.append(addition.notation));
		if (getTransposedGroundNote() != null) {
			sb.append(GROUNDNOTE_SEPERATOR);
			sb.append(getTransposedGroundNote());
		}
		return sb.toString();
	}
}