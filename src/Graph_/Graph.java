package Graph_;

import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Graph {
	public static final double EPS = 1.0E-6D;
	private ArrayList<Node> _nodes;
	private Random _rand;
	private int _edge_count = 0;

	public Graph() {
		_nodes = new ArrayList();
	}

	public void add(Node d) {
		if (!exist(d)) {
			_nodes.add(d);
		}
	}

	public int size() {
		return _nodes.size();
	}

	public String toString() {
		String ans = "";
		ans = ans + size() + "\n" + _edge_count;
		for (int i = 0; i < size(); i++) {
			Node cr = (Node) _nodes.get(i);
			ans = ans + cr + "\n";
		}
		return ans;
	}

	public void toFile() {
		long t = new Date().getTime();
		String name = "Graph_" + size() + "_" + _edge_count + "_" + t;
		toFile(name);
	}

	public void toFile(String f) {
		try {
			FileWriter fw = new FileWriter(f);
			PrintWriter os = new PrintWriter(fw);

			os.print(size() + "\n");
			for (int i = 0; i < size(); i++) {
				Node cr = (Node) _nodes.get(i);
				os.print(i + " " + cr.get_info() + "\n");
			}

			os.print(_edge_count);
			for (int i = 0; i < size(); i++) {
				Node cr = (Node) _nodes.get(i);
				ArrayList<Edge> ni = cr.get_ni();

				for (int a = 0; a < ni.size(); a++) {
					int index = ((Edge) ni.get(a)).getInd();
					double w = ((Edge) ni.get(a)).getW();
					if (index > i) {
						Node ot = (Node) _nodes.get(index);

						os.print("\n" + i + " " + index + " " + w);
					}
				}
			}
			os.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Err: unable to write Graph file named: " + f);
		}
	}

	public void clear_meta_data() {
		for (int i = 0; i < size(); i++)
			((Node) _nodes.get(i)).get_info().init();
	}

	public Node getNodeByIndex(int i) {
		Node ans = null;
		if ((i >= 0) && (i < size())) {
			ans = (Node) _nodes.get(i);
		}
		return ans;
	}

	public boolean exist(Node d) {
		return getNodeByName(d.get_name()) != null;
	}

	public int getNodeIndexByName(String s) {
		int ans = -1;
		for (int i = 0; (ans == -1) && (i < size()); i++) {
			Node cr = (Node) _nodes.get(i);
			String name = cr.get_name();
			if (s.equals(name)) {
				ans = i;
			}
		}
		return ans;
	}

	public Node getNodeByName(String s) {
		Node ans = null;
		int ind = getNodeIndexByName(s);
		if (ind != -1) {
			ans = (Node) _nodes.get(ind);
		}
		return ans;
	}

	public void addEdge(String a, String b, double w) {
		Node va = getNodeByName(a);
		Node vb = getNodeByName(b);
		if ((va != null) && (vb != null)) {
			va.add(vb, w);
			vb.add(va, w);
		}
	}
}
