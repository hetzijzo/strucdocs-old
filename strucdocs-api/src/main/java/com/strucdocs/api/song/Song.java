package com.strucdocs.api.song;

import com.strucdocs.api.Artist;
import com.strucdocs.api.Transposable;
import com.strucdocs.api.chord.Note;
import com.strucdocs.api.chord.Scale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song
    implements SongComponent, Transposable, Serializable {

    private static final long serialVersionUID = -5894730035637748152L;

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Artist artist;

    private Note key;

    private final Set<SongPart> parts = new LinkedHashSet<>();

    /* Transient fields */
    private transient Note transposedKey;

    /**
     * Get all SongParts of this Song.
     *
     * @return
     */
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
