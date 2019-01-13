package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Packman_Game.Fruit;

class FruitTests {
	/**
	 * Fruit tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	@Test
	void NewFruit_and_to_stirngtest() {
		Fruit f=new Fruit(0, new Point3D(4,2,0), 5);
		Fruit f2=new Fruit(0, 4, 2, 0, 4);
		if(f.toString().equals(f2.toString())) {
			fail("Constractor problem");
		}
		f2.setWeight(5);
		if(!f.toString().equals(f2.toString())) {
			fail("Setweight problem");
		}
		f2.setID(1);
		if(f.toString().equals(f2.toString())) {
			fail("Setid problem");
		}
	
	}
}