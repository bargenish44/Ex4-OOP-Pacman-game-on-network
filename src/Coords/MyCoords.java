package Coords;

import Geom.Point3D;

import java.util.Hashtable;

import java.util.Map;

import com.sun.javafx.geom.AreaOp.AddOp;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import Coords.coords_converter;

/**
 *  This class implements coords_converter.
 * @author Bar Genish
 * @author Elyashiv Deri
 */

public class MyCoords implements coords_converter {

	private double _x,_y,_z;
	public MyCoords(Point3D p) {
		_x=p.x();
		_y=p.y();
		_z=p.z();
	}
	public MyCoords(double x,double y, double z) {
		_x=x;
		_y=y;
		_z=z;
	}
	public MyCoords() {
		_x=0;
		_y=0;
		_z=0;
	}
	public Point3D getPoint3D() {
		return new Point3D(_x,_y,_z);
	}
	public void setPoint3D(Point3D p) {
		_x=p.x();
		_y=p.y();
		_z=p.z();
	}
	final static int world = 6371000;
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param gps Point3D
	 * @param local_vector_in_meter Point3D
	 * @return Point3D new point which is the gps point transformed by a 3D vector (in meters).
	 */

	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		double lonNorm=Math.cos(gps.x()*(Math.PI/180));

		if(!isValid_GPS_Point(gps)) 

			return null;


		double meterTolat =Math.asin(local_vector_in_meter.x()/world)*(180/Math.PI);

		double meterTolong=Math.asin(local_vector_in_meter.y()/world*lonNorm)*(180/Math.PI);

		double x=meterTolat+gps.x();

		double y=meterTolong+gps.y();

		double z=gps.z()+local_vector_in_meter.z();

		if((y>90)||(y<-90)) { 

			System.out.println("Invalid x");

			return null;

		}

		if(x>180) {

			x=((x+180)%360)-180;

		}

		if(x<-180) {

			x=(y+180)+180;

		}

		Point3D negps = new Point3D (x,y,z);

		if(isValid_GPS_Point(negps))

			return negps;

		return null;

	}
	/** computes the 3D distance (in meters) between the two gps like points 
	 * @param gps0 Point3D
	 * @param gps1 Point3D
	 * @return double: the 3D distance (in meters) between the two gps like points 
	 */

	public double distance3d(Point3D gps0, Point3D gps1) {

		double LonNorm=Math.cos(gps0.x()*Math.PI/180);

		double diff = gps1.x()-gps0.x();

		double radian = (diff*Math.PI)/180;

		double tometer1 = Math.sin(radian)*world;

		double diff2 = gps1.y()-gps0.y();

		double radian2 = (diff2*Math.PI)/180;

		double tometer2 = Math.sin(radian2)*world*LonNorm;



		return Math.sqrt((tometer1*tometer1) + (tometer2*tometer2));

	}
	/** computes the 3D vector (in meters) between two gps like points 
	 * @param gps0 Point3D
	 * @param gps1 Point3D
	 * @return Point3D the 3D vector (in meters) between two gps like points 
	 */

	public Point3D vector3D(Point3D gps0, Point3D gps1) {

		double LonNorm=Math.cos(gps0.x()*Math.PI/180);

		double diff = gps1.x()-gps0.x();

		double radian = (diff*Math.PI)/180;

		double tometer1 = Math.sin(radian)*world;

		double diff2 = gps1.y()-gps0.y();

		double radian2 = (diff2*Math.PI)/180;

		double tometer2 = Math.sin(radian2)*world*LonNorm;

		double diff3 = gps1.z()-gps0.z();
		Point3D vector3d=new Point3D (tometer1 , tometer2, diff3);
		return vector3d;

	}

	/** computes the polar representation of the 3D vector be gps0--gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @param gps0 Point3D
	 * @param gps1 Point3D
	 * @return double[]: an azimuth (aka yaw), elevation (pitch), and distance
	 */

	public  double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double [] ans = {0,0,0};

		ans[0] = gps0.north_angle(gps1);

		ans[1] = gps0.up_angle(gps1); 

		ans[2] =(distance3d(gps0, gps1));

		return ans;

	}
	/**
	 * return true if this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p Point3D
	 * @return boolean if this point is a valid lat, lon , lat coordinate
	 */

	public boolean isValid_GPS_Point(Point3D p) {

		if(p.x() >= -180 && p.x() <= 180 && p.y() >= -90 && p.y() <= 90 && p.z() >= -450) {

			return true;

		}

		return false;

	}

}

