package sudoku;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GridPanel extends JPanel{

	/*
	 * Define the number of rows and columns for the grid
	 * Create a list for each square
	 * Define a variable to store which square is selected
	 */
	private int columnCount = 9;
	private int rowCount = 9;
	private List<Rectangle> cells;
	private Point selectedCell;
	
	public int[][] theNumbers = new int[9][9];
	public int numberEntered = 0;
	
	public GridPanel() {

		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				theNumbers[i][j]=0;
			}
		}
		
		cells = new ArrayList<>(columnCount * rowCount);		// Create an array list for the cells
		MouseAdapter mouseHandler;								// Create mouse listener for the mouseMove
		MouseAdapter mouseHandler2;
		mouseHandler2 = new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				
				// Click and get the number
				int col = (int)(e.getX()/(400.0/9));
				int row = (int)(e.getY()/(400.0/9));
				
				
				JFrame f = new JFrame("Enter");
				f.setSize(300, 100);
				JPanel jp = new JPanel();
				jp.setVisible(true);
				JLabel jl = new JLabel("Enter a number: ");
				JTextField jt = new JTextField(2);
				jp.add(jl);
				jp.add(jt);
				
				JButton jb = new JButton("Submit");
				jb.addActionListener(new ActionListener(){
					@Override 
					public void actionPerformed(ActionEvent e){
						numberEntered = Integer.parseInt(jt.getText());
						//System.out.println(numberEntered);
						theNumbers[row][col] = numberEntered;
						f.dispose();
						
						invalidate();
						repaint();
					}
					
				});
				
				jp.add(jb);
				
				f.add(jp);
				f.setLocationRelativeTo(null);
				f.setVisible(true);		
				
			}};
		mouseHandler = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point point = e.getPoint();						

				int width = getWidth();
				int height = getHeight();

				int cellWidth = width / columnCount;
				int cellHeight = height / rowCount;

				int xOffset = (width - (columnCount * cellWidth)) / 2;
				int yOffset = (height - (rowCount * cellHeight)) / 2;

				selectedCell = null;
				if (e.getX() >= xOffset && e.getY() >= yOffset) {

					int column = (e.getX() - xOffset) / cellWidth;
					int row = (e.getY() - yOffset) / cellHeight;

					if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
						selectedCell = new Point(column, row);
					}

				}
				repaint();

			}

		};
		addMouseMotionListener(mouseHandler);
		addMouseListener(mouseHandler2);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}


	@Override
	public void invalidate() {
		cells.clear();
		selectedCell = null;
		super.invalidate();
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		int width = getWidth();
		int height = getHeight();

		int cellWidth = width / columnCount;
		int cellHeight = height / rowCount;

		int xOffset = (width - (columnCount * cellWidth)) / 2;
		int yOffset = (height - (rowCount * cellHeight)) / 2;

		if (cells.isEmpty()) {
			for (int row = 0; row < rowCount; row++) {
				for (int col = 0; col < columnCount; col++) {
					Rectangle cell = new Rectangle(
							xOffset + (col * cellWidth),
							yOffset + (row * cellHeight),
							cellWidth,
							cellHeight);
					
					//System.out.println("row&col: "+row+" "+col);
					//System.out.println(theNumbers[row][col]);
					
					/*
					if(theNumbers[row][col]!=0){
						g2d.drawString("Q",100,100);
						//g2d.drawString(""+theNumbers[row][col], (row-1)*(400/9)+400/9/2, (col-1)*(400/9)+400/9/2);
					}
					*/
					
					cells.add(cell);
				}
			}
		}

		if (selectedCell != null) {
			
			int index = selectedCell.x + (selectedCell.y * columnCount);
			Rectangle cell = cells.get(index);
			g2d.setColor(Color.BLUE);
			g2d.fill(cell);

		}

		g2d.setColor(Color.GRAY);
		for (Rectangle cell : cells) {
			g2d.draw(cell);
		}


		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(theNumbers[i][j]!=0){
					g2d.drawString(""+theNumbers[i][j], (int)((j)*(400.0/9)+400.0/9/2), (int)((i)*(400.0/9)+400.0/9/2));
				}
			}
		}
		
		g2d.dispose();
		
		
		
	}

	public void setNumber(int row, int col, int num){
		theNumbers[row][col] = num;
	}

}
