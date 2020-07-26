package org.projector.dsv;

import static org.junit.Assert.assertEquals;
import static org.projector.dsv.SimpleSerializer.COMMA;
import static org.projector.dsv.SimpleSerializer.SEMICOLON;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class SimpleDeserializerTest {
    @Test
    public void testEmptyTable() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("");

        DsvDeserializer d = new SimpleDeserializer(COMMA);
        DsvTable table = d.deserialize(lines);

        assertEquals(0, table.getRowsCount());
        assertEquals(0, table.getColumnsCount());
    }

    @Test
    public void testNormalTable() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("1;Hello;English");
        lines.add("2;Hola;Espanol");
        lines.add("3;Привет;Russian");

        DsvDeserializer d = new SimpleDeserializer(SEMICOLON);
        DsvTable table = d.deserialize(lines);

        assertEquals(1, table.getCell(0, 0).getInt());
        assertEquals("Hello", table.addCell(0, 1).getString());
        assertEquals("English", table.addCell(0, 2).getString());

        assertEquals(2, table.getCell(1, 0).getInt());
        assertEquals("Hola", table.getCell(1, 1).getString());
        assertEquals("Espanol", table.getCell(1, 2).getString());

        assertEquals(3, table.getCell(2, 0).getInt());
        assertEquals("Привет", table.getCell(2, 1).getString());
        assertEquals("Russian", table.getCell(2, 2).getString());
    }

    @Test
    public void testUnescapeValues() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("#,\"Escaped value\",\"Com,ma\",\"Semi;colon\",\"Line\r\nbreak\",\"Double\"\"quote\"");

        DsvDeserializer d = new SimpleDeserializer(COMMA);
        DsvTable table = d.deserialize(lines);

        assertEquals(1, table.getRowsCount());
        assertEquals(6, table.getColumnsCount());
        assertEquals("#", table.getCell(0, 0).getString());
        assertEquals("Escaped value", table.getCell(0, 1).getString());
        assertEquals("Com,ma", table.getCell(0, 2).getString());
        assertEquals("Semi;colon", table.getCell(0, 3).getString());
        assertEquals("Line\r\nbreak", table.getCell(0, 4).getString());
        assertEquals("Double\"quote", table.getCell(0, 5).getString());
    }
}
