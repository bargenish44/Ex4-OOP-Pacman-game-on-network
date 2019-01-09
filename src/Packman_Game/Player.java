package Packman_Game;

import javax.swing.ImageIcon;

import Geom.Path;
import Geom.Point3D;

public class Player {
	/**
	 * This class represents Player- the packman that we control.
	 * every Player have point3D , speed , radius, score and path.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	private int ID;
	private Point3D pos;
	private double speed;
	private double Radius;
	private Path path;
	private double score=0;
	private double azimuth=0;
	private ImageIcon playerimage;
	/**
	 * Regular constractor.
	 * @param id the number of the player.
	 * @param x the x point of the player.
	 * @param y the y point of the player.
	 * @param z the z point of the player.
	 * @param speed the speed of the player.
	 * @param Radius the radius of the player.
	 */
	public Player(int id,double x,double y,double z, double speed, double Radius) {
		setID(id);
		setPos(new Point3D(x, y, z));
		setSpeed(speed);
		setRadius(Radius);
		setPath(new Path());
		setAzimuth(0);
		setPlayerimage(new ImageIcon("player.png"));
	}
	/**
	 * Regular constractor.
	 * @param id the number of the player.
	 * @param p the point of the player.
	 * @param speed the speed of the player.
	 * @param Raduis the raduis of the player.
	 */
	public Player(int id,Point3D p,double speed,double Raduis) {
		setID(id);
		setPos(p);
		setSpeed(speed);
		setRadius(Raduis);
		setPath(new Path());
		setAzimuth(0);
		setPlayerimage(new ImageIcon("player.png"));
	}
	/**
	 * Copy constractor.
	 * @param ot the player that we want to copy.
	 */
	public Player(Player ot) {
		setID(ot.ID);
		setPos(ot.getPos());
		setSpeed(ot.getSpeed());
		setRadius(ot.getRadius());
		setPath(ot.getPath());
		setScore(ot.getScore());
		setPlayerimage(ot.getPlayerimage());
	}
	public Point3D getPos() {//getters and setters
		return pos;
	}
	public void setPos(Point3D orinet) {
		pos = orinet;
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
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(double az) {
		azimuth = az;
		while(azimuth<0||azimuth>360) {
			if(azimuth<0)azimuth+=360;
			if(azimuth>360)azimuth-=360;
		}
	}
	public ImageIcon getPlayerimage() {
		return playerimage;
	}
	public void setPlayerimage(ImageIcon playerimage) {
		this.playerimage = playerimage;
	}
	/**
	 * write the Player as string.
	 * @return string of the Player.
	 */
	public String toString() {
		return ID+","+pos.toString()+","+speed+","+Radius;
	}
}