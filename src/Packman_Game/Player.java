package Packman_Game;

import Geom.Path;
import Geom.Point3D;

public class Player {
	/**
	 * This class represents Player- the packman that we control.
	 * every Player have point3D , speed , radius, score and path.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private Point3D orinet;
	private double speed;
	private double Radius;
	private Path path;
	private double score=0;

	public Player(double x,double y,double z, double speed, double Radius) {//constractors
		setOrinet(new Point3D(x, y, z));
		setSpeed(speed);
		setRadius(Radius);
		setPath(new Path());
	}
	public Player(Point3D p,double speed,double Raduis) {
		setOrinet(p);
		setSpeed(speed);
		setRadius(Raduis);
		setPath(new Path());
	}
	public Player(Player ot) {
		setOrinet(ot.getOrinet());
		setSpeed(ot.getSpeed());
		setRadius(ot.getRadius());
		setPath(ot.getPath());
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
	public Path getPath() {
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
	/**
	 * write the Packman as string.
	 * @return string of the Packman.
	 */
	public String toString() {
		return "Player pos is: "+orinet.toString()+", Player speed is: "+speed+", Player radius is: "+Radius+", Player score is: "+getScore();
	}
}