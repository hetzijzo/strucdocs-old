package com.strucdocs.api.song;

import com.strucdocs.api.Transposable;
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
public class SongPart
    implements SongComponent, Transposable, Serializable {

    private static final long serialVersionUID = 3146634301775003439L;

    private Long id;

    @NotNull
    private SongPartType type;

    private final Set<SongLine> lines = new LinkedHashSet<>();

    public Set<SongLine> getLines() {
        return Collections.unmodifiableSet(lines);
    }

    public void addLine(SongLine line) {
        this.lines.add(line);
    }

    @Override
    public void transpose(Scale scale, int steps) {
        getLines().parallelStream().forEach(line -> line.transpose(scale, steps));
    }

    @Override
    public boolean isTransposed() {
        return getLines().stream().anyMatch(Transposable::isTransposed);
    }
}
