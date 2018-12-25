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
int id= 1;
double speed = 2;
double raduius = 2;
Point3D pos = null;
	@Test
	void testGhost() {
		Ghost test_1 = new Ghost(id, pos, speed, raduius);
	
		Ghost test_2 = new Ghost(1, null, 2, 2);
		
		if(!test_1.equals(test_2))
		fail("Not yet implemented");
	}

}
