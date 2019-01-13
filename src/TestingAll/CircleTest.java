package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class CircleTest {
	/**
	 * Circle tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	@Test
	void NewCircle_and_to_stirngtest() {
		Geom.Circle c=new Geom.Circle(new Point3D(4,3,0),5);
		Geom.Circle c2=new Geom.Circle(4,3,0,5);
		Geom.Circle c3=new Geom.Circle(4,3,5);
		if(!c.toString().equals(c2.toString())||!c.toString().equals(c3.toString())) {
			fail("You have problem with the constractor");
		}
	}
	@Test
	void distanc3detest() {
		Geom.Circle c=new Geom.Circle(new Point3D(4,3,0),5);
		if(c.distance3D(new Point3D(4,3,0))!=0.0)
			fail("You have problem with the distance3d");
		if(c.distance3D(new Point3D(10,3,0))==0.0) 
			fail("You have problem with the distance3d");
	}
	@Test
	void distanc2detest() {
		Geom.Circle c=new Geom.Circle(new Point3D(4,3,0),5);
		if(c.distance2D(new Point3D(9,3,0))!=0.0)
			fail("You have problem with the distance3d");
		if(c.distance2D(new Point3D(10,3,0))!=1.0) {
			fail("You have problem with the distance3d");
		}
	}
}
