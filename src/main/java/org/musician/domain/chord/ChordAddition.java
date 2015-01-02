package org.musician.domain.chord;

public enum ChordAddition {
	minor("m"),
	major("maj"),
	second("2"),
	fourth("4"),
	fifth("5"),
	sixth("6"),
	seventh("7"),
	ninth("9"),
	eleventh("11"),
	sus2("sus2"),
	sus4("sus4"),
	add2("sus2"),
	add4("add4"),
	dim5("b5"),
	aug5("#5");

	String notation;

	private ChordAddition(String notation) {
		this.notation = notation;
	}

	@Override
	public String toString() {
		return notation;
	}
}