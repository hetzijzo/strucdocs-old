package org.musician.domain;

import org.musician.domain.chord.Scale;

public interface Transposable {

	void transpose(Scale scale, int steps);

	boolean isTransposed();

}
