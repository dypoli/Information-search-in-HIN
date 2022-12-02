package dataset;
import Densest.*;
import SearchingBasic.*;

public class TestAll {

    public void Basic(){
        DataReader d = new DataReader("src/dataset/Foursquare/graph.txt", "src/dataset/Foursquare/vertex.txt", "src/dataset/Foursquare/edge.txt");
        //GenerateGraph d = new GenerateGraph(5000, 1230);
        int[][] graph = d.readGraph();
        int[] vertex = d.readVertexType();
        int[] edge = d.readEdgeType();
        MetaPath queryMPath = new MetaPath("4 7 0 6 4");
        BasicCore BC =new BasicCore(graph, vertex, edge, queryMPath, 42531);
        System.out.println(BC.max_degree());
        for (int i=20; i<=BC.max_degree();i+=80){
            System.out.println(i);
            System.out.println(BC.getDensity(i));
            System.out.println();
        }

    } 



    public void Densest(){
        DataReader d = new DataReader("src/dataset/ET2/graph.txt", "src/dataset/ET2/vertex.txt", "src/dataset/ET2/edge.txt");
        //GenerateGraph d = new GenerateGraph(5000, 1230);
        int[][] graph = d.readGraph();
        int[] vertex = d.readVertexType();
        int[] edge = d.readEdgeType();
        MetaPath queryMPath = new MetaPath("1 3 0 0 1");
        DensestGraph DG = new DensestGraph(graph, vertex, edge, queryMPath, 4);
        System.out.println("HERE");
        long startT1 = System.currentTimeMillis();/////////////////////
        System.out.println(DG.Density());
        long startT2 = System.currentTimeMillis();/////////////////////
        System.out.println("time: "+(startT2 - startT1) / 1000 + " second");



    } 


    public void FastDensest(){
        DataReader d = new DataReader("src/dataset/Foursquare/graph.txt", "src/dataset/Foursquare/vertex.txt", "src/dataset/Foursquare/edge.txt");
        //GenerateGraph d = new GenerateGraph(5000, 1230);
        int[][] graph = d.readGraph();
        int[] vertex = d.readVertexType();
        int[] edge = d.readEdgeType();
        MetaPath queryMPath = new MetaPath("4 7 0 6 4");
        FastDensestGraph DG = new FastDensestGraph(graph, vertex, edge, queryMPath, 42531);
        System.out.println("HERE");
        long startT1 = System.currentTimeMillis();/////////////////////
        System.out.println(DG.Density());
        long startT2 = System.currentTimeMillis();/////////////////////
        System.out.println("time: "+(startT2 - startT1) / 1000 + " second");



    } 
    
    public static void main(String[] args) {
        TestAll ta = new TestAll();
        ta.FastDensest();
    }
}
