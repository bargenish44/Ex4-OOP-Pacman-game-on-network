package Graph_;

import java.util.ArrayList;




public class Node
{
  private static int _counter = 0;
  private int _id;
  private Node_Info _info;
  private ArrayList<Edge> _ni;
  private String _name;
  
  public Node(String s) {
    set_id(_counter++);
    set_ni(new ArrayList());
    _info = new Node_Info();
    set_name(s); }
  
  public static int getCounter() { return _counter; }
  
  public int get_id() { return _id; }
  
  public void set_id(int _id) {
    this._id = _id;
  }
  
  public Node_Info get_info() { return _info; }
  
  public void set_pos(Node_Info ni) {
    _info = ni;
  }
  
  public ArrayList<Edge> get_ni() { return _ni; }
  

  public void set_ni(ArrayList<Edge> _ni) { this._ni = _ni; }
  
  public boolean hasEdge(int id) {
    boolean ans = false;
    for (int i = 0; i < _ni.size(); i++) {
      if (id == ((Edge)_ni.get(i)).getInd()) ans = true;
    }
    return ans;
  }

  public boolean add(Node n, double w)
  {
    boolean did = false;
    int nid = n.get_id();
    if (!hasEdge(nid)) {
      did = true;
      
      Edge e = new Edge(nid, w);
      _ni.add(e);
    }
    return did; }
  
  public double getDist() { return _info._dist; }
  public ArrayList<String> getPath() { return _info._temp_path; }
  

  public int degree() { return _ni.size(); }
  
  public String toString() { String ans = "Node: " + _id + ", name, " + _name + " ,|ni|, " + _ni.size() + " , " + _info;
    return ans;
  }
  
  public String get_name() { return _name; }
  
  private void set_name(String name) {
    _name = name;
  }
}
