package Algorithm;

import Algorithm.Grid2d;

public class Example {
	public Example() {
		double[][] map = { { 0, 1, 2 }, { 3, 3, 2 }, { 0, -1, 0 } };
		Grid2d map2d = new Grid2d(map, false);
		System.out.println(map2d.findPath(0, 0, 2, 2));
	}

	public static void main(String[] args) {
		new Example();
	}

}
