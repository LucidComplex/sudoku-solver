package base;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tan on 11/29/15.
 */
public class SudokuParser {
    public static Sudoku parse(String fileName) throws IOException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader reader = new BufferedReader(in);
        String line;
        int size = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            // skip blank line
            if (line.trim().length() == 0) {
                continue;
            }
            // strip spaces
            line = line.replaceAll(" ", "");
            sb.append(line);
        }
        Sudoku sudoku = new Sudoku(sb.toString(), size);
        return sudoku;
    }
}
