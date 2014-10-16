package org.musician.domain.song;

import lombok.Data;
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
public class Lyric
		implements Serializable {

	private static final long serialVersionUID = -718877086550862881L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexName = "lyricText_Idx", indexType = IndexType.FULLTEXT)
	private String text;
}