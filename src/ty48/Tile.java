package ty48;

import java.awt.*;

class Tile {
	
	private int value;
	private int x, y;
	private double screenX, screenY;
	private double vx = 0;
	private double vy = 0;
	private Color color;
	private boolean justCollided = false;
	
	Tile(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		screenX = x;
		screenY = y;
		color = Colors.getColor(value);
	}
	
	Tile(int x, int y) {
		if (Math.random() < .9) {
			value = 2;
		} else {
			value = 4;
		}
		this.x = x;
		this.y = y;
		screenX = x;
		screenY = y;
		color = Colors.getColor(value);
	}
	
	void moveLeft() {
		x--;
	}
	
	void moveRight() {
		x++;
	}
	
	void moveDown() {
		y++;
	}
	
	void moveUp() {
		y--;
	}
	
	boolean canCollide(Tile other) {
		return other.value == value && !justCollided && !other.justCollided;
	}
	
	void collide(Tile other) {
		value = value + other.value;
		color = Colors.getColor(value);
		justCollided = true;
	}
	
	void didntJustCollide() {
		justCollided = false;
	}
	
	int getValue() {
		return value;
	}
	
	void paint(Graphics g) {
		
		g.setColor(color);
		g.fillRect(Main.scale * x, Main.scale * y, Main.scale, Main.scale);
		g.setColor(new Color(50, 50, 50));
		g.drawRect(Main.scale * x, Main.scale * y, Main.scale, Main.scale);
		g.setFont(new Font("Verdana", Font.BOLD, Main.scale / 2));
		
		if (value < 9) {
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(value), x * Main.scale + 5 * Main.scale / 16, y * Main.scale + 11 * Main.scale / 16);
		} else if (value < 65) {
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + 2 * Main.scale / 16, y * Main.scale + 11 * Main.scale / 16);
		} else if (value < 513) {
			g.setFont(new Font("Verdana", Font.BOLD, (int) (Main.scale / 2.5)));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + Main.scale / 16, y * Main.scale + 11 * Main.scale / 16);
		} else if (value < 8193) {
			g.setFont(new Font("Verdana", Font.BOLD, Main.scale / 3));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + Main.scale / 16, y * Main.scale + 11 * Main.scale / 16);
		} else if (value < 65537) {
			g.setFont(new Font("Verdana", Font.BOLD, Main.scale / 4));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + Main.scale / 16, y * Main.scale + 10 * Main.scale / 16);
		} else if (value < 524289) {
			g.setFont(new Font("Verdana", Font.BOLD, Main.scale / 5));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + Main.scale / 16, y * Main.scale + 10 * Main.scale / 16);
		} else if (value < 8388609) {
			g.setFont(new Font("Verdana", Font.BOLD, Main.scale / 6));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(value), x * Main.scale + Main.scale / 16, y * Main.scale + 10 * Main.scale / 16);
		}
	}
}
