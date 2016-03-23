package com.strucdocs.api.song;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class Lyric
    implements SongComponent, Serializable {

    private static final long serialVersionUID = -718877086550862881L;

    private Long id;

    @NotNull
    private String text;

    /**
     * Construct a Lyric with required text.
     *
     * @param text String representation of the Lyric
     */
    public Lyric(@NotNull String text) {
        this.text = text;
    }
}
