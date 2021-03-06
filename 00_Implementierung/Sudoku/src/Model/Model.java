package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {

	private String link;
	
	public List<SudokuListItems> loadSudokus(int form, int level) {
		
		link = "Sudokus";
		
		if(form == 0)
			link += "\\SimpleSudoku_neu";
		else if(form == 1)
			link += "\\FreiformSudoku_neu";
		else
			link += "\\NinjaSudoku_neu";
	
		
		if(level == 0)
			link += "\\Anfaenger";
		else if(level == 1)
			link += "\\Fortgeschritten";
		else
			link += "\\Profi";
		
		
		if(form == 0)
			return loadSimpleSudokus();
		else if(form == 1)
			return loadFFSudokus();
		else
			return loadNinjaSudokus();
	}
	
	
	public List<SudokuListItems> loadSimpleSudokus() {

		List<SudokuListItems> list = new ArrayList<>();

		File f = new File(link);

		if (f.isDirectory()) {

			File[] arr = f.listFiles();

			for (File f1 : arr) {

				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");

				list.add(new SudokuListItems(arrrr[0], link + "\\" + sudokuPath));
			}
		}

		return list;
	}

	public List<SudokuListItems> loadFFSudokus() {

		List<SudokuListItems> list = new ArrayList<>();

		File f = new File(link);

		if (f.isDirectory()) {

			File[] arr = f.listFiles();

			for (File f1 : arr) {

				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");

				list.add(new SudokuListItems(arrrr[0], link + "\\" + sudokuPath));
			}
		}

		return list;
	}
	
	public List<SudokuListItems> loadNinjaSudokus(){
		
		List<SudokuListItems> list = new ArrayList<>();

		File f = new File(link);

		if (f.isDirectory()) {

			File[] arr = f.listFiles();

			for (File f1 : arr) {

				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");

				list.add(new SudokuListItems(arrrr[0], link + "\\" + sudokuPath));
			}
		}
		
		return list;
	}
}
