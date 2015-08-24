package sudoku.controller;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

import java.awt.event.ActionEvent;

import sudoku.model.SudokuPuzzleModel;
import sudoku.model.SudokuPuzzleTemplate;
import sudoku.view.SudokuPuzzleView;

public class SudokuPuzzleController {

	private SudokuPuzzleModel model;
	private SudokuPuzzleView view;
	private static int startPuzzle = 0;
	private static int solvedPuzzle = 1;
	
    public SudokuPuzzleController(SudokuPuzzleModel model, SudokuPuzzleView view){
        this.model = model;
        this.view = view;
        initController();
    }
    
    private void initController(){
    	//isValidDimension(model.getRow());		// getColumn is also possible. Row and Col are the same (based on sudokuSize).
    	controlSolveButton();
        //loadTemplate();
        changeField();
    }
    
    public void controlSolveButton(){        
        ActionListener actionListener = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {
            	  model.solveSudoku();
            	  updateView(solvedPuzzle);
              }
        };                
        view.getSolveButton().addActionListener(actionListener);   
    }
    
    public void changeField(){
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener(){
    		public void propertyChange(PropertyChangeEvent evt) {
        		if (evt.getNewValue() != null) {  
        			//Do Something Here
        			System.out.println(evt.getOldValue() + "  " + evt.getNewValue());
        		}
        	}
    	};
   	
    	for (int row = 0; row < model.getRow(); row++){
    		for (int col = 0; col < model.getColumn(); col++){
    			try {
					view.getField(startPuzzle, row, col).commitEdit();
				} catch (ParseException e) {
					//e.printStackTrace();
				}
    			view.getField(startPuzzle, row, col).addPropertyChangeListener("value", propertyChangeListener);
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
    	SudokuPuzzleTemplate template = new SudokuPuzzleTemplate();
    	template.sudokuExamplePuzzle(model.getSudokuPuzzle());
    	updateView(startPuzzle);
     }
}