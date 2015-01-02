package org.musician.domain.song;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.chord.Chord;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NodeEntity
@JsonAutoDetect
@Data
public class SongLine
		implements Serializable {

	private static final long serialVersionUID = 379697678705188851L;

	@GraphId
	private Long id;

	@NotNull
	@RelatedTo(type = "CONTAINS_CHORD", direction = Direction.OUTGOING)
	@Fetch
	@JsonUnwrapped
	private Chord chord;

	@RelatedTo(type = "CONTAINS_LYRIC", direction = Direction.OUTGOING)
	@Fetch
	@JsonUnwrapped
	private Lyric lyric;
}
