package org.projector.dsv;

import static org.projector.utils.Nullable.ifNullOrNot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.projector.impl.DefaultStream;
import org.projector.interfaces.Stream;
import org.projector.types.Duet;

public class DsvTable {
    private Map<Duet<Integer, Integer>, DsvCell> cells;
    private Map<Integer, List<DsvCell>> rows;
    private Map<Integer, List<DsvCell>> columns;
    private int maxRow, maxColumn;

    public DsvTable() {
        cells = new HashMap<>();
        rows = new HashMap<>();
        columns = new HashMap<>();
    }

    public void clear() {
        cells.clear();
        rows.clear();
        columns.clear();
    }

    public DsvCell getCell(int row, int column) {
        return cells.get(new Duet<>(row, column));
    }

    private List<DsvCell> row(int r) {
        return ifNullOrNot(rows.get(r), () -> {
            List<DsvCell> row = new ArrayList<>();
            rows.put(r, row);
            return row;
        }, row -> row);
    }

    private List<DsvCell> column(int c) {
        return ifNullOrNot(columns.get(c), () -> {
            List<DsvCell> column = new ArrayList<>();
            columns.put(c, column);
            return column;
        }, column -> column);
    }

    public List<DsvCell> getRow(int row) {
        List<DsvCell> list = row(row);
        list.sort((c1, c2) -> Integer.compare(c1.getColumnNumber(), c2.getColumnNumber()));
        return Collections.unmodifiableList(list);
    }

    public List<DsvCell> getColumn(int column) {
        List<DsvCell> list = column(column);
        list.sort((c1, c2) -> Integer.compare(c1.getRowNumber(), c2.getRowNumber()));
        return Collections.unmodifiableList(list);
    }

    public DsvCell addCell(int row, int column) {
        if (row >= maxRow) {
            maxRow = row + 1;
        }
        if (column >= maxColumn) {
            maxColumn = column + 1;
        }

        for (int r = 0; r < maxRow; r++) {
            List<DsvCell> rowList = row(r);

            for (int c = 0; c < maxColumn; c++) {
                Duet<Integer, Integer> point = point(r, c);
                DsvCell cell = cells.get(point);
                if (cell == null) {
                    cell = new DsvCell(r, c, null);
                    cells.put(point, cell);

                    rowList.add(cell);
                    column(c).add(cell);
                }
            }
        }

        Duet<Integer, Integer> point = point(row, column);
        DsvCell cell = cells.get(point);

        if (cell != null) {
            return cell;
        }

        cell = new DsvCell(row, column, null);
        cells.put(point, cell);
        row(row).add(cell);
        column(column).add(cell);

        return cell;
    }

    public DsvCell removeCell(int row, int column) {
        return cells.remove(point(row, column));
    }

    public int getRowsCount() {
        return maxRow;
    }

    public int getColumnsCount() {
        return maxColumn;
    }
    
    public Stream<DsvCell> toCellStream() {
        ArrayList<DsvCell> cellList = new ArrayList<>();
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                cellList.add(cells.get(point(i, j)));
            }
        }
        
        return new DefaultStream<>(cellList);
    }
    
    public Stream<Object> toValueStream() {
        return toCellStream().select((cell, loop) -> cell.get(value -> value));
    }

    private Duet<Integer, Integer> point(int row, int column) {
        return new Duet<>(row, column);
    }
}
