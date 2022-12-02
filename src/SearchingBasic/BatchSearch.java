package SearchingBasic;

import java.util.*;

public class BatchSearch {
	private int graph[][] = null;//data graph, including vertex IDs, edge IDs, and their link relationships
	private int vertexType[] = null;//vertex -> type
	private int edgeType[] = null;//edge -> type
	private MetaPath queryMPath = null;

	public BatchSearch(int graph[][], int vertexType[], int edgeType[], MetaPath queryMPath) {
		this.graph = graph;
		this.vertexType = vertexType;
		this.edgeType = edgeType;
		this.queryMPath = queryMPath;
	}

	public Set<Integer> collect(Set<Integer> startSet, Set<Integer> keepSet) {
		int pathLen = queryMPath.pathLen;
		Set<Integer> batchSet = startSet;
		for (int index = 0; index < pathLen; index++) {
			int targetVType = queryMPath.vertex[index + 1], targetEType = queryMPath.edge[index];
			Set<Integer> nextBatchSet = new HashSet<Integer>();
			for (int anchorId : batchSet) {
				int nbArr[] = graph[anchorId];
				for (int i = 0; i < nbArr.length; i += 2) {
					int nbVertexID = nbArr[i], nbEdgeID = nbArr[i + 1];
					if (targetVType == vertexType[nbVertexID] && targetEType == edgeType[nbEdgeID]) {
						if (index == queryMPath.pathLen - 1) {//impose restriction
							if (keepSet.contains(nbVertexID)) {
								nextBatchSet.add(nbVertexID);
							}
						} else {
							nextBatchSet.add(nbVertexID);
						}
					}
				}
			}
			batchSet = nextBatchSet;
		}
		return batchSet;
	}

	public Set<Integer> collect(int startId, Set<Integer> keepSet) {
		Set<Integer> anchorSet = new HashSet<Integer>();
		anchorSet.add(startId);

		for (int layer = 0; layer < queryMPath.pathLen; layer++) {
			int targetVType = queryMPath.vertex[layer + 1], targetEType = queryMPath.edge[layer];

			Set<Integer> nextAnchorSet = new HashSet<Integer>();
			for (int anchorId : anchorSet) {
				int nb[] = graph[anchorId];
				for (int i = 0; i < nb.length; i += 2) {
					int nbVertexID = nb[i], nbEdgeID = nb[i + 1];
					if (targetVType == vertexType[nbVertexID] && targetEType == edgeType[nbEdgeID]) {
						if (layer < queryMPath.pathLen - 1) {
							nextAnchorSet.add(nbVertexID);
						} else {
							if (keepSet.contains(nbVertexID))
								nextAnchorSet.add(nbVertexID);//impose restriction
						}
					}
				}
			}
			anchorSet = nextAnchorSet;
		}

		anchorSet.remove(startId);//2018-9-19-bug: remove the duplicated vertex
		return anchorSet;
	}

	public Map<Integer, ArrayList<Integer>> collect(Set<Integer> keepSet) {
		Map<Integer, ArrayList<Integer>> whole = new HashMap<>();
		for (int i : keepSet) {
			Set<Integer> s = collect(i, keepSet);
			whole.put(i, new ArrayList<>(s));
		}

		return whole;
	}
	/*
	public static void main(String[] args) {
		int vertexType[] = { 1, 0, 3, 1 };
		int edgeType[] = { 3, 2, 3, 0, 5, 0 };
		int graph[][] = { { 1, 0 }, { 0, 3, 3, 5, 2, 1 }, { 1, 4 }, { 1, 2 } };
		MetaPath queryMPath = new MetaPath("1 3 0 0 1");
		BatchSearch b = new BatchSearch(graph, vertexType, edgeType, queryMPath);
		Set<Integer> s = new HashSet<>();
		s.add(0);
		s.add(3);
		Set<Integer> re = b.collect(0, s);
		System.out.println(re.contains(3));
	}*/
}
