package sudoku.model;

public class SudokuPuzzleModel {
	
	private final int[][] sudokuPuzzle;
	private final int squareRow;
	private final int squareCol;
	private int currentRow, currentCol;

	public SudokuPuzzleModel(int sudokuSize, int squareRow, int squareCol){
		sudokuPuzzle = new int[sudokuSize][sudokuSize];
		this.squareRow = squareRow;
		this.squareCol = squareCol;
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
	
	public boolean solveSudoku(){
		if (!isEmptyField(getSudokuPuzzle())) {
			return true;
		}
		
		int row = currentRow; 
		int col = currentCol;
		
		for (int num = 1; num <= getRow(); num++) {
			if (isSafe(row, col, num)) {
				setValue(row, col, num);
				if (solveSudoku()) {
					return true;
				} else {
					setValue(row, col, 0);
				}
			}
		}
		return false;
	}
	
	public int getValue(int row, int col){
		return sudokuPuzzle[row][col];
	}

	private void setValue(int row, int col, int value){
		sudokuPuzzle[row][col] = value;
	}

	private boolean isEmptyField(int[][] model) {
		for (int row = 0; row < getRow(); row++) {
            for (int col = 0; col < getColumn(); col++) {
            	if (getValue(row, col) == 0) {
            		currentRow = row;
            		currentCol = col;
            		return true;
            	}
            }
        }
		return false;
	}
	
	private boolean isSafe(int row, int col, int num){
		if (isNumRowValid(row, num) && isNumColumnValid(col, num) && isNumSquareValid(row, col, num)) {
			return true;
		}
		return false;
	}
	
	private boolean isNumRowValid(int row, int num) {
        for (int col = 0; col < getColumn(); col++) {
        	if (getValue(row, col) == num){
        		return false;
        	} 
        }
        return true;
	}
	
	private boolean isNumColumnValid(int col, int num) {
        for (int row = 0; row < getRow(); row++) {
        	if (getValue(row, col) == num){
        		return false;
        	}
        }
        return true;
	}
	
	private int calculateSquareIndex(int index, int factor) {
		return (int) (factor * Math.round(Math.ceil(index/factor)));
	}
	
	private boolean isNumSquareValid(int indexRow, int indexCol, int num) {
		for (int row = 0 + calculateSquareIndex(indexRow, squareRow); row < squareRow + calculateSquareIndex(indexRow, squareRow); row++) {
			for (int col = 0 + calculateSquareIndex(indexCol, squareCol); col < squareCol + calculateSquareIndex(indexCol, squareCol); col++) {
				if (getValue(row, col) == num){
	        		return false;
	        	}
			}
        }
        return true;	
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
//    	}
//	}
}