package com.strucdocs.domain.song;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NodeEntity
@JsonAutoDetect
@Data
@NoArgsConstructor
public class Lyric
		implements SongComponent, Serializable {

	private static final long serialVersionUID = -718877086550862881L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexName = "lyricText", indexType = IndexType.FULLTEXT)
	private String text;

	/**
	 * Construct a Lyric with required text.
	 * @param text String representation of the Lyric
	 */
	public Lyric(@NotNull String text) {
		this.text = text;
	}
}