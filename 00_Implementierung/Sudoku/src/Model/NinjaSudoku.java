package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class NinjaSudoku extends Sudoku {

	public NinjaSudoku(SudokuListItems sli) {

		countHints = 0;
		hints = new ArrayList<int[]>();
		this.sli = sli;

		readSudoku(sli.getSudokuPath());
	}

	// Konstruktor zu Testzwecken
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

	public boolean checkComplete() {
		// noch nicht implementiert
		return false;
	}


	public void saveSudoku(String filename) {

		try (CSVWriter writer = new CSVWriter(new FileWriter(filename));) {

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

	// prüft, ob eine Zahl korrekt ist
	public boolean numberIsValid(int x, int y, int value) {

		if (!this.checkColumn(y, x, value)) {
			return false;
		}
		if (!this.checkRow(y, x, value)) {
			return false;
		}

		return check9SquareNumPossible(y, x, value);
	}
}
