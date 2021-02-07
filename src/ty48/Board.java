package ty48;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Board {
	
	private int sizeX, sizeY;
	private Tile[][] tiles;
	
	Board(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		tiles = new Tile[sizeX][sizeY];
		fill();
	}
	
	private void fill() {
		for (int i = 0; i < 2; i++) {
			int randomX = (int) (Math.random() * sizeX);
			int randomY = (int) (Math.random() * sizeY);
			tiles[randomX][randomY] = new Tile(2, randomX, randomY);
		}
	}
	
	boolean isOver() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (tiles[i][j] == null || canMoveRight() || canMoveLeft() || canMoveDown() || canMoveUp()) {
					return false;
				}
			}
		}
		System.out.println("over");
		return true;
	}
	
	private boolean isFull() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (tiles[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	void moveLeft() {
		if (!isOver()) {
			while (canMoveLeft()) {
				for (int i = 1; i < sizeX; i++) {
					for (int j = 0; j < sizeY; j++) {
						if (tiles[i][j] != null && tiles[i - 1][j] == null) {
							tiles[i][j].moveLeft();
							tiles[i - 1][j] = tiles[i][j];
							tiles[i][j] = null;
						} else if (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i - 1][j])) {
							tiles[i - 1][j].collide(tiles[i][j]);
							tiles[i][j] = null;
						}
					}
				}
			}
			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					if (tiles[i][j] != null) {
						tiles[i][j].didntJustCollide();
					}
				}
			}
			addAnotherTile();
		}
	}
	
	private boolean canMoveLeft() {
		for (int i = 1; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (tiles[i][j] != null && tiles[i - 1][j] == null || (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i - 1][j]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	void moveRight() {
		if (!isOver()) {
			while (canMoveRight()) {
				for (int i = sizeX - 2; i >= 0; i--) {
					for (int j = 0; j < sizeY; j++) {
						if (tiles[i][j] != null && tiles[i + 1][j] == null) {
							tiles[i][j].moveRight();
							tiles[i + 1][j] = tiles[i][j];
							tiles[i][j] = null;
						} else if (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i + 1][j])) {
							tiles[i + 1][j].collide(tiles[i][j]);
							tiles[i][j] = null;
						}
					}
				}
			}
			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					if (tiles[i][j] != null) {
						tiles[i][j].didntJustCollide();
					}
				}
			}
			addAnotherTile();
		}
	}
	
	private boolean canMoveRight() {
		for (int i = 0; i < sizeX - 1; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (tiles[i][j] != null && tiles[i + 1][j] == null || (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i + 1][j]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	void moveDown() {
		if (!isOver()) {
			while (canMoveDown()) {
				for (int i = 0; i < sizeX; i++) {
					for (int j = sizeY - 2; j >= 0; j--) {
						if (tiles[i][j] != null && tiles[i][j + 1] == null) {
							tiles[i][j].moveDown();
							tiles[i][j + 1] = tiles[i][j];
							tiles[i][j] = null;
						} else if (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i][j + 1])) {
							tiles[i][j + 1].collide(tiles[i][j]);
							tiles[i][j] = null;
						}
					}
				}
			}
			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					if (tiles[i][j] != null) {
						tiles[i][j].didntJustCollide();
					}
				}
			}
			addAnotherTile();
		}
	}
	
	private boolean canMoveDown() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY - 1; j++) {
				if (tiles[i][j] != null && tiles[i][j + 1] == null || (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i][j + 1]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	void moveUp() {
		if (!isOver()) {
			while (canMoveUp()) {
				for (int i = 0; i < sizeX; i++) {
					for (int j = 1; j < sizeY; j++) {
						if (tiles[i][j] != null && tiles[i][j - 1] == null) {
							tiles[i][j].moveUp();
							tiles[i][j - 1] = tiles[i][j];
							tiles[i][j] = null;
						} else if (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i][j - 1])) {
							tiles[i][j - 1].collide(tiles[i][j]);
							tiles[i][j] = null;
						}
					}
				}
			}
			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					if (tiles[i][j] != null) {
						tiles[i][j].didntJustCollide();
					}
				}
			}
			addAnotherTile();
		}
	}
	
	private boolean canMoveUp() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 1; j < sizeY; j++) {
				if (tiles[i][j] != null && tiles[i][j - 1] == null || (tiles[i][j] != null && tiles[i][j].canCollide(tiles[i][j - 1]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	void doRandomMove() {
		int choice = (int)(Math.random()*4);
		
		switch(choice) {
			case 0:
				moveUp();
				break;
			case 1:
				moveDown();
				break;
			case 2:
				moveRight();
				break;
			case 3:
				moveLeft();
				break;
			default:
				break;
		}
	}
	
	void doOkayMove() {
		if (canMoveDown()) {
			moveDown();
		} else if (canMoveLeft() || canMoveRight()) {
			int choice = (int)(Math.random()*2);
			if (choice == 0) {
				moveLeft();
			} else {
				moveRight();
			}
		} else if (canMoveUp()) {
			moveUp();
		}
	}
	
	private void addAnotherTile() {
		int randomX, randomY;
		if (!isFull()) {
			do {
				randomX = (int) (Math.random() * sizeX);
				randomY = (int) (Math.random() * sizeY);
			} while (tiles[randomX][randomY] != null);
			
			tiles[randomX][randomY] = new Tile(randomX, randomY);
		}
	}
	
	void paint(Graphics g) {
		for (Tile[] tileRow : tiles) {
			for (Tile tile : tileRow) {
				if (tile != null) {
					tile.paint(g);
				}
			}
		}
	}
	
	void saveState() throws FileNotFoundException {
		PrintStream stream = new PrintStream(new File("savedstate.txt"));
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (tiles[i][j] == null) {
					stream.print("null ");
				} else {
					stream.print(tiles[i][j].getValue() + " ");
				}
			}
			stream.println();
		}
	}
	
	void loadState() throws FileNotFoundException {
		int[][] state = new int[sizeX][sizeY];
		
		Scanner scanner = new Scanner(new File("savedstate.txt"));
		int x = 0;
		int y = 0;
		while (x < sizeX) {
			Scanner linescan = new Scanner(scanner.nextLine());
			
			while (y < sizeY) {
				String value = linescan.next();
				if (value.equals("null")) {
					state[x][y] = 0;
				} else {
					state[x][y] = Integer.valueOf(value);
				}
				y++;
			}
			x++;
			y = 0;
		}
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				if (state[i][j] != 0) {
					tiles[i][j] = new Tile(state[i][j], i, j);
				} else {
					tiles[i][j] = null;
				}
			}
		}
	}
}
