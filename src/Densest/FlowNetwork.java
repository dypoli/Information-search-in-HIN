package Densest;

import java.util.*;

public class FlowNetwork {
    int edgeNum = 0;
    Map<Integer, int[]> pnbMap = null;
    //FlowNetwork [vertex id,<neighbor_id,{edge weight,edge weight}>]
    private Map<Integer, Map<Integer, double[]>> FlowNetwork = null;

    public FlowNetwork(Map<Integer, int[]> pnbMap) {
        this.pnbMap = pnbMap;
        for (int[] i : pnbMap.values()) {
            this.edgeNum += i.length;
        }
        this.edgeNum = this.edgeNum / 2;
    }

    public Map<Integer, Map<Integer, double[]>> Construct(double alph) {
        FlowNetwork = new HashMap<Integer, Map<Integer, double[]>>();
        /*
        for (int i:pnbMap.keySet())
            FlowNetwork.put(i, new HashMap<Integer, double[]>()); */
        //format : FlowNetwork[vertex or clique id] =<destination vertex or clique id,{weight,weight}>
        int source = -1;
        int sink = -2;
        for (int j : pnbMap.keySet()) {
            HashMap<Integer, double[]> temp = new HashMap<Integer, double[]>();
            int deg_j = pnbMap.get(j).length;
            for (int k : pnbMap.get(j)) {
                double[] weight = { 1.0, 1.0 };
                temp.put(k, weight);
            }
            double[] weight = { 0.0, 0.0 }; // weight from vertex id 'j' to sink
            double[] weight2 = { (double) edgeNum + 2 * alph - deg_j, (double) edgeNum + 2 * alph - deg_j }; //weight from vertex id 'j' to tink m+2*alpha-degree(j)
            temp.put(source, weight);
            temp.put(sink, weight2);
            FlowNetwork.put(j, temp);
        }
        //add edge start from source end at each vertex
        HashMap<Integer, double[]> temp2 = new HashMap<Integer, double[]>();
        for (int key : pnbMap.keySet()) {
            double[] weight = { (double) edgeNum, (double) edgeNum };
            temp2.put(key, weight);
        }
        FlowNetwork.put(source, temp2);
        //add edge start from sink end at each vertex
        HashMap<Integer, double[]> temp3 = new HashMap<Integer, double[]>();
        for (int key : pnbMap.keySet()) {
            double[] weight = { 0.0, 0.0 };
            temp3.put(key, weight);
        }
        FlowNetwork.put(sink, temp3);
        return FlowNetwork;
    }

    public static void main(String[] args) {
        // create the graph
        Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
        int[] temp = { 1, 2 };
        pnbMap.put(0, temp);
        int[] temp1 = { 0, 3, 4 };
        pnbMap.put(1, temp1);
        int[] temp2 = { 0, 3, 4 };
        pnbMap.put(2, temp2);
        int[] temp3 = { 1, 2 };
        pnbMap.put(3, temp3);
        int[] temp4 = { 1, 2 };
        pnbMap.put(4, temp4);

        //check flownetwork
        FlowNetwork f = new FlowNetwork(pnbMap);
        System.out.println("edge number: " + f.edgeNum);
        Map<Integer, Map<Integer, double[]>> c = f.Construct(1);
        for (int i : c.keySet()) {
            System.out.print("current vertex: " + i);
            for (int j : c.get(i).keySet()) {
                System.out.print(" neighbor vertex " + j + " weight: " + (int) c.get(i).get(j)[0]);
            }
            System.out.println();
        }
    }

}
