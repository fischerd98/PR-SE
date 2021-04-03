package View;

import javax.swing.JButton;

public class SudokuButton extends JButton {

	private int x;
	private int y;

	public SudokuButton() {
		super();
	}

	public SudokuButton(String text, int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}