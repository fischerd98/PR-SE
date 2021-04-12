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

	public SimpleSudoku(SudokuListItems sli) {
		
		this.sli = sli;
		
		readSudoku(sli.getSudokuPath());
	}
	
	public SimpleSudoku() {
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

	public boolean checkVal(int posX, int posY, int num) {

		// Spalte
		for (int i = 0; i < 9; i++) {
			if (start[i][posX] == num) {
				return false;
			}
		}

		// Zeile
		for (int i = 0; i < 9; i++) {
			if (start[posY][i] == num) {
				return false;
			}
		}

		int x;
		int y;

		// 9er Kasten
		if (posX >= 0 && posX <= 2) {
			x = 0;
		} else if (posX >= 3 && posX <= 5) {
			x = 1;
		} else {
			x = 2;
		}

		if (posY >= 0 && posY <= 2) {
			y = 0;
		} else if (posY >= 3 && posY <= 5) {
			y = 1;
		} else {
			y = 2;
		}

		for (int i = x * 3; i < (x + 1) * 3; i++) {
			for (int k = y * 3; k < (y + 1) * 3; k++) {
				if (start[i][k] == num)
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
			}
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
}
