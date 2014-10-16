package org.musician.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.musician.domain.song.Song;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonAutoDetect
@Data
public class RepertoireSong
		implements Serializable {

	private static final long serialVersionUID = -7107422818906955686L;

	@GraphId
	private Long id;

	@NotNull
	private Repertoire repertoire;

	@NotNull
	private Song song;
}