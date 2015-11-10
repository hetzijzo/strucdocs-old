package com.strucdocs.domain;

import lombok.*;
import lombok.experimental.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "user"})
@ToString(exclude = {"roles"})
public class Musician
		implements Serializable {

	private static final long serialVersionUID = -8404328516678811028L;

	@GraphId
	private Long id;

	@NotNull
    @RelatedTo(type = Relationship.IS)
    private User user;

	@NotNull
	@Indexed(indexName = "musician.name", indexType = IndexType.FULLTEXT)
	private String name;

	@Email
	@NotNull
	private String email;

	private LocalDate birthDate;

	private String city;

	private String country;

	private Set<Instrument> instruments = new HashSet<>();

	@RelatedToVia(type = Relationship.PLAYS_IN)
	@Fetch
	private Set<MusicianRole> roles = new HashSet<>();

	public MusicianRole playsIn(final Band band, final Instrument instrument) {
		final MusicianRole role = new MusicianRole(this, band, instrument);
		if (roles == null) {
			roles = new HashSet<>();
		}
		roles.add(role);
		return role;
	}
}