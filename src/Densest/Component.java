package Densest;

import java.util.*;

public class Component {
    Map<Integer, ArrayList<Integer>> graph = null;
    double density = 0.0;
    int edgeNum = 0;
    int vertexNum = 0;

    public Component(Map<Integer, ArrayList<Integer>> graph) {
        this.graph = graph;
        this.vertexNum = graph.size();
        for (int i : graph.keySet()) {
            edgeNum += graph.get(i).size();
        }
        edgeNum = edgeNum / 2;
        this.density = ((double) edgeNum) / ((double) vertexNum);
    }

    public double get_density() {
        this.vertexNum = graph.size();
        this.edgeNum = 0;
        for (int i : graph.keySet()) {
            edgeNum += graph.get(i).size();
        }
        edgeNum = edgeNum / 2;
        this.density = ((double) edgeNum) / ((double) vertexNum);
        return density;
    }

    public Map<Integer, ArrayList<Integer>> get_graph() {
        return graph;
    }

    public int get_edgeNum() {
        return edgeNum;
    }

    public int get_vertexNum() {
        return vertexNum;
    }

    public Map<Integer, ArrayList<Integer>> get_kcore(Map<Integer, Integer> kcore, int k) {
        Map<Integer, ArrayList<Integer>> r = new HashMap<>();
        ArrayList<Integer> exist = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> store = graph;
        for (int i : kcore.keySet()) {
            if (kcore.get(i) >= k) {
                exist.add(i);
            }
        }
        for (int i : store.keySet()) {
            if (!exist.contains(i)) {
                continue;
            }
            ArrayList<Integer> tt = new ArrayList<>();
            for (int j : store.get(i)) {
                if (!exist.contains(j)) {
                    continue;
                }
                tt.add(j);
            }
            r.put(i, tt);
        }
        return r;
    }
}
