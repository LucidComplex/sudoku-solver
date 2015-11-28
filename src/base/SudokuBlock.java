package base;

/**
 * Created by tan on 11/29/15.
 */
public class SudokuBlock {
    public int[][] config;
    public SudokuBlock(String block) {
        initBlock(block);
        int row = 0;
        int col = 0;
        for (char c : block.toCharArray()) {
            int rowIndex = row / config.length;
            int colIndex = col % config[0].length;
            config[rowIndex][colIndex] = c - '0';
            col++;
            row++;
        }
    }

    private void initBlock(String block) {
        int len = block.length();
        if (len < 9) {
            config = new int[2][len / 2];
        } else {
            config = new int[3][3];
        }
    }
}
