package Q8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Q8_2 {
    public static class Cell {
        public int row;
        public int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row &&
                    col == cell.col;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, col);
        }
    }

    public static enum CellType {
        Blank, Visited, Blocked,
    }
    public static class Matrix {
        CellType[] data;
        int rows;
        int cols;

        public int getRows() {
            return rows;
        }
        public int getCols() {
            return cols;
        }

        public Matrix(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            data = new CellType[rows * cols];
            Arrays.fill(data, CellType.Blank);
        }

        public CellType getCell(int r, int c)
        {
            return data[(r-1) * cols + (c-1)];
        }
        public void setCell(int r, int c, CellType v)
        {
            data[(r-1) * cols + (c-1)] = v;
        }
    }


    public static boolean findPath(Matrix m, int curRow, int curCol, LinkedList<Cell> rc)
    {
        if (m.getCell(curRow, curCol) != CellType.Blank)
            return false;
        m.setCell(curRow, curCol, CellType.Visited);

        rc.addLast(new Cell(curRow, curCol));
        if (curRow == m.getRows() && curCol == m.getCols())
            return true;

        if (curRow < m.getRows())
        {
            if (findPath(m, curRow+1, curCol, rc))
                return true;
        }
        if (curCol < m.getCols())
        {
            if (findPath(m, curRow, curCol+1, rc))
                return true;
        }
        rc.removeLast();
        return false;
    }
}
