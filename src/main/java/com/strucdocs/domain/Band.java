package com.strucdocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"repertoire"})
public class Band
		implements Serializable {

	private static final long serialVersionUID = -691641219438850748L;

	@GraphId
	public Long id;

	@NotNull
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "bandName")
	public String name;

	@RelatedTo(type = Relationship.PLAYS_IN, direction = Direction.INCOMING)
	public Set<Musician> musicians;

//	@RelatedToVia(type = Relationship.PLAYS_IN, direction = Direction.INCOMING)
//	private Iterable<MusicianRole> musicianRoles;

	@RelatedTo(type = Relationship.PLAYS_GENRE, direction = Direction.INCOMING)
	public Set<Genre> genres;

	@RelatedTo(type = "PLAYS_REPERTOIRE", direction = Direction.OUTGOING)
	@JsonIgnore
	private Repertoire repertoire;
}