package org.musician.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.musician.domain.song.Song;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@NodeEntity
@JsonAutoDetect
@Data
@Builder
@ToString(exclude = {"songs"})
@EqualsAndHashCode(exclude = {"songs"})
public class Artist
		implements Serializable {

	private static final long serialVersionUID = -6833874651694635049L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "artistName", unique = true)
	private String name;

	@RelatedTo(type = Relationship.PERFORMED_BY, direction = Direction.INCOMING)
	@JsonIgnore
	private Set<Song> songs;
}