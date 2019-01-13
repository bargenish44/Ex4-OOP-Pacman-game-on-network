package Algorithm;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import Coords.LatLonAlt;
import Geom.Circle;
import Geom.Path;
import Geom.Point3D;
import Packman_Game.Box;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Ghost;
import Packman_Game.Map;
import Packman_Game.Player;
import Robot.Packman;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

public class Shortestfruitalg {
	private Game game;
	private Game tempgame;
	private int width;
	private int hight;
	private double eps = 10;
	Map map=new Map();

	public void setGame(Game g) {
		this.game=g;
	}
	public Shortestfruitalg(Game g,int w,int h) {
		game = new Game(g);
		width=w;
		hight=h;
		tempgame=new Game(game);
	}
	public void initforpixels() {
		Point3D p;
		Point3D p1;
		for(int i=0;i<tempgame.getBoxarr().size();i++) {
			p=map.CoordsToPixel(tempgame.getBoxarr().get(i).getLeftDown(), width, hight);
			p1=map.CoordsToPixel(tempgame.getBoxarr().get(i).getRightUp(), width, hight);
			p.set_x(p.x()-eps);
			p.set_y(p.y()+eps);
			p1.set_x(p1.x()+eps);
			p1.set_y(p1.y()-eps);
			tempgame.getBoxarr().get(i).setLeftDown(p);
			tempgame.getBoxarr().get(i).setRightUp(p1);
		}
		for(int i=0;i<tempgame.getFruitArr().size();i++) {
			tempgame.getFruitArr().get(i).setPos(map.CoordsToPixel(tempgame.getFruitArr().get(i).getPos(), width, hight));
		}
		for(int i=0;i<tempgame.getGhostarr().size();i++) {
			tempgame.getGhostarr().get(i).setPos(map.CoordsToPixel(tempgame.getGhostarr().get(i).getPos(),width,hight));
		}
		for(int i=0;i<tempgame.getPackmanArr().size();i++) {
			tempgame.getPackmanArr().get(i).setPos(map.CoordsToPixel(tempgame.getPackmanArr().get(i).getPos(), width, hight));
		}
		tempgame.getPlayer().setPos(map.CoordsToPixel(tempgame.getPlayer().getPos(), width, hight));
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

	public Fruit shortpathalgo(Game g) {
		game=g;
		if (game.getBoxarr().isEmpty())
			return algowithoutboxes();
		return algowithboxs();
	}

	private Fruit algowithoutboxes() {
		double min = Double.MAX_VALUE;
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
	private Path calcpath(Fruit fruit, Game game) {
		Path p = new Path();
		if(LineofSight(game.getPlayer().getPos(), fruit.getPos())==true) {
			p.getPoints().add(fruit.getPos());
			return p;
		}
		initforpixels();
		game=new Game(tempgame);
		ArrayList<Point3D> Points = new ArrayList<>();
		Points.add(game.getPlayer().getPos());
		Graph graph = new Graph();
		graph.add(new Node("a"));
		graph.add(new Node("10"));
		graph.add(new Node("source"));
//		int count=0;
//		for (Box box : game.getBoxarr()) {
//			Point3D leftdown = box.getLeftDown();
//			Point3D rightup = box.getRightUp();
//			Point3D rightdown = new Point3D(rightup.ix(), leftdown.iy());
//			Point3D leftup = new Point3D(leftdown.ix(), rightup.iy());
//			
//			graph.add(new Node(""+count++));
//			graph.add(new Node(""+count++));
//			graph.add(new Node(""+count++));
//			graph.add(new Node(""+count++));
//			Points.add(leftdown);
//			Points.add(rightup);
//			Points.add(rightdown);
//			Points.add(leftup);
	//}

//		for (int i = 1; i < graph.size()-1; i++) {
//			if(LineofSight(Points.get(0),Points.get(i)))
//				graph.addEdge("a", ""+i, Points.get(0).distance2D(Points.get(i)));
//				System.out.println("a >>  " + i );
//		}
//		for(int i=1;i<graph.size()-1;i++) {
//			for(int j=i+1;j<graph.size()-1;j++) {
//				if(LineofSight(Points.get(i), Points.get(j)))
//					graph.addEdge(""+i,""+j,Points.get(i).distance2D(Points.get(j)));
//				System.out.println( i+ "   >>  " + j );
//			}
//		}
		graph.addEdge("a", "" + 10, 10);
		graph.addEdge("" +10, "source", 10);
//		graph.addEdge("source", "" + 10, 10);
		Graph_Algo.dijkstra(graph,"a");
		return p;
	}

	public double escapefroomguest(Player p, Fruit f) {
		Map map = new Map();
		for (int i = 0; i < game.getGhostarr().size(); i++) {
			if (map.distance3d(p.getPos(), game.getGhostarr().get(i).getPos()) < 10)
				if (map.azimuth_elevation_dist(p.getPos(), f.getPos())[0] == map.azimuth_elevation_dist(p.getPos(),
						game.getGhostarr().get(i).getPos())[0]) {
					return searchangle(p, f, game.getGhostarr().get(i));
				}
		}
		return -1;
	}

	private double searchangle(Player p, Fruit f, Ghost g) {
		double Pangle = p.getAzimuth();
		double angle = Pangle;
		for (int i = 1; i <= 6; i++) {
			angle += 30 * i;
			if (move(f, angle) == true)
				return angle;
			angle = Pangle - (30 * i);
			if (move(f, angle) == true)
				return angle;
		}
		return -1;
	}

	private boolean escapeposcheck(Packman p, Fruit f) {
		Point3D tmp = p.getLocation();
		if (isIn(tmp) == true)
			return false;
		for (int i = 0; i < game.getGhostarr().size(); i++) {
			Point3D temp = p.getLocation();
			if (temp.equals(game.getGhostarr().get(i).getPos()))
				return false;
		}
		return true;
	}

	private boolean move(Fruit f, double angle) {
		Packman p = new Packman(new LatLonAlt(game.getPlayer().getPos().x(), game.getPlayer().getPos().y(), 0),
				game.getPlayer().getSpeed());
		p.setOrientation(angle);
		p.move(100.0D);
		if (escapeposcheck(p, f) == false)
			return false;
		return true;
	}

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
}
