package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Graph_.GETpath;
import Packman_Game.Box;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Ghost;
import Packman_Game.Packman;
import Packman_Game.Player;

class GETpathTest {
	int ID = 1;
	Point3D P = new Point3D(2, 2, 0);
	double weight = 4;
	Game test_4 = new Game();
	Fruit fruit = new Fruit(ID, P, weight);
	ArrayList<Fruit> Fruitarr;
	ArrayList<Packman>Packmanarr;
	ArrayList<Ghost>Ghostarr;
	ArrayList<Box>Boxarr;
	Player player;
	String GameName;
	Game g = new Game(Packmanarr, Fruitarr, Ghostarr, Boxarr, player);

	@Test
	void test() {

		GETpath test_1 = new GETpath(g, fruit);

		GETpath test_2 = new GETpath(test_4, new Fruit(ID, P, weight));

		if(!test_1.getGraph().equals(test_2.getGraph()))
			
			fail("Not yet implemented");

	}

}
