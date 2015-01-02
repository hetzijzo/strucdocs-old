package org.musician.domain.chord;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;
import org.apache.commons.lang3.StringUtils;
import org.musician.converter.ChordDeserializer;
import org.musician.converter.ChordSerializer;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@NodeEntity(partial = true)
@JsonSerialize(using = ChordSerializer.class)
@JsonDeserialize(using = ChordDeserializer.class)
@Data
@Builder
@EqualsAndHashCode(exclude = {"transposedChordNote"})
public class Chord
		implements Serializable {

	public static final String GROUNDNOTE_SEPERATOR = "/";

	private static final long serialVersionUID = -5788157528932128761L;

	@GraphId
	private Long id;

	@NotNull
	@GraphProperty
	private ChordNote chordNote;

	@GraphProperty
	private ChordNote groundNote;

	@GraphProperty
	private final Set<ChordAddition> chordAdditions = new LinkedHashSet<>();

	/* Transient fields */
	private ChordNote transposedChordNote;
	private ChordNote transposedGroundNote;

	public Set<ChordAddition> getChordAdditions() {
		return Collections.unmodifiableSet(chordAdditions);
	}

	public void addChordAddition(ChordAddition chordAddition) {
		this.chordAdditions.add(chordAddition);
	}

	public ChordNote getTransposedChordNote() {
		return transposedChordNote == null ? chordNote : transposedChordNote;
	}

	public ChordNote getTransposedGroundNote() {
		return transposedGroundNote == null ? groundNote : transposedGroundNote;
	}

	public void transpose(int steps) {
		transposedChordNote = getTransposedChordNote().transpose(steps);

		if (getTransposedGroundNote() != null) {
			transposedGroundNote = getTransposedGroundNote().transpose(steps);
		}
	}

	public boolean isTransposed() {
		return getTransposedChordNote() != chordNote;
	}

	public static Chord fromString(String chordString) {
		ChordNote groundNote;
		if (StringUtils.contains(chordString, GROUNDNOTE_SEPERATOR)) {
			String groundNoteString = StringUtils.substringAfter(chordString, "/");
			groundNote = getHighestMatchingScore(ChordNote.class, groundNoteString);
			chordString = StringUtils.substringBefore(chordString, "/");
		} else {
			groundNote = null;
		}

		ChordNote chordNote = getHighestMatchingScore(ChordNote.class, chordString);

		Chord chord = Chord.builder()
				.chordNote(chordNote)
				.groundNote(groundNote)
				.build();

		String chordAdditionalString = StringUtils.substringAfter(chordString, chordNote.notation);
		while (!chordAdditionalString.isEmpty()) {
			ChordAddition addition = getHighestMatchingScore(ChordAddition.class, chordAdditionalString);
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
		sb.append(getTransposedChordNote().notation);
		getChordAdditions().forEach(addition -> sb.append(addition.notation));
		if (getTransposedGroundNote() != null) {
			sb.append(GROUNDNOTE_SEPERATOR);
			sb.append(getTransposedGroundNote());
		}
		return sb.toString();
	}
}