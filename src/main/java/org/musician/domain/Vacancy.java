package org.musician.domain;

import lombok.Data;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.neo4j.annotation.GraphId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonAutoDetect
@Data
@Builder
public class Vacancy
		implements Serializable {

	private static final long serialVersionUID = 4296217062480831258L;

	@GraphId
	private Long id;

	@JsonIgnore
	private Musician musician;

	@NotNull
	private VacancyType type;
}