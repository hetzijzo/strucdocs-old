package org.musician.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NodeEntity
@JsonAutoDetect
@Data
public class Genre
		implements Serializable {

	private static final long serialVersionUID = -3324026739108756673L;

	@GraphId
	private Long id;

	@NotNull
	private String genre;
}