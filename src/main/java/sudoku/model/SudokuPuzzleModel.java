package sudoku.model;

public class SudokuPuzzleModel {
	
	private final int[][] sudokuPuzzle;

	public SudokuPuzzleModel(int sudokuSize){
		sudokuPuzzle = new int[sudokuSize][sudokuSize];
	}
	
	public int getValue(int row, int col){
		return sudokuPuzzle[row][col];
	}

	public void setValue(int row, int col, int value){
		sudokuPuzzle[row][col] = value;
	}
	
	public int[][] getSudokuPuzzle(){
		return sudokuPuzzle;
	}

	public int getRow(){
		return sudokuPuzzle.length;
	}
	
	public int getColumn(){
		return sudokuPuzzle[0].length;
	}
	
	public boolean solveSudoku(SudokuPuzzleModel model){
		int[] rowCol = searchEmptyField(model);
		if (rowCol[0] == 99 && rowCol[1] == 99) {
			return true;
		}
		for (int num = 1; num <= getRow(); num++) {
			if (isSafe(rowCol[0], rowCol[1], num, model)) {
				//System.out.println(rowCol[0] + "  " + rowCol[1] + "   " + num + "   " + isSafe(rowCol[0], rowCol[1], num, model));
				model.setValue(rowCol[0], rowCol[1], num);
				if (solveSudoku(model)) {
					return true;
				} else {
					model.setValue(rowCol[0], rowCol[1], 0);
				}
			}
		}
		return false;
	}
	
	private int[] searchEmptyField(SudokuPuzzleModel model) {
		int[] rowCol = new int[2];
		rowCol[0] = 99;
		rowCol[1] = 99;
		
		for (int row = 0; row < getRow(); row++) {
            for (int col = 0; col < getColumn(); col++) {
            	if (model.getValue(row, col) == 0) {
            		rowCol[0] = row;
            		rowCol[1] = col;
            		return rowCol;
            	}
            }
        }
		return rowCol;
	}
	
	private boolean isSafe(int row, int col, int num, SudokuPuzzleModel model){
		if (checkRow(row, num, model) && checkColumn(col, num, model) && checkSquare(row, col, num, model)) {
			return true;
		}
		return false;
	}
	
	private boolean checkRow(int row, int num, SudokuPuzzleModel model) {
        for (int col = 0; col < getColumn(); col++) {
        	if (model.getValue(row, col) == num){
        		return false;
        	} 
        }
        return true;
	}
	
	private boolean checkColumn(int col, int num, SudokuPuzzleModel model) {
        for (int row = 0; row < getRow(); row++) {
        	if (model.getValue(row, col) == num){
        		return false;
        	}
        }
        return true;
	}
	
	private int calculateSquareIndex(int index) {
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
		case "3":
			index = 9;
			break;
		}
		return index;
	}
	
	private boolean checkSquare(int indexRow, int indexCol, int num, SudokuPuzzleModel model) {
		for (int row = 0 + calculateSquareIndex(indexRow); row < 3 + calculateSquareIndex(indexRow); row++) {
			for (int col = 0 + calculateSquareIndex(indexCol); col < 3 + calculateSquareIndex(indexCol); col++) {
				if (model.getValue(row, col) == num){
	        		return false;
	        	}
			}
        }
        return true;	
	}
	
//	public int getEmptyField(){
//		for (int row = 0; row < getRow(); row++) {
//            for (int col = 0; col < getColumn(); col++) {
//            	if (sudokuPuzzle[row][col] == 0) {
//            		System.out.println(row +"," + col + "  " + sudokuPuzzle[row][col]);
//            		return sudokuPuzzle[row][col];
//            	}
//            }
//        }
//		return 9999;
//	}
//	
//	private void waitSecs(int sec){
//	long millis = (sec * 1000);
//	
//	//System.out.println("Wait");
//	try {
//		Thread.sleep((millis));
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	//System.out.println("Go");
//}
//
//private void printModel(SudokuModel model){
//	for (int row = 0; row < 9; row++) {
//		for (int col = 0; col < 9; col++) {
//			System.out.print(model.getValue(row, col));
//		}
//    }
//}
}