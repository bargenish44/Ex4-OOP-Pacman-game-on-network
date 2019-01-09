package Algorithm;

import Coords.Cords;
import Coords.LatLonAlt;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;
import Robot.Fruit;

import java.io.Serializable;
import java.util.Date;



public class Packman
  implements GIS_element, Serializable
{
  private LatLonAlt _pos;
  private double _ori;
  private double _speed;
  private Meta_data _meta_data;
  private long _start_time;
  private double _radius;
  public double _currTime = 0.0D;
  private static int _count = 0;
  private int _id;
  
  public Packman(LatLonAlt start, double speed)
  {
    _speed = speed;
    _pos = new LatLonAlt(start);
    initTime();
    _id = (_count++);
    _radius = 1.0D;
  }
  
  public Packman(String line) { String[] arr = line.split(",");
    _id = Integer.parseInt(arr[1]);
    String p = arr[2] + "," + arr[3] + "," + arr[4];
    _pos = new LatLonAlt(p);
    _speed = Double.parseDouble(arr[5]);
    _start_time = 0L;
    _radius = Double.parseDouble(arr[6]);
  }
  

  public Packman(Packman packman) { this(packman.toString()); }
  
  public String toString() {
    String ans = "P," + _id + "," + getLocation() + "," + _speed + "," + _radius;
    return ans;
  }
  
  public String toString1() { String ans = "M," + _id + "," + getLocation() + "," + _speed + "," + _radius;
    return ans;
  }
  
  public String toString2() { String ans = "G," + _id + "," + getLocation() + "," + _speed + "," + _radius;
    return ans; }
  
  public LatLonAlt getLocation() { return _pos; }
  
  public void setLocation(LatLonAlt l) { _pos = l; }
  
  public void initTime() { _start_time = new Date().getTime(); }
  
  public long delta_time() { long dt_ms = new Date().getTime() - _start_time;
    return dt_ms;
  }
  
  public double setOrientation(LatLonAlt p) { double ang = 0.0D;
    ang = _pos.azimuth_elevation_dist(p)[0];
    setOrientation(ang);
    return ang;
  }
  
  public double distance3D(Fruit f) { double d = 0.0D;
    d = getLocation().GPS_distance(f.getLocation());
    return d;
  }
  
  public double distance3D(Packman f) { double d = 0.0D;
    d = getLocation().GPS_distance(f.getLocation());
    return d;
  }
  
  public Geom_element getGeom() { return (Geom_element) _pos; }
  
  public Meta_data getData() { return _meta_data; }
  

  public void translate(Point3D vec) { _pos.add(vec); }
  
  public double getOrientation() { return _ori; }
  public void setOrientation(double ori_deg) { _ori = ori_deg; }
  
  public double get_speed() { return _speed; }
  
  public void set_speed(double _speed) {
    this._speed = _speed;
  }
  
  public double get_radius() { return _radius; }
  
  public void set_radius(double _radius) {
    this._radius = _radius;
  }
  

  public void move(double dt)
  {
    double d = dt * _speed / 1000.0D;
    double[] ll1 = { _pos.lat(), _pos.lon(), _pos.alt() };
    
    double[] ans = Cords.offsetLatLonAzmDist(ll1, getOrientation(), d);
    _pos = new LatLonAlt(ans[0], ans[1], _pos.alt());
  }
  
  public double[] getOrientation(LatLonAlt l) { double[] ll1 = { _pos.lat(), _pos.lon(), _pos.alt() };
    double[] ll2 = { l.lat(), l.lon(), l.alt() };
    double[] ans = Cords.azmDist(ll1, ll2);
    return ans;
  }
}
