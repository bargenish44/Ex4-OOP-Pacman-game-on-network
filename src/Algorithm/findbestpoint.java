package Algorithm;

import java.util.ArrayList;

import Geom.*;
import Packman_Game.*;

public class findbestpoint {
	private Game game;

	public findbestpoint(Game g) {
		setGame(g);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	Map map = new Map();
	Point3D leftUp = new Point3D(0, 0, 0);

	public Point3D bestStart(int width, int hight) {
		Point3D rightDown = new Point3D(width, hight);
		double x = (leftUp.x() - rightDown.x()) / 2;
		double y = (leftUp.y() - rightDown.y()) / 2;
		Point3D center = new Point3D(x, y);
		Point3D rightUp = new Point3D(width, 0);
		Point3D leftDown = new Point3D(0, hight);
		Point3D[] bestBox = sidescomperator(game.getFruitArr(), leftUp, center, center, rightDown,
				new Point3D(leftUp.x(), center.y()), new Point3D(center.x(), rightDown.y()),
				new Point3D(center.x(), leftUp.y()), new Point3D(rightDown.x(), center.y()));
		Point3D leftUp2 = new Point3D(bestBox[0]);
		Point3D rightDown2 = new Point3D(bestBox[1]);
		double x2 = (leftUp2.x() - rightDown2.x()) / 2;
		double y2 = (leftUp2.y() - rightDown2.y()) / 2;
		Point3D center2 = new Point3D(x2, y2);
		Point3D rightUp2 = new Point3D(rightDown2.x(), 0);
		Point3D leftDown2 = new Point3D(0, rightDown2.y());
		Point3D[] bestBox2 = sidescomperator(game.getFruitArr(), leftUp2, center2, center2, rightDown2,
				new Point3D(leftUp2.x(), center2.y()), new Point3D(center2.x(), rightDown2.y()),
				new Point3D(center2.x(), leftUp2.y()), new Point3D(rightDown2.x(), center2.y()));
		double X_max = bestBox2[1].x();
		double Y_max = bestBox2[1].y();
		double X_min = bestBox2[0].x();
		double Y_min = bestBox2[0].x();

		Point3D ans = null;
		for (Fruit fruit : game.getFruitArr()) {
			if (fruit.getOrient().x() > X_min && fruit.getOrient().x() < X_max && fruit.getOrient().y() > Y_min
					&& fruit.getOrient().y() < Y_max) {
				ans = fruit.getOrient();
			}
		}
		return ans;
	}

	private int countfruits(ArrayList<Fruit> arr, Point3D leftup, Point3D rightdown) {
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			Point3D tmp = arr.get(i).getOrient();
			if ((tmp.x() <= rightdown.ix() && tmp.x() >= leftup.ix())
					&& (tmp.y() <= rightdown.iy() && tmp.y() >= leftup.iy()))
				count++;
		}
		return count;
	}

	private Point3D putsonpackman(ArrayList<Packman> array, Point3D leftup, Point3D rightdown) {
		for (int i = 0; i < array.size(); i++) {
			Point3D tmp = array.get(i).getOrinet();
			if ((tmp.x() <= rightdown.ix() && tmp.x() >= leftup.ix())
					&& (tmp.y() <= rightdown.iy() && tmp.y() >= leftup.iy()))
				return tmp;
		}
		return new Point3D(-1, -1, -1);
	}

	private Point3D[] sidescomperator(ArrayList<Fruit> arr, Point3D leftup0, Point3D rightdown0, Point3D leftup1,
			Point3D rightdown1, Point3D leftup2, Point3D rightdown2, Point3D leftup3, Point3D rightdown3) {
		int count0 = countfruits(arr, leftup0, rightdown0);
		int count1 = countfruits(arr, leftup1, rightdown1);
		int count2 = countfruits(arr, leftup2, rightdown2);
		int count3 = countfruits(arr, leftup3, rightdown3);
		Point3D[] bestside = new Point3D[2];
		if (count0 > count1 && count0 > count2 && count0 > count3) {
			bestside[0] = leftup0;
			bestside[1] = rightdown0;
		} else if (count1 > count0 && count1 > count2 && count1 > count3) {
			bestside[0] = leftup1;
			bestside[1] = rightdown1;
		} else if (count2 > count0 && count2 > count1 && count2 > count3) {
			bestside[0] = leftup2;
			bestside[1] = rightdown2;
		} else {
			bestside[0] = leftup3;
			bestside[1] = rightdown3;
		}
		return bestside;
	}
}