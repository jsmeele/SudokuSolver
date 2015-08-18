package sudoku.run;

import javax.swing.SwingUtilities;

import sudoku.controller.SudokuController;
import sudoku.model.SudokuModel;
import sudoku.solver.SudokuSolver;
import sudoku.view.SudokuView;

public class SudokuRun {
//    public void run() {
//        // ******************** here You can swap Your true implementation
//        //SudokuImplementation sudokuImplementation = new DummySudokuImplementation();
//        // ***************************** *************** ********* **** ** *
//
//
//        SudokuView sudokuView = new SudokuView();
//        //sudokuView.setSudokuImplementation(sudokuImplementation);
//        sudokuView.setVisible(true);
//    }
//    
//    public static void main(String args[]) {
//        tryToSetSystemLookAndFeel();
//        EventQueue.invokeLater(new SudokuRun());
//    }
//
//    private static void tryToSetSystemLookAndFeel() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception ex) {
//            System.out.println("Couldn't set LAF");
//        }
//    }
	
	private static int sudokuSize = 9;
	
	public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
            	SudokuModel model = new SudokuModel(sudokuSize);
            	SudokuView view = new SudokuView(sudokuSize); 
            	SudokuSolver solver = new SudokuSolver(); 
            	SudokuController controller = new SudokuController(model,view, solver);
            	controller.loadTemplate();
            	controller.controlSolveButton();
            	controller.changeField();
            }
        }); 
	}
}
