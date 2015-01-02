package org.musician.component.transposer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.musician.domain.chord.Chord;
import org.musician.domain.chord.ChordAddition;
import org.musician.domain.chord.ChordNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
public class TransposerComponentImplTest {

	@Autowired
	private TransposerComponent transposerComponent;

	@Test
	public void testTransposeChordPlus1() {
		Chord chord = Chord.builder().chordNote(ChordNote.A).build();
		chord.addChordAddition(ChordAddition.minor);

		Chord transposedChord = transposerComponent.transposeChord(chord, 1);

		assertEquals(ChordNote.A, transposedChord.getChordNote());
		assertEquals(ChordNote.ASharp, transposedChord.getTransposedChordNote());
		assertTrue(transposedChord.getChordAdditions().contains(ChordAddition.minor));
	}

	@Test
	public void testTransposeChordPlus2() {
		Chord chord = Chord.builder().chordNote(ChordNote.A).build();

		Chord transposedChord = transposerComponent.transposeChord(chord, 2);

		assertEquals(ChordNote.A, transposedChord.getChordNote());
		assertEquals(ChordNote.B, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordPlus5() {
		Chord chord = Chord.builder().chordNote(ChordNote.G).build();

		Chord transposedChord = transposerComponent.transposeChord(chord, 5);

		assertEquals(ChordNote.G, transposedChord.getChordNote());
		assertEquals(ChordNote.C, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus1() {
		Chord chord = Chord.builder().chordNote(ChordNote.D).build();

		Chord transposedChord = transposerComponent.transposeChord(chord, -1);

		assertEquals(ChordNote.D, transposedChord.getChordNote());
		assertEquals(ChordNote.DFlat, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus3() {
		Chord chord = Chord.builder().chordNote(ChordNote.D).build();

		Chord transposedChord = transposerComponent.transposeChord(chord, -3);

		assertEquals(ChordNote.D, transposedChord.getChordNote());
		assertEquals(ChordNote.B, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus5() {
		Chord chord = Chord.builder().chordNote(ChordNote.B).build();

		Chord transposedChord = transposerComponent.transposeChord(chord, -5);

		assertEquals(ChordNote.B, transposedChord.getChordNote());
		assertEquals(ChordNote.GFlat, transposedChord.getTransposedChordNote());
	}
}