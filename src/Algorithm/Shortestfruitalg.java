package Algorithm;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Geom.Circle;
import Geom.Path;
import Geom.Point3D;
import Packman_Game.Box;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Ghost;
import Packman_Game.Map;
import Packman_Game.Player;
import graph.Graph;
import graph.Node;

public class Shortestfruitalg {
	private Game game;

	public Shortestfruitalg(Game g) {
		game = g;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	private double Calculatetime(Player p, Fruit f) {
		Circle c = new Circle(p.getPos(), p.getRadius());
		Map m = new Map();
		double dist = m.distance3d(c.get_cen(), f.getPos()) - c.get_radius();
		if (dist <= 0)
			return 0;
		return dist / p.getSpeed();
	}

	private double Calculatetimewithbox(Player p, Fruit f) {
		Circle c = new Circle(p.getPos(), p.getRadius());
		Map map = new Map();
		double dist = 0;
		Path path = (calcpath(f, game));
		dist = path.GetDist();
		if (!path.getPoints().isEmpty())
			dist += map.distance3d(c.get_cen(), path.getPoints().get(0));
		return dist;
	}

	public Fruit shortpathalgo(Game game) {
		setGame(game);
		if (game.getBoxarr().isEmpty())
			return algowithoutboxes();
		return algowithboxs();
	}

	private Fruit algowithoutboxes() {// לחפש קודם מה שאין לנו מכשול בדרך אליו ורק אז ללכת לכיוון הכי קרוב במכשול
		double min = Double.MAX_VALUE;// לבדוק זמן ודאי לכל פרי אם יש מכשול להפעיל אלגוריתם שיגיד כמה זמן יקח ואז ללכת
		// להכי קרוב בהכרח
		double tmp = 0;
		Fruit fruittemp = game.getFruitArr().get(0);
		for (int i = 0; i < game.getFruitArr().size(); i++) {
			tmp = Calculatetime(game.getPlayer(), game.getFruitArr().get(i));
			if (tmp < min) {
				min = tmp;
				fruittemp = game.getFruitArr().get(i);
			}
		}
		return fruittemp;
	}

	private Fruit algowithboxs() {
		double min = Double.MAX_VALUE;
		double tmp = 0;
		Fruit fruittemp = game.getFruitArr().get(0);
		for (int i = 0; i < game.getFruitArr().size(); i++) {
			tmp = Calculatetimewithbox(game.getPlayer(), game.getFruitArr().get(i));
			if (tmp < min) {
				min = tmp;
				fruittemp = game.getFruitArr().get(i);
			}
		}
		return fruittemp;
	}

	public boolean isIn(Point3D point) {
		boolean ans = true;
		double x = point.x();
		double y = point.y();
		for (Box box : game.getBoxarr()) {
			if (x > box.getRightUp().x() || x < box.getLeftDown().x() || y > box.getLeftDown().y()
					|| y < box.getRightUp().y()) {
				ans = false;
			}
		}
		return ans;
	}

	public ArrayList<Point3D> getOuters() {
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		for (Box box : game.getBoxarr()) {
			if (!isIn(box.getRightUp())) {
				box.getRightUp().set_y(box.getRightUp().y() + 0.001);
				ans.add(box.getRightUp());
			} else if (!isIn(box.getLeftDown())) {
				box.getLeftDown().set_x(box.getLeftDown().x() - 0.001);
				ans.add(box.getLeftDown());
			}
		}
		return ans;
	}

	public ArrayList<Point3D> cleanShot(Point3D point) {
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		ArrayList<Point3D> outers = getOuters();
		for (int i = 0; i < outers.size(); i++) {
			if (!LineofSight(point, outers.get(i))) {
				ans.add(outers.get(i));
			}

		}
		return ans;
	}

	public ArrayList<Point3D> cleanShot() {
		Player player = game.getPlayer();
		ArrayList<Point3D> ans = new ArrayList<Point3D>();
		Point3D player_p = player.getPos();
		ArrayList<Point3D> outers = getOuters();
		for (int i = 0; i < outers.size(); i++) {
			if (!LineofSight(player_p, outers.get(i))) {
				ans.add(outers.get(i));
			}

		}
		return ans;
	}

	private Path calcpath(Fruit fruit, Game game) {// לבנות גרף ואז להשתמש בקוד של בועז להוסיף
		Path p = new Path();
		Graph graph = new Graph();
		graph.add(new Node("player"));
		graph.add(new Node("fruit"));
		for (Box box : game.getBoxarr()) {
			Point3D leftdown = box.getLeftDown();
			Point3D rightup = box.getRightUp();
			Point3D rightdown = new Point3D(rightup.ix(), leftdown.iy());
			Point3D leftup = new Point3D(leftdown.ix(), rightup.iy());
			graph.add(new Node("leftdown"));
			graph.add(new Node("rightup"));
			graph.add(new Node("rightdown"));
			graph.add(new Node("leftup"));
		}
		for (int i = 0; i < graph.size(); i++) {
			Node node = graph.getNodeByIndex(i);

		}
		return p;
	}

//	public double escapefroomguest(Point3D p, Fruit f) {// יחזיר את הזוית שבא לנו לברוח אליה
//		Map map = new Map();
//		for (int i = 0; i < game.getGhostarr().size(); i++) {
//			if (map.distance3d(p, game.getGhostarr().get(i).getPos()) < 5)
//				if (map.azimuth_elevation_dist(p, f.getPos())[0] == map.azimuth_elevation_dist(p,
//						game.getGhostarr().get(i).getPos())[0]) {
//					return searchangle(p, f, game.getGhostarr().get(i));
//				}
//		}
//		return -1;
//	}

//	private double searchangle(Point3D p, Fruit f, Ghost g) {// לחפש את הזוית שצריך ללכת אליה
//		Map map = new Map();
//		double speed=20;
//		for(int i=0;i<12;i++) {
//			
//		}
//		if (map.azimuth_elevation_dist(p, f.getPos())[0] - 50 > 0)
//			return map.azimuth_elevation_dist(p, f.getPos())[0] - 50;
//		return map.azimuth_elevation_dist(p, f.getPos())[0] + 50;
//	}

	public double Go2Fruit() {
		Map map = new Map();
		for (int i = 0; i < game.getFruitArr().size(); i++) {
			if (map.distance3d(game.getPlayer().getPos(), game.getFruitArr().get(i).getPos()) < 10)
				return map.azimuth_elevation_dist(game.getPlayer().getPos(), game.getFruitArr().get(i).getPos())[0];
		}
		return -1;
	}

	private boolean isColliding(Rectangle2D rect1, Line2D line2) {
		if (line2 != null) {
			return line2.intersects(rect1);
		}
		return false;
	}

	public boolean LineofSight(Point3D point1, Point3D point2) {
		Map map = new Map();
		Player player = new Player(game.getPlayer());
		Line2D line = new Line2D.Double(point1.x(), point1.y(), point2.x(), point2.y());
		ArrayList<Box> boxs = new ArrayList<>();
		for (int i = 0; i < game.getBoxarr().size(); i++) {
			boxs.add(new Box(game.getBoxarr().get(i)));
			double minx = Math.min(boxs.get(i).getLeftDown().x(), boxs.get(i).getRightUp().x());
			double miny = Math.min(boxs.get(i).getLeftDown().y(), boxs.get(i).getRightUp().y());
			double xwidth = Math.abs(boxs.get(i).getLeftDown().x() - boxs.get(i).getRightUp().x());
			double yhight = Math.abs(boxs.get(i).getLeftDown().y() - boxs.get(i).getRightUp().y());
			Rectangle2D r = new Rectangle2D.Double(minx, miny, xwidth, yhight);
			if (isColliding(r, line) == true)
				return false;
		}
		return true;
	}

	public boolean LineofSight(Fruit fruit, int width, int hight) {
		Map map = new Map();
		Player player = new Player(game.getPlayer());
		Fruit f = new Fruit(fruit);
		player.setPos(map.CoordsToPixel(player.getPos(), width, hight));
		f.setPos(map.CoordsToPixel(f.getPos(), width, hight));
		Line2D line = new Line2D.Double(player.getPos().ix(), player.getPos().iy(), f.getPos().ix(), f.getPos().iy());
		ArrayList<Box> boxs = new ArrayList<>();
		for (int i = 0; i < game.getBoxarr().size(); i++) {
			boxs.add(new Box(game.getBoxarr().get(i)));
			boxs.get(i).setLeftDown(map.CoordsToPixel(boxs.get(i).getLeftDown(), width, hight));
			boxs.get(i).setRightUp(map.CoordsToPixel(boxs.get(i).getRightUp(), width, hight));
			int minx = Math.min(boxs.get(i).getLeftDown().ix(), boxs.get(i).getRightUp().ix());
			int miny = Math.min(boxs.get(i).getLeftDown().iy(), boxs.get(i).getRightUp().iy());
			int xwidth = Math.abs(boxs.get(i).getLeftDown().ix() - boxs.get(i).getRightUp().ix());
			int yhight = Math.abs(boxs.get(i).getLeftDown().iy() - boxs.get(i).getRightUp().iy());
			Rectangle2D r = new Rectangle2D.Double(minx, miny, xwidth, yhight);
			if (isColliding(r, line) == true)
				return false;
		}
		return true;
	}
}