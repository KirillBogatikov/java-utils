package org.projector.dsv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleSerializerTest {
    @Test
    public void testEmptyTable() {
        DsvTable table = new DsvTable();
        SimpleSerializer s = new SimpleSerializer(SimpleSerializer.SEMICOLON);
        assertEquals("", s.serialize(table));
    }

    @Test
    public void testNormalTable() {
        DsvTable table = new DsvTable();

        table.addCell(0, 0).setInt(1);
        table.addCell(0, 1).setString("Hello");
        table.addCell(0, 2).setString("English");
        table.addCell(1, 0).setInt(2);
        table.addCell(1, 1).setString("Hola");
        table.addCell(1, 2).setString("Espanol");
        table.addCell(2, 0).setInt(3);
        table.addCell(2, 1).setString("Привет");
        table.addCell(2, 2).setString("Russian");

        SimpleSerializer s = new SimpleSerializer(SimpleSerializer.SEMICOLON);
        String expected = "1;Hello;English\r\n"
                        + "2;Hola;Espanol\r\n"
                        + "3;Привет;Russian";
        assertEquals(expected, s.serialize(table));
    }

    @Test
    public void testSegmentationTable() {
        DsvTable table = new DsvTable();

        table.addCell(0, 0).setInt(1);
        table.addCell(0, 2).setString("Hello");
        table.addCell(0, 4).setString("English");
        table.addCell(1, 0).setInt(2);
        table.addCell(1, 1).setString("Buenas noches");
        table.addCell(1, 2).setString("Hola");
        table.addCell(1, 4).setString("Espanol");
        table.addCell(2, 0).setInt(3);
        table.addCell(2, 1).setString("Добрый вечер");
        table.addCell(2, 2).setString("Привет");
        table.addCell(2, 3).setString("Доброе утро");
        table.addCell(2, 4).setString("Russian");

        SimpleSerializer s = new SimpleSerializer(SimpleSerializer.SEMICOLON);
        String expected = "1;;Hello;;English\r\n"
                        + "2;Buenas noches;Hola;;Espanol\r\n"
                        + "3;Добрый вечер;Привет;Доброе утро;Russian";
        assertEquals(expected, s.serialize(table));
    }

    @Test
    public void testEscapeValues() {
        DsvTable table = new DsvTable();

        table.addCell(0, 0).setInt(1);
        table.addCell(0, 1).setString("Line\r\nbreak");
        table.addCell(0, 2).setString("Double\"quote");
        table.addCell(0, 3).setString("Semi;colon");
        table.addCell(0, 4).setString("Com,ma");

        SimpleSerializer s = new SimpleSerializer(SimpleSerializer.SEMICOLON);
        String expected = "1;\"Line\r\nbreak\";\"Double\"\"quote\";\"Semi;colon\";\"Com,ma\"";
        assertEquals(expected, s.serialize(table));
    }
}
