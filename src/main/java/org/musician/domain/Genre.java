package org.musician.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NodeEntity
@JsonAutoDetect
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Genre
		implements Serializable {

	private static final long serialVersionUID = -3324026739108756673L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexType = IndexType.LABEL)
	private String genre;
}