
package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Model.SimpleSudoku;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class SimpleSudokuTest {

	SimpleSudoku s;
	int[][] start;

	@BeforeEach
	public void setUp() {
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
			s.setStart(start);
    }

	@AfterEach
	public void clean(){
		s.setStart(start);
	}

	@Test
	public void testCheckValue() {

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
	//values im Array
	@Test
	public void testSetField() {

		s.setField(1,1,4);
		assertEquals(4,s.getVal(1,1));
		s.setField(8,5,1);
		assertEquals(1,s.getVal(8,5));
		s.setField(0,8,4);
		assertEquals(4,s.getVal(0,8));
		s.setField(3,3,8);
		assertEquals(8,s.getVal(3,3));
		s.setField(6,7,9);
		assertEquals(9,s.getVal(6,7));
		
	}

	
//	//solve() test
	@Test
	public void testSolve() {

		assertTrue(s.solveSudoku());  
		
	}
	//check Complete
	@Test
	public void testComplete() {
	 
		start = new int[][] { { 8, 1, 2, 7, 5, 3, 6, 4, 9 }, { 9, 4, 3, 6, 8, 2, 1, 7, 5 },
			{ 6, 7, 5, 4, 9, 1, 2, 8, 3 }, { 1, 5, 4, 2, 3, 7, 8, 9, 6 }, { 3, 6, 9, 8, 4, 5, 7, 2, 1 },
			{ 2, 8, 7, 1, 6, 9, 5, 3, 4 }, { 5, 2, 1, 9, 7, 4, 3, 6, 8 }, { 4, 3, 8, 5, 2, 6, 9, 1, 7 },
			{ 7, 9, 6, 3, 1, 8, 4, 5, 2 } };
		s = new SimpleSudoku();
		s.setStart(start);
		assertTrue(s.checkComplete());
	}
	
	
	
}
	
