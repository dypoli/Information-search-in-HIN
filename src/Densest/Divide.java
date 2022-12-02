package Densest;
import SearchingBasic.*;
import java.util.*;


public class Divide {
    Map<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
    Map<Integer, ArrayList<Integer>> store = null;

    public Divide(Map<Integer, ArrayList<Integer>> graph) {
        this.store = graph;
        for (int i : graph.keySet()) {
            HashSet<Integer> t = new HashSet<Integer>();
            for (int j : graph.get(i)) {
                t.add(j);
            }
            this.graph.put(i, t);
        }
    }
   
    public List<Component> dividGraph() {

        List<Component> r = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> connectMap = new HashMap<>(store);
        Set<Integer> vertexSet = new HashSet<>(connectMap.keySet());
        while (vertexSet.size() != 0) {
            List<Integer> te = new ArrayList<>(vertexSet);
            Map<Integer, ArrayList<Integer>> groupId = groupSearch(te.get(0), connectMap, vertexSet);
            r.add(new Component(groupId));
        }

        return r;
    }

    private Map<Integer, ArrayList<Integer>> groupSearch(int id, Map<Integer, ArrayList<Integer>> connectMap,
            Set<Integer> vertexSet) {
        Set<Integer> r = new HashSet<>();
        Map<Integer, ArrayList<Integer>> re = new HashMap<>();
        List<Integer> ite = new ArrayList<>();
        if (!vertexSet.contains(id)) {
            System.out.println("id is wrong in Divid-groupsearch");
            return null;
        }
        vertexSet.remove(id);
        r.add(id);
        ite.add(id);
        while (ite.size() != 0) {
            int Tid = ite.get(0);
            ite.remove(0);
            re.put(Tid, connectMap.get(Tid));
            for (int i : connectMap.get(Tid)) {
                if (!r.contains(i)) {
                    r.add(i);
                    ite.add(i);
                    vertexSet.remove(i);
                }
            }
        }
        return re;
    }


}
