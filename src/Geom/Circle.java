package Geom;

import Coords.Geom_element;
import Packman_Game.Map;

/**
 * This class represents Circle.
 * @author Bar Genish
 * @author Elyashiv Deri
 * @author lioz elmalem
 * https://en.wikipedia.org/wiki/Circle
 */
public class Circle implements Geom_element {
	private Point3D cen;
	private double radius;
	/**
	 * Regular constractor.
	 * @param cen the center of the circle.
	 * @param rad the raduis of the circle.
	 */
	public Circle(Point3D cen, double rad) {
		this.set_cen(cen);
		this.set_radius(rad);
	}
	/**
	 * Regular constractor.
	 * @param x the x point of the center of the circle.
	 * @param y the y point of the center of the circle.
	 * @param z the z point of the center of the circle.
	 * @param rad the raduis of the circle.
	 */
	public Circle(double x,double y,double z,double rad) {
		Point3D p=new Point3D(x,y,z);
		this.set_cen(p);
		this.set_radius(rad);
	}
	/**
	 * Regular constractor.
	 * @param x the x point of the center of the circle.
	 * @param y the y point of the center of the circle.
	 * @param rad the raduis of the circle.
	 */
	public Circle(double x,double y,double rad) {
		Point3D p=new Point3D(x,y);
		this.set_cen(p);
		this.set_radius(rad);
	}
	/** 
	 * return the distance3d between two points3d as double.
	 * */
	@Override
	public double distance3D(Point3D p) {
		Map m = new Map();
		double dist=m.distance3d(cen, p);
		if(dist<=radius)return 0;
		return dist-radius;
	}
	/** 
	 * return the distance2d between two points3d as double.
	 * */
	@Override
	public double distance2D(Point3D p) {
		double dist=cen.distance2D(p);
		dist-=radius;
		if(dist<0)return 0;
		return dist;
	}
	//setters and getters
	public Point3D get_cen() {
		return cen;
	}

	public void set_cen(Point3D cen) {
		this.cen = cen;
	}

	public double get_radius() {
		return radius;
	}

	public void set_radius(double radius) {
		this.radius = radius;
	}
	/**
	 * write the circle as string
	 * @return string of all the circle
	 */
	public String toString() {
		return "Center is: "+cen.toString()+", radius is: "+radius;
	}
}
