package Algorithm;

import java.util.ArrayList;

import Geom.*;
import Packman_Game.*;

public class findbestpoint {
	private Game game;

	public findbestpoint(Game g) {
		setGame(g);
	}	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Point3D findspot(int width,int hight) {
		Map map=new Map();
		Point3D leftup=new Point3D(0,0);
		Point3D rightdown=new Point3D(width,hight);
		ArrayList<Fruit>arr=new ArrayList<>();
		for(int i=0;i<game.getFruitArr().size();i++) {
			arr.add(new Fruit(game.getFruitArr().get(i)));
			arr.get(i).setOrient(map.CoordsToPixel(arr.get(i).getOrient(), width, hight));
		}
		ArrayList<Packman>array=new ArrayList<>();
		for(int i=0;i<array.size();i++) {
			array.add(new Packman(game.getPackmanArr().get(i)));
			array.get(i).setOrinet(map.CoordsToPixel(array.get(i).getOrinet(), width, hight));
		}
		Point3D middle=new Point3D(width/2,hight/2);
		Point3D[]bestside=sidescomperator(arr, leftup, middle, middle, rightdown, new Point3D(leftup.ix(),middle.iy()), new Point3D(middle.ix(),rightdown.iy()), new Point3D(middle.ix(),leftup.iy()), new Point3D(rightdown.ix(),middle.iy()));
		leftup=new Point3D(bestside[0]);
		rightdown=new Point3D(bestside[1]);
		middle=new Point3D((leftup.ix()+rightdown.ix())/2,(leftup.iy()+rightdown.iy())/2);
		bestside=sidescomperator(arr, leftup, middle, middle, rightdown, new Point3D(leftup.ix(),middle.iy()), new Point3D(middle.ix(),rightdown.iy()), new Point3D(middle.ix(),leftup.iy()), new Point3D(rightdown.ix(),middle.iy()));
		Point3D best=putsonpackman(array,bestside[0],bestside[1]);
		if(best.x()!=-1) {
			best=map.PixelToCoords(best.ix(), best.iy(), best.iz(), width, hight);
			return best;
		}
		else {
			for(int i=0;i<arr.size();i++) {
				best=arr.get(i).getOrient();
				if((best.x()<=rightdown.ix()&&best.x()>=leftup.ix())&&(best.y()<=rightdown.iy()&&best.y()>=leftup.iy())) {
					best=map.PixelToCoords(best.ix(), best.iy(), best.iz(), width, hight);
					return best;
				}
			}
		}
		return map.PixelToCoords(0, 0, 0, width, hight);
	}
	private int countfruits(ArrayList<Fruit>arr,Point3D leftup, Point3D rightdown) {
		int count=0;
		for(int i=0;i<arr.size();i++) {
			Point3D tmp=arr.get(i).getOrient();
			if((tmp.x()<=rightdown.ix()&&tmp.x()>=leftup.ix())&&(tmp.y()<=rightdown.iy()&&tmp.y()>=leftup.iy()))
				count++;
		}
		return count;
	}
	private Point3D putsonpackman(ArrayList<Packman>array,Point3D leftup, Point3D rightdown) {
		for(int i=0;i<array.size();i++) {
			Point3D tmp=array.get(i).getOrinet();
			if((tmp.x()<=rightdown.ix()&&tmp.x()>=leftup.ix())&&(tmp.y()<=rightdown.iy()&&tmp.y()>=leftup.iy()))
				return tmp;
		}
		return new Point3D(-1,-1,-1);
	}

	private Point3D[] sidescomperator(ArrayList<Fruit>arr,Point3D leftup0,Point3D rightdown0,Point3D leftup1,Point3D rightdown1,Point3D leftup2,Point3D rightdown2,Point3D leftup3,Point3D rightdown3) {
		int count0=countfruits(arr,leftup0,rightdown0);
		int count1=countfruits(arr,leftup1,rightdown1);
		int count2=countfruits(arr,leftup2,rightdown2);
		int count3=countfruits(arr,leftup3,rightdown3);
		Point3D[]array =new Point3D[2];
		if(count0>count1&&count0>count2&&count0>count3) {
			array[0]=leftup0;
			array[1]=rightdown0;
		}
		else if(count1>count0&&count1>count2&&count1>count3) {
			array[0]=leftup1;
			array[1]=rightdown1;
		}
		else if(count2>count0&&count2>count1&&count2>count3) {
			array[0]=leftup2;
			array[1]=rightdown2;
		}
		else{
			array[0]=leftup3;
			array[1]=rightdown3;
		}
		return array;
	}
}
