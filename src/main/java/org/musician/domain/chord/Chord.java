package org.musician.domain.chord;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
@JsonAutoDetect
@Data
@EqualsAndHashCode(exclude = {"transposedChordNote"})
public class Chord
		implements Serializable {

	private static final long serialVersionUID = -5788157528932128761L;

	@GraphId
	private Long id;

	@NotNull
	private ChordNote chordNote;

	private Set<ChordAddition> chordAdditions = new HashSet<>();

	private ChordNote transposedChordNote;

	public Set<ChordAddition> getChordAdditions() {
		return Collections.unmodifiableSet(chordAdditions);
	}

	public void addChordAddition(ChordAddition chordAddition) {
		this.chordAdditions.add(chordAddition);
	}

	public void transpose(int steps) {
		transposedChordNote = getChordNote().transpose(steps);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(chordNote.getName());
		chordAdditions.forEach(addition -> sb.append(addition.getName()));
		return sb.toString();
	}
}