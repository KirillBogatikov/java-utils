package org.projector.impl;

import org.projector.interfaces.Loop;

public class DefaultLoop implements Loop {
    public static final int ADD = 0, SKIP = 1, STOP = 2;

    int index;
    int action;

    public void skip() {
        action = SKIP;
    }

    public void stop() {
        action = STOP;
    }

    public int index() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
