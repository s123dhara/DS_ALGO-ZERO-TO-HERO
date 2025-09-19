class Spreadsheet {
    private int[][] grid;

    public Spreadsheet(int rows) {
        grid = new int[rows][26]; // columns A–Z
    }

    public void setCell(String cell, int value) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = value;
    }

    public void resetCell(String cell) {
        int[] pos = parseCell(cell);
        grid[pos[0]][pos[1]] = 0;
    }

    public int getValue(String formula) {
        String[] parts = formula.substring(1).split("\\+"); // remove '='
        return parseValue(parts[0]) + parseValue(parts[1]);
    }

    /** Helper: Parse value (cell or number) */
    private int parseValue(String token) {
        if (Character.isLetter(token.charAt(0))) {
            int[] pos = parseCell(token);
            return grid[pos[0]][pos[1]];
        } else {
            return Integer.parseInt(token);
        }
    }

    /** Helper: Parse cell like "B2" into [rowIndex, colIndex] */
    private int[] parseCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return new int[]{row, col};
    }
}


/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */