package sudoku.run;

import javax.swing.SwingUtilities;

import sudoku.controller.SudokuPuzzleController;
import sudoku.model.SudokuPuzzleModel;
import sudoku.view.SudokuPuzzleView;

public class SudokuRun {
	private static int sudokuSize = 12;		//Sudoku Row and Column size
	private static int squareRow = 4;		//Sudoku inner square row size
	private static int squareCol = 3;		//Sudoku inner square column size
	
	public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
//            	SudokuSize sizeView = new SudokuSize();
//            	new SudokuSizeController(sizeView);
            	SudokuPuzzleView view = new SudokuPuzzleView(sudokuSize);
            	SudokuPuzzleModel model = new SudokuPuzzleModel(sudokuSize, squareRow, squareCol);
            	new SudokuPuzzleController(model,view);
            }
        }); 
	}
}
