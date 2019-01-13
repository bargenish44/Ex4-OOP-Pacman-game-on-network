package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Packman_Game.Ghost;

class GhostTest {
	/**
	 * Ghost tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
int id= 1;
double speed = 2;
double raduius = 2;
Point3D pos = null;
	@Test
	void testGhost() {
		Ghost test_1 = new Ghost(id, pos, speed, raduius);
	
		Ghost test_2 = new Ghost(1, null, 2, 2);
		
		if(test_1.equals(test_2))
		fail("Not yet implemented");
	}
	void testGhost_2() {
		int id= 3;
		double speed = 1;
		double raduius = 1;
		Point3D pos = new Point3D(1,1,0);
		
		Ghost test_3 = new Ghost(id, pos, speed, raduius);
	
		Ghost test_4 = new Ghost(3,pos , 1, 1);
		
		if(test_3.equals(test_4))
		fail("Not yet implemented");
	}

}
