package Algorithm;

import Geom.Circle;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Map;
import Packman_Game.Player;

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
}
