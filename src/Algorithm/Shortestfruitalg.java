package Algorithm;

import Geom.Circle;
import Geom.Path;
import Geom.Point3D;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Ghost;
import Packman_Game.Map;
import Packman_Game.Player;
import graph.Node;

public class Shortestfruitalg {
	private Game game;
	public Shortestfruitalg(Game g) {
		game=g;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	private double Calculatetime(Player p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		Map m=new Map();
		double dist=m.distance3d(c.get_cen(),f.getOrient())-c.get_radius();
		if(dist<=0)return 0;
		return dist/p.getSpeed();
	}
	private double Calculatetimewithbox(Player p,Fruit f) {
		Circle c=new Circle(p.getOrinet(),p.getRadius());
		Map map=new Map();
		double dist=0;
		Path path=(calcpath(p,f,game));
		dist=path.GetDist();
		if(!path.getArr().isEmpty())
			dist+=map.distance3d(c.get_cen(), path.getArr().get(0));
		return dist;
	}
	public Fruit algowithoutboxes() {
		double min=Double.MAX_VALUE;
		double tmp=0;
		Fruit fruittemp=game.getFruitArr().get(0);
		for(int i=0;i<game.getFruitArr().size();i++) {
			tmp=Calculatetime(game.getPlayer(),game.getFruitArr().get(i));
			if(tmp<min) {
				min=tmp;
				fruittemp=game.getFruitArr().get(i);
			}
		}
		return fruittemp;
	}
	public Fruit algowithboxs() {
		double min=Double.MAX_VALUE;
		double tmp=0;
		Fruit fruittemp=game.getFruitArr().get(0);
		for(int i=0;i<game.getFruitArr().size();i++) {
			tmp=Calculatetimewithbox(game.getPlayer(),game.getFruitArr().get(i));
			if(tmp<min) {
				min=tmp;
				fruittemp=game.getFruitArr().get(i);
			}
		}
		return fruittemp;
	}
	private Path calcpath(Player player,Fruit fruit,Game game) {//לבנות גרף ואז להשתמש בקוד של בועז להוסיף
		Path p=new Path();
		for(int i=0;i<game.getBoxarr().size();i++) {
			Point3D leftdown=game.getBoxarr().get(i).getLeftDown();
			Point3D rightup=game.getBoxarr().get(i).getRightUp();
			Point3D rightdown=new Point3D(rightup.ix(),leftdown.iy());
			Point3D leftup=new Point3D(leftdown.ix(),rightup.iy());
			Node box1leftdown=new Node("box1leftdown");
			
		}
		return p;
	}
	public double escapefroomguest(Point3D p,Fruit f) {//יחזיר את הזוית שבא לנו לברוח אליה
		Map map=new Map();
		//		boolean ans=false;
		for(int i=0;i<game.getGhostarr().size();i++) {
			if(map.distance3d(p, game.getGhostarr().get(i).getPos())<5)
				if(map.azimuth_elevation_dist(p, f.getOrient())[0]==map.azimuth_elevation_dist(p, game.getGhostarr().get(i).getPos())[0]) {
					return searchangle(p,f,game.getGhostarr().get(i));
				}
		}
		return -1;
	}
	private double searchangle(Point3D p,Fruit f,Ghost g) {//לחפש את הזוית שצריך ללכת אליה
		
	}
	public double Go2Fruit() {
		Map map=new Map();
		for(int i=0;i<game.getFruitArr().size();i++) {
			if(map.distance3d(game.getPlayer().getOrinet(), game.getFruitArr().get(i).getOrient())<10)
				return map.azimuth_elevation_dist(game.getPlayer().getOrinet(),game.getFruitArr().get(i).getOrient())[0];
		}
		return -1;
	}
}