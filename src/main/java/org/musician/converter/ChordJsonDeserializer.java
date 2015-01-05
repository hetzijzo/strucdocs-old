package org.musician.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.musician.domain.chord.Chord;

import java.io.IOException;

public class ChordJsonDeserializer
		extends JsonDeserializer<Chord> {

	@Override
	public Chord deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		return Chord.fromString(jsonParser.getValueAsString());
	}
}
