package sudoku.controller;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import sudoku.model.SudokuModel;
import sudoku.model.SudokuTemplate;
import sudoku.view.SudokuView;
import sudoku.solver.SudokuSolver;

public class SudokuController {

	private SudokuModel model;
	private SudokuView view;
	private SudokuSolver solver;
	private SudokuTemplate template;
	private static int startPuzzle = 0;
	private static int solvedPuzzle = 1;
	
    public SudokuController(SudokuModel model, SudokuView view, SudokuSolver solver){
        this.model = model;
        this.view = view;
        this.solver = solver;
        template = new SudokuTemplate();
    }
    
    public void controlSolveButton(){        
        ActionListener actionListener = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {
            	  solver.solveSudoku(model);
            	  updateView(solvedPuzzle);
              }
        };                
        view.getSolveButton().addActionListener(actionListener);   
    }
    
    public void changeField(){
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener(){
    		public void propertyChange(PropertyChangeEvent evt) {
        		if (evt.getNewValue() != null) {  // && evt.getNewValue().equals("false") && !(evt.getPropertyName().equals("editValid"))
        			System.out.println(evt.getOldValue() + "  " + evt.getNewValue()); // + "  " + view.getField(0, 0, 0).getText());
        		}
        	}
    	};
   	
    	for (int row = 0; row < model.getRow(); row++){
    		for (int col = 0; col < model.getColumn(); col++){
    			try {
					view.getField(0, row, col).commitEdit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
    			view.getField(0, row, col).addPropertyChangeListener("value", propertyChangeListener);
        	}
    	}
    }
    
    private void updateView(int index){
    	String value;
    	for (int row = 0; row < model.getRow(); row++){
    		for (int col = 0; col < model.getColumn(); col++){
    			if (!(model.getValue(row, col) == 0)) {
    				value = Integer.toString(model.getValue(row, col));
    			} else {
    				value = null;
    			}
    			view.getField(index, row, col).setText(value);

        	}
    	}
    }
    
    public void loadTemplate() {
    	template.sudokuExamplePuzzle(model.getSudokuPuzzle());
    	updateView(startPuzzle);
    }
}
