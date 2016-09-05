package sudoku;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

	public GridPanel() {

		cells = new ArrayList<>(columnCount * rowCount);		// Create an array list for the cells
		MouseAdapter mouseHandler;								// Create mouse listener for the mouseMove

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
				
				// If the mouse is over this cell, call the method to change cell color
				if (e.getX() >= xOffset && e.getY() >= yOffset) {	

					int column = (e.getX() - xOffset) / cellWidth;
					int row = (e.getY() - yOffset) / cellHeight;

					if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
						selectedCell = new Point(column, row);
					}

				}
				repaint();

			}

			@Override
			public void mouseClicked(MouseEvent e){
				
				
				
			}

		};
		addMouseMotionListener(mouseHandler);

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

		g2d.dispose();
	}
}
