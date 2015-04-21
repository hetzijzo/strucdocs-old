package com.strucdocs.domain.song;

import com.strucdocs.domain.Artist;
import com.strucdocs.domain.Relationship;
import com.strucdocs.domain.Transposable;
import com.strucdocs.domain.chord.Note;
import com.strucdocs.domain.chord.Scale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity(partial = true)
@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song
		implements SongComponent, Transposable, Serializable {

	private static final long serialVersionUID = -5894730035637748152L;

	@GraphId
	private Long id;

	@NotNull
	@GraphProperty
	@Indexed(indexName = "songTitle_Idx", indexType = IndexType.FULLTEXT)
	@Fetch
	private String title;

	@NotNull
	@GraphProperty
	@RelatedTo(type = Relationship.PERFORMED_BY)
	@Fetch
	private Artist artist;

	@GraphProperty
	private Note key;

	@GraphProperty
	@RelatedTo(type = "CONTAINS_PART")
	@Fetch
	private final Set<SongPart> parts = new LinkedHashSet<>();

	/* Transient fields */
	private Note transposedKey;

	public Set<SongPart> getParts() {
		return Collections.unmodifiableSet(parts);
	}

	public void addPart(SongPart part) {
		parts.add(part);
	}

	public Note getTransposedKey() {
		return transposedKey == null ? key : transposedKey;
	}

	@Override
	public void transpose(Scale scale, int steps) {
		key = getTransposedKey().transpose(scale, steps);

		getParts().parallelStream().forEach(part -> part.transpose(scale, steps));
	}

	@Override
	public boolean isTransposed() {
		return getTransposedKey() != key;
	}
}