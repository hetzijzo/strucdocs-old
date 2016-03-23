package com.strucdocs.api;

import lombok.*;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A (cover) Band with members ({@link Musician Musicians}) and a {@link Repertoire}.
 */
@JsonAutoDetect
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"repertoire"})
public class Band
    implements Serializable {

    private static final long serialVersionUID = -691641219438850748L;

    public Long id;

    public String name;

    public Set<Musician> musicians = new HashSet<>();

    public Set<Genre> genres = new HashSet<>();

    private Repertoire repertoire;
}
