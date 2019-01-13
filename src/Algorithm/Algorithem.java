package Algorithm;

import java.awt.geom.Line2D;

import java.util.ArrayList;


import Geom.Point3D;
import Packman_Game.Game;
import Packman_Game.Map;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
/**
 * 
 * @author Netanel Ben-Isahar
 * @author Daniel Abergel
 *this class is responsible of creating the path that avoid gettiing into the boxes from the player location
 * to a fruit 
 */
public class Algorithem 
{
	ArrayList<Point3D> PixelList ;
	ArrayList<Line2D> Lines ; 
	public ArrayList<Point3D> PixelInclude ;
	public ArrayList<String> shortestPath;
	public ArrayList<Point3D> point3DInclude;
	Map map ; 

	/**
	 * this constructor is responsible of creating an algorithm object
	 * @param game represents our game
	 * @param map represents our map
	 */
	public Algorithem(Game game , Map map)
	{ 
		point3DInclude = new ArrayList<Point3D>();
		PixelList = PF.getPixels(game, map) ;
		PixelInclude = new ArrayList<Point3D>();
		Lines = new ArrayList<Line2D>();
		UpdateLines(PixelList);
		shortestPath = new ArrayList<String>();
		this.map = map;
		RemovePoints();
	}
	/**
	 * init all parameters 
	 * @param game represents our game
	 * @param map represents our map
	 */
	public void init(Game game , Map map)
	{
		PointFinder  PF = new PointFinder() ; 
		point3DInclude.clear();
		PixelList = PF.getPixels(game, map) ;
		PixelInclude.clear();
		Lines.clear();
		UpdateLines(PixelList);
		shortestPath.clear();
		this.map = map;
		RemovePoints();
	}


	/**
	 * this function responsible of update the PixelInclude&shortestPath arraysõ 
	 * @param myLocation represents the player location.
	 * @param destLocation represents a fruit location.
	 */
	public double StartAlgo(Point3D myLocation,Point3D destLocation)
	{

	

		PixelInclude.add(myLocation);
		PixelInclude.addAll(PixelList);
		PixelInclude.add(destLocation);
		int size = PixelInclude.size() ;


		Graph G = new Graph();
		String Source = "0";
		String Target = "" + (size-1); 

		G.add(new Node(Source)); // Node "a" (0)
		for(int i=1;i<size-1;i++) 
		{
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(Target)); // Node "b" (15)

		for (int i = 0; i < size-1; i++) {
			for (int j = 0; j < size; j++) 
			{
				if(i != j)
				{
					if(DidISeehim(PixelInclude.get(i), PixelInclude.get(j)))
					{
//						System.out.println(i + ">>" + j ); if you want to know how the graph look print this .
						G.addEdge("" + i, "" + j, PixelInclude.get(i).dis(PixelInclude.get(j)));
					}
				}

			}
		}

		Graph_Algo.dijkstra(G, Source);

		for (int i = 1; i < PixelInclude.size()-1; i++) 
		{

			switch (PixelInclude.get(i).v()) {
			case 1:
				PixelInclude.get(i).set_PixelX(PixelInclude.get(i).get_Point3DX()-10);
				PixelInclude.get(i).set_PixelY(PixelInclude.get(i).get_Point3DY()+10);
				break;
			case 2:
				PixelInclude.get(i).set_PixelX(PixelInclude.get(i).get_Point3DX()+10);
				PixelInclude.get(i).set_PixelY(PixelInclude.get(i).get_Point3DY()-10);
				break;
			case 3:
				PixelInclude.get(i).set_PixelX(PixelInclude.get(i).get_Point3DX()+10);
				PixelInclude.get(i).set_PixelY(PixelInclude.get(i).get_Point3DY()+10);
				break;
			case 0:
				PixelInclude.get(i).set_PixelX(PixelInclude.get(i).get_Point3DX()-10);
				PixelInclude.get(i).set_PixelY(PixelInclude.get(i).get_Point3DY()-10);
				break;
			}

		}

		for (int i = 0; i < PixelInclude.size(); i++) {
			point3DInclude.add(new Point3D(map.Pixel2GPSPoint(PixelInclude.get(i).get_Point3DX(), PixelInclude.get(i).get_Point3DY())));

		}
		Node b = G.getNodeByName(Target);
		System.out.println("***** Graph Demo for OOP_Ex4 *****");
		System.out.println(b);
		System.out.println("Dist: "+b.getDist());
		shortestPath = b.getPath();
		shortestPath.add("" + (point3DInclude.size()-1));
		for(int i=0;i<shortestPath.size();i++) {
			System.out.print(","+shortestPath.get(i));
		}
		return b.getDist();

	}






	/**
	 * 	this is a boolean function that decide if a point "can see" another point.
	 * @param p1 represents the first point.
	 * @param p2 represents the second point.
	 * @return true if a point can see another point.
	 */

	private boolean DidISeehim(Point3D p1 , Point3D p2) 
	{
		Line2D Line = new Line2D.Double(p1.get_Point3DX(),p1.get_Point3DY(),p2.get_Point3DX(),p2.get_Point3DY());
		boolean ans = true ; 
		boolean checkIFonLine = true;
		for (int i = 0; i < Lines.size() && ans; i++)
		{
			checkIFonLine = true ; 
			Point3D pixel1Line = new Point3D(Lines.get(i).getX1(),Lines.get(i).getY1());
			Point3D pixel2Line = new Point3D(Lines.get(i).getX2(),Lines.get(i).getY2());
			if(p1.equals(pixel1Line) || p1.equals(pixel2Line) || p2.equals(pixel1Line) || p2.equals(pixel2Line)) checkIFonLine = false ;
			if(checkIFonLine && IFintersects(Line,Lines.get(i))) ans = false ; 
		}
		return ans;
	}
	
	/**
	 * this function check if two segments intersects.
	 * @param line1 represents the first segment.
	 * @param line2 represents the second segment.
	 * @return true if wo segments intersects.
	 */
	private boolean IFintersects(Line2D line1 , Line2D line2 )
	{
		return line1.intersectsLine(line2);
	}
	
	/**
	 * this function responsible of changing the points of the corner of the box to a meter away.
	 * @param Pixels represents the pixels array.
	 */
	private void UpdateLines(ArrayList<Point3D> Pixels) {

		for (int i = 0; i < Pixels.size(); i = i + 4) 
		{

			Lines.add(new Line2D.Double(Pixels.get(i).get_Point3DX(),Pixels.get(i).get_Point3DY(),Pixels.get(i+3).get_Point3DX(),Pixels.get(i+3).get_Point3Y())); // left wall
			Lines.add(new Line2D.Double(Pixels.get(i).get_Point3DX(),Pixels.get(i).get_Point3DY(),Pixels.get(i+2).get_Point3DX(),Pixels.get(i+2).get_Point3DY())); // down wall
			Lines.add(new Line2D.Double(Pixels.get(i+2).get_Point3DX(),Pixels.get(i+2).get_Point3DY(),Pixels.get(i+1).get_Point3DX(),Pixels.get(i+1).get_Point3DY())); // right wall
			Lines.add(new Line2D.Double(Pixels.get(i+3).get_Point3DX(),Pixels.get(i+3).get_Point3DY(),Pixels.get(i+1).get_Point3DX(),Pixels.get(i+1).get_Point3DY())); // up wall
			Lines.add(new Line2D.Double(Pixels.get(i).get_Point3DX(),Pixels.get(i).get_Point3DY(),Pixels.get(i+1).get_Point3DX(),Pixels.get(i+1).get_Point3DY()));
			Lines.add(new Line2D.Double(Pixels.get(i+2).get_Point3DX(),Pixels.get(i+2).get_Point3DY(),Pixels.get(i+3).get_Point3DX(),Pixels.get(i+3).get_Point3DY()));
		}

	}
	/**
	 *  this function responsible of removing the points that we don't need anymore.
	 */
	private void RemovePoints() {
		ArrayList<Point3D> RemoveList = new ArrayList<Point3D>(PixelList);
		for (int i = RemoveList.size()-1; 0 < i; i--) {
			for (int j = 0; j < PixelList.size(); j =j+4) 
			{
				try {
					if(RemoveList.get(i).get_Point3DX() > PixelList.get(j).get_Point3DX() && RemoveList.get(i).get_Point3DX() < PixelList.get(j+1).get_Point3DX() && RemoveList.get(i).get_Point3DY() < PixelList.get(j).get_Point3DY() && RemoveList.get(i).get_Point3DY() > PixelList.get(j+1).get_Point3DY())
					{
						RemoveList.remove(i);
						break;
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		PixelList = RemoveList ;
	}


	public int findShortest(Game game) {
		
		int index = 0 ;
		double min = Double.MAX_VALUE ; 
		for (int i = 0; i < game.getFruits().size(); i++) 
		{
			init(game, game.getGameMap());
			
			double dis =StartAlgo(game.getGameMap().GPSPoint2Pixel(game.getPlayer().getGps()), game.getGameMap().GPSPoint2Pixel(game.getFruits().get(i).getGps()));
			if(dis < min)
			{
				index = i ;
				min = dis ;
			}
		}
		return index; 
	}

}

