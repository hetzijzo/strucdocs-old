package com.strucdocs.api;

import com.strucdocs.api.song.Song;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonAutoDetect
@Data
public class RepertoireSong
    implements Serializable {

    private static final long serialVersionUID = -7107422818906955686L;

    private Long id;
    @NotNull
    private Repertoire repertoire;
    @NotNull
    private Song song;
}
