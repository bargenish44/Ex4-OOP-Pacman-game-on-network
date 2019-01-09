package Geom;

import java.util.ArrayList;
import Geom.Point3D;
/**
 * This class represents a path- arraylist of 3D point in space.
 * @author Bar Genish
 * @author Elyashiv Deri
 * @author lioz elmalem
 */
public class Path{
	private ArrayList<Point3D>Points=new ArrayList<Point3D>();
	/**
	 * Defult constractor
	 */
	public Path() {
		Points=new ArrayList<Point3D>();
	}
	/**
	 * Regular constractor.
	 * @param arr array of points3D that we want to insert to our new path.
	 */
	public Path( ArrayList<Point3D>arr) {
		setPoints(arr);
	}
	/**
	 * Copy constractor
	 * @param p the path that we want to copy.
	 */
	public Path(Path p) {
		setPoints(p.Points);
	}
	//getters and setters
	public ArrayList<Point3D> getPoints() {
		return Points;
	}
	public void setPoints(ArrayList<Point3D> array) {
		Points.clear();
		Points.addAll(array);
	}
	/**
	 * calculate the distance of the path in meters.
	 * @return double that represent the path distance in meters.
	 */
	public double GetDist() {
		double dist=0;
		Circle c;
		if(Points.size()<=1)return 0;
		for(int i=0;i<Points.size();i++) {
			try {
				c=new Circle(Points.get(i),1);
				dist+=c.distance3D(Points.get(i+1));
			}catch(IndexOutOfBoundsException e) {}
		}
		return dist;
	}
}
