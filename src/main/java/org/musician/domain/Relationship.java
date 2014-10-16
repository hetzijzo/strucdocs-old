package org.musician.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Relationship {

	public static final String CONTAINS_SONG = "CONTAINS_SONG";
	public static final String PLAYS_IN = "PLAYS_IN";
	public static final String PERFORMED_BY = "PERFORMED_BY";

}
