package com.strucdocs.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Interface which defines all Neo4J relations.
 */
public interface Relationship {

	String IS = "IS";
	String CONTAINS_SONG = "CONTAINS_SONG";
	String PLAYS_IN = "PLAYS_IN";
	String PERFORMED_BY = "PERFORMED_BY";
	String PLAYS_GENRE = "PLAYS_GENRE";

}
