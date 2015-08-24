package sudoku.run;

import javax.swing.SwingUtilities;

import sudoku.controller.SudokuPuzzleController;
import sudoku.model.SudokuPuzzleModel;
import sudoku.view.SudokuPuzzleView;

public class SudokuRun {
	private static int sudokuSize = 9;		//3x3, 4x4, 5x5 6x6 are fine
	
	public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
//            	SudokuSize sizeView = new SudokuSize();
//            	new SudokuSizeController(sizeView);
            	SudokuPuzzleView view = new SudokuPuzzleView(sudokuSize);
            	SudokuPuzzleModel model = new SudokuPuzzleModel(sudokuSize);
            	new SudokuPuzzleController(model,view);
            }
        }); 
	}
}
