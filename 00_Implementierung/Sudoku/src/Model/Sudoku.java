package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public abstract class Sudoku {

	protected int[][] start;
	protected SudokuListItems sli;
	protected List<int[]> hints;
	protected int countHints;

	public int[][] getStart() {
		return start;
	}

	public void setStart(int[][] start) {
		this.start = start;
	}

	public void setField(int posX, int posY, int num) {
		start[posX][posY] = num;
	}

	public int getVal(int x, int y) {
		return start[x][y];
	}

	public List<int[]> getHints() {
		return hints;
	}

	public int getCountHints() {
		return countHints;
	}
	
	public int incrementCountHints() {
		countHints++;
		return countHints;
	}
	
	
	
	public abstract boolean checkVal(int posX, int posY, int num);

	public boolean check9SquareNumPossible(int posx, int posy, int val) {

		int startX = posx / 3;
		startX *= 3;
		int endX = startX + 3;

		int startY = posy / 3;
		startY *= 3;
		int endY = startY + 3;

		for (int i = startX; i < endX; i++) {
			for (int y = startY; y < endY; y++) {
				if (this.start[i][y] == val)
					return false;
			}
		}

		return true;
	}

	public abstract boolean checkComplete();

	public void saveSudoku() {
		saveSudoku(sli.getSudokuPath());
	}

	public void saveNewSudoku(String name) {
		saveSudoku(name);
	}
	
	public void saveSudoku(String filename) {

		try (CSVWriter writer = new CSVWriter(new FileWriter(filename));) {

			writer.writeAll(toStringArr(start));
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void readSudoku(String path);

	
	public int[][] toIntArray(List<List<String>> records) {

		int[][] arr = new int[records.size()][records.size()];

		for (int i = 0; i < records.size(); i++) {
			for (int y = 0; y < records.get(i).size(); y++) {
				arr[i][y] = Integer.parseInt(records.get(i).get(y).trim());
			}
		}

		return arr;
	}

	public Iterable<String[]> toStringArr(int[][] arr) {

		List<String[]> list = new ArrayList<>();
		String[] arrS;

		for (int i = 0; i < arr.length; i++) {

			arrS = new String[arr[i].length];

			for (int y = 0; y < arr[i].length; y++) {
				arrS[y] = arr[i][y] + "";
			}

			list.add(arrS);
		}

		return list;
	}

	public boolean solveSudokuNextStep(int x, int y) {

		int[][] copiedArray = new int[start.length][start[0].length];
		
		for (int i = 0; i < copiedArray.length; i++)
			copiedArray[i] = Arrays.copyOf(start[i], start[i].length);
				
		int[][] empty = getEmptyFields();

		boolean solvable = solveSudokuRec(copiedArray, empty, 0);
		
		start[x][y] = copiedArray[x][y];
 		
		int[] arr = new int[]{x, y};
		hints.add(arr);
		
		return solvable;
	}

	public boolean solveSudoku() {

		int[][] empty = getEmptyFields();

		boolean solvable = solveSudokuRec(start, empty, 0);

		if (solvable) {
			this.countHints += empty.length;
			return true;
		} else {
			return false;
		}
	}

	// Sudoku lösen
	public boolean solveSudokuRec(int[][] sudoku, int[][] list, int position) {
		if (position >= list.length) {// Wenn alle Elemente durch sind
			return (true);
		}
		int x = list[position][0];
		int y = list[position][1];

		for (int i = 1; i <= 9; i++) {
			if (numberIsValid(x, y, i)) {
				sudoku[y][x] = i;
				if (solveSudokuRec(sudoku, list, position + 1)) {
					return (true);
				}
				sudoku[y][x] = 0;
			}
		}
		return (false);
	}

	// prüft, ob eine Zahl korrekt ist
	public abstract boolean numberIsValid(int x, int y, int value);

	// gibt alle leeren Felder zurück
	public int[][] getEmptyFields() {

		int[][] list = new int[0][2];

		for (int i = 0; i < start.length; i++) {
			for (int j = 0; j < start[i].length; j++) {
				if (start[i][j] == 0) {
					int[][] temp = new int[list.length + 1][2];
					for (int k = 0; k < list.length; k++)
						temp[k] = list[k];
					temp[temp.length - 1][0] = j;// j = x
					temp[temp.length - 1][1] = i;// i = y
					list = temp;
				}
			}
		}

		return (list);
	}
}
