package org.projector.dsv.impl;

import static org.projector.utils.Nullable.checkNotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.projector.dsv.DsvSerializer;
import org.projector.dsv.DsvWriter;
import org.projector.dsv.data.DsvTable;

public class DefaultWriter implements DsvWriter {
    private Writer writer;
    private OutputStream stream;
    private DsvSerializer serializer;

    public DefaultWriter(Writer writer, DsvSerializer serializer) {
        checkNotNull(writer, "Writer");
        this.writer = writer;
        setSerializer(serializer);
    }

    public DefaultWriter(OutputStream stream, DsvSerializer serializer) {
        checkNotNull(stream, "Output stream");
        this.stream = stream;
        setSerializer(serializer);
    }

    @Override
    public void setSerializer(DsvSerializer serializer) {
        if (serializer == null) {
            this.serializer = new DefaultSerializer(DefaultSerializer.COMMA);
        } else {
            this.serializer = serializer;
        }
    }

    @Override
    public int write(DsvTable table) throws IOException {
        checkNotNull(table, "Table");
        String result = serializer.serialize(table);

        if (writer != null) {
            writer.write(result);
        } else {
            stream.write(result.getBytes());
        }

        return result.length();
    }
}
