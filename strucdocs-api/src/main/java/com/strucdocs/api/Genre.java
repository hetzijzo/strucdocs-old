package com.strucdocs.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.io.Serializable;

/**
 * A music genre representation.
 */
@JsonAutoDetect
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Genre
    implements Serializable {

    private static final long serialVersionUID = -3324026739108756673L;

    private Long id;

    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }
}
