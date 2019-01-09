package Algorithm;

import java.util.ArrayList;

import Geom.*;
import Packman_Game.*;
/**
 * A class that calculates the best start point on screen.
 * @author Bar Genish
 * @author Elyashiv Deri
 * @author lioz elmalem
 */

public class findbestpoint {
	private Game game;
	/**
	 * findbestpoint Constractor.
	 * @param g Game of the class.
	 */
	public findbestpoint(Game g) {
		setGame(g);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	/**
	 * function that find the best point to start.
	 * @param width the width of the screen in pixels.
	 * @param hight the hight if the screen in pixels.
	 * @return Point3D the point of the spot that we want to puts the player on.
	 */
	public Point3D bestStart(int width, int hight) {
		Point3D leftUp = new Point3D(0, 0, 0);
		Point3D rightDown = new Point3D(width, hight);
		double x = (leftUp.x() - rightDown.x()) / 2;
		double y = (leftUp.y() - rightDown.y()) / 2;
		Point3D center = new Point3D(x, y);
		Point3D[] bestBox = sidescomperator(game.getFruitArr(), leftUp, center, center, rightDown,
				new Point3D(leftUp.x(), center.y()), new Point3D(center.x(), rightDown.y()),
				new Point3D(center.x(), leftUp.y()), new Point3D(rightDown.x(), center.y()));
		Point3D leftUp2 = new Point3D(bestBox[0]);
		Point3D rightDown2 = new Point3D(bestBox[1]);
		double x2 = (leftUp2.x() - rightDown2.x()) / 2;
		double y2 = (leftUp2.y() - rightDown2.y()) / 2;
		Point3D center2 = new Point3D(x2, y2);
		Point3D[] bestBox2 = sidescomperator(game.getFruitArr(), leftUp2, center2, center2, rightDown2,
				new Point3D(leftUp2.x(), center2.y()), new Point3D(center2.x(), rightDown2.y()),
				new Point3D(center2.x(), leftUp2.y()), new Point3D(rightDown2.x(), center2.y()));
		double X_max = bestBox2[1].x();
		double Y_max = bestBox2[1].y();
		double X_min = bestBox2[0].x();
		double Y_min = bestBox2[0].x();
		Point3D ans = null;
		for (Fruit fruit : game.getFruitArr()) {
			if (fruit.getPos().x() > X_min && fruit.getPos().x() < X_max && fruit.getPos().y() > Y_min
					&& fruit.getPos().y() < Y_max) {
				ans = fruit.getPos();
			}
		}
		return ans;
	}
	/**
	 * help function that get a frame and count the fruits inside the frame.
	 * @param lefdown the point of the upper left of the frame.
	 * @param rightdown the point of the right down of the frame.
	 * @param arr arraylist of fruits that we want to cheak if they are inside the frame. 
	 * @return count - how many fruits are inside the frame.
	 */
	private int countfruits(ArrayList<Fruit> arr, Point3D leftup, Point3D rightdown) {
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			Point3D tmp = arr.get(i).getPos();
			if ((tmp.x() <= rightdown.ix() && tmp.x() >= leftup.ix())
					&& (tmp.y() <= rightdown.iy() && tmp.y() >= leftup.iy()))
				count++;
		}
		return count;
	}
	/**
	 * help function that get a 4 frames and returns the frame with the most fruits.
	 * @param leftup0 the point of the upper left of the first frame.
	 * @param leftup1 the point of the upper left of the second frame.
	 * @param leftup2 the point of the upper left of the third frame.
	 * @param leftupn3 the point of the upper left of the fourth frame.
	 * @param rightdown0 the point of the right down of the first frame.
	 * @param rightdown1 the point of the right down of the second frame.
	 * @param rightdown2 the point of the right down of the third frame.
	 * @param rightdown3 the point of the right down of the fourth frame.
	 * @param arr arraylist of fruits that we want to cheak if they are inside the frames. 
	 * @return Point3D[] - the frame with the most fruits inside of him.
	 */
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