package TestingAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Packman_Game.Player;

class PlayerTest {

	int ID = 2;
	Point3D pos = new Point3D(3, 4, 0);
	double speed = 3;
	double Radius = 1;
	double azimuth=0;
	Player test_3 = new Player(ID, pos, speed, Radius);
	Player test_4 = new Player(test_3);
	@Test
	void test() {

		Player test_1 = new Player(ID, pos, speed, Radius);

		Player test_2 = new Player(2, new Point3D(3, 4, 0), 3, 1);

		if(!test_1.toString().equals(test_2.toString())) {

			fail("Not yet implemented");
		}
	
		test_3.setAzimuth(azimuth);
		
		test_4.setAzimuth(0);
		
		if(!test_3.toString().equals(test_4.toString())) {

			fail("Not yet implemented");
		}
	}
}
