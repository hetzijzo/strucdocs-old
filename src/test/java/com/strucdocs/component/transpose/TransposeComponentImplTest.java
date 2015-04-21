package com.strucdocs.component.transpose;

import com.strucdocs.domain.chord.Chord;
import com.strucdocs.domain.chord.Interval;
import com.strucdocs.domain.chord.Note;
import com.strucdocs.domain.chord.Scale;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class TransposeComponentImplTest {

	private TransposeComponent transposeComponent = new TransposeComponentImpl();

	@Test
	public void testTransposeChordPlus1() {
		Chord chord = Chord.builder().note(Note.A).build();
		chord.addChordAddition(Interval.minor);

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Up, 1);

		assertEquals(Note.A, transposedChord.getNote());
		assertEquals(Note.ASharp, transposedChord.getTransposedNote());
		assertTrue(transposedChord.getAdditions().contains(Interval.minor));
	}

	@Test
	public void testTransposeChordPlus2() {
		Chord chord = Chord.builder().note(Note.A).build();

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Up, 2);

		assertEquals(Note.A, transposedChord.getNote());
		assertEquals(Note.B, transposedChord.getTransposedNote());
	}

	@Test
	public void testTransposeChordPlus5() {
		Chord chord = Chord.builder().note(Note.G).build();

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Up, 5);

		assertEquals(Note.G, transposedChord.getNote());
		assertEquals(Note.C, transposedChord.getTransposedNote());
	}

	@Test
	public void testTransposeChordMinus1() {
		Chord chord = Chord.builder().note(Note.D).build();

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Down, 1);

		assertEquals(Note.D, transposedChord.getNote());
		assertEquals(Note.DFlat, transposedChord.getTransposedNote());
	}

	@Test
	public void testTransposeChordMinus3() {
		Chord chord = Chord.builder().note(Note.D).build();

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Down, 3);

		assertEquals(Note.D, transposedChord.getNote());
		assertEquals(Note.B, transposedChord.getTransposedNote());
	}

	@Test
	public void testTransposeChordMinus5() {
		Chord chord = Chord.builder().note(Note.B).build();

		Chord transposedChord = transposeComponent.transposeChord(chord, Scale.Down, 5);

		assertEquals(Note.B, transposedChord.getNote());
		assertEquals(Note.GFlat, transposedChord.getTransposedNote());
	}
}