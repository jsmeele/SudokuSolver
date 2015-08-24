package sudoku.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class SudokuPuzzleView {

	private static final Insets sixPixelInset = new Insets(6, 6, 6, 6);
	private static Integer sudokuSize;
	
	private JFrame sudokuFrame;
	private ArrayList<ArrayList<ArrayList<JFormattedTextField>>>	sudukoPuzzle;
	private JButton solveButton;
	
	public SudokuPuzzleView(int sudokuSize) {
		SudokuPuzzleView.sudokuSize = sudokuSize;
		initSudokuView();
	}
	
    private void initSudokuView() {
    	sudukoPuzzle = new ArrayList<ArrayList<ArrayList<JFormattedTextField>>>();
		sudokuFrame = new JFrame("Sudoku Solver");
		sudokuFrame.getContentPane().add(createSudokuView(), BorderLayout.CENTER);
		sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sudokuFrame.setMinimumSize(new Dimension(700, 350));
		sudokuFrame.setVisible(true);
		sudokuFrame.pack();
    }

	private JPanel createSudokuView(){							
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.add(createSudokuGrid(true));
		centerPanel.add(createSolveButton());
		centerPanel.add(createSudokuGrid(false));
		return centerPanel;
	}
	
	private JPanel createSudokuGrid(boolean editable){
		JPanel centerOuterPanelGrid = new JPanel(new GridLayout(sudokuSize, sudokuSize, 6, 6));
		sudukoPuzzle.add(new ArrayList<ArrayList<JFormattedTextField>>());							//new Index for Start puzzle (0) or Solved puzzle (1)
		int index = sudukoPuzzle.size() - 1;
        for (int i = 0; i < sudokuSize; i++) {
        	sudukoPuzzle.get(index).add(new ArrayList<JFormattedTextField>());						//new row
            for (int j = 0; j < sudokuSize; j++) {
            	sudukoPuzzle.get(index).get(i).add(createField(editable));							//new column
            	centerOuterPanelGrid.add(sudukoPuzzle.get(index).get(i).get(j));
            }
        }
        centerOuterPanelGrid.setBorder(new EmptyBorder(sixPixelInset));
		return centerOuterPanelGrid;
	}

    private JButton createSolveButton() {
        solveButton = new JButton("Solve");
        return solveButton;
    }

    private JFormattedTextField createField(boolean editable) {
    	JFormattedTextField field = new JFormattedTextField();
        try {
            field.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter(getMaskFormatString())));
        } catch (java.text.ParseException ex) {
        	System.err.println("formatter is bad: " + ex.getMessage());
        }
        field.setPreferredSize(new Dimension(30, 30));
        field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        field.setBorder(null);
       	field.setEditable(editable);
        field.setBackground(Color.WHITE); // Default is gray for non-editables, change to white for consistent TextFields
        return field;
    }
    
    private String getMaskFormatString() {
    	String format = "";
    	for (int i = 0; i < String.valueOf(sudokuSize).length(); i++) {
    		format = format + "#";
    	}
    	return format;
    }
   
    public JButton getSolveButton(){
        return solveButton;
    }
    
    public JFormattedTextField getField(int index, int row, int col){
        return sudukoPuzzle.get(index).get(row).get(col);
    }
}