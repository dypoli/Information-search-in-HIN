package Densest;

import java.util.*;

/**
 * compute the Min-Cut, according to the flow networks.
 * 
 * In this class, we used various exiting algorithms. 
 * EK:
 */
public class FindMinCut {

    /** data structure used to record flow network*/
    public Map<Integer, Map<Integer, double[]>> FlowNetwork = null;

    /** array used to save path  <current vertex id, parent vertex id>*/
    public Map<Integer, Integer> parent = null;
    /** source vertex */
    private int s = -1;
    /** sink vertex */
    private int t = -2;

    /**
     * 
     * @param FlowNetwork data structure used to record flow network
     * @param s source vertex
     * @param t sink vertex
     */
    public FindMinCut(Map<Integer, Map<Integer, double[]>> FlowNetwork) {
        this.FlowNetwork = FlowNetwork;
    }

    /**
     * EK algorithm to solve the max-flow problem
     * the min-cut(S-T) is saved in the array 'parent[]'
     * S: parent[i]>-3
     * T: parent[i]=-3
     * @return the value of max flow
     */
    public double EdmondsKarp() {
        parent = new HashMap<Integer, Integer>();
        double result = augmentPath(parent);
        double sum = 0;
        double temp[];

        while (result != -3) {
            int cur = t;
            double fre = 0;

            while (cur != s) {
                temp = FlowNetwork.get(parent.get(cur)).get(cur);
                //System.out.println(temp[0]+" "+result);
                fre = temp[0];//weight from cur parent to cur
                temp[0] = temp[0] - result;
                temp = FlowNetwork.get(cur).get(parent.get(cur));
                temp[0] = temp[0] + result;
                cur = parent.get(cur);

            }
            sum += result;
            result = augmentPath(parent);
        }

        return sum;
    }

    /**
     * use BFS algorithm to find the augment path. 
     * @param parent the array to save the path
     * @return the minimum capacity of each edge in the path
     */
    private double augmentPath(Map<Integer, Integer> parent) {
        double maxflow = Integer.MAX_VALUE;
        for (int i : FlowNetwork.keySet()) {
            parent.put(i, -3);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        parent.put(s, s);
        double temp[];

        while (!queue.isEmpty()) {
            int p = queue.poll();
            if (p == t) {
                while (p != s) {
                    temp = FlowNetwork.get(parent.get(p)).get(p);
                    if (maxflow > temp[0])
                        maxflow = temp[0];
                    p = parent.get(p);
                }
                break;
            }
            Map<Integer, double[]> store = FlowNetwork.get(p);
            for (int key : store.keySet()) {
                temp = store.get(key);
                if (parent.get(key) == -3 && temp[0] > 0) {
                    parent.put(key, p);
                    queue.add(key);
                }
            }
        }

        if (parent.get(t) == -3) {
            //get the min-cut
            return -3;
        }
        return maxflow;
    }

    /**
     * 
     * @return the parent
     */
    public Map<Integer, Integer> getparent() {
        return parent;
    }

    public static void main(String[] args) {
        // create the graph
        Map<Integer, Map<Integer, double[]>> FlowNetwork = new HashMap<Integer, Map<Integer, double[]>>();
        //node source
        Map<Integer, double[]> s1 = new HashMap<Integer, double[]>();
        double[] temp_s1 = { 16, 16 };
        double[] temp_s2 = { 13, 13 };
        double[] zero = { 0, 0 };
        s1.put(1, temp_s1);
        s1.put(2, temp_s2);
        FlowNetwork.put(-1, s1);
        //node v1
        Map<Integer, double[]> v1 = new HashMap<Integer, double[]>();
        double[] temp_v1_1 = { 10, 10 };
        double[] temp_v1_2 = { 12, 12 };
        v1.put(2, temp_v1_1);
        v1.put(3, temp_v1_2);
        v1.put(-1, zero);
        v1.put(2, zero);
        FlowNetwork.put(1, v1);
        //node v2
        Map<Integer, double[]> v2 = new HashMap<Integer, double[]>();
        double[] temp_v2_1 = { 4, 4 };
        double[] temp_v2_2 = { 14, 14 };
        v2.put(1, temp_v2_1);
        v2.put(4, temp_v2_2);
        v2.put(-1, zero);
        v2.put(1, zero);
        FlowNetwork.put(2, v2);
        //node v3
        Map<Integer, double[]> v3 = new HashMap<Integer, double[]>();
        double[] temp_v3_1 = { 9, 9 };
        double[] temp_v3_2 = { 20, 20 };
        v3.put(2, temp_v3_1);
        v3.put(-2, temp_v3_2);
        v3.put(1, zero);
        v3.put(4, zero);
        FlowNetwork.put(3, v3);
        //node v4
        Map<Integer, double[]> v4 = new HashMap<Integer, double[]>();
        double[] temp_v4_1 = { 7, 7 };
        double[] temp_v4_2 = { 4, 4 };
        v4.put(3, temp_v4_1);
        v4.put(-2, temp_v4_2);
        v4.put(2, zero);
        FlowNetwork.put(4, v4);
        //node sink
        Map<Integer, double[]> t = new HashMap<Integer, double[]>();
        t.put(3, zero);
        t.put(4, zero);
        FlowNetwork.put(-2, t);
        for (int i : FlowNetwork.keySet()) {
            System.out.print("current vertex: " + i);
            for (int j : FlowNetwork.get(i).keySet()) {
                System.out.print(" neighbor vertex " + j + " weight: " + (int) FlowNetwork.get(i).get(j)[0]);
            }
            System.out.println();
        }
        FindMinCut f = new FindMinCut(FlowNetwork);
        System.out.println(f.EdmondsKarp());
        for (int k : f.parent.keySet()) {
            String i = f.parent.get(k) > -3 ? "source" : "sink";
            System.out.println("vertex: " + k + " belogn to: " + i);
        }
    }
}