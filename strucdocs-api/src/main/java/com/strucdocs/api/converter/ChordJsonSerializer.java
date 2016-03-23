package com.strucdocs.api.converter;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.strucdocs.api.chord.Chord;

import java.io.IOException;

public class ChordJsonSerializer
    extends JsonSerializer<Chord> {

    @Override
    public void serialize(Chord value, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException {
        jsonGenerator.writeString(value.toString());
    }
}
