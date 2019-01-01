package Coords;


import Geom.Point3D;

public class convert {
	private int mapWidth;
	private int mapHeight;
	private double mapLongitudeStart;
	private double mapLatitudeStart;
	private double mapLongitude;
	private double mapLatitude;
	
	
	public convert(int mapWidth,int mapHeight,double mapLongitudeStart,double mapLatitudeStart,double mapLongitudeEnd,double mapLatitudeEnd) {
		this.mapWidth=mapWidth;
		this.mapHeight=mapHeight;
		this.mapLongitudeStart=mapLongitudeStart;
		this.mapLatitudeStart=mapLatitudeStart;
		this.mapLongitude=mapLongitudeEnd-mapLongitudeStart;
		this.mapLatitude=mapLatitudeStart-mapLatitudeEnd;
		
	}
	 

	public Point3D PointPix2Gps(Point3D p) {
		double xPIX=p.x();
		double yPIX=p.y();
		   double x=xPIX*mapLongitude ;	
		   x=x/(mapWidth);	
		   x=x +mapLongitudeStart;
	       double y=yPIX*mapLatitude;
	       y=y/mapHeight;
	       y=y-mapLatitudeStart;
	       y=y*(-1);
	       Point3D p1=new Point3D(y,x,0);
	       return p1;
	}

	
	public Point3D PointGps2Pix(Point3D p) {
	    double x,y;
	    x=p.y() - mapLongitudeStart;
	    y = mapLatitudeStart-p.x();
	    int x1 = (int) (mapWidth*(x/mapLongitude));
	    int y1 = (int) (mapHeight*(y/mapLatitude));
	    Point3D p1=new Point3D(x1,y1,0);
        return p1;
	}

	
	public void setFrame(int mapWidth,int mapHeight) {
		this.mapHeight=mapHeight;
		this.mapWidth=mapWidth;
	}

}
