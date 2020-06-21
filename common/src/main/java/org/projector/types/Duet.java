package org.projector.types;

import org.projector.utils.Equaling;

public class Duet<A, B> {
    private A a;
    private B b;

    public Duet(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        if (a != null) {
            result = prime * result + a.hashCode();
        }
        if (b != null) {
            result = prime * result + b.hashCode();
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Duet) {
            Duet<?, ?> duet = (Duet<?, ?>)obj;
            return Equaling.equals(a, duet.a) && Equaling.equals(b, duet.b);
        }
        return false;
    }
}
