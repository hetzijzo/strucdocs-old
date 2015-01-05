package org.musician.domain.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.musician.domain.Artist;
import org.musician.domain.Relationship;
import org.musician.domain.Transposable;
import org.musician.domain.chord.Note;
import org.musician.domain.chord.Scale;
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
		implements Serializable, Transposable {

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
	public boolean isTransposed() {
		return getTransposedKey() != key;
	}

	@Override
	public void transpose(Scale scale, int steps) {
		key = getTransposedKey().transpose(scale, steps);

		getParts().parallelStream().forEach(
				part -> part.getLines().parallelStream().forEach(
						line -> line.getChord().transpose(scale, steps)
				)
		);
	}
}