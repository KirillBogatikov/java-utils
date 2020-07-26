package org.projector.utils;

import java.util.Arrays;

public class StringJoiner implements Appendable, CharSequence {
    private char[] sequence;
    private char[] delimiter;
    private boolean empty;
    private int index;

    public StringJoiner(CharSequence base, CharSequence delimiter) {
        if (base == null) {
            sequence = new char[10];
            index = 0;
            empty = true;
        } else {
            sequence = charsOf(base);
            index = sequence.length;
            empty = false;
        }

        this.delimiter = charsOf(delimiter);
    }

    public StringJoiner(CharSequence delimiter) {
        this(null, delimiter);
    }

    private char[] charsOf(CharSequence sequence) {
        char[] chars = new char[sequence.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = sequence.charAt(i);
        }
        return chars;
    }

    @Override
    public int length() {
        return index;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Index must be greater than or equal to 0 and less than " + length());
        }

        return sequence[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || start > length() || end > length() || start >= end) {
            throw new IndexOutOfBoundsException(
                    "Start must be greater than or equal to 0, less than " + length() + " and less than " + end);
        }

        char[] sequence = new char[end - start];
        System.arraycopy(this.sequence, start, sequence, 0, end - start);
        return new String(sequence);
    }

    @Override
    public StringJoiner append(CharSequence csq) {
        int length = csq.length();
        if (sequence.length - index < length + delimiter.length) {
            ensureCapacity(length + delimiter.length);
        }

        if (empty) {
            empty = false;
        } else {
            for (int i = 0; i < delimiter.length; i++, index++) {
                sequence[index] = delimiter[i];
            }
        }
        for (int i = 0; i < length; i++, index++) {
            sequence[index] = csq.charAt(i);
        }

        return this;
    }

    @Override
    public StringJoiner append(CharSequence csq, int start, int end) {
        return append(csq.subSequence(start, end));
    }

    @Override
    public StringJoiner append(char c) {
        if (index >= sequence.length) {
            ensureCapacity(10);
        }
        sequence[index++] = c;
        empty = false;
        return this;
    }

    private void ensureCapacity(int size) {
        char[] newSequence = new char[sequence.length + size];
        System.arraycopy(sequence, 0, newSequence, 0, index);
        sequence = newSequence;
    }

    public StringJoiner append(Object object) {
        append(String.valueOf(object));
        return this;
    }

    public StringJoiner delete(int start, int end) {
        if (start < 0 || start > length() || end > length() || start >= end) {
            throw new IndexOutOfBoundsException(
                    "Start must be greater than or equal to 0, less than " + length() + " and less than " + end);
        }

        int length = end - start;
        char[] newSequence = new char[sequence.length - length];
        System.arraycopy(sequence, 0, newSequence, 0, start);
        System.arraycopy(sequence, end, newSequence, start, sequence.length - end);
        sequence = newSequence;
        index -= length;

        return this;
    }

    public StringJoiner deleteCharAt(int index) {
        delete(index, index + 1);
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof StringJoiner) {
            StringJoiner joiner = (StringJoiner) other;
            return Arrays.equals(joiner.sequence, sequence);
        }
        if (other instanceof CharSequence) {
            return other.equals(toString());
        }

        return false;
    }

    @Override
    public String toString() {
        if (index == 0) {
            return "";
        }

        return subSequence(0, index).toString();
    }
}
