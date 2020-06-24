package org.projector.types;

import java.util.HashMap;
import java.util.Map;

public class Table {
	private Map<Duet<Integer, Integer>, Object> matrix;
	
	public Table(Map<Duet<Integer, Integer>, Object> matrix) {
		this.matrix = matrix;
	}
	
	public Table() {
		this(new HashMap<>());
	}
	
	private Duet<Integer, Integer> duet(int row, int column) {
		return new Duet<>(row, column);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(int row, int column) {
		return (T)matrix.get(duet(row, column));
	}
	
	public void add(int row, int column, Object value) {
		matrix.put(duet(row, column), value);
	}
	
	public void remove(int row, int column) {
		matrix.remove(duet(row, column));
	}
	
	public boolean isDefined(int row, int column) {
		return matrix.containsKey(duet(row, column));
	}
	
	public Duet<Integer, Integer> size() {
		int width = 0, height = 0;
		
		for (Duet<Integer, Integer> key : matrix.keySet()) {
			width = Math.max(width, key.getB());
			height = Math.max(height, key.getA());
		}
		
		return new Duet<>(width + 1, height + 1);
	}
	
	public Object[][] exportByColumns() {
		Duet<Integer, Integer> size = size();
		
		Object[][] map = new Object[size.getA()][size.getB()];
		for (int c = 0; c < size.getA(); c++) {
			for (int r = 0; r < size.getB(); r++) {
				map[c][r] = matrix.get(duet(r, c));
			}
		}
		
		return map;
	}
	
	public Object[][] exportByRows() {
		Duet<Integer, Integer> size = size();
		
		Object[][] map = new Object[size.getA()][size.getB()];
		for (int c = 0; c < size.getA(); c++) {
			for (int r = 0; r < size.getB(); r++) {
				map[r][c] = matrix.get(duet(r, c));
			}
		}
		
		return map;
	}
}
