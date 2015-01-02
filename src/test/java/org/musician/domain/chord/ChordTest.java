package org.musician.domain.chord;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ChordTest {

	@Test
	public void testCSharpMaj7()
			throws Exception {
		Chord chord = Chord.fromString("C#maj7");
		assertThat(chord, notNullValue());
		assertThat(chord.getChordNote(), is(ChordNote.CSharp));

		Set<ChordAddition> chordAdditions = chord.getChordAdditions();
		assertThat(chordAdditions.size(), is(2));
		Iterator<ChordAddition> chordAdditionIterator = chordAdditions.iterator();
		assertThat(chordAdditionIterator.next(), is(ChordAddition.major));
		assertThat(chordAdditionIterator.next(), is(ChordAddition.seventh));
	}

	@Test
	public void testBFlatSus4()
			throws Exception {
		Chord chord = Chord.fromString("Bbsus4");
		assertThat(chord, notNullValue());
		assertThat(chord.getChordNote(), is(ChordNote.BFlat));

		Set<ChordAddition> chordAdditions = chord.getChordAdditions();
		assertThat(chordAdditions.size(), is(1));
		Iterator<ChordAddition> chordAdditionIterator = chordAdditions.iterator();
		assertThat(chordAdditionIterator.next(), is(ChordAddition.sus4));
	}

	@Test
	public void testAminor()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		assertThat(chord, notNullValue());
		assertThat(chord.getChordNote(), is(ChordNote.A));

		Set<ChordAddition> chordAdditions = chord.getChordAdditions();
		assertThat(chordAdditions.size(), is(1));
		Iterator<ChordAddition> chordAdditionIterator = chordAdditions.iterator();
		assertThat(chordAdditionIterator.next(), is(ChordAddition.minor));
	}

	@Test
	public void testAminorMajor7()
			throws Exception {
		Chord chord = Chord.fromString("Ammaj7");
		assertThat(chord, notNullValue());
		assertThat(chord.getChordNote(), is(ChordNote.A));

		Set<ChordAddition> chordAdditions = chord.getChordAdditions();
		assertThat(chordAdditions.size(), is(3));
		Iterator<ChordAddition> chordAdditionIterator = chordAdditions.iterator();
		assertThat(chordAdditionIterator.next(), is(ChordAddition.minor));
		assertThat(chordAdditionIterator.next(), is(ChordAddition.major));
		assertThat(chordAdditionIterator.next(), is(ChordAddition.seventh));
	}

	@Test
	public void testDminorMajor7b5()
			throws Exception {
		Chord chord = Chord.fromString("Dm7b5");
		assertThat(chord, notNullValue());
		assertThat(chord.getChordNote(), is(ChordNote.D));

		Set<ChordAddition> chordAdditions = chord.getChordAdditions();
		assertThat(chordAdditions.size(), is(3));
		Iterator<ChordAddition> chordAdditionIterator = chordAdditions.iterator();
		assertThat(chordAdditionIterator.next(), is(ChordAddition.minor));
		assertThat(chordAdditionIterator.next(), is(ChordAddition.seventh));
		assertThat(chordAdditionIterator.next(), is(ChordAddition.dim5));
	}

	@Test
	public void testTransposeAminor1()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(1);

		assertThat(chord.getTransposedChordNote(), is(ChordNote.ASharp));
		assertThat(chord.isTransposed(), is(true));
	}

	@Test
	public void testTransposeAminorMin4()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(-4);

		ChordNote transposedChord = chord.getTransposedChordNote();
		assertThat(transposedChord, is(ChordNote.F));
	}

	@Test
	public void testTranspose_UndoTranspose()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(2);
		chord.transpose(-2);

		assertThat(chord.getTransposedChordNote(), is(ChordNote.A));
		assertThat(chord.isTransposed(), is(false));
	}

	@Test
	public void testTranspose_UndoTransposeMultipleSteps()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(2);
		chord.transpose(-1);
		chord.transpose(-1);

		assertThat(chord.getTransposedChordNote(), is(ChordNote.A));
		assertThat(chord.isTransposed(), is(false));
	}

	@Test
	public void testEmGroundNoteG()
			throws Exception {
		Chord chord = Chord.fromString("Em/G");

		assertThat(chord.getTransposedChordNote(), is(ChordNote.E));
		assertThat(chord.getGroundNote(), is(ChordNote.G));
		assertThat(chord.isTransposed(), is(false));

		assertThat(chord.toString(), equalTo("Em/G"));
	}
}