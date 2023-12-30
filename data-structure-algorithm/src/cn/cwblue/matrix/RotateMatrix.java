package cn.cwblue.matrix;

/**
 * 有一个n*n的矩阵，现在把整个矩阵顺时针旋转90°
 *
 * @author wen
 */
public class RotateMatrix {
    public static void rotateMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        // 定义四个角
        int startRow = 0;
        int startColumn = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;
        // 每次循环都会向里走步层
        while (startRow <= endRow) {
            rotateEdge(matrix, startRow++, startColumn++, endRow--, endColumn--);
        }
    }

    private static void rotateEdge(int[][] matrix, int startRow, int startColumn, int endRow, int endColumn) {
        int temp = 0;
        // 顺时针交换四个数据
        for (int i = 0; i < endRow - startRow; i++) {
            temp = matrix[startRow][startColumn + i];
            matrix[startRow][startColumn + i] = matrix[endRow - i][startColumn];
            matrix[endRow - i][startColumn] = matrix[endRow][endColumn - i];
            matrix[endRow][endColumn - i] = matrix[startRow + i][endColumn];
            matrix[startRow + i][endColumn] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = num++;
            }
        }
        rotateMatrix(matrix);
    }
}
