package org.projector.dsv;

import java.util.List;

import org.projector.dsv.data.DsvTable;

public interface DsvDeserializer {
    public DsvTable deserialize(List<String> lines);
}
