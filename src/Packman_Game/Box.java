package Packman_Game;

import Geom.Point3D;

public class Box {
	private int ID;
	private Point3D LeftDown;
	private Point3D RightUp;
	public Box(int ID,Point3D ru,Point3D ld) {
		setLeftDown(ld);
		setRightUp(ru);
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
	public String toString() {
		return ID+","+RightUp.toString()+","+LeftDown.toString()+","+1;	
	}
	
}
