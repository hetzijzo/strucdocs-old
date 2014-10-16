package org.musician.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

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

//	@RelatedToVia(type = CONTAINS_SONG, direction = INCOMING)
//	@Fetch
//	private Iterable<Song> songs;
}