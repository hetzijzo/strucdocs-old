package org.musician.domain.chord;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum ChordLadder {
	Up(ChordNote.A,
			ChordNote.ASharp, ChordNote.B, ChordNote.C, ChordNote.CSharp, ChordNote.D, ChordNote.DSharp,
			ChordNote.E, ChordNote.F, ChordNote.FSharp, ChordNote.G, ChordNote.GSharp),
	Down(ChordNote.AFlat, ChordNote.G, ChordNote.GFlat, ChordNote.F, ChordNote.E, ChordNote.EFlat, ChordNote.D, ChordNote.DFlat,
			ChordNote.C, ChordNote.B, ChordNote.BFlat, ChordNote.A);

	private final LinkedList<ChordNote> chordNotes;

	private ChordLadder(ChordNote... chordNotes) {
		this.chordNotes = new LinkedList<ChordNote>();
		this.chordNotes.addAll(Arrays.asList(chordNotes));
	}

	public List<ChordNote> getChordNotes() {
		return this.chordNotes;
	}

	public ChordNote transpose(ChordNote chordNote, int steps) {
		List<ChordNote> chordNoteList = getChordNotes();
		int currentPosition;
		for (currentPosition = 0; currentPosition < getChordNotes().size(); currentPosition++) {
			if (chordNoteList.get(currentPosition) == chordNote) {
				break;
			}
		}

		int newPosition = (currentPosition + steps) % 12;
		if (newPosition < 0) {
			newPosition += 12;
		}

		return getChordNotes().get(newPosition);
	}
}
