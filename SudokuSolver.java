public class SudokuSolver 
{

    // Our Sudoku board
    static int[][] sudokuBoard = 
    {

        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    /**
     * Checks if placing a number at a specific position on the Sudoku board is valid.
     *
     * @param board The Sudoku board.
     * @param row   The row index of the position to check.
     * @param col   The column index of the position to check.
     * @param num   The number to place.
     * @return true if placing the number is valid, false otherwise.
     */
    static boolean isValid(int[][] board, int row, int col, int num) 
    {

        // Check the row
        for (int j = 0; j < 9; j++) 
        {

            if (board[row][j] == num) 
            {

                return false;
            }
        }

        // Check the column
        for (int i = 0; i < 9; i++) 
        {

            if (board[i][col] == num) 
            {

                return false;
            }
        }

        // Check the 3x3 sub-grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) 
        {

            for (int j = startCol; j < startCol + 3; j++) 
            {

                if (board[i][j] == num) 
                {

                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * Recursively attempts to solve the Sudoku by filling in numbers and backtracking when necessary.
     *
     * @param board The Sudoku board.
     * @param row   The current row to fill.
     * @param col   The current column to fill.
     * @return true if a solution is found, false otherwise.
     */
    static boolean solveSudoku(int[][] board, int row, int col) 
    {

        if (row == 9) 
        {

            return true;
        }

        if (col == 9) 
        {

            return solveSudoku(board, row + 1, 0);
        }
      
        if (board[row][col] != 0) 
        {

            return solveSudoku(board, row, col + 1);
        }
       
        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) 
            {

                board[row][col] = num;

                // Recursively attempt to solve the rest of the board
                if (solveSudoku(board, row, col + 1)) 
                {

                    return true;
                }

                // Backtrack if the number placement does not lead to a solution
                board[row][col] = 0;
            }
        }

        return false;
    }

    /**
     * Prints the Sudoku board to the console.
     *
     * @param board The Sudoku board to print.
     */
    static void printBoard(int[][] board) 
    {

        for (int i = 0; i < 9; i++) 
        {

            for (int j = 0; j < 9; j++) 
            {

                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) 
    {

        System.out.println("Original Sudoku board:");
        printBoard(sudokuBoard);

        // Solve the Sudoku puzzle
        if (solveSudoku(sudokuBoard, 0, 0)) 
        {

            System.out.println("Solved Sudoku board:");
            printBoard(sudokuBoard);
        } else 
        {

            System.out.println("No solution exists.");
        }
    }
}