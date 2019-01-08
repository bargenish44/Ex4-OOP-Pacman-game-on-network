//package Graph_;
//
//import java.util.ArrayList;
//
//public class Graph_Algo {
//	public Graph_Algo() {
//	}
//
//	public static double diameter(Graph g) {
//		double ans = 0.0D;
//		for (int i = 0; i < g.size(); i++) {
//			double dist = dijkstra(g, i);
//			if (ans < dist) {
//				ans = dist;
//				System.out.println(i + ") " + ans);
//			}
//		}
//		return ans;
//	}
//
//	public static double radius(Graph g) {
//		double ans = Double.MAX_VALUE;
//		for (int i = 0; i < g.size(); i++) {
//			double dist = dijkstra(g, i);
//			if (ans > dist) {
//				ans = dist;
//				System.out.println(i + ") " + ans);
//			}
//		}
//		return ans;
//	}
//
//	public static double dijkstra(Graph g, String source) {
//		double ans = -1.0D;
//		int ind = g.getNodeIndexByName(source);
//		if (ind != -1) {
//			ans = dijkstra1(g, ind);
//		}
//		return ans;
//	}
//
//	private static double dijkstra(Graph g, int source) {
//		clearGraphData(g);
//		return dijkstra1(g, source);
//	}
//
//	private static void dijkstra_with_BL(Graph g, int source, ArrayList<Integer> bl) {
//		clearGraphData(g);
//		for (int i = 0; i < bl.size(); i++) {
//			int ind_bl = ((Integer) bl.get(i)).intValue();
//			Node c = g.getNodeByIndex(ind_bl);
//			get_info_color = 2;
//		}
//		dijkstra1(g, source);
//	}
//
//	private static double dijkstra1(Graph g, int source) {
//		Node src = g.getNodeByIndex(source);
//		get_info_color = 1;
//		ArrayList<Node> grays = new ArrayList();
//		grays.add(src);
//		int non_white = 0;
//		for (int i = 0; i < g.size(); i++) {
//			if (getNodeByIndexget_info_color != 0) {
//				non_white++;
//			}
//		}
//		double ans = -1.0D;
//		while ((!grays.isEmpty()) && (non_white < g.size())) {
//			double min = Double.MAX_VALUE;
//			Node min_node = null;
//			Node source_node = null;
//			int min_ind = 0;
//			for (int i = 0; i < grays.size(); i++) {
//				Node cr = (Node) grays.get(i);
//				double dist_cr = get_info_dist;
//				ArrayList<Edge> ni = cr.get_ni();
//				for (int a = 0; a < ni.size(); a++) {
//					Edge e_cr = (Edge) ni.get(a);
//					Node wi = g.getNodeByIndex(e_cr.getInd());
//					if (get_info_color == 0) {
//						double d1 = dist_cr + e_cr.getW();
//						if (d1 < min) {
//							min_node = wi;
//							min = d1;
//							source_node = cr;
//							min_ind = i;
//						}
//					}
//				}
//			}
//			non_white++;
//			get_info_color = 1;
//			get_info_temp_path.addAll(get_info_temp_path);
//			get_info_temp_path.add(source_node.get_name());
//			get_info_dist = min;
//			ans = min;
//			grays.add(min_node);
//			get_info_count_ni += 1;
//			if (get_info_count_ni == source_node.degree()) {
//				get_info_color = 2;
//				grays.remove(min_ind);
//			}
//		}
//		return ans;
//	}
//
//	public static void clearGraphData(Graph g) {
//		g.clear_meta_data();
//	}
//}
