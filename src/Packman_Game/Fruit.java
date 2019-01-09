package Packman_Game;


import javax.swing.ImageIcon;

import Geom.Point3D;

public class Fruit {
	/**
	 * This class represents Fruit- target on space every fruit have id, point3D,weight and time(the time that he were eaten in).
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 * @author lioz elmalem
	 */
	private int ID;
	private Point3D pos;
	private double Weight;
	private ImageIcon fruitimage;
	private Time time;
	/**
	 * Regular constractor.
	 * @param ID the fruit number
	 * @param x the x point of the fruit
	 * @param y the y point of the fruit
	 * @param z the z point of the fruit
	 * @param weight the weight of the fruit
	 */
	public Fruit(int ID,double x,double y,double z, double weight) {
		setID(ID);
		setPos(new Point3D(x, y,z));
		setWeight(weight);
		time=new Time();
		setFruitimage(new ImageIcon("cherry.png"));
	}
	/**
	 * Regular constractor
	 * @param ID the number of the fruit
	 * @param P the point of the fruit
	 * @param weight the weight of the fruit
	 */
	public Fruit(int ID,Point3D P,double weight) {
		setID(ID);
		setPos(P);
		setWeight(weight);
		time=new Time();
		setFruitimage(new ImageIcon("cherry.png"));
	}
	/**
	 * Copy constractor.
	 * @param ot create a deep copy of ot fruit
	 */
	public Fruit(Fruit ot) {
		setID(ot.getID());
		setPos(ot.getPos());
		setWeight(ot.getWeight());
		setTime(ot.getTime());
		setFruitimage(ot.getFruitimage());
	}
	public ImageIcon getFruitimage() {//getters and setters
		return fruitimage;
	}
	public void setFruitimage(ImageIcon fruitimage) {
		this.fruitimage = fruitimage;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Point3D getPos() {
		return pos;
	}
	public void setPos(Point3D orient) {
		pos = orient;
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
		return ID+","+pos.toString()+","+Weight;
	}
}
