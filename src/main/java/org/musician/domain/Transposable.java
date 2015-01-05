package org.musician.domain;

import org.musician.domain.chord.Scale;

public interface Transposable<T> {

	boolean isTransposed();

	void transpose(Scale scale, int steps);

}
