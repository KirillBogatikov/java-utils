package org.projector.dsv;

import org.projector.interfaces.Consumer;
import static org.projector.utils.Nullable.ifNullOrNot;

public class DsvCell {
    private int row, column;
    private String value;

    DsvCell(int row, int column, String value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public byte getByte() {
        return get(v -> Byte.valueOf(v));
    }

    public int getInt() {
        return get(v -> Integer.valueOf(v));
    }

    public short getShort() {
        return get(v -> Short.valueOf(v));
    }

    public long getLong() {
        return get(v -> Long.valueOf(v));
    }

    public double getDouble() {
        return get(v -> Double.valueOf(v));
    }

    public float getFloat() {
        return get(v -> Float.valueOf(v));
    }

    public boolean getBoolean() {
        return get(v -> Boolean.valueOf(v));
    }

    public String getString() {
        return value;
    }

    public <T> T get(Consumer<String, T> consumer) {
        return consumer.consume(value);
    }

    public DsvCell setByte(byte b) {
        set(b, v -> v.toString());
        return this;
    }

    public DsvCell setInt(int i) {
        set(i, v -> v.toString());
        return this;
    }

    public DsvCell setShort(int s) {
        set(s, v -> v.toString());
        return this;
    }

    public DsvCell setLong(long l) {
        set(l, v -> v.toString());
        return this;
    }

    public DsvCell setDouble(double d) {
        set(d, v -> v.toString());
        return this;
    }

    public DsvCell setFloat(float f) {
        set(f, v -> v.toString());
        return this;
    }

    public DsvCell setBoolean(boolean b) {
        set(b, v -> v.toString());
        return this;
    }

    public DsvCell setString(String s) {
        value = s;
        return this;
    }

    public <T> DsvCell set(T value, Consumer<T, String> consumer) {
        this.value = consumer.consume(value);
        return this;
    }

    public boolean hasValue() {
        return ifNullOrNot(value, () -> false, v -> !v.isEmpty());
    }

    public int getRowNumber() {
        return row;
    }

    public int getColumnNumber() {
        return column;
    }

    @Override
    public String toString() {
        String v = ifNullOrNot(value, () -> "<no value>", val -> val);
        return String.format("(%d, %d) = %s", row, column, v);
    }
}
