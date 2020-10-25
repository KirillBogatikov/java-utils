package org.projector.dsv;

import static org.junit.Assert.assertEquals;
import static org.projector.dsv.impl.DefaultSerializer.COMMA;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;
import org.projector.dsv.data.DsvTable;
import org.projector.dsv.impl.DefaultSerializer;
import org.projector.dsv.impl.DefaultWriter;

public class DsvWriterTest {
    @Test(expected = NullPointerException.class)
    public void testNullWriter() {
        Writer w = null;
        new DefaultWriter(w, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullStream() {
        OutputStream s = null;
        new DefaultWriter(s, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullTable() throws IOException {
        DsvTable table = null;
        DefaultWriter dsvWriter = new DefaultWriter(System.out, null);
        dsvWriter.write(table);
    }

    @Test
    public void testWriteOnelineTable() throws IOException {
        DsvTable table = new DsvTable();
        table.addCell(0, 0).setString("#");
        table.addCell(0, 1).setString("Name");
        table.addCell(0, 2).setString("Description");

        StringWriter writer = new StringWriter();
        DefaultWriter dsvWriter = new DefaultWriter(writer, new DefaultSerializer(COMMA));
        dsvWriter.write(table);

        assertEquals("#,Name,Description", writer.toString());
    }

    @Test
    public void testWriteMultilineTable() throws IOException {
        DsvTable table = new DsvTable();
        table.addCell(0, 0).setString("#");
        table.addCell(0, 1).setString("Name");
        table.addCell(0, 2).setString("Description");
        table.addCell(1, 0).setInt(0);
        table.addCell(1, 1).setString("Kirill");
        table.addCell(1, 2).setString("Author");
        table.addCell(2, 0).setInt(1);
        table.addCell(2, 1).setString("John");
        table.addCell(2, 2).setString("Maintainer");
        table.addCell(3, 0).setInt(2);
        table.addCell(3, 1).setString("Liza");
        table.addCell(3, 2).setString("Project manager");

        StringWriter writer = new StringWriter();
        DefaultWriter dsvWriter = new DefaultWriter(writer, new DefaultSerializer(COMMA));
        dsvWriter.write(table);

        String expected = "#,Name,Description\r\n"
                            + "0,Kirill,Author\r\n"
                            + "1,John,Maintainer\r\n"
                            + "2,Liza,Project manager";
        assertEquals(expected, writer.toString());
    }
}
