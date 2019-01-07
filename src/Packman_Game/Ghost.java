package Packman_Game;

import javax.swing.ImageIcon;

import Geom.Point3D;

public class Ghost {
	private int ID;
	private Point3D pos;
	private double speed;
	private double radius;
	private ImageIcon ghostimage;
	public Ghost(int id,Point3D pos,double speed,double radius) {
		setID(id);
		setPos(pos);
		setSpeed(speed);
		setRadius(radius);
		setGhostimage(new ImageIcon("ghost.png"));
	}
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
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
	public ImageIcon getGhostimage() {
		return ghostimage;
	}
	public void setGhostimage(ImageIcon ghostimage) {
		this.ghostimage = ghostimage;
	}
	/**
	 * write the Ghost as string.
	 * @return string of the Ghost.
	 */
	public String toString() {
		return ID+","+pos.toString()+","+speed+","+radius;
	}
}
