package Densest;
import SearchingBasic.*;
import java.util.*;

public class DensestGraph {
    private int graph[][] = null;//data graph, including vertice IDs, edge IDs, and their link relationships
	private int vertexType[] = null;//vertex -> type
	private int edgeType[] = null;//edge -> type
	private MetaPath queryMPath = null;//the query meta-path
	private Map<Integer, int[]> output = null;
    private int query = 0;
    private double density=0.0; 

	public DensestGraph(int graph[][], int vertexType[], int edgeType[], MetaPath queryMPath, int query) {
		this.graph = graph;
		this.vertexType = vertexType;
		this.edgeType = edgeType;
		this.queryMPath = queryMPath;
        this.query=query;
	}

    public int[] ObtainDensest(){
        this.density=0.0;
        Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
        NeighborVertex NB = new NeighborVertex(graph, vertexType, edgeType, queryMPath, query);
        pnbMap=NB.build();
        Exact ex=new Exact(pnbMap);
        int[] r =ex.RunAlgo();
        this.density=ex.get_density();
        return r;
    }

    public double Density(){
        ObtainDensest();
        return density;
    }
}
