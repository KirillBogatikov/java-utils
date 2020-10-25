package org.projector.dsv.impl;

import static org.projector.utils.Nullable.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.projector.dsv.DsvDeserializer;
import org.projector.dsv.DsvReader;
import org.projector.dsv.data.DsvTable;

public class DefaultReader implements DsvReader {
    private Reader reader;
    private InputStream stream;
    private DsvDeserializer deserializer;

    public DefaultReader(Reader reader, DsvDeserializer deserializer) {
        checkNotNull(reader, "Writer");
        this.reader = reader;
        setDeserializer(deserializer);
    }

    public DefaultReader(InputStream stream, DsvDeserializer deserializer) {
        checkNotNull(stream, "Input stream");
        this.stream = stream;
        setDeserializer(deserializer);
    }

    @Override
    public void setDeserializer(DsvDeserializer deserializer) {
        if (deserializer == null) {
            this.deserializer = new DefaultDeserializer(DefaultDeserializer.COMMA);
        } else {
            this.deserializer = deserializer;
        }
    }

    @Override
    public DsvTable read() throws IOException {
        List<String> lines;

        if (reader != null) {
            lines = readFromReader();
        } else {
            lines = readFromStream();
        }

        return deserializer.deserialize(lines);
    }

    private List<String> readFromReader() throws IOException {
        char[] buffer = new char[4096];

        int index = 0, count;
        while((count = reader.read(buffer, index, 4096)) != -1) {
            index += count;

            char[] newBuffer = new char[buffer.length + 4096];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            buffer = newBuffer;
        }

        return readFromCharArray(buffer, index);
    }

    private List<String> readFromStream() throws IOException {
        byte[] buffer = new byte[4096];

        int index = 0, count;
        while((count = stream.read(buffer, index, 4096)) != -1) {
            index += count;

            byte[] newBuffer = new byte[buffer.length + 4096];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);

            buffer = newBuffer;
        }

        String text = new String(buffer);
        return readFromCharArray(text.toCharArray(), count);
    }

    private List<String> readFromCharArray(char[] buffer, int count) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder lineBuilder = new StringBuilder();

        boolean quote = false;
        for (int i = 0; i < count; i++) {
            char s = buffer[i];

            switch (s) {
                case '"': quote = !quote; break;
                case '\n': {
                    if (i > 0 && buffer[i - 1] == '\r' && !quote) {
                        lineBuilder.deleteCharAt(lineBuilder.length() - 1); //delete CR

                        lines.add(lineBuilder.toString());
                        lineBuilder = new StringBuilder();
                        continue;
                    }
                }
            }

            lineBuilder.append(s);
        }

        if (lineBuilder.length() > 0) {
            lines.add(lineBuilder.toString());
        }

        return lines;
    }
}
