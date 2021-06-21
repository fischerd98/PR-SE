package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class SimpleSudoku extends Sudoku {

	
	public SimpleSudoku(SudokuListItems sli) {
		super();
		
		countHints = 0;
		hints = new ArrayList<int[]>();
		super.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	// Konstruktor zu Testzwecken
	public SimpleSudoku() {
		
		hints = new ArrayList<int[]>();

		// lösbar
		start = new int[][] { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
				{ 0, 7, 0, 0, 9, 0, 2, 0, 0 }, { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
				{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } };
	}

	public SimpleSudoku(int[][] arr) {
		this.start = arr;
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

	// prüft, ob eine Zahl korrekt ist
	public boolean numberIsValid(int x, int y, int value) {
		if (start[y][x] != 0) {// Falls ein Feld schon belegt ist
			return (false);
		}
		int feld_x = (x / 3) * 3;
		int feld_y = (y / 3) * 3;
		for (int i = 0; i < start.length; i++) {
			if (start[y][i] == value || start[i][x] == value) {// Zeile und Spalte
				return (false);
			} else if (start[feld_y + (i / 3)][feld_x + (i % 3)] == value) {
				return (false);
			}
		}

		return (true);
	}
}
