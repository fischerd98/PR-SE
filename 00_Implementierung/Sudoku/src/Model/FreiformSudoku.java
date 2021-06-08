package Model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

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

//		System.out.println("Read");
//
//		int[][] arr = new int[records.size()][records.size()];
//
//		for (int i = 0; i < records.size(); i++) {
//			for (int y = 0; y < records.get(i).size(); y++) {
//
//				// arr[i][y] = Integer.parseInt(records.get(i).get(y).trim());
//			}
//			System.out.println();
//		}
//
//		return arr;

		int[][] arr = new int[9][9];

		for (int i = 0; i < records.size(); i++) {
			for (int y = 0; y < records.get(i).size(); y++) {
				arr[i][y] = Integer.parseInt(records.get(i).get(y).trim());
			}
		}

		return arr;
	}
}