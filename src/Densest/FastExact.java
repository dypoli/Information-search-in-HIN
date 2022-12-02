package Densest;

import SearchingBasic.*;
import java.util.*;


public class FastExact {
    private int graph[][] = null;//data graph, including vertice IDs, edge IDs, and their link relationships
    private int vertexType[] = null;//vertex -> type
    private int edgeType[] = null;//edge -> type
    private MetaPath queryMPath = null;//the query meta-path
    public double density = 0.00;
    private int[] final_set = null;
    private Map<Integer, int[]> homograph = null;
    public Map<Integer, Integer> decomp_output = null;//for test
    private int query = 0;

    public FastExact(int graph[][], int vertexType[], int edgeType[], MetaPath queryMPath, int query) {
        this.graph = graph;
        this.vertexType = vertexType;
        this.edgeType = edgeType;
        this.queryMPath = queryMPath;
        this.query = query;
    }

    public int[] FastBasic() {
        final_set = null;
        homograph = null;

        Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
        NeighborVertex NB = new NeighborVertex(graph, vertexType, edgeType, queryMPath, query);
        pnbMap=NB.build();
        homograph = clone(pnbMap);
        long startT2 = System.currentTimeMillis();/////////////////////
        kCoreDecomposition k = new kCoreDecomposition(pnbMap);
        Map<Integer, Integer> decomp = k.decomposition();
        this.decomp_output = decomp;
        int current_k = k.get_density();
        long endT2 = System.currentTimeMillis();/////////////
        System.out.println("time: " + (endT2 - startT2) / 1000 + " second");/////////////////////
        System.out.println("current_k: " + current_k);

        Map<Integer, ArrayList<Integer>> subgraph = k.get_kcore(current_k);

        System.out.println("appro density: " + calculate_set_density2(subgraph));
        /*FastBCore fb = new FastBCore(graph, vertexType, edgeType);
        Map<Integer, int[]> ll = fb.query(queryMPath, current_k);
        
        Map<Integer, ArrayList<Integer>> subgraph = formatChange(ll);*/
        Divide d = new Divide(subgraph);
        List<Component> connected_subgraph = d.dividGraph();
        int new_k = current_k;
        double lower_nound = 0.0;
        for (Component i : connected_subgraph) {//find max_density among these connected graph
            if (new_k < (int) Math.ceil(i.get_density())) {
                new_k = (int) Math.ceil(i.get_density());
            }
            if (lower_nound < i.get_density()) {
                lower_nound = i.get_density();
            }
        }
        System.out.println(connected_subgraph.size());
        for (Component i : connected_subgraph) {

            Map<Integer, ArrayList<Integer>> cur = i.get_graph();
            if (new_k != current_k) {//we need to find new kcore the subgraph
                cur = i.get_kcore(decomp, new_k);
            }
            if (cur.size() == 0) {//the subgraph is not k''core
                continue;
            }
            double l = (double) lower_nound;
            double u = (double) k.kMax();
            int[] vertex_set = {};
            if (final_set == null) {
                int count = 0;
                int[] tempA = new int[cur.size()];
                for (int p : cur.keySet()) {
                    tempA[count] = p;
                    count++;
                }
                final_set = tempA;
                density = calculate_set_density(final_set);
            }
            double bais = 1.0 / (cur.size() * (cur.size() - 1));
            if (bais < 0.000000000000001) {
                bais = 0.000000000000001;
            }

            FlowNetwork f2 = new FlowNetwork(transfer_format(cur));
            boolean exist = true;
            for (int j = 0; j < 1; j++) {
                double alph = l-bais;
                FindMinCut fc = new FindMinCut(f2.Construct(alph));
                fc.EdmondsKarp();//run the minimum cut algo
                Map<Integer, Integer> parent = fc.getparent();
                int[][] r = divide_s_T(parent);
                int[] source = r[0];
                if (source.length == 1) { //S={s}
                    exist = false;
                }
            }
            if (!exist) {
                continue;
            }

            FlowNetwork f = new FlowNetwork(transfer_format(cur));
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
                    if (alph > Math.ceil(l)) {//adjust current graph
                        cur = remove_node(cur, decomp, (int) Math.ceil(alph));
                        f = new FlowNetwork(transfer_format(cur));
                        bais = 1.0 / (cur.size() * (cur.size() - 1));
                        if (bais < 0.000000000000001) {
                            bais = 0.000000000000001;
                        }
                    }
                    l = alph;
                    vertex_set = remove_source_vertex(source);
                }
            }
            if (vertex_set.length == 0) {
                continue;
            }
            double curen_den = calculate_set_density(vertex_set);
            if (curen_den > density) {
                final_set = vertex_set;
                density = curen_den;
            } else if (curen_den == density) {
                int[] n = new int[final_set.length + vertex_set.length];
                for (int in = 0; in < final_set.length + vertex_set.length; in++) {
                    if (in < final_set.length) {
                        n[in] = final_set[in];
                    } else {
                        n[in] = vertex_set[in - final_set.length];
                    }
                }
                final_set = n;
            }
        }
        return final_set;
    }

    public double get_density() {
        if (final_set == null) {
            FastBasic();
        }
        return calculate_set_density(final_set);
    }

    public Map<Integer, int[]> get_pathMap() {
        if (final_set == null) {
            FastBasic();
        }
        Map<Integer, ArrayList<Integer>> r = new HashMap<>();
        for (int i : homograph.keySet()) {
            if (!check_contain(i, final_set)) {
                continue;
            }
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j : homograph.get(i)) {
                if (check_contain(j, final_set)) {
                    temp.add(j);
                }
            }
            r.put(i, temp);
        }
        return transfer_format(r);
    }

    private boolean check_contain(int id, int[] list) {
        for (int j : list) {
            if (id == j) {
                return true;
            }
        }
        return false;
    }

    private double calculate_set_density(int[] vertex_set) {
        int vertexNum = vertex_set.length;
        int edgeNum = 0;
        List<Integer> l = new ArrayList<>();
        for (int i : vertex_set) {
            l.add(i);
        }
        for (int i : homograph.keySet()) {
            if (!l.contains(i)) {
                continue;
            }
            for (int j : homograph.get(i)) {
                if (l.contains(j)) {
                    edgeNum += 1;
                }
            }
        }
        edgeNum = edgeNum / 2;
        return ((double) edgeNum) / ((double) vertexNum);
    }

    private double calculate_set_density2(Map<Integer, ArrayList<Integer>> r) {
        int count = 0;
        int[] vertex_set = new int[r.size()];
        for (int i : r.keySet()) {
            vertex_set[count] = i;
            count += 1;
        }
        int vertexNum = vertex_set.length;
        int edgeNum = 0;
        List<Integer> l = new ArrayList<>();
        for (int i : vertex_set) {
            l.add(i);
        }
        for (int i : homograph.keySet()) {
            if (!l.contains(i)) {
                continue;
            }
            for (int j : homograph.get(i)) {
                if (l.contains(j)) {
                    edgeNum += 1;
                }
            }
        }
        edgeNum = edgeNum / 2;
        return ((double) edgeNum) / ((double) vertexNum);
    }

    //remove node which is not in k_core
    private Map<Integer, ArrayList<Integer>> remove_node(Map<Integer, ArrayList<Integer>> cur,
            Map<Integer, Integer> decomp, int k) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i : cur.keySet()) {
            if (decomp.get(i) >= k) {
                temp.add(i);
            }
        }
        for (int i : cur.keySet()) {
            if (!temp.contains(i)) {
                cur.remove(i);
                continue;
            }
            for (int j = 0; j < cur.get(i).size(); j++) {
                if (!temp.contains(cur.get(i).get(j))) {
                    cur.get(i).remove(j);
                }
            }
        }
        return cur;
    }

    private Map<Integer, int[]> clone(Map<Integer, int[]> origin) {
        Map<Integer, int[]> r = new HashMap<>();
        for (int i : origin.keySet()) {
            int[] temp = new int[origin.get(i).length];
            for (int j = 0; j < origin.get(i).length; j++) {
                temp[j] = origin.get(i)[j];
            }
            r.put(i, temp);
        }

        return r;
    }

    public Map<Integer, int[]> transfer_format(Map<Integer, ArrayList<Integer>> graph) {
        Map<Integer, int[]> t = new HashMap<>();
        for (int i : graph.keySet()) {
            int[] temp = new int[graph.get(i).size()];
            for (int j = 0; j < graph.get(i).size(); j++) {
                temp[j] = graph.get(i).get(j);
            }
            t.put(i, temp);
        }
        return t;
    }

    private int[][] divide_s_T(Map<Integer, Integer> parent) {
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

    private int[] remove_source_vertex(int[] source) {
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

    private Map<Integer, ArrayList<Integer>> formatChange(Map<Integer, int[]> input) {
        Map<Integer, ArrayList<Integer>> output = new HashMap<>();
        for (int i : input.keySet()) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j : input.get(i)) {
                temp.add(j);
            }
            output.put(i, temp);
        }
        return output;
    }


}

