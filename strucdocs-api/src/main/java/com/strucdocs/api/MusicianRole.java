package com.strucdocs.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class MusicianRole
    implements Serializable {

    private static final long serialVersionUID = -8873328515578811028L;

    private Long id;
    private Musician musician;
    private Band band;
    private Instrument instrument;

    public MusicianRole(Musician musician, Band band, Instrument instrument) {
        this.musician = musician;
        this.band = band;
        this.instrument = instrument;
    }
}
