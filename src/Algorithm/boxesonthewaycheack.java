package Algorithm;

import java.util.ArrayList;

import Geom.Point3D;
import Packman_Game.*;

public class boxesonthewaycheack {
	public boolean cheak(Game game,Player player,Fruit fruit,int width,int hight) {
		Map map=new Map();
		Player p=new Player(player);
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
}
