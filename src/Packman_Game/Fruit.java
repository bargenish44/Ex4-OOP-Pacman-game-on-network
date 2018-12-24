package Packman_Game;


import Geom.Point3D;

public class Fruit {
	/**
	 * This class represents Fruit- target on space every fruit have id, point3D,weight and time(the time that he were eaten in).
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private int ID;
	private Point3D orient;
	private double Weight;
	private Time time;
	public Fruit(int ID,double x,double y,double z, double weight) {//constractors
		setID(ID);
		setOrient(new Point3D(x, y,z));
		setWeight(weight);
		time=new Time();
	}
	public Fruit(int ID,Point3D P,double weight) {
		setID(ID);
		setOrient(P);
		setWeight(weight);
		time=new Time();
	}
	public Fruit(Fruit ot) {
		setID(ot.getID());
		setOrient(ot.getOrient());
		setWeight(ot.getWeight());
		setTime(ot.getTime());
	}
	public int getID() {//getters and setters
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Point3D getOrient() {
		return orient;
	}
	public void setOrient(Point3D orient) {
		this.orient = orient;
	}
	public double getWeight() {
		return Weight;
	}
	public void setWeight(double weight) {
		Weight = weight;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	/**
	 * write the Fruit as string.
	 * @return string of the Fruit.
	 */
	public String toString() {
		return ID+","+orient.toString()+","+Weight;
	}
}
