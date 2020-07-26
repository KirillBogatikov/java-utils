package org.projector.dsv;

import org.projector.utils.StringJoiner;

public class SimpleSerializer implements DsvSerializer {
    public static final char COMMA = ',', TAB = '\t', SEMICOLON = ';';

    private char delimiter;

    public SimpleSerializer(char delimiter) {
        if ("\r\n\"".indexOf(delimiter) != -1) {
            throw new IllegalArgumentException(String.format("Delimiter \"%s\" does not allowed", delimiter));
        }
        this.delimiter = delimiter;
    }

    @Override
    public String serialize(DsvTable table) {
        StringJoiner rowJoiner = new StringJoiner("\r\n");

        String pattern = String.format("^.*([,;\"]|(\r\n)|(%s)).*$", delimiter);
        for (int i = 0; i < table.getRowsCount(); i++) {
            StringJoiner cellJoiner = new StringJoiner(delimiter);

            for (int j = 0; j < table.getColumnsCount(); j++) {
                DsvCell cell = table.getCell(i, j);
                if (cell == null) {
                    cellJoiner.append("");
                    continue;
                }

                String value = cell.getString();
                if (value == null) {
                    cellJoiner.append("");
                    continue;
                }

                if (value.matches(pattern)) {
                    value = String.format("\"%s\"", value.replaceAll("\"", "\"\""));
                }
                cellJoiner.append(value);
            }

            rowJoiner.append(cellJoiner);
        }

        return rowJoiner.toString();
    }

}
