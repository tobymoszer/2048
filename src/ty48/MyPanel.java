package ty48;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

class MyPanel extends JPanel implements KeyListener {
	
	private Board board;
	private Timer timer = new Timer(0, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.isOver()) {
				timer.stop();
			}
			board.doOkayMove();
			repaint();
		}
	});
	
	MyPanel(Board board) {
		this.board = board;
		Font font = new Font("Verdana", Font.BOLD, Main.scale / 2);
		setFont(font);
		setBackground(new Color(50, 50, 50));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.paint(g);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			board.moveLeft();
		} else if (e.getKeyCode() == 39) {
			board.moveRight();
		} else if (e.getKeyCode() == 40) {
			board.moveDown();
		} else if (e.getKeyCode() == 38) {
			board.moveUp();
		} else if (e.getKeyCode() == 83) {
			try {
				board.saveState();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else if (e.getKeyCode() == 82) {
			try {
				board.loadState();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else {
			if (timer.isRunning()) {
				timer.stop();
			} else {
				timer.start();
			}
		}
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}
}
