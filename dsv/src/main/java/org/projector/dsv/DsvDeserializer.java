package org.projector.dsv;

import java.util.List;

public interface DsvDeserializer {
    public DsvTable deserialize(List<String> lines);
}
