package org.musician.domain.chord;

public enum ChordAddition {
	minor("m"),
	major("maj"),
	two("2"),
	four("4"),
	septime("7"),
	sus("sus");

	private String name;

	private ChordAddition(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}