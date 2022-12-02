package SearchingBasic;
import java.util.*;

public class NeighborVertex {
    private int graph[][] = null;//data graph, including vertice IDs, edge IDs, and their link relationships
	private int vertexType[] = null;//vertex -> type
	private int edgeType[] = null;//edge -> type
	private MetaPath queryMPath = null;//the query meta-path
	private Map<Integer, int[]> output = null;
    private int query = 0;

	public NeighborVertex(int graph[][], int vertexType[], int edgeType[], MetaPath queryMPath, int query) {
		this.graph = graph;
		this.vertexType = vertexType;
		this.edgeType = edgeType;
		this.queryMPath = queryMPath;
        this.query=query;
	}

	public Map<Integer, int[]> build() {
		//step 1: collect vertices of the same type
		int STARTTYPE = queryMPath.vertex[0];
		Set<Integer> keepSet = new HashSet<Integer>();
		for (int i = 0; i < vertexType.length; i++) {
			if (vertexType[i] == STARTTYPE) {
				keepSet.add(i);
			}
		}
		//step 2: find neighbors of each target vertex
		BatchSearch affVertexFinder = new BatchSearch(graph, vertexType, edgeType, queryMPath);
		Map<Integer, int[]> pnbMap = new HashMap<Integer, int[]>();
		for (int startId : keepSet) {
			Set<Integer> nbSet = affVertexFinder.collect(startId, keepSet);
			int nbArr[] = new int[nbSet.size()];
			int i = 0;
			for (int nbId : nbSet) {
				nbArr[i] = nbId;
				i++;
			}
			pnbMap.put(startId, nbArr);
		}
		output = pnbMap;
        Cluster c= new Cluster(pnbMap, query);
       
		return c.clust();
	}

	//only for test: input an set of vertex, i will compute there density
	public double check_density(int[] s) {
		if (output == null) {
			build();
		}
		List<Integer> temp = new ArrayList<>();
		for (int i : s) {
			temp.add(i);
		}
		double edgeNum = 0.0;
		double vertexNum = 0.0;
		for (int i : output.keySet()) {
			if (!temp.contains(i)) {
				continue;
			}
			vertexNum += 1;
			for (int j : output.get(i)) {
				if (!temp.contains(j)) {
					continue;
				}
				edgeNum += 1;
			}

		}
		edgeNum = edgeNum / 2;
		return edgeNum / vertexNum;
	}
}
