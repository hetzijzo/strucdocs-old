package org.musician.component.transposer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.musician.domain.chord.Chord;
import org.musician.domain.chord.ChordAddition;
import org.musician.domain.chord.ChordNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransposerComponentConfiguration.class)
public class TransposerComponentImplTest {

	@Autowired
	private TransposerComponent transposerComponent;

	@Test
	public void testTransposeChordPlus1() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.A);
		chord.addChordAddition(ChordAddition.minor);

		Chord transposedChord = transposerComponent.transposeChord(chord, 1);

		assertEquals(ChordNote.A, transposedChord.getChordNote());
		assertEquals(ChordNote.ASharp, transposedChord.getTransposedChordNote());
		assertTrue(transposedChord.getChordAdditions().contains(ChordAddition.minor));
	}

	@Test
	public void testTransposeChordPlus2() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.A);

		Chord transposedChord = transposerComponent.transposeChord(chord, 2);

		assertEquals(ChordNote.A, transposedChord.getChordNote());
		assertEquals(ChordNote.B, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordPlus5() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.G);

		Chord transposedChord = transposerComponent.transposeChord(chord, 5);

		assertEquals(ChordNote.G, transposedChord.getChordNote());
		assertEquals(ChordNote.C, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus1() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.D);

		Chord transposedChord = transposerComponent.transposeChord(chord, -1);

		assertEquals(ChordNote.D, transposedChord.getChordNote());
		assertEquals(ChordNote.DFlat, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus3() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.D);

		Chord transposedChord = transposerComponent.transposeChord(chord, -3);

		assertEquals(ChordNote.D, transposedChord.getChordNote());
		assertEquals(ChordNote.B, transposedChord.getTransposedChordNote());
	}

	@Test
	public void testTransposeChordMinus5() {
		Chord chord = new Chord();
		chord.setChordNote(ChordNote.B);

		Chord transposedChord = transposerComponent.transposeChord(chord, -5);

		assertEquals(ChordNote.B, transposedChord.getChordNote());
		assertEquals(ChordNote.GFlat, transposedChord.getTransposedChordNote());
	}
}