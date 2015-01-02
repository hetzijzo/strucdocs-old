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

	private static final long serialVersionUID = -5788157528932128761L;

	@GraphId
	private Long id;

	@NotNull
	@GraphProperty
	private ChordNote chordNote;

	@GraphProperty
	private Set<ChordAddition> chordAdditions = new LinkedHashSet<>();

	/* Transient fields */
	private ChordNote transposedChordNote;

	public Set<ChordAddition> getChordAdditions() {
		return Collections.unmodifiableSet(chordAdditions);
	}

	public void addChordAddition(ChordAddition chordAddition) {
		this.chordAdditions.add(chordAddition);
	}

	public ChordNote getTransposedChordNote() {
		return transposedChordNote == null ? chordNote : transposedChordNote;
	}

	public void transpose(int steps) {
		transposedChordNote = getTransposedChordNote().transpose(steps);
	}

	public boolean isTransposed() {
		return getTransposedChordNote() != chordNote;
	}

	public static Chord fromStringValue(final String notationValue) {
		ChordNote note = getHighestMatchingScore(ChordNote.class, notationValue);

		Chord chord = Chord.builder().chordNote(note).build();

		String additionsValue = notationValue.substring(notationValue.indexOf(note.notation) + note.notation.length());

		while (!additionsValue.isEmpty()) {
			ChordAddition addition = getHighestMatchingScore(ChordAddition.class, additionsValue);
			chord.addChordAddition(addition);
			additionsValue = additionsValue.substring(additionsValue.indexOf(addition.notation) + addition.notation.length());
		}

		return chord;
	}

	private static <T extends Enum> T getHighestMatchingScore(Class<T> itemsClass, String stringValue) {
		return getMatchingScores(itemsClass, stringValue).entrySet()
				.stream()
				.filter(e -> stringValue.indexOf(e.getKey().toString()) == 0)
				.max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
				.get()
				.getKey();
	}

	private static <T extends Enum> Map<T, Double> getMatchingScores(Class<T> itemsClass, String stringValue) {
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
		return sb.toString();
	}
}