package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class NinjaSudoku extends Sudoku {

	private int[][] start;

	private SudokuListItems sli;

	private List<int[]> hints;

	private int countHints;

	public NinjaSudoku(SudokuListItems sli) {

		countHints = 0;
		hints = new ArrayList<int[]>();
		this.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	public NinjaSudoku() {

		hints = new ArrayList<int[]>();
		start = new int[][] { { 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 },
				{ 1, 2, 3, 4, 5, 6, 0, 0, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 1, 1, 1 } };

		start = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
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

		getFieldNums(posX, posY);

		if (!checkColumn(posX, posY, num)) {
			return false;
		}
		if (!checkRow(posX, posY, num)) {
			return false;
		}

		return check9SquareNumPossible(posX, posY, num);
	}

	// 1 - 2
	// - 3 -
	// 4 - 5
	public List<Integer> getFieldNums(int posX, int posY) {

		ArrayList<Integer> fields = new ArrayList<>();

		if (posX <= 5) {

			if (posY <= 8) {
				fields.add(1);
			} else {
				fields.add(2);
			}

		} else if (posX >= 6 && posX <= 8) {

			if (posY <= 5) {
				fields.add(1);
			} else if (posY <= 8) {
				fields.add(1);
				fields.add(3);
			} else if (posY <= 11) {
				fields.add(3);
			} else if (posY <= 14) {
				fields.add(3);
				fields.add(2);
			} else {
				fields.add(2);
			}

		} else if (posX <= 11) {
			fields.add(3);
		} else if (posX >= 12 && posX <= 14) {

			if (posY <= 5) {
				fields.add(4);
			} else if (posY <= 8) {
				fields.add(4);
				fields.add(3);
			} else if (posY <= 11) {
				fields.add(3);
			} else if (posY <= 14) {
				fields.add(3);
				fields.add(5);
			} else {
				fields.add(5);
			}
		} else if (posX >= 15) {
			if (posY <= 8) {
				fields.add(4);
			} else {
				fields.add(5);
			}
		}

		return fields;
	}

	public boolean checkRow(int posX, int posY, int num) {

		ArrayList<Integer> fields = (ArrayList<Integer>) getFieldNums(posX, posY);

		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i) == 1) {

				for (int y = 0; y <= 8; y++) {
					if (start[posX][y] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 2) {

				for (int y = 12; y <= 20; y++) {
					if (start[posX][y] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 3) {

				for (int y = 6; y <= 14; y++) {
					if (start[posX][y] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 4) {

				for (int y = 0; y <= 8; y++) {
					if (start[posX][y] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 5) {

				for (int y = 12; y <= 20; y++) {
					if (start[posX][y] == num) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public boolean checkColumn(int posX, int posY, int num) {

		ArrayList<Integer> fields = (ArrayList<Integer>) getFieldNums(posX, posY);

		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i) == 1) {

				System.out.println("Test");

				for (int y = 0; y <= 8; y++) {
					if (start[y][posY] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 2) {

				for (int y = 0; y <= 8; y++) {
					if (start[y][posY] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 3) {

				for (int y = 6; y <= 14; y++) {
					if (start[y][posY] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 4) {

				for (int y = 12; y <= 20; y++) {
					if (start[y][posY] == num) {
						return false;
					}
				}
			} else if (fields.get(i) == 5) {

				for (int y = 12; y <= 20; y++) {
					if (start[y][posY] == num) {
						return false;
					}
				}
			}
		}

		return true;
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

//	public boolean checkComplete() {
//
//		boolean found;
//
//		// Array wird durchlaufen
//		// wenn in jeder Zeile, Spalte und in jedem Kasten jede Zahl vorkommt muss es
//		// stimmen
//
//		// Zeilen
//		for (int i = 0; i < start.length; i++) {
//
//			found = false;
//
//			for (int z = 1; z < 10; z++) {
//
//				found = false;
//
//				for (int y = 0; y < start[i].length; y++) {
//					if (start[i][y] == z) {
//						found = true;
//					}
//				}
//
//				if (!found) {
//					return false;
//				}
//			}
//		}
//
//		// Spalten
//		for (int i = 0; i < start.length; i++) {
//
//			found = false;
//
//			for (int z = 1; z < 10; z++) {
//
//				found = false;
//
//				for (int y = 0; y < start[i].length; y++) {
//					if (start[y][i] == z) {
//						found = true;
//					}
//				}
//
//				if (!found) {
//					return false;
//				}
//			}
//		}
//
//		// Kasten
//		for (int i = 0; i < 3; i++) {
//			for (int y = 0; y < 3; y++) {
//				for (int z = 1; z < 10; z++) {
//
//					found = false;
//
//					for (int n = i * 3; n < (i + 1) * 3; n++) {
//						for (int m = y * 3; m < (y + 1) * 3; m++) {
//							found = true;
//						}
//					}
//
//					if (!found)
//						return false;
//				}
//			}
//		}
//
//		return true;
//	}

	public void saveSudoku() {
		// saveSudoku("SimpleSudoku\\angefangen\\" + sli.getName());
	}

	public void saveSudoku(String filename) {

//		try (CSVWriter writer = new CSVWriter(new FileWriter(filename + ".csv"));) {
//
//			writer.writeAll(toStringArr(start));
//			writer.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void readSudoku(String path) {

//		List<List<String>> records = new ArrayList<List<String>>();
//
//		try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
//			String[] values = null;
//			while ((values = csvReader.readNext()) != null) {
//				records.add(Arrays.asList(values));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		start = toIntArray(records);
	}

	public int[][] toIntArray(List<List<String>> records) {

		int[][] arr = new int[records.size()][records.size()];

//		for (int i = 0; i < records.size(); i++) {
//			for (int y = 0; y < records.get(i).size(); y++) {
//				arr[i][y] = Integer.parseInt(records.get(i).get(y).trim());
//				System.out.print(arr[i][y] + " ");
//			}
//			System.out.println();
//		}

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
//		String[] arrS;
//
//		for (int i = 0; i < arr.length; i++) {
//
//			arrS = new String[arr[i].length];
//
//			for (int y = 0; y < arr[i].length; y++) {
//				arrS[y] = arr[i][y] + "";
//			}
//
//			list.add(arrS);
//		}

		return list;
	}

	public boolean solveSudokuNextStep(int x, int y) {

//		int[][] copiedArray = new int[start.length][start[0].length];
//		
//		for (int i = 0; i < copiedArray.length; i++)
//			copiedArray[i] = Arrays.copyOf(start[i], start[i].length);
//				
//		int[][] empty = getEmptyFields();
//
//		boolean solvable = solveSudokuRec(copiedArray, empty, 0);
//		
//		start[x][y] = copiedArray[x][y];
// 		
//		int[] arr = new int[]{x, y};
//		hints.add(arr);
//		
//		return solvable;

		return false;
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

	// Sudoku l�sen
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
		return false;
	}

	// pr�ft, ob eine Zahl korrekt ist
	public boolean numberIsValid(int x, int y, int value) {

//		if (sudoku[y][x] != 0) {// Falls ein Feld schon belegt ist
//			return (false);
//		}

		if (!this.checkColumn(y, x, value)) {
			return false;
		}
		if (!this.checkRow(y, x, value)) {
			return false;
		}

		return check9SquareNumPossible(y, x, value);
	}

	// gibt alle leeren Felder zur�ck
	public int[][] getEmptyFields() {

		int[][] list = new int[0][2];

		for (int i = 0; i < start.length; i++) {
			for (int j = 0; j < start[i].length; j++) {
				if (!((i >= 9 && i <= 11 && (j <= 5 || j >= 15)) || (j >= 9 && j <= 11 && (i <= 5 || i >= 15)))) {
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
		}

		return (list);
	}
}
