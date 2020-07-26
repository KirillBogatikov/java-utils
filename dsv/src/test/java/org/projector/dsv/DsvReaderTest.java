package org.projector.dsv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.projector.dsv.SimpleSerializer.COMMA;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class DsvReaderTest {
    @Test(expected = NullPointerException.class)
    public void testNullWriter() {
        Reader r = null;
        new DsvReader(r, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullStream() {
        InputStream s = null;
        new DsvReader(s, null);
    }

    @Test
    public void testEmptyTable() throws IOException {
        StringReader reader = new StringReader("");
        DsvReader dsvReader = new DsvReader(reader, null);
        DsvTable table = dsvReader.read();

        assertNotNull(table);
        assertEquals(0, table.getRowsCount());
        assertEquals(0, table.getColumnsCount());
    }

    @Test
    public void testReadOnelineTable() throws IOException {
        StringReader reader = new StringReader("#,Name,Description");
        DsvReader dsvReader = new DsvReader(reader, new SimpleDeserializer(COMMA));

        DsvTable table = dsvReader.read();

        assertEquals(1, table.getRowsCount());
        assertEquals(3, table.getColumnsCount());
        assertEquals("#", table.getCell(0, 0).getString());
        assertEquals("Name", table.getCell(0, 1).getString());
        assertEquals("Description", table.getCell(0, 2).getString());
    }

    @Test
    public void testReadMultilineTable() throws IOException {
        StringReader reader = new StringReader("#,Name,Description\r\n0,Kirill,Author\r\n1,Maria,Developer");
        DsvReader dsvReader = new DsvReader(reader, new SimpleDeserializer(COMMA));

        DsvTable table = dsvReader.read();

        assertEquals(3, table.getRowsCount());
        assertEquals(3, table.getColumnsCount());

        assertEquals("#", table.getCell(0, 0).getString());
        assertEquals("Name", table.getCell(0, 1).getString());
        assertEquals("Description", table.getCell(0, 2).getString());

        assertEquals("0", table.getCell(1, 0).getString());
        assertEquals("Kirill", table.getCell(1, 1).getString());
        assertEquals("Author", table.getCell(1, 2).getString());

        assertEquals("1", table.getCell(2, 0).getString());
        assertEquals("Maria", table.getCell(2, 1).getString());
        assertEquals("Developer", table.getCell(2, 2).getString());
    }

    @Test
    public void testReadSegmentationTable() throws IOException {
        StringReader reader = new StringReader("#,Name,Description\r\n0,,Author\r\n,Maria,Developer");
        DsvReader dsvReader = new DsvReader(reader, new SimpleDeserializer(COMMA));

        DsvTable table = dsvReader.read();

        assertEquals(3, table.getRowsCount());
        assertEquals(3, table.getColumnsCount());

        assertEquals("#", table.getCell(0, 0).getString());
        assertEquals("Name", table.getCell(0, 1).getString());
        assertEquals("Description", table.getCell(0, 2).getString());

        assertEquals("0", table.getCell(1, 0).getString());
        assertFalse(table.getCell(1, 1).hasValue());
        assertEquals("Author", table.getCell(1, 2).getString());

        assertFalse(table.getCell(2, 0).hasValue());
        assertEquals("Maria", table.getCell(2, 1).getString());
        assertEquals("Developer", table.getCell(2, 2).getString());
    }

}
