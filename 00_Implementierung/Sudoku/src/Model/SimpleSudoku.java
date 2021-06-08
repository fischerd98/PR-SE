package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SimpleSudoku extends Sudoku {

	private int[][] game;
	private int[][] start;

	private SudokuListItems sli;

	private List<int[]> hints;
	
	private int countHints;
	
	public SimpleSudoku(SudokuListItems sli) {

		countHints = 0;
		hints = new ArrayList<int[]>();
		this.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	public SimpleSudoku() {
		
		hints = new ArrayList<int[]>();
		game = new int[9][9];
		start = new int[][] { { 1, 2, 3, 4, 5, 6, 0, 0, 0 }, { 1, 0, 3, 4, 5, 6, 0, 0, 0 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0 }, { 1, 0, 3, 4, 5, 6, 0, 0, 2 }, { 1, 0, 3, 4, 5, 6, 0, 0, 0 },
				{ 1, 0, 3, 4, 5, 0, 0, 0, 0 }, { 1, 0, 3, 4, 0, 0, 0, 0, 0 }, { 1, 0, 3, 4, 0, 0, 0, 0, 0 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 2 } };

		// lösbar
		start = new int[][] { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
				{ 0, 7, 0, 0, 9, 0, 2, 0, 0 }, { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
				{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } };

//		// gelöst
//		start = new int[][] { { 8, 1, 2, 7, 5, 3, 6, 4, 9 }, { 9, 4, 3, 6, 8, 2, 1, 7, 5 },
//				{ 6, 7, 5, 4, 9, 1, 2, 8, 3 }, { 1, 5, 4, 2, 3, 7, 8, 9, 6 }, { 3, 6, 9, 8, 4, 5, 7, 2, 1 },
//				{ 2, 8, 7, 1, 6, 9, 5, 3, 4 }, { 5, 2, 1, 9, 7, 4, 3, 6, 8 }, { 4, 3, 8, 5, 2, 6, 9, 1, 7 },
//				{ 7, 9, 6, 3, 1, 8, 4, 5, 2 } };
	}

	public SimpleSudoku(int[][] arr) {
		this.start = arr;
	}

	public int[][] getGame() {
		return game;
	}

	public void setGame(int[][] game) {
		this.game = game;
	}

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

	public boolean checkComplete() {

		boolean found;

		// Array wird durchlaufen
		// wenn in jeder Zeile, Spalte und in jedem Kasten jede Zahl vorkommt muss es
		// stimmen

		// Zeilen
		for (int i = 0; i < start.length; i++) {

			found = false;

			for (int z = 1; z < 10; z++) {

				found = false;

				for (int y = 0; y < start[i].length; y++) {
					if (start[i][y] == z) {
						found = true;
					}
				}

				if (!found) {
					return false;
				}
			}
		}

		// Spalten
		for (int i = 0; i < start.length; i++) {

			found = false;

			for (int z = 1; z < 10; z++) {

				found = false;

				for (int y = 0; y < start[i].length; y++) {
					if (start[y][i] == z) {
						found = true;
					}
				}

				if (!found) {
					return false;
				}
			}
		}

		// Kasten
		for (int i = 0; i < 3; i++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 1; z < 10; z++) {

					found = false;

					for (int n = i * 3; n < (i + 1) * 3; n++) {
						for (int m = y * 3; m < (y + 1) * 3; m++) {
							found = true;
						}
					}

					if (!found)
						return false;
				}
			}
		}

		return true;
	}

	public void saveSudoku() {
		saveSudoku("SimpleSudoku\\angefangen\\" + sli.getName());
	}

	public void saveSudoku(String filename) {

		try (CSVWriter writer = new CSVWriter(new FileWriter(filename + ".csv"));) {

			writer.writeAll(toStringArr(start));
			writer.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readSudoku(String path) {

		List<List<String>> records = new ArrayList<List<String>>();

		try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
			String[] values = null;
			while ((values = csvReader.readNext()) != null) {
				records.add(Arrays.asList(values));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		start = toIntArray(records);
	}

	public int[][] toIntArray(List<List<String>> records) {

		int[][] arr = new int[records.size()][records.size()];

		for (int i = 0; i < records.size(); i++) {
			for (int y = 0; y < records.get(i).size(); y++) {
				arr[i][y] = Integer.parseInt(records.get(i).get(y));
				System.out.print(arr[i][y] + " ");
			}
			System.out.println();
		}

		return arr;
	}

//	public void readSudoku() {
//
//		List<List<String>> records = new ArrayList<List<String>>();
//
//		try (CSVReader csvReader = new CSVReader(new FileReader("D://output.csv"));) {
//			String[] values = null;
//			while ((values = csvReader.readNext()) != null) {
//				records.add(Arrays.asList(values));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		toIntArray(records);
//	}
//
//	public int[][] toIntArray(List<List<String>> records) {
//
//		int[][] arr = new int[records.size()][records.size()];
//
//		for (int i = 0; i < records.size(); i++) {
//			for (int y = 0; y < records.get(i).size(); y++) {
//				arr[i][y] = Integer.parseInt(records.get(i).get(y));
//			}
//		}
//
//		return arr;
//	}

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
//		if (solvable) {
//			return copiedArray[x][y];
//		} else {
//			return -1;
//		}
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
			if (numberIsValid(sudoku, x, y, i)) {
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
	public static boolean numberIsValid(int[][] sudoku, int x, int y, int value) {
		if (sudoku[y][x] != 0) {// Falls ein Feld schon belegt ist
			return (false);
		}
		int feld_x = (x / 3) * 3;
		int feld_y = (y / 3) * 3;
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[y][i] == value || sudoku[i][x] == value) {// Zeile und Spalte
				return (false);
			} else if (sudoku[feld_y + (i / 3)][feld_x + (i % 3)] == value) {
				return (false);
			}
		}

		return (true);
	}

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
