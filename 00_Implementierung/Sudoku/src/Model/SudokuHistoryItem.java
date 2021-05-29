package Model;

public class SudokuHistoryItem {

	private int x;
	private int y;
	private int vBefore;
	private int vAfter;

	public SudokuHistoryItem(int x, int y, int vAfter, int vBefore) {
		super();
		this.x = x;
		this.y = y;
		this.vAfter = vAfter;
		this.vBefore = vBefore;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getvBefore() {
		return vBefore;
	}
	public int getvAfter() {
		return vAfter;
	}
}