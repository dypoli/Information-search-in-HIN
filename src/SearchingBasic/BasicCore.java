package SearchingBasic;
import java.util.*;

public class BasicCore {
    private int graph[][] = null;//data graph, including vertice IDs, edge IDs, and their link relationships
	private int vertexType[] = null;//vertex -> type
	private int edgeType[] = null;//edge -> type
	private MetaPath queryMPath = null;//the query meta-path
	private Map<Integer, int[]> output = null;
    private int query = 0;
	private kCoreDecomposition KD=null;

	public BasicCore(int graph[][], int vertexType[], int edgeType[], MetaPath queryMPath, int query) {
		this.graph = graph;
		this.vertexType = vertexType;
		this.edgeType = edgeType;
		this.queryMPath = queryMPath;
        this.query=query;
	}

    public Map<Integer, ArrayList<Integer>> ObtainKCore(int k){
        Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
        NeighborVertex NB = new NeighborVertex(graph, vertexType, edgeType, queryMPath, query);
        pnbMap=NB.build();
		this.KD =new kCoreDecomposition(pnbMap);

        return KD.get_kcore(k);
    }

	public  Map<Integer, ArrayList<Integer>> Kcore(int k){
		if (KD==null){
			ObtainKCore(k);
		}
		return KD.get_kcore(k);
	}

	public double getDensity(int k){
		if (KD==null){
			ObtainKCore(k);
		}
		Map<Integer, ArrayList<Integer>> kcore=Kcore(k);
		double vertexNum=kcore.size();
		double edgeNum=0;
		for (int i:kcore.keySet()){
			edgeNum+=kcore.get(i).size();
		}
		edgeNum=edgeNum/2;

		return edgeNum/vertexNum;
	}

	public int max_degree(){
		if (KD==null){
			Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
			NeighborVertex NB = new NeighborVertex(graph, vertexType, edgeType, queryMPath, query);
			pnbMap=NB.build();
			this.KD =new kCoreDecomposition(pnbMap);
		}
		return KD.kMax();
	}

}
