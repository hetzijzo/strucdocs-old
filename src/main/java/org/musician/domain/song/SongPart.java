package org.musician.domain.song;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


@NodeEntity
@JsonAutoDetect
@Data
public class SongPart
		implements Serializable {

	private static final long serialVersionUID = 3146634301775003439L;

	@GraphId
	private Long id;

	@NotNull
	private SongPartType type;

	@RelatedTo(type = "CONTAINS_LINE", direction = Direction.OUTGOING)
	@Fetch
	private final Set<SongLine> lines = new LinkedHashSet<>();

	public Set<SongLine> getLines() {
		return Collections.unmodifiableSet(lines);
	}

	public void addLine(SongLine line) {
		this.lines.add(line);
	}
}