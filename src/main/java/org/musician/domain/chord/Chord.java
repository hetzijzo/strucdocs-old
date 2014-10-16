package org.musician.domain.chord;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@JsonAutoDetect
@Data
@ToString(exclude = {"transposedChordNote"})
@EqualsAndHashCode(exclude = {"transposedChordNote"})
public class Chord
		implements Serializable {

	private static final long serialVersionUID = -5788157528932128761L;

	@GraphId
	private Long id;

	@NotNull
	private ChordNote chordNote;

	@RelatedTo(type = "HAS_CHORDADDITION", direction = Direction.OUTGOING)
	private List<ChordAddition> chordAdditions = new ArrayList<ChordAddition>();

	private ChordNote transposedChordNote;

	public List<ChordAddition> getChordAdditions() {
		return Collections.unmodifiableList(chordAdditions);
	}

	public void addChordAddition(ChordAddition chordAddition) {
		this.chordAdditions.add(chordAddition);
	}

	public void transpose(int steps) {
		transposedChordNote = getChordNote().transpose(steps);
	}
}