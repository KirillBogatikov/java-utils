package org.projector.dsv;

import java.io.IOException;

import org.projector.dsv.data.DsvTable;

public interface DsvWriter {
    public void setSerializer(DsvSerializer serializer);
    public int write(DsvTable table) throws IOException;
}
