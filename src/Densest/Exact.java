package Densest;

import java.util.*;



public class Exact {
    /** the number of vertex in the given graph*/
    Map<Integer, int[]> graph = null;
    int[] final_set = null;
    FlowNetwork f = null;

    public Exact(Map<Integer, int[]> graph) {
        this.graph = graph;
        this.f = new FlowNetwork(graph);
        final_set = new int[graph.size()];
        int count = 0;
        for (int i : graph.keySet()) {
            final_set[count] = i;
            count++;
        }
    }

    public int[] RunAlgo() {
        double l = 0.0;
        double u = get_max_degree(graph);
        double bais = 1.0 / (graph.size() * (graph.size() - 1));
        int[] vertex_set = {};
        if (bais < 0.000000000000001) {
            bais = 0.000000000000001;
        }
        while (u - l >= bais) {
            double alph = (u + l) / 2;
            FindMinCut fc = new FindMinCut(f.Construct(alph));
            fc.EdmondsKarp();//run the minimum cut algo
            Map<Integer, Integer> parent = fc.getparent();
            int[][] r = divide_s_T(parent);
            int[] source = r[0];
            if (source.length == 1) { //S={s}
                u = alph;
            } else {
                l = alph;
                vertex_set = remove_source_vertex(source);
            }
        }
        this.final_set = vertex_set;
        return vertex_set;
    }

    public double get_density() {
        if (final_set == null) {
            RunAlgo();
        }
        int[] s = final_set;
        List<Integer> temp = new ArrayList<>();
        for (int i : s) {
            temp.add(i);
        }
        double edgeNum = 0.0;
        double vertexNum = 0.0;
        for (int i : graph.keySet()) {
            if (!temp.contains(i)) {
                continue;
            }
            vertexNum += 1;
            for (int j : graph.get(i)) {
                if (!temp.contains(j)) {
                    continue;
                }
                edgeNum += 1;
            }

        }
        edgeNum = edgeNum / 2;
        return edgeNum / vertexNum;
    }

    //helper function to get highest vertex degree in a graph
    public double get_max_degree(Map<Integer, int[]> graph) {
        double j = 0.0;
        for (int i : graph.keySet()) {
            if ((double) graph.get(i).length > j) {
                j = (double) graph.get(i).length;
            }
        }
        return j;
    }

    //input map 'parent' (<vertex_id, parent_vertex_id>), if parent_vertex_id > -3, it belong to source, else sink
    public int[][] divide_s_T(Map<Integer, Integer> parent) {
        ArrayList<Integer> source = new ArrayList<Integer>();
        ArrayList<Integer> sink = new ArrayList<Integer>();
        for (int i : parent.keySet()) {
            if (parent.get(i) > -3) {//belong to sink
                source.add(i);
            } else {
                sink.add(i);
            }
        }
        //transfer to int[]
        int[] a = new int[source.size()];
        int[] b = new int[sink.size()];
        int i = 0;
        for (int so : source) {
            a[i] = so;
            i++;
        }
        i = 0;
        for (int so : sink) {
            b[i] = so;
            i++;
        }
        int[][] r = { a, b };
        return r;
    }

    public int[] remove_source_vertex(int[] source) {
        int[] r = new int[source.length - 1];
        int j = 0;
        for (int i : source) {
            if (i != -1) {
                r[j] = i;
                j++;
            }
        }
        return r;
    }

    public static void main(String[] args) {

        Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
        int[] temp = { 1, 2 };
        pnbMap.put(0, temp);
        int[] temp1 = { 0, 3, 4, 5 };
        pnbMap.put(1, temp1);
        int[] temp2 = { 0, 3, 4, 5 };
        pnbMap.put(2, temp2);
        int[] temp3 = { 1, 2 };
        pnbMap.put(3, temp3);
        int[] temp4 = { 1, 2 };
        pnbMap.put(4, temp4);
        int[] temp5 = { 1, 2 };
        pnbMap.put(5, temp5);
        Exact e = new Exact(pnbMap);
        int[] i = e.RunAlgo();
        System.out.println(i.length);
        for (int j = 0; j < i.length; j++) {
            System.out.print(i[j] + " ");
        }
        System.out.println("");
        System.out.println(e.get_density());

    }

}
