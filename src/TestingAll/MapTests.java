package TestingAll;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;
import Packman_Game.Map;

class MapTests {
	/**
	 * Map tests.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	@Test
	void New_Map_Tests() {
		Map map=new Map();
		Map map2=new Map(new Point3D(4,4,4),new Point3D(6,6,6),"Ariel2.png");
		if(map.getMap().equals(map2.getMap()))
			fail("Constractor problem");
		map2.setMap(map.getMap());
		if(!map.getMap().equals(map2.getMap()))
			fail("Seteer/geeter problem");
	}
	@Test
	void converts_Tests() {
		Map map=new Map();
		Point3D p=map.getLeftUp();
		Point3D p2=map.CoordsToPixel(p, 10, 10);
		p=map.PixelToCoords(p2.ix(), p2.iy(), p2.iz(), 10, 10);
		if(!p.toString().equals(map.getLeftUp().toString()))
			fail("covert problem");
	}
	@Test
	void distance_in_pixels_Tests() {
		Map map=new Map();
		Point3D p=new Point3D(4,4,4);
		Point3D p2=new Point3D(4,4,4);
		double dist=10;
		dist=map.Distance_IN_Pixels(p, p2);
		if(dist!=0)
			fail("dist problem");
		p=new Point3D(10,4,4);
		dist=0;
		dist=map.Distance_IN_Pixels(p, p2);
		if(dist==0)
			fail("dist problem");
	}
	@Test
	void distance_in_coords_Tests() {
		Map map=new Map();
		Point3D p=new Point3D(map.getLeftUp());
		Point3D p2=new Point3D(map.getLeftUp());
		double dist=10;
		dist=map.distance3d(p, p2);
		if(dist!=0)
			fail("dist problem");
		p=new Point3D(map.getRightDown());
		dist=0;
		dist=map.distance3d(p, p2);
		if(dist==0)
			fail("dist problem");
	}
	@Test
	void testAzimuth_elevation_dist() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p2=new Point3D(32.106352,35.205225,650);
		double [] p3= {313.2304203264989,-2.3247635173865278,493.0523318324134};
		MyCoords temp =new MyCoords();
		if(Arrays.equals(p3, temp.azimuth_elevation_dist(p1, p2))) 
			fail("the 'Azimuth_elevation_dist' function doesnt work well");
	}
}
