package Graph_;

import java.util.HashMap;

import Geom.Point3D;
import Packman_Game.Game;

public class Test {
	public static void main(String[] args) {
		Node node = new Node("node");
		System.out.println(node.toString());
		Game game = new Game();
		game = game.load("C:\\Users\\DELL\\git\\Ex4-OOP\\data\\game17.59.09.271242500.csv");
		pointsForGraph k = new pointsForGraph(game);
		HashMap<Point3D, Node> g = k.getPath();
		System.out.println(g.toString());
	}

}
