package com.strucdocs.domain.song;

import com.strucdocs.domain.Transposable;
import com.strucdocs.domain.chord.Scale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongPart
		implements SongComponent, Transposable, Serializable {

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

	@Override
	public void transpose(Scale scale, int steps) {
		getLines().parallelStream().forEach(line -> line.transpose(scale, steps));
	}

	@Override
	public boolean isTransposed() {
		return getLines().stream().anyMatch(Transposable::isTransposed);
	}
}