package Packman_Game;

import Geom.Point3D;

public class Box {
	private Point3D LeftUp;
	private Point3D LeftDown;
	private Point3D RightUp;
	private Point3D RightDown;
	public Box(Point3D lu,Point3D ld,Point3D ru,Point3D rd) {
		setLeftDown(ld);
		setLeftUp(lu);
		setRightDown(rd);
		setRightUp(ru);
	}
	public Point3D getLeftUp() {
		return LeftUp;
	}
	public void setLeftUp(Point3D leftUp) {
		LeftUp = leftUp;
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
	public Point3D getRightDown() {
		return RightDown;
	}
	public void setRightDown(Point3D rightDown) {
		RightDown = rightDown;
	}
}
