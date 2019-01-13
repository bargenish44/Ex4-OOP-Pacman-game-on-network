package Graph_;

import Geom.Point3D;
import java.util.ArrayList;
import java.util.HashMap;
import Graph_.Graph;
import Algorithm.Shortestfruitalg;
import Packman_Game.Box;
import Packman_Game.Fruit;
import Packman_Game.Game;
import graph.Graph_Algo;
import Graph_.Node;

public class GETpath {
	private Game game = new Game();
	private Graph Graph = new Graph();
	private ArrayList<Point3D> path;
	private Shortestfruitalg sf = new Shortestfruitalg(game);

	public GETpath(Game game, Fruit fruit) {
		path = new ArrayList<Point3D>();
		path.add(game.getPlayer().getPos());
		String source = "a";
		String target = "b";
		Graph.add(new Node(source));
		int counter = 0;
		this.game = new Game(game);
		for (Box box : game.getBoxarr()) {
			path.add(box.getLeftDown());
			Graph.add(new Node("" + counter));
			Graph.addEdge("a", "" + counter, game.getPlayer().getPos().distance2D(box.getLeftDown()));
			counter++;
			path.add(box.getRightUp());
			Graph.add(new Node("" + counter));
			Graph.addEdge("a", "" + counter, game.getPlayer().getPos().distance2D(box.getRightUp()));
			counter++;
		}
		path.add(fruit.getPos());
		Graph.add(new Node(target));
		Graph.addEdge(source, target, game.getPlayer().getPos().distance3D(fruit.getPos()));
		counter++;
		for (int i = 1; i < path.size() - 1; i++) {
			String s1 = "" + i;
			String s2 = "" + (i + 1);
			double distance = path.get(i).distance3D(path.get(i - 1));
			Graph.addEdge(s1, s2, distance);
		}
	}

	public Graph getGraph() {
		return this.Graph;
	}
}
