package com.strucdocs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.util.Assert;

import java.io.Serializable;

@RelationshipEntity(type = Relationship.PLAYS_IN)
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class MusicianRole
		implements Serializable {

	private static final long serialVersionUID = -8873328515578811028L;

	@GraphId
	private Long id;

	@StartNode
	private Musician musician;

	@EndNode
	@Fetch
	private Band band;

	private Instrument instrument;

	public MusicianRole(Musician musician, Band band, Instrument instrument) {
		Assert.noNullElements(new Object[]{musician, band, instrument});
		this.musician = musician;
		this.band = band;
		this.instrument = instrument;
	}
}
