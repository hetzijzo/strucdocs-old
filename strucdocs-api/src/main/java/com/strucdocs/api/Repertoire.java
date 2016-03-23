package com.strucdocs.api;

import com.strucdocs.api.song.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Repertoire
    implements Serializable {

    private static final long serialVersionUID = -1212011622909947576L;

    private Long id;

    @NotNull
    private String name;
    private Iterable<Song> songs;
}
