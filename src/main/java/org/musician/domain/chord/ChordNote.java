package org.musician.domain.chord;

public enum ChordNote {
	A("A"),
	ASharp("A#"),
	BFlat("Bb"),
	B("B"),
	C("C"),
	CSharp("C#"),
	DFlat("Db"),
	D("D"),
	DSharp("D#"),
	EFlat("Eb"),
	E("E"),
	F("F"),
	FSharp("F#"),
	GFlat("Gb"),
	G("G"),
	GSharp("G#"),
	AFlat("Ab");

	String notation;

	private ChordNote(String notation) {
		this.notation = notation;
	}

	public ChordNote transpose(int steps) {
		ChordLadder ladder;
		if (steps > 0) {
			ladder = ChordLadder.Up;
		} else {
			steps = steps * -1;
			ladder = ChordLadder.Down;
		}

		return ladder.transpose(this, steps);
	}

	@Override
	public String toString() {
		return notation;
	}
}