package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Packman_Game.Packman;

class PackmanTests {
	/**
	 * Packman tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	@Test
	void Packman_Tests() {
		Packman p=new Packman(0,2,2,2,5,5);
		Packman p2=new Packman(0,new Point3D(2,2,2),5,5);
		if(!p.toString().equals(p2.toString()))
			fail("Constractor/tostring problem");
		Packman p3=new Packman(1,3,3,3,6,6);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setID(0);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setRadius(5);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		p3.setSpeed(5);
		if(p.toString().equals(p3.toString()))
			fail("Constractor/tostring problem");
		double a=p3.getScore();
		p3.setScore(2);
		if(a==p3.getScore())
			fail("Constractor/tostring problem");
		p3.resetScore();
		if(a!=p3.getScore())
			fail("Constractor/tostring problem");

	}
}
