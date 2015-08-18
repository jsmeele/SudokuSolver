package sudoku.solver;

import sudoku.model.SudokuModel;

public class SudokuSolver {

	public boolean solveSudoku(SudokuModel model){
		int[] rowCol = searchEmptyField(model);
		if (rowCol[0] == 99 && rowCol[1] == 99) {
			return true;
		}
		
		for (int num = 1; num < 10; num++) {
			if (isSafe(rowCol[0], rowCol[1], num, model)) {
				model.setValue(rowCol[0], rowCol[1], num);
				//printModel(model);
				//System.out.println("END *" + searchEmptyField(model)[0] + "* *" + searchEmptyField(model)[1] + "*");
				
				if (solveSudoku(model)) { // && rowCol[0] != 99 && rowCol[1] != 99){
					return true;
				} else {
					model.setValue(rowCol[0], rowCol[1], 0);
				}
			}
			

		}
		return false;
	}
	
//	private void waitSecs(int sec){
//		long millis = (sec * 1000);
//		
//		//System.out.println("Wait");
//		try {
//			Thread.sleep((millis));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println("Go");
//	}
//	
//	private void printModel(SudokuModel model){
//		for (int row = 0; row < 9; row++) {
//			for (int col = 0; col < 9; col++) {
//				System.out.print(model.getValue(row, col));
//			}
//        }
//	}
	
	private int[] searchEmptyField(SudokuModel model) {
		int[] rowCol = new int[2];
		rowCol[0] = 99;
		rowCol[1] = 99;
		
		for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	if (model.getValue(row, col) == 0) {
            		rowCol[0] = row;
            		rowCol[1] = col;
            		return rowCol;
            	}
            }
        }
		//System.out.println("Done");
		return rowCol;
	}
	
	private boolean isSafe(int row, int col, int num, SudokuModel model){
		//System.out.println("Test");
		if (checkRow(row, num, model) && checkColumn(col, num, model) && checkSquare(row, col, num, model)) {
			return true;
		}
		return false;
	}
	
	private boolean checkRow(int row, int num, SudokuModel model) {
        for (int col = 0; col < 9; col++) {
        	if (model.getValue(row, col) == num){
        		return false;
        	} 
        }
        return true;
	}
	
	private boolean checkColumn(int col, int num, SudokuModel model) {
        for (int row = 0; row < 9; row++) {
        	if (model.getValue(row, col) == num){
        		return false;
        	}
        }
        return true;
	}
	
	private int calculateSquareIndex(int index) {
		//Determine Square section for row or column
		switch ("" + Math.round(index/3)) { 
		case "0":
			index = 0;
			break;
		case "1":
			index = 3;
			break;
		case "2":
			index = 6;
			break;
		}
		return index;
	}
	
	private boolean checkSquare(int indexRow, int indexCol, int num, SudokuModel model) {
		//System.out.println(indexRow + "," + indexCol + "   Calc minRow " + (0 + calculateSquareIndex(indexRow)) + " maxRow " + (3 + calculateSquareIndex(indexRow)) + "           minCol " + (0 + calculateSquareIndex(indexCol)) + " maxCol " + (3 + calculateSquareIndex(indexCol)));
		for (int row = 0 + calculateSquareIndex(indexRow); row < 3 + calculateSquareIndex(indexRow); row++) {
			for (int col = 0 + calculateSquareIndex(indexCol); col < 3 + calculateSquareIndex(indexCol); col++) {
				if (model.getValue(row, col) == num){
	        		return false;
	        	}
			}
        }
        return true;	
	}
}