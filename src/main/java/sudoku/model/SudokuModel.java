package sudoku.model;


public class SudokuModel {
	
	//private SudokuTemplate template = new SudokuTemplate();
	private int[][] sudokuPuzzle;

	public SudokuModel(int sudokuSize){
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
}
