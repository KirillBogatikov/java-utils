package org.projector.dsv;

import org.projector.dsv.data.DsvTable;

public interface DsvSerializer {
    public String serialize(DsvTable table);
}
