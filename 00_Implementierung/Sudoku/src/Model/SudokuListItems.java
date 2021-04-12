package Model;

public class SudokuListItems {

	private String name;
	private String sudokuPath;
	
	public SudokuListItems(String name, String sudokuPath) {
		super();
		this.name = name;
		this.sudokuPath = sudokuPath;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSudokuPath() {
		return sudokuPath;
	}
	public void setSudokuPath(String sudokuPath) {
		this.sudokuPath = sudokuPath;
	}
	
	public String toString() {
		return this.name;
	}
}