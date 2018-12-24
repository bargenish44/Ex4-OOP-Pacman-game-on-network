package Packman_Game;

import Geom.Path;
import Geom.Point3D;

public class Packman {
	/**
	 * This class represents Packman- robot on space that can move.
	 * every Packman have id, point3D , speed , radius, score, path and time(the time that he stay in the same spot).
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private int ID;
	private Point3D orinet;
	private double speed;
	private double Radius;
	private Path path;
	private double score=0;
	private Time time; 

	public Packman(int ID,double x,double y,double z, double speed, double Radius) {//constractors
		setOrinet(new Point3D(x, y, z));
		setSpeed(speed);
		setRadius(Radius);
		setID(ID);
		setPath(new Path());
		time=new Time();
	}
	public Packman(int ID,Point3D p,double spped,double Raduis) {
		setID(ID);
		setOrinet(p);
		setSpeed(spped);
		setRadius(Raduis);
		setPath(new Path());
		time=new Time();
	}
	public Packman(Packman ot) {
		setID(ot.getID());
		setOrinet(ot.getOrinet());
		setSpeed(ot.getSpeed());
		setRadius(ot.getRadius());
		setPath(ot.getPath());
		setTime(ot.getTime());
		setScore(ot.getScore());
	}
	public Point3D getOrinet() {//getters and setters
		return orinet;
	}
	public void setOrinet(Point3D orinet) {
		this.orinet = orinet;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getRadius() {
		return Radius;
	}
	public void setRadius(double radius) {
		Radius = radius;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score += score;
	}
	public void resetScore() {
		this.score=0;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	/**
	 * write the Packman as string.
	 * @return string of the Packman.
	 */
	public String toString() {
		return ID+","+orinet.toString()+","+speed+","+Radius;
	}
}
