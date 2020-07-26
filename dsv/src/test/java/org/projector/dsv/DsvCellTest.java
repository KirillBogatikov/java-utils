package org.projector.dsv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DsvCellTest {
    private DsvTable table;

    @Before
    public void prepare() {
        table = new DsvTable();
    }

    @Test
    public void testPosition() {
        DsvCell cell = table.addCell(13, 5);
        assertEquals(13, cell.getRowNumber());
        assertEquals(5, cell.getColumnNumber());
    }

    @Test
    public void testSet() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.set("Hello, world!", v -> v.split(",")[0]);
        assertEquals("Hello", cell.getString());
    }

    @Test
    public void testGet() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setString("What's up?");
        assertEquals("What", cell.get(v -> v.split("'")[0]));
    }

    @Test
    public void testString() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setString("Hello, world!");
        assertEquals("Hello, world!", cell.getString());
    }

    @Test
    public void testInt() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setInt(15);
        assertEquals("15", cell.getString());
        assertEquals(15, cell.getInt());
    }

    @Test
    public void testLong() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setLong(15123412421234L);
        assertEquals("15123412421234", cell.getString());
        assertEquals(15123412421234L, cell.getLong());
    }

    @Test
    public void testShort() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setShort(15123);
        assertEquals("15123", cell.getString());
        assertEquals(15123, cell.getShort());
    }

    @Test
    public void testDouble() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setDouble(15123.67123);
        assertEquals("15123.67123", cell.getString());
        assertEquals(15123.67123, cell.getDouble(), 0.01);
    }

    @Test
    public void testSetFloat() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setFloat(153.67f);
        assertEquals("153.67", cell.getString());
        assertEquals(153.67f, cell.getFloat(), 0.01f);
    }

    @Test
    public void testByte() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setByte((byte) 1);
        assertEquals("1", cell.getString());
        assertEquals(1, cell.getByte());
    }

    @Test
    public void testBoolean() {
        table.clear();
        DsvCell cell = table.addCell(0, 0);
        cell.setBoolean(true);
        assertEquals("true", cell.getString());
        assertTrue(cell.getBoolean());
    }
}
