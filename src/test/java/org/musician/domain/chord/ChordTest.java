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
		assertThat(chord.getNote(), is(Note.CSharp));

		Set<Interval> intervals = chord.getAdditions();
		assertThat(intervals.size(), is(2));
		Iterator<Interval> chordAdditionIterator = intervals.iterator();
		assertThat(chordAdditionIterator.next(), is(Interval.major));
		assertThat(chordAdditionIterator.next(), is(Interval.seventh));
	}

	@Test
	public void testBFlatSus4()
			throws Exception {
		Chord chord = Chord.fromString("Bbsus4");
		assertThat(chord, notNullValue());
		assertThat(chord.getNote(), is(Note.BFlat));

		Set<Interval> intervals = chord.getAdditions();
		assertThat(intervals.size(), is(1));
		Iterator<Interval> chordAdditionIterator = intervals.iterator();
		assertThat(chordAdditionIterator.next(), is(Interval.sus4));
	}

	@Test
	public void testAminor()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		assertThat(chord, notNullValue());
		assertThat(chord.getNote(), is(Note.A));

		Set<Interval> intervals = chord.getAdditions();
		assertThat(intervals.size(), is(1));
		Iterator<Interval> chordAdditionIterator = intervals.iterator();
		assertThat(chordAdditionIterator.next(), is(Interval.minor));
	}

	@Test
	public void testAminorMajor7()
			throws Exception {
		Chord chord = Chord.fromString("Ammaj7");
		assertThat(chord, notNullValue());
		assertThat(chord.getNote(), is(Note.A));

		Set<Interval> intervals = chord.getAdditions();
		assertThat(intervals.size(), is(3));
		Iterator<Interval> chordAdditionIterator = intervals.iterator();
		assertThat(chordAdditionIterator.next(), is(Interval.minor));
		assertThat(chordAdditionIterator.next(), is(Interval.major));
		assertThat(chordAdditionIterator.next(), is(Interval.seventh));
	}

	@Test
	public void testDminorMajor7b5()
			throws Exception {
		Chord chord = Chord.fromString("Dm7b5/Bb");
		assertThat(chord, notNullValue());
		assertThat(chord.getNote(), is(Note.D));
		assertThat(chord.getGroundNote(), is(Note.BFlat));

		Set<Interval> intervals = chord.getAdditions();
		assertThat(intervals.size(), is(3));
		Iterator<Interval> chordAdditionIterator = intervals.iterator();
		assertThat(chordAdditionIterator.next(), is(Interval.minor));
		assertThat(chordAdditionIterator.next(), is(Interval.seventh));
		assertThat(chordAdditionIterator.next(), is(Interval.dim5));
	}

	@Test
	public void testTransposeAminor1()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(Scale.Up, 1);

		assertThat(chord.getTransposedNote(), is(Note.ASharp));
		assertThat(chord.isTransposed(), is(true));
	}

	@Test
	public void testTransposeAminorMin4()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(Scale.Down, 4);

		Note transposedChord = chord.getTransposedNote();
		assertThat(transposedChord, is(Note.F));
	}

	@Test
	public void testTranspose_UndoTranspose()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(Scale.getScale(2), 2);
		chord.transpose(Scale.getScale(-2), 2);

		assertThat(chord.getTransposedNote(), is(Note.A));
		assertThat(chord.isTransposed(), is(false));
	}

	@Test
	public void testTranspose_UndoTransposeMultipleSteps()
			throws Exception {
		Chord chord = Chord.fromString("Am");
		chord.transpose(Scale.Up, 2);
		chord.transpose(Scale.Down, 1);
		chord.transpose(Scale.Down, 1);

		assertThat(chord.getTransposedNote(), is(Note.A));
		assertThat(chord.isTransposed(), is(false));
	}

	@Test
	public void testEmGroundNoteG()
			throws Exception {
		Chord chord = Chord.fromString("Em/G");

		assertThat(chord.getTransposedNote(), is(Note.E));
		assertThat(chord.getGroundNote(), is(Note.G));
		assertThat(chord.isTransposed(), is(false));

		assertThat(chord.toString(), equalTo("Em/G"));
	}
}