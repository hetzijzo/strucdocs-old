package org.musician.domain.song;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.Artist;
import org.musician.domain.Relationship;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
	private String title;

	@RelatedTo(type = Relationship.PERFORMED_BY)
	@Fetch
	@NotNull
	private Artist artist;

	@RelatedTo(type = "CONTAINS_PART")
	@Fetch
	private List<SongPart> songParts;

	public void addSongPart(SongPart songPart) {
		if (songParts == null) {
			songParts = new LinkedList<SongPart>();
		}
		songParts.add(songPart);
	}
}