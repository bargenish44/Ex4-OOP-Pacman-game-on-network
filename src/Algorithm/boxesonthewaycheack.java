package Algorithm;

import java.util.ArrayList;

import Geom.Point3D;
import Packman_Game.*;

public class boxesonthewaycheack {
	private Game game;
	public boxesonthewaycheack(Game game){
		this.game=game;
	}
	public boolean cheak(Fruit fruit,int width,int hight) {
		Map map=new Map();
		Player p=new Player(game.getPlayer());
		Fruit f=new Fruit(fruit);
		ArrayList<Box>boxs=new ArrayList<>();
		for(int i=0;i<game.getBoxarr().size();i++) {
			boxs.add(new Box(game.getBoxarr().get(i)));
			boxs.get(i).setLeftDown(map.CoordsToPixel(boxs.get(i).getLeftDown(), width, hight));
			boxs.get(i).setRightUp(map.CoordsToPixel(boxs.get(i).getRightUp(), width, hight));
		}
		p.setOrinet(map.CoordsToPixel(p.getOrinet(), width, hight));
		f.setOrient(map.CoordsToPixel(f.getOrient(), width, hight));
		Point3D Ppoint=p.getOrinet();
		Point3D Fpoint=f.getOrient();
		if(Ppoint.ix()==Fpoint.ix()) {
			if(Ppoint.iy()==Fpoint.iy())
				return false;
			for(int i=0;i<boxs.size();i++) {
				if(between(Ppoint.ix(),Fpoint.ix(),boxs.get(i).getLeftDown().ix()))
					return true;
				if(between(Ppoint.ix(),Fpoint.ix(),boxs.get(i).getRightUp().ix()))
					return true;
			}
		}
		if(Ppoint.iy()==Fpoint.iy()) {
			for(int i=0;i<boxs.size();i++) {
				if(between(Ppoint.iy(),Fpoint.iy(),boxs.get(i).getLeftDown().iy()))
					return true;
				if(between(Ppoint.iy(),Fpoint.iy(),boxs.get(i).getRightUp().iy()))
					return true;
			}
		}
		else {
			for(int i=0;i<boxs.size();i++) {
				int maxx=Math.max(boxs.get(i).getLeftDown().ix(), boxs.get(i).getRightUp().ix());
				int minx=Math.min(boxs.get(i).getLeftDown().ix(), boxs.get(i).getRightUp().ix());
				int maxy=Math.max(boxs.get(i).getLeftDown().iy(), boxs.get(i).getRightUp().iy());
				int miny=Math.min(boxs.get(i).getLeftDown().iy(), boxs.get(i).getRightUp().iy());
				if(betweens(Fpoint.iy(),maxy,miny)==true)
					if(between(Ppoint.ix(),Fpoint.ix(),maxx)==true&&between(Ppoint.ix(),Fpoint.ix(),minx)==true)
						return true;
				if(betweens(Fpoint.ix(),maxx,minx)==true)
					if(between(Ppoint.iy(),Fpoint.iy(),maxy)==true&&between(Ppoint.iy(),Fpoint.iy(),miny)==true)
						return true;
				if(betweens(Fpoint.ix(),maxx,minx)==true)
//					if(Fpoint.iy()<=maxy&&Ppoint.iy()<maxy)//לסדר
						if(Ppoint.ix()>maxx&&Ppoint.iy()>=miny)
							return true;
				if(betweens(Ppoint.ix(),maxx,minx)==true)
					if(Fpoint.iy()<=maxy&&Ppoint.iy()<maxy)
//						if(Fpoint.ix()>maxx&&Fpoint.iy()>=miny)//לסדר
							return true;//לסדר מקרים בהם הפרי/פקמן נמצא בין האיקס של הקופסאות והשני נמצא בין  או מעל הויי ומקרה בו הפקמן/פרי נמצא ממש בפינה העליונה והפרי נמצא במקום לא טוב וזה עדיין מחזיר false. 

			}
		}
		//לבדוק אם הוא נמצא כשהאיקס והווי שונים
		return false;
	}
	private boolean between(int xstart,int xend,int xmiddle) {
		if(xmiddle<=xend&&xmiddle>=xstart)
			return true;
		if(xmiddle>=xend&&xmiddle<=xstart)
			return true;
		return false;
	}
	private boolean betweens(int x,int xmiddlemax,int xmiddlemin) {
		if(x<=xmiddlemax&&x>=xmiddlemin)
			return true;
		return false;
	}
}
