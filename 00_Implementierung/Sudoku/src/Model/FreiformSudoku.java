package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FreiformSudoku extends Sudoku {

	private int[][] fieldConstruction;
	private int[][] start;

	private List<int[]> hints;

	private SudokuListItems sli;

	private int countHints;

	public FreiformSudoku(SudokuListItems sli) {

		System.out.println("kjsklfsalfs");

		countHints = 0;
		hints = new ArrayList<int[]>();
		this.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	public FreiformSudoku() {

		hints = new ArrayList<int[]>();

		fieldConstruction = new int[][] { { 1, 1, 1, 2, 2, 3, 3, 3, 3 }, { 1, 1, 2, 2, 2, 3, 3, 3, 3 },
				{ 1, 1, 2, 2, 5, 4, 4, 4, 3 }, { 1, 1, 2, 2, 5, 4, 4, 4, 4 }, { 6, 6, 5, 5, 5, 5, 5, 4, 4 },
				{ 6, 6, 6, 6, 5, 8, 8, 9, 9 }, { 7, 6, 6, 6, 5, 8, 8, 9, 9 }, { 7, 7, 7, 7, 8, 8, 8, 9, 9 },
				{ 7, 7, 7, 7, 8, 8, 9, 9, 9 } };

		start = new int[][] { { 3, 1, 9, 7, 8, 2, 6, 4, 5 }, { 5, 2, 4, 9, 6, 8, 7, 1, 3 },
				{ 7, 8, 5, 2, 3, 4, 1, 6, 9 }, { 4, 6, 1, 3, 7, 9, 5, 2, 8 }, { 9, 4, 6, 8, 5, 1, 2, 3, 7 },
				{ 1, 7, 8, 6, 9, 3, 4, 5, 2 }, { 8, 3, 2, 5, 4, 6, 9, 7, 1 }, { 2, 5, 3, 4, 1, 7, 8, 9, 6 },
				{ 6, 9, 7, 1, 2, 5, 3, 8, 4 } };

//		start = new int[][] { { 1, 2, 3, 4, 5, 6, 0, 0, 0 }, { 1, 0, 3, 4, 5, 6, 0, 0, 0 },
//				{ 1, 2, 3, 4, 5, 6, 0, 0, 0 }, { 1, 0, 3, 4, 5, 6, 0, 0, 2 }, { 1, 0, 3, 4, 5, 6, 0, 0, 0 },
//				{ 1, 0, 3, 4, 5, 0, 0, 0, 0 }, { 1, 0, 3, 4, 0, 0, 0, 0, 0 }, { 1, 0, 3, 4, 0, 0, 0, 0, 0 },
//				{ 1, 2, 3, 4, 5, 6, 0, 0, 2 } };
//
//		// lösbar
//		start = new int[][] { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
//				{ 0, 7, 0, 0, 9, 0, 2, 0, 0 }, { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
//				{ 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
//				{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } };

//		// gelöst
//		start = new int[][] { { 8, 1, 2, 7, 5, 3, 6, 4, 9 }, { 9, 4, 3, 6, 8, 2, 1, 7, 5 },
//				{ 6, 7, 5, 4, 9, 1, 2, 8, 3 }, { 1, 5, 4, 2, 3, 7, 8, 9, 6 }, { 3, 6, 9, 8, 4, 5, 7, 2, 1 },
//				{ 2, 8, 7, 1, 6, 9, 5, 3, 4 }, { 5, 2, 1, 9, 7, 4, 3, 6, 8 }, { 4, 3, 8, 5, 2, 6, 9, 1, 7 },
//				{ 7, 9, 6, 3, 1, 8, 4, 5, 2 } };
	}

	public int[][] getFieldConstruction() {
		return fieldConstruction;
	}

	public void setFieldConstruction(int[][] fieldConstruction) {
		this.fieldConstruction = fieldConstruction;
	}

	public int[][] getStart() {
		return start;
	}

	public void setStart(int[][] start) {
		this.start = start;
	}

	public List<int[]> getHints() {
		return hints;
	}

	public void setHints(List<int[]> hints) {
		this.hints = hints;
	}

	public SudokuListItems getSli() {
		return sli;
	}

	public void setSli(SudokuListItems sli) {
		this.sli = sli;
	}

	public int getCountHints() {
		return countHints;
	}

	public void setCountHints(int countHints) {
		this.countHints = countHints;
	}

	public void setField(int posX, int posY, int num) {
		start[posX][posY] = num;
	}

	public int getVal(int x, int y) {
		return start[x][y];
	}
	
	public int incrementCountHints() {
		countHints++;
		return countHints;
	}

	public void readSudoku(String path) {

		System.out.println("Read");

		List<List<String>> records = new ArrayList<List<String>>();

		try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (records.size() == 19) {
			fieldConstruction = toIntArray(records.subList(0, 9));
			start = toIntArray(records.subList(10, 19));
		}

		System.out.println();

	}

	public int[][] toIntArray(List<List<String>> records) {

		int[][] arr = new int[9][9];

		for (int i = 0; i < records.size(); i++) {
			for (int y = 0; y < records.get(i).size(); y++) {
				arr[i][y] = Integer.parseInt(records.get(i).get(y).trim());
			}
		}

		return arr;
	}

	public boolean checkVal(int posX, int posY, int num) {

		// Spalte
		for (int i = 0; i < 9; i++) {
			if (start[i][posY] == num) {
				return false;
			}
		}

		// Zeile
		for (int i = 0; i < 9; i++) {
			if (start[posX][i] == num) {
				return false;
			}
		}

		return check9SquareNumPossible(posX, posY, num);
	}

	public boolean check9SquareNumPossible(int posx, int posy, int val) {

		int fieldColor = fieldConstruction[posx][posy];

		int count = 0;

		// falls das Feld bereits so belegt ist
		// sonst würde es zu oft gezählt
		if (start[posx][posy] == val)
			count--;

		for (int i = 0; i < start.length; i++) {
			for (int y = 0; y < start[i].length; y++) {
				if (fieldConstruction[i][y] == fieldColor && start[i][y] == val) {
					count++;
				}
			}
		}

		return count < 1;
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
			if (checkVal(y, x, i)) {
				sudoku[y][x] = i;
				if (solveSudokuRec(sudoku, list, position + 1)) {
					return (true);
				}
				sudoku[y][x] = 0;
			}
		}
		return (false);
	}

//	// prüft, ob eine Zahl korrekt ist
//	public static boolean numberIsValid(int[][] sudoku, int x, int y, int value) {
//		if (sudoku[y][x] != 0) {// Falls ein Feld schon belegt ist
//			return (false);
//		}
//		int feld_x = (x / 3) * 3;
//		int feld_y = (y / 3) * 3;
//		for (int i = 0; i < sudoku.length; i++) {
//			if (sudoku[y][i] == value || sudoku[i][x] == value) {// Zeile und Spalte
//				return (false);
//			} else if (sudoku[feld_y + (i / 3)][feld_x + (i % 3)] == value) {
//				return (false);
//			}
//		}
//
//		return (true);
//	}
	
	
	
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
	
	
	
	
	
	public boolean solveSudokuNextStep(int x, int y) {

		int[][] copiedArray = new int[start.length][start[0].length];
		
		for (int i = 0; i < copiedArray.length; i++)
			copiedArray[i] = Arrays.copyOf(start[i], start[i].length);
				
		int[][] empty = getEmptyFields();
		System.out.println(empty.length);

		boolean solvable = solveSudokuRec(copiedArray, empty, 0);
		
		start[x][y] = copiedArray[x][y];
 		
		int[] arr = new int[]{x, y};
		hints.add(arr);
		
		return solvable;

	}
	
	
	
	public void saveSudoku() {
		saveSudoku("Sudokus\\FreiformSudoku\\Level1\\" + sli.getName());
	}

	public void saveSudoku(String filename) {

		try (CSVWriter writer = new CSVWriter(new FileWriter(filename + ".csv"));) {

			writer.writeAll(toStringArr(start));
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Iterable<String[]> toStringArr(int[][] arr) {

		List<String[]> list = new ArrayList<>();
		String[] arrS;

		for (int i = 0; i < fieldConstruction.length; i++) {

			arrS = new String[fieldConstruction[i].length];

			for (int y = 0; y < fieldConstruction[i].length; y++) {
				arrS[y] = fieldConstruction[i][y] + "";
			}

			list.add(arrS);
		}
		
		list.add(new String[] {"\n"});

		for (int i = 0; i < arr.length; i++) {

			arrS = new String[arr[i].length];

			for (int y = 0; y < arr[i].length; y++) {
				arrS[y] = arr[i][y] + "";
			}

			list.add(arrS);
		}
		
		return list;
	}
}