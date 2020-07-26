package org.projector.dsv;

import java.util.List;

public class SimpleDeserializer implements DsvDeserializer {
    public static final char COMMA = ',', TAB = '\t', SEMICOLON = ';';

    private char delimiter;

    public SimpleDeserializer(char delimiter) {
        if ("\r\n\"".indexOf(delimiter) != -1) {
            throw new IllegalArgumentException(String.format("Delimiter \"%s\" does not allowed", delimiter));
        }
        this.delimiter = delimiter;
    }

    @Override
    public DsvTable deserialize(List<String> lines) {
        DsvTable table = new DsvTable();

        int row = 0;
        for (String line : lines) {
            StringBuilder valueBuilder = new StringBuilder();
            boolean quote = false;
            char[] buffer = line.toCharArray();

            int column = 0;
            for (int i = 0; i < buffer.length; i++) {
                char s = buffer[i];

                if (s == '"') {
                    quote = !quote;
                } else if (s == delimiter && !quote) {
                    addCell(table, row, column++, valueBuilder);
                    valueBuilder = new StringBuilder();
                    continue;
                }

                valueBuilder.append(s);
            }

            if (valueBuilder.length() > 0) {
                addCell(table, row, column++, valueBuilder);
            }
            row++;
        }

        return table;
    }

    private void addCell(DsvTable table, int row, int column, StringBuilder builder) {
        String value = builder.toString();

        if (value.isEmpty()) {
            value = null;
        } else if (value.matches("^\"(.|\r\n)*\"$")) {
            value = value.substring(1, value.length() - 1);
            value = value.replaceAll("\"\"", "\"");
        }

        table.addCell(row, column++)
            .setString(value);
    }

}
