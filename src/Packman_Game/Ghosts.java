package Packman_Game;

import Geom.Point3D;

public class Ghosts {
	private int id;
	private Point3D pos;
	private double speed;
	private double radius;
	public Ghosts(int id,Point3D pos,double speed,double radius) {
		setId(id);
		setPos(pos);
		setSpeed(speed);
		setRadius(radius);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Point3D getPos() {
		return pos;
	}

	public void setPos(Point3D pos) {
		this.pos = pos;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
