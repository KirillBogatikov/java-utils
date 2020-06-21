package org.projector.types;

import org.projector.utils.Equaling;

public class Trio<A, B, C> extends Duet<A, B> {
    private C c;

    public Trio(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    @Override
    public int hashCode() {
        final int prime = 31;

        int result = super.hashCode();
        if (c != null) {
            result = prime * result + c.hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Trio) {
            Trio<?, ?, ?> trio = (Trio<?, ?, ?>)obj;
            return super.equals(trio) && Equaling.equals(c, trio.c);
        }
        return false;
    }
}
