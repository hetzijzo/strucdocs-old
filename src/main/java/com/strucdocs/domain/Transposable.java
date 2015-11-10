package com.strucdocs.domain;

import com.strucdocs.domain.chord.Scale;

public interface Transposable {

	void transpose(Scale scale, int steps);

	boolean isTransposed();

}
