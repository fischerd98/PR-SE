package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.FreiformSudoku;
import Model.SimpleSudoku;

class FreiformSudokuTest {

	FreiformSudoku s;
	
	
	int [][] start = new int[][] { 
		{ 3, 1, 9, 7, 8, 2, 6, 4, 5 }, 
		{ 5, 2, 4, 9, 6, 8, 7, 1, 3 },
		{ 7, 8, 5, 2, 3, 4, 1, 6, 9 }, 
		{ 4, 6, 1, 3, 7, 9, 5, 2, 8 }, 
		{ 9, 4, 6, 8, 5, 1, 2, 3, 7 },
		{ 1, 7, 8, 6, 9, 3, 4, 5, 2 }, 
		{ 8, 3, 2, 5, 4, 6, 9, 7, 1 }, 
		{ 2, 5, 3, 4, 1, 7, 8, 0, 6 },
		{ 6, 9, 7, 1, 2, 5, 3, 8, 4 } };
		
	int [][] fieldConstruction = new int[][] { 
			{ 1, 1, 1, 2, 2, 3, 3, 3, 3 }, 
			{ 1, 1, 2, 2, 2, 3, 3, 3, 3 },
			{ 1, 1, 2, 2, 5, 4, 4, 4, 3 }, 
			{ 1, 1, 2, 2, 5, 4, 4, 4, 4 }, 
			{ 6, 6, 5, 5, 5, 5, 5, 4, 4 },
			{ 6, 6, 6, 6, 5, 8, 8, 9, 9 }, 
			{ 7, 6, 6, 6, 5, 8, 8, 9, 9 }, 
			{ 7, 7, 7, 7, 8, 8, 8, 9, 9 },
			{ 7, 7, 7, 7, 8, 8, 9, 9, 9 } };
		
		
		
	@BeforeEach
	public void setUp() {
		
		s = new FreiformSudoku();
    }

	@AfterEach
	public void clean(){
		s.setStart(start);
		s.setFieldConstruction(fieldConstruction);
	}

	
	@Test
	public void testCheckValFreiform() {

		assertFalse(s.checkVal(4,1,9));
		assertTrue(s.checkVal(0,0,3));
//		assertTrue(s.checkVal(8,5,1));
//		assertTrue(s.checkVal(0,8,4));
//		assertTrue(s.checkVal(3,3,8));
//		assertFalse(s.checkVal(0,8,8));
//		assertFalse(s.checkVal(1,1,6));
//		assertFalse(s.checkVal(5,2,5));
//		assertFalse(s.checkVal(0,7,2));
//		assertFalse(s.checkVal(3,6,3));
//		assertFalse(s.checkVal(5,4,7));

	}
//	@Test
//	public void testGetEmptyFields() {
//
//		assertFalse(s.checkVal(4,1,9));
//		assertFalse(s.checkVal(1,4,9));
//		assertTrue(s.checkVal(8,5,1));
//		assertTrue(s.checkVal(0,8,4));
//		assertTrue(s.checkVal(3,3,8));
//		assertFalse(s.checkVal(0,8,8));
//		assertFalse(s.checkVal(1,1,6));
//		assertFalse(s.checkVal(5,2,5));
//		assertFalse(s.checkVal(0,7,2));
//		assertFalse(s.checkVal(3,6,3));
//		assertFalse(s.checkVal(5,4,7));
//
//	}
}
