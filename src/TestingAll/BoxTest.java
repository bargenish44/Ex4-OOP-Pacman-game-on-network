package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Packman_Game.Box;


class BoxTest {
	/**
	 * Box tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */

	int ID = 3;
	Point3D LeftDown = new Point3D(4,2,0);
	Point3D RightUp = new Point3D(3,1,0);
	int ID_2;
	Point3D LeftDown_2 = null;
	Point3D RightUp_2 = null;

	@Test
	void test() {

		Box TEST_1=new Box(ID,LeftDown,RightUp);

		Box TEST_2=new Box(3, new Point3D(4,2,0),new Point3D(3,1,0));
		

		Box TEST_3=new Box(ID_2,LeftDown_2,RightUp_2);

		Box TEST_4=new Box(ID_2,null,null);

		if(!TEST_1.toString().equals(TEST_2.toString())) {
			fail("Constractor problem");


			if(!TEST_3.toString().equals(TEST_4.toString())) {
				fail("Constractor problem");
			}

		}

	}
}
