package org.projector.dsv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class DsvTableTest {
    @Test
    public void testCell() {
        DsvTable table = new DsvTable();
        DsvCell cell = table.addCell(13, 5);
        cell.setInt(162);

        assertEquals(cell, table.getCell(13, 5));
        assertEquals(162, table.getCell(13, 5).getInt());
    }

    @Test
    public void testRow() {
        DsvTable table = new DsvTable();

        DsvCell c_5_13 = table.addCell(5, 13).setInt(15);
        DsvCell c_5_12 = table.addCell(5, 12).setInt(17);
        DsvCell c_5_11 = table.addCell(5, 11).setInt(19);

        DsvCell c_1_5 = table.addCell(1, 5).setInt(5);
        DsvCell c_1_3 = table.addCell(1, 3).setInt(7);
        DsvCell c_1_1 = table.addCell(1, 1).setInt(9);

        List<DsvCell> row = table.getRow(5);
        assertEquals(14, row.size());
        assertEquals(c_5_11, row.get(11));
        assertEquals(c_5_12, row.get(12));
        assertEquals(c_5_13, row.get(13));

        row = table.getRow(1);
        assertEquals(14, row.size());
        assertEquals(c_1_1, row.get(1));
        assertEquals(c_1_3, row.get(3));
        assertEquals(c_1_5, row.get(5));
    }

    @Test
    public void testColumn() {
        DsvTable table = new DsvTable();

        DsvCell c_13_5 = table.addCell(13, 5).setInt(15);
        DsvCell c_12_5 = table.addCell(12, 5).setInt(17);
        DsvCell c_11_5 = table.addCell(11, 5).setInt(19);

        DsvCell c_5_1 = table.addCell(5, 1).setInt(5);
        DsvCell c_3_1 = table.addCell(3, 1).setInt(7);
        DsvCell c_1_1 = table.addCell(1, 1).setInt(9);

        List<DsvCell> column = table.getColumn(5);
        assertEquals(14, column.size());
        assertEquals(c_11_5, column.get(11));
        assertEquals(c_12_5, column.get(12));
        assertEquals(c_13_5, column.get(13));

        column = table.getColumn(1);
        assertEquals(14, column.size());
        assertEquals(c_1_1, column.get(1));
        assertEquals(c_3_1, column.get(3));
        assertEquals(c_5_1, column.get(5));
    }

    @Test
    public void testSize() {
        DsvTable table = new DsvTable();

        for (int r = 0; r < 16; r++) {

            for (int c = 0; c < 4; c++) {
                table.addCell(r, c);
            }

        }

        assertEquals(16, table.getRowsCount());
        assertEquals(4, table.getColumnsCount());
    }

    @Test
    public void testRemoveCell() {
        DsvTable table = new DsvTable();

        DsvCell c_13_5 = table.addCell(13, 5).setInt(15);
        table.addCell(12, 5).setInt(17);
        DsvCell c_11_5 = table.addCell(11, 5).setInt(19);

        table.removeCell(12, 5);
        assertEquals(c_13_5, table.getCell(13, 5));
        assertNull(table.getCell(12, 5));
        assertEquals(c_11_5, table.getCell(11, 5));
    }

    @Test
    public void testClear() {
        DsvTable table = new DsvTable();

        table.addCell(13, 5).setInt(15);
        table.addCell(12, 5).setInt(17);
        table.addCell(11, 5).setInt(19);

        table.addCell(5, 1).setInt(5);
        table.addCell(3, 1).setInt(7);
        table.addCell(1, 1).setInt(9);

        table.clear();

        List<DsvCell> column = table.getColumn(5);
        assertTrue(column.isEmpty());

        column = table.getColumn(1);
        assertTrue(column.isEmpty());

        assertNull(table.getCell(13, 5));
        assertNull(table.getCell(12, 5));
        assertNull(table.getCell(11, 5));
        assertNull(table.getCell(5, 1));
        assertNull(table.getCell(3, 1));
        assertNull(table.getCell(1, 1));
    }

    @Test
    public void testSegmentationRow() {
        DsvTable table = new DsvTable();

        DsvCell c_5_13 = table.addCell(5, 13).setInt(15);
        DsvCell c_5_11 = table.addCell(5, 11).setInt(19);

        DsvCell c_1_5 = table.addCell(1, 5).setInt(5);
        DsvCell c_1_3 = table.addCell(1, 3).setInt(7);

        List<DsvCell> row = table.getRow(5);
        assertEquals(14, row.size());

        for (int i = 0; i < 14; i++) {
            if (i == 11) {
                assertEquals(c_5_11, row.get(i));
            } else if (i == 13) {
                assertEquals(c_5_13, row.get(i));
            } else {
                assertFalse(row.get(i).hasValue());
            }
        }

        row = table.getRow(1);
        assertEquals(14, row.size());

        for (int i = 0; i < 14; i++) {
            if (i == 3) {
                assertEquals(c_1_3, row.get(i));
            } else if (i == 5) {
                assertEquals(c_1_5, row.get(i));
            } else {
                assertFalse(row.get(i).hasValue());
            }
        }
    }
}
