package Packman_Game;

import Geom.Point3D;
/**
 * This class represents BOX- box have 2 points and it simulate obstacle that the player can't walk through it.
 * @author Bar Genish
 * @author Elyashiv Deri
 * @author lioz elmalem
 */
public class Box {
	private int ID;
	private Point3D LeftDown;
	private Point3D RightUp;
	/**
	 * Regular constractor.
	 * @param ID the number of the box.
	 * @param ru the right up point of the box.
	 * @param ld the left down point of the box.
	 */
	public Box(int ID,Point3D ru,Point3D ld) {
		setLeftDown(ld);
		setRightUp(ru);
	}
	/**
	 * Copy constractor.
	 * @param box the box that we want to copy.
	 */
	public Box(Box box) {
		setID(box.ID);
		setLeftDown(box.LeftDown);
		setRightUp(box.RightUp);
	}
	public Point3D getLeftDown() {
		return LeftDown;
	}
	public void setLeftDown(Point3D leftDown) {
		LeftDown = leftDown;
	}
	public Point3D getRightUp() {
		return RightUp;
	}
	public void setRightUp(Point3D rightUp) {
		RightUp = rightUp;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * write the box as string.
	 * @return string of the Box.
	 */
	public String toString() {
		return ID+","+RightUp.toString()+","+LeftDown.toString()+","+1;	
	}
}
