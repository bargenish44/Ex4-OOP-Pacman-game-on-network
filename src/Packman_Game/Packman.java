package Packman_Game;

import javax.swing.ImageIcon;

import Geom.Path;
import Geom.Point3D;

public class Packman {
	/**
	 * This class represents Packman- robot on space that can move.
	 * every Packman have id, point3D , speed , radius, score, path and time(the time that he stay in the same spot).
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	private int ID;
	private Point3D orinet;
	private double speed;
	private double Radius;
	private Path path;
	private double score=0;
	private Time time;
	private ImageIcon packmanimage;
	/**
	 * defult constractor.
	 * @param ID the count of the packman.
	 * @param x the x point of the packman.
	 * @param y the y point of the packman.
	 * @param z the z point of the packman.
	 * @param speed the packman speed.
	 * @param Raduis the raduis of the packman.
	 */
	public Packman(int ID,double x,double y,double z, double speed, double Radius) {
		setOrinet(new Point3D(x, y, z));
		setSpeed(speed);
		setRadius(Radius);
		setID(ID);
		setPath(new Path());
		time=new Time();
		setPackmanimage(new ImageIcon("pacman.jpg"));
	}
	/**
	 * defult constractor.
	 * @param ID the count of the packman.
	 * @param p the point of the packman.
	 * @param speed the packman speed.
	 * @param Raduis the raduis of the packman.
	 */
	public Packman(int ID,Point3D p,double speed,double Raduis) {
		setID(ID);
		setOrinet(p);
		setSpeed(speed);
		setRadius(Raduis);
		setPath(new Path());
		time=new Time();
		setPackmanimage(new ImageIcon("pacman.jpg"));
	}
	/**
	 * Copy constractor.
	 * @param ot other packman that we want to copy.
	 */
	public Packman(Packman ot) {
		setID(ot.getID());
		setOrinet(ot.getOrinet());
		setSpeed(ot.getSpeed());
		setRadius(ot.getRadius());
		setPath(ot.getPath());
		setTime(ot.getTime());
		setScore(ot.getScore());
		setPackmanimage(ot.getPackmanimage());
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
	public ImageIcon getPackmanimage() {
		return packmanimage;
	}
	public void setPackmanimage(ImageIcon packmanimage) {
		this.packmanimage = packmanimage;
	}
	/**
	 * write the Packman as string.
	 * @return string of the Packman.
	 */
	public String toString() {
		return ID+","+orinet.toString()+","+speed+","+Radius;
	}
}
