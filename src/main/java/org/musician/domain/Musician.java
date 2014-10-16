package org.musician.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
@JsonAutoDetect
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"roles"})
public class Musician
		implements Serializable {

	private static final long serialVersionUID = -8404328516678811028L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexName = "username", indexType = IndexType.FULLTEXT, unique = true)
	private String username;

	@NotNull
	private String name;

	@Email
	private String email;

	private LocalDate birthDate;

	private String city;

	private String country;

	private Set<Instrument> instruments;

	@RelatedToVia(type = Relationship.PLAYS_IN)
	@Fetch
	private Set<MusicianRole> roles = new HashSet<>();

	public MusicianRole playedIn(Band band, Instrument instrument) {
		final MusicianRole role = new MusicianRole(this, band, instrument);
		roles.add(role);
		return role;
	}
}