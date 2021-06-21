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

	public FreiformSudoku(SudokuListItems sli) {

		countHints = 0;
		hints = new ArrayList<int[]>();
		super.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	// Konstruktor zu Testzwecken
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
	}

	public int[][] getFieldConstruction() {
		return fieldConstruction;
	}

	public void setFieldConstruction(int[][] fieldConstruction) {
		this.fieldConstruction = fieldConstruction;
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

		if (records.size() == 19) {
			fieldConstruction = toIntArray(records.subList(0, 9));
			start = toIntArray(records.subList(10, 19));
		}
	}

//	public boolean checkVal(int posX, int posY, int num) {
//
//		// Spalte
//		for (int i = 0; i < 9; i++) {
//			if (start[i][posY] == num) {
//				return false;
//			}
//		}
//
//		// Zeile
//		for (int i = 0; i < 9; i++) {
//			if (start[posX][i] == num) {
//				return false;
//			}
//		}
//
//		return check9SquareNumPossible(posX, posY, num);
//	}

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

		list.add(new String[] { "\n" });

		for (int i = 0; i < arr.length; i++) {

			arrS = new String[arr[i].length];

			for (int y = 0; y < arr[i].length; y++) {
				arrS[y] = arr[i][y] + "";
			}

			list.add(arrS);
		}

		return list;
	}

	@Override
	public boolean checkVal(int posX, int posY, int num) {

		// nocht nicht implementiert

		return false;
	}

	@Override
	public boolean checkComplete() {

		// noch nicht implementiert

		return false;
	}

	@Override
	public boolean numberIsValid(int x, int y, int value) {
		// nocht nicht implementiert
		return false;
	}
}