package com.strucdocs.api;


import com.strucdocs.api.chord.Scale;

public interface Transposable {

    void transpose(Scale scale, int steps);

    boolean isTransposed();

}
