package org.musician.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.song.Song;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.io.Serializable;

@NodeEntity
@JsonAutoDetect
@Data
public class Repertoire
		implements Serializable {

	private static final long serialVersionUID = -1212011622909947576L;

	@GraphId
	private Long id;

	private String name;

	@RelatedTo(type = Relationship.CONTAINS_SONG, direction = Direction.INCOMING)
	@Fetch
	private Iterable<Song> songs;
}