package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {

	public Model() {
		// loadSimpleSudokus();
	}

	public List<SudokuListItems> loadSimpleSudokus() {

		List<SudokuListItems> list = new ArrayList<>();

		File f = new File("Sudokus\\SimpleSudoku\\Level1");

		if (f.isDirectory()) {

			File[] arr = f.listFiles();

			for (File f1 : arr) {

				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");

				list.add(new SudokuListItems(arrrr[0], "Sudokus\\SimpleSudoku\\Level1\\" + sudokuPath));
			}
		}

		return list;
	}

	public List<SudokuListItems> loadFFSudokus() {

		List<SudokuListItems> list = new ArrayList<>();

		File f = new File("Sudokus\\FreiformSudoku\\Level1");

		if (f.isDirectory()) {

			File[] arr = f.listFiles();

			for (File f1 : arr) {

				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");

				list.add(new SudokuListItems(arrrr[0], "Sudokus\\FreiformSudoku\\Level1\\" + sudokuPath));
			}
		}

		return list;
	}
}
