package org.projector.types;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TableTest {

	@Test
	public void test2x2ByColumns() {
		Table t = new Table();
		t.add(0, 0, "A");
		t.add(0, 1, "B");
		t.add(1, 0, "C");
		t.add(1, 1, "D");
		
		Object[][] expected = new Object[][] {
			{ "A", "C" }, //column 0
			{ "B", "D" }  //column 1
		};
		assertExport(expected, t.exportByColumns());
	}

	@Test
	public void test5x5ByColumns() {
		Table t = new Table();
		
		t.add(0, 0, "0A");
		t.add(0, 1, "0B");
		t.add(0, 2, "0C");
		t.add(0, 3, "0D");
		t.add(0, 4, "0E");
		
		t.add(1, 0, "1A");
		t.add(1, 1, "1B");
		t.add(1, 2, "1C");
		t.add(1, 3, "1D");
		t.add(1, 4, "1E");
		
		t.add(2, 0, "2A");
		t.add(2, 1, "2B");
		t.add(2, 2, "2C");
		t.add(2, 3, "2D");
		t.add(2, 4, "2E");
		
		t.add(3, 0, "3A");
		t.add(3, 1, "3B");
		t.add(3, 2, "3C");
		t.add(3, 3, "3D");
		t.add(3, 4, "3E");
		
		t.add(4, 0, "4A");
		t.add(4, 1, "4B");
		t.add(4, 2, "4C");
		t.add(4, 3, "4D");
		t.add(4, 4, "4E");
		
		Object[][] expected = new Object[][] {
			{ "0A", "1A", "2A", "3A", "4A" }, //column 0
			{ "0B", "1B", "2B", "3B", "4B" },  //column 1
			{ "0C", "1C", "2C", "3C", "4C" },  //column 2
			{ "0D", "1D", "2D", "3D", "4D" },  //column 3
			{ "0E", "1E", "2E", "3E", "4E" },  //column 4
		};
		assertExport(expected, t.exportByColumns());
	}
	

	@Test
	public void test2x2ByRows() {
		Table t = new Table();
		t.add(0, 0, "A");
		t.add(0, 1, "B");
		t.add(1, 0, "C");
		t.add(1, 1, "D");
		
		Object[][] expected = new Object[][] {
			{ "A", "B" }, //row 0
			{ "C", "D" }  //row 1
		};
		assertExport(expected, t.exportByRows());
	}

	@Test
	public void test5x5ByRows() {
		Table t = new Table();
		
		t.add(0, 0, "0A");
		t.add(0, 1, "0B");
		t.add(0, 2, "0C");
		t.add(0, 3, "0D");
		t.add(0, 4, "0E");
		
		t.add(1, 0, "1A");
		t.add(1, 1, "1B");
		t.add(1, 2, "1C");
		t.add(1, 3, "1D");
		t.add(1, 4, "1E");
		
		t.add(2, 0, "2A");
		t.add(2, 1, "2B");
		t.add(2, 2, "2C");
		t.add(2, 3, "2D");
		t.add(2, 4, "2E");
		
		t.add(3, 0, "3A");
		t.add(3, 1, "3B");
		t.add(3, 2, "3C");
		t.add(3, 3, "3D");
		t.add(3, 4, "3E");
		
		t.add(4, 0, "4A");
		t.add(4, 1, "4B");
		t.add(4, 2, "4C");
		t.add(4, 3, "4D");
		t.add(4, 4, "4E");
		
		Object[][] expected = new Object[][] {
			{ "0A", "0B", "0C", "0D", "0E" }, //row 0
			{ "1A", "1B", "1C", "1D", "1E" }, //row 1
			{ "2A", "2B", "2C", "2D", "2E" }, //row 2
			{ "3A", "3B", "3C", "3D", "3E" }, //row 3
			{ "4A", "4B", "4C", "4D", "4E" }, //row 4
		};
		assertExport(expected, t.exportByRows());
	}
	
	
	private void assertExport(Object[][] expected, Object[][] actual) {
		for (int c = 0; c < expected.length; c++) {
			for (int r = 0; r < expected[c].length; r++) {
				assertEquals(expected[c][r], actual[c][r]);
			}
		}
	}
}
