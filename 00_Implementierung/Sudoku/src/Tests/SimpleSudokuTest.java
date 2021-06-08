
package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Model.SimpleSudoku;
import View.SudokuWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class SimpleSudokuTest {

	SimpleSudoku s;
	int[][] start;

	@BeforeEach
	public void setUp() throws Exception {
		start = new int[][] {
			  { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
			  { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
			  { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
			  { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
			  { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
			  { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
			  { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
			  { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
			  { 0, 9, 0, 0, 0, 0, 4, 0, 0 } 
			};
			s = new SimpleSudoku();
			s.setGame(start);
    }

	@AfterEach
	public void clean() throws Exception {
		s.setGame(start);
	}

	@Test
	public void CheckValue() {

		assertFalse(s.checkVal(4,1,9));
		assertFalse(s.checkVal(1,4,9));
		assertTrue(s.checkVal(8,5,1));
		assertTrue(s.checkVal(0,8,4));
		assertTrue(s.checkVal(3,3,8));
		assertFalse(s.checkVal(0,8,8));
		assertFalse(s.checkVal(1,1,6));
		assertFalse(s.checkVal(5,2,5));
		assertFalse(s.checkVal(0,7,2));
		assertFalse(s.checkVal(3,6,3));
		assertFalse(s.checkVal(5,4,7));


	}

}

