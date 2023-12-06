package com.cwblue.matrix;

/**
 * 有一个n行m列的矩阵，要求按照Z字形打印出数据
 *
 * @author wen
 */
public class ZigzagPrintMatrix {
    public static void zigzagPrintMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        // a指针
        int aRow = 0;
        int aColumn = 0;
        // b指针
        int bRow = 0;
        int bColumn = 0;
        // 向上打印还是向下打印
        boolean flag = true;
        // 矩阵边界
        int maxRow = matrix.length - 1;
        int maxColumn = matrix[0].length - 1;
        while (aRow != maxRow + 1) {
            printMatrix(matrix, aRow, aColumn, bRow, bColumn, flag);
            aRow = aColumn == maxColumn ? aRow + 1 : aRow;
            aColumn = aColumn == maxColumn ? aColumn : aColumn + 1;
            bColumn = bRow == maxRow ? bColumn + 1 : bColumn;
            bRow = bRow == maxRow ? bRow : bRow + 1;
            // 每次打印完要改变方向
            flag = !flag;
        }
    }

    private static void printMatrix(int[][] matrix, int aRow, int aColumn, int bRow, int bColumn, boolean flag) {
        if (flag) {
            while (bRow != aRow - 1) {
                System.out.print(matrix[bRow--][bColumn++] + " ");
            }
        }else {
            while (aRow != bRow + 1) {
                System.out.print(matrix[aRow++][aColumn--] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][3];
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = num++;
            }
        }
        zigzagPrintMatrix(matrix);
    }
}
