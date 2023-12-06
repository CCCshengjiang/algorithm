package com.cwblue.matrix;

/**
 * 一个n行m列矩阵，需要从外围开始转圈打印，直到所有数据都被打印
 *
 * @author wen
 */
public class PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix) {
        int firstRow = 0;
        int firstColumn = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;
        while (firstRow <= endRow && firstColumn <= endColumn) {
            printEdge(matrix, firstRow++, firstColumn++, endRow--, endColumn--);
        }

    }

    private static void printEdge(int[][] matrix, int firstRow, int firstColumn, int endRow, int endColumn) {
        if (firstRow == endRow) { // 如果矩阵是个列矩阵
            while (firstColumn <= endColumn) {
                System.out.print(matrix[firstRow][firstColumn++] + " ");
            }
        } else if (firstColumn == endColumn) { // 如果矩阵是个行矩阵
            while (firstRow <= endRow) {
                System.out.print(matrix[firstRow++][firstColumn] + " ");
            }
        }else { // 正常打印
            int row = firstRow;
            int column = firstColumn;
            while (firstColumn != endColumn) {
                System.out.print(matrix[firstRow][firstColumn++] + " ");
            }
            while (firstRow != endRow) {
                System.out.print(matrix[firstRow++][firstColumn] + " ");
            }
            while (firstColumn != column) {
                System.out.print(matrix[firstRow][firstColumn--] + " ");
            }
            while (firstRow != row) {
                System.out.print(matrix[firstRow--][firstColumn] + " ");
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
        spiralOrderPrint(matrix);
    }
}
