package org.projector.dsv;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import static org.projector.utils.Nullable.checkNotNull;

public class DsvWriter {
    private Writer writer;
    private OutputStream stream;
    private DsvSerializer serializer;

    public DsvWriter(Writer writer, DsvSerializer serializer) {
        checkNotNull(writer, "Writer");
        this.writer = writer;
        setSerializer(serializer);
    }

    public DsvWriter(OutputStream stream, DsvSerializer serializer) {
        checkNotNull(stream, "Output stream");
        this.stream = stream;
        setSerializer(serializer);
    }

    public void setSerializer(DsvSerializer serializer) {
        if (serializer == null) {
            this.serializer = new SimpleSerializer(SimpleSerializer.COMMA);
        } else {
            this.serializer = serializer;
        }
    }

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
