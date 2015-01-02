package org.musician.domain.song;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.Artist;
import org.musician.domain.Relationship;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
@JsonAutoDetect
@Data
public class Song
		implements Serializable {

	private static final long serialVersionUID = -5894730035637748152L;

	@GraphId
	private Long id;

	@NotNull
	@Indexed(indexName = "songTitle_Idx", indexType = IndexType.FULLTEXT)
	@Fetch
	private String title;

	@RelatedTo(type = Relationship.PERFORMED_BY)
	@Fetch
	@NotNull
	private Artist artist;

	@RelatedTo(type = "CONTAINS_PART")
	@Fetch
	private Set<SongPart> songParts = new LinkedHashSet<>();

	public Set<SongPart> getSongParts() {
		return Collections.unmodifiableSet(songParts);
	}

	public void addSongPart(SongPart songPart) {
		songParts.add(songPart);
	}
}