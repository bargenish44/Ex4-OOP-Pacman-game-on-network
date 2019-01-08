package Graph_;

import java.util.ArrayList;


public class Node_Info
{
  public static final int WHITE = 0;
  public static final int GRAY = 1;
  public static final int BLACK = 2;
  public static final int ERR = -1;
  public int _color;
  public double _dist;
  public ArrayList<String> _temp_path;
  public int _count_ni;
  
  public Node_Info()
  {
    this(null);
  }
  
  public Node_Info(Point3D p) { init(); }
  

  public void init()
  {
    _color = 0;
    _dist = 0.0D;
    _temp_path = new ArrayList();
    _count_ni = 0;
  }
  
  public String toString() { String ans = " dist," + _dist + ", path:";
    for (int i = 0; i < _temp_path.size(); i++) {
      ans = ans + "," + (String)_temp_path.get(i);
    }
    return ans;
  }
}
