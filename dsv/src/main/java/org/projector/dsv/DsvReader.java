package org.projector.dsv;

import java.io.IOException;

import org.projector.dsv.data.DsvTable;

public interface DsvReader {
    public void setDeserializer(DsvDeserializer deserializer);
    public DsvTable read() throws IOException;
}
