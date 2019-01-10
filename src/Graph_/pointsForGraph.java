package Graph_;

import Geom.Point3D;
import java.util.ArrayList;
import java.util.HashMap;

import Algorithm.Shortestfruitalg;
import Packman_Game.Game;

public class pointsForGraph {
	private Game game = new Game();
	Shortestfruitalg alg;

	public pointsForGraph(Game game) {
		game = new Game(game);
		alg= new Shortestfruitalg(game);
	}

	public HashMap<Point3D, Node> getPath() {
		HashMap<Point3D, Node> list = new HashMap<Point3D, Node>();
		ArrayList<Point3D> clean_shot = alg.cleanShot();
		ArrayList<Point3D> outers = alg.getOuters();
		int count = 0;
		for (int i = 0; i < outers.size(); i++) {
			String s = "Node (" + count + ") :";
			Node node = new Node(s);
			if (alg.cleanShot(outers.get(i)).size() > 0) {
				Node addOne = new Node("node");
				ArrayList<Point3D> temp = alg.cleanShot(outers.get(i));
				double distance = outers.get(i).distance3D(outers.get(i + 1));
				node.add(addOne, distance);
				System.out.println(node.get_info().toString());
				i++;
			}
			count++;
		}
		return list;
	}

}
