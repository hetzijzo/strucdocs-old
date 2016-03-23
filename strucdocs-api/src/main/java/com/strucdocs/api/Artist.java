package com.strucdocs.api;

import com.strucdocs.api.song.Song;
import lombok.*;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
@XmlRootElement
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"songs"})
@EqualsAndHashCode(exclude = {"songs"})
public class Artist
    implements Serializable {

    private static final long serialVersionUID = -6833874651694635049L;

    private Long id;

    private String name;

    @JsonIgnore
    @XmlTransient
    private final Set<Song> songs = new HashSet<>();
}
