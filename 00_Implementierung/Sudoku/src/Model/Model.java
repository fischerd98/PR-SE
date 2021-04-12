package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {

	
	
	public Model() {
		//loadSimpleSudokus();
	}
	
	public List<SudokuListItems> loadSimpleSudokus() {
		
		List<SudokuListItems> list = new ArrayList<>();
		
		File f = new File("SimpleSudoku\\angefangen");
		
		if(f.isDirectory()) {
			
			File[] arr = f.listFiles();
			
			for(File f1 : arr) {	
				
				String sudokuPath = f1.getName();
				String[] arrrr = sudokuPath.split("\\.");
				
				list.add(new SudokuListItems(arrrr[0], "SimpleSudoku\\angefangen\\" + sudokuPath));
			}
		}
		
		return list;
	}
}
