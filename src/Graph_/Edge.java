package Graph_;



public class Edge
{
  private int _index;
  

  private double _weight;
  


  public Edge(int ind, double w)
  {
    _index = ind;
    _weight = w;
  }
  
  public String toString() { return _index + ":" + _weight; }
  
  public double getW() { return _weight; }
  public int getInd() { return _index; }
}
