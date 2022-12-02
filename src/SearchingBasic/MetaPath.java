package SearchingBasic;


public class MetaPath {
	public int vertex[];
	public int edge[];
	public int pathLen = -1;

	public MetaPath(int vertex[], int edge[]) {
		this.vertex = vertex;
		this.edge = edge;
		this.pathLen = edge.length;//the number of relations in a meta-path

		if (vertex.length != edge.length + 1) {
			System.out.println("the meta-path is incorrect");
		}
	}

	public MetaPath(String metaPathStr) {
		String s[] = metaPathStr.trim().split(" ");
		this.pathLen = s.length / 2;
		this.vertex = new int[pathLen + 1];
		this.edge = new int[pathLen];

		for (int i = 0; i < s.length; i++) {
			int value = Integer.parseInt(s[i]);
			if (i % 2 == 0) {
				vertex[i / 2] = value;
			} else {
				edge[i / 2] = value;
			}
		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < pathLen; i++) {
			str += vertex[i] + "-" + edge[i] + "-";
		}
		str += vertex[pathLen];
		return str;
	}

	public int[] toInt() {
		int[] t = new int[2 * pathLen + 1];
		for (int i = 0; i < 2 * pathLen + 1; i++) {
			if (i % 2 == 0) {
				t[i] = vertex[i / 2];
			} else {
				t[i] = edge[(i - 1) / 2];
			}
		}
		return t;
	}

	public static void main(String[] args) {
		MetaPath queryMPath = new MetaPath("2 8 1 3 0 0 1 8 2");
		String news = queryMPath.toString();
		System.out.println(news);
		String s[] = news.trim().split("-");
		System.out.println(s.length);
		String s2[] = new String[s.length - 4];
		for (int i = 0; i < s.length - 4; i++) {
			s2[i] = s[i + 2];
		}
		String metaPathStr = "";
		for (int i = 0; i < s2.length - 1; i++) {
			metaPathStr = metaPathStr + s2[i] + " ";
		}
		metaPathStr = metaPathStr + s2[s2.length - 1];
		MetaPath newmeta = new MetaPath(metaPathStr);
		System.out.println(newmeta.toString());
	}
}
