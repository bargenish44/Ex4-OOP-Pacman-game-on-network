package Geom;

import java.util.ArrayList;
import Geom.Point3D;
/**
 * This class represents a path- arraylist of 3D point in space.
 * @author Bar Genish
 * @author Elyashiv Deri
 */
public class Path{
	private ArrayList<Point3D>arr=new ArrayList<Point3D>();

	public Path() {//constractor
		arr=new ArrayList<Point3D>();
	}
	public Path( ArrayList<Point3D>arr) {
		setArr(arr);
	}
	public Path(Path p) {//constractor
		setArr(p.arr);
	}
	//getters and setters
	public ArrayList<Point3D> getArr() {
		return arr;
	}
	public void setArr(ArrayList<Point3D> array) {
		arr.clear();
		arr.addAll(array);
	}
	/**
	 * calculate the distance of the path in meters.
	 * @return double that represent the path distance in meters.
	 */
	public double GetDist() {
		double dist=0;
		Circle c;
		if(arr.size()<=1)return 0;
		for(int i=0;i<arr.size();i++) {
			try {
				c=new Circle(arr.get(i),1);
				dist+=c.distance3D(arr.get(i+1));
			}catch(IndexOutOfBoundsException e) {}
		}
		return dist;
	}
}
