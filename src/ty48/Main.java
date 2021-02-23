package ty48;

import javax.swing.*;

public class Main {
	
	private static final int sizeX = 10;
	private static final int sizeY = 10;
	static final int scale = 100;
	
	public static void main(String[] args) {
		Board board = new Board(sizeX, sizeY);
		
		JFrame frame = new JFrame("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(scale*sizeX + 17, scale*sizeY + 40);
		
		MyPanel panel = new MyPanel(board);
		
		frame.add(panel);
		frame.addKeyListener(panel);
		
		frame.setVisible(true);
		panel.repaint();
	}
}
