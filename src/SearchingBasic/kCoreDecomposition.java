package SearchingBasic;

import java.util.*;
//for test 


public class kCoreDecomposition {
    Map<Integer, HashSet<Integer>> pnbMap = null;
    double max_density = 0.0;
    double edgeNum = 0.0;
    double vertexNum = 0.0;
    Map<Integer, Integer> kcore = null;
    Map<Integer, int[]> store = null;

    public kCoreDecomposition(Map<Integer, int[]> pnbMap) {
        this.pnbMap = transfer(pnbMap);
        this.store = pnbMap;
        for (int i : this.pnbMap.keySet()) {
            edgeNum += this.pnbMap.get(i).size();
        }
        edgeNum = edgeNum / 2;
        vertexNum = (double) this.pnbMap.size();
        max_density = ((double) edgeNum) / ((double) pnbMap.size());
    }

    //return the core number for each vertex <vertex_id,core number>
    public Map<Integer, Integer> decomposition() {
        Map<Integer, HashSet<Integer>> pnbMap = new HashMap<>(this.pnbMap);
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();//<vertex,degree>
        Map<Integer, HashSet<Integer>> degree_set = new HashMap<Integer, HashSet<Integer>>();//<degree,[vertex_id]>
        int max_degree = -1;
        for (int i : pnbMap.keySet()) {
            int degree = pnbMap.get(i).size();
            temp.put(i, degree);
            if (max_degree < degree)
                max_degree = degree;
            if (!degree_set.containsKey(degree)) {
                HashSet<Integer> l = new HashSet<Integer>();
                degree_set.put(degree, l);
            }
            degree_set.get(degree).add(i);
        }
        int cur_deg = 0;
        while (check(degree_set)) {
            cur_deg = get_minimum_degree(cur_deg, degree_set, max_degree);
            HashSet<Integer> set = degree_set.get(cur_deg);
            List<Integer> temp_list = new ArrayList<Integer>(set);//transfer the set to list, so that we can get the first element
            int cur_vertex = temp_list.get(0);
            for (int i : pnbMap.get(cur_vertex)) {
                if (temp.get(i) > cur_deg) {
                    int n = temp.get(i) - 1;
                    degree_set.get(temp.get(i)).remove(i);
                    temp.put(i, n);
                    if (!degree_set.containsKey(n)) {
                        HashSet<Integer> l = new HashSet<Integer>();
                        degree_set.put(n, l);
                    }
                    degree_set.get(n).add(i);
                }
                pnbMap.get(i).remove(cur_vertex);
            }
            degree_set.get(cur_deg).remove(cur_vertex);
            edgeNum = edgeNum - pnbMap.get(cur_vertex).size();
            pnbMap.remove(cur_vertex);
            vertexNum = vertexNum - 1;
            double temp_density = ((double) edgeNum) / ((double) vertexNum);
            if (Math.ceil(temp_density) > Math.ceil(max_density)) {
                max_density = temp_density;
            }
        }
        kcore = temp;
        return temp;
    }

    public int get_density() {
        return (int) Math.ceil(max_density);
    }

    public Map<Integer, ArrayList<Integer>> get_kcore(int k) {
        if (kcore == null) {
            decomposition();
        }
        Map<Integer, ArrayList<Integer>> r = new HashMap<>();
        ArrayList<Integer> exist = new ArrayList<>();
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

    public int[] get_kcore2(int k) {
        if (kcore == null) {
            decomposition();
        }
        Map<Integer, ArrayList<Integer>> r = new HashMap<>();
        ArrayList<Integer> exist = new ArrayList<>();
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
        int[] rr = new int[r.size()];
        int count = 0;
        for (int i : r.keySet()) {
            rr[count] = i;
            count += 1;
        }
        return rr;
    }

    public int kMax() {
        if (kcore == null) {
            decomposition();
        }
        int i = -1;
        for (int j : kcore.keySet()) {
            if (i < kcore.get(j)) {
                i = kcore.get(j);
            }
        }
        return i;
    }

    private boolean check(Map<Integer, HashSet<Integer>> degree_set) {
        for (int i : degree_set.keySet()) {
            if (degree_set.get(i).size() != 0)
                return true;
        }
        return false;
    }

    private int get_minimum_degree(int cur_deg, Map<Integer, HashSet<Integer>> degree_set, int max_degree) {
        int i = 0;
        for (i = cur_deg; i < max_degree; i++) {
            if (degree_set.get(i) == null) {
                continue;
            }

            if (degree_set.get(i).size() > 0)
                return i;
        }
        return max_degree;
    }

    private Map<Integer, HashSet<Integer>> transfer(Map<Integer, int[]> pnbMap) {
        Map<Integer, HashSet<Integer>> r = new HashMap<Integer, HashSet<Integer>>();
        for (int i : pnbMap.keySet()) {
            int[] t = pnbMap.get(i);
            HashSet<Integer> temp = new HashSet<Integer>();
            for (int j : t) {
                temp.add(j);
            }
            r.put(i, temp);
        }
        return r;
    }

}
