package SearchingBasic;
import java.util.*;

public class Cluster {
    Map<Integer, HashSet<Integer>> pnbMap = null;
    int query = 0;

    public Cluster(Map<Integer, int[]> pnbMap, int query){

        this.pnbMap=formate(pnbMap);
        this.query=query;
    }

    public Map<Integer, int[]> clust(){
        Set<Integer> temp = new HashSet<Integer>();
        temp.add(query);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(query);
        while(queue.size()!=0){
            int ver = queue.poll();
            HashSet<Integer> next = pnbMap.get(ver);
            for(int i:next){
                if(!temp.contains(i)){
                    temp.add(i);
                    queue.offer(i);
                }
            }
        }
        Set<Integer> temp2 = new HashSet<Integer>();
        for(int i:pnbMap.keySet()){
            if(!temp.contains(i)){
                temp2.add(i);
            }
        }

        for(int i:temp2){
            pnbMap.remove(i);
        }

        for(int i:pnbMap.keySet()){
            HashSet<Integer> connection = new HashSet<Integer>();
            for(int j: pnbMap.get(i)){
                if(temp.contains(j)){
                    connection.add(j);
                }
            }
            pnbMap.put(i,connection);
        }

        return formate2(pnbMap);
    
    }

    public Map<Integer, HashSet<Integer>> formate(Map<Integer, int[]> pnbMap){
        Map<Integer, HashSet<Integer>> temp = new HashMap<>();
        for(int i:pnbMap.keySet()){
            HashSet<Integer> t = new HashSet<>();
            for(int j:pnbMap.get(i)){
                t.add(j);
            }
            temp.put(i, t);
        }
        return temp;
    }

    public Map<Integer, int[]> formate2(Map<Integer, HashSet<Integer>>pnbMap){
        Map<Integer, int[]> temp = new HashMap<>();
        for(int i:pnbMap.keySet()){
            int[] t = new int[pnbMap.get(i).size()];
            int count=0;
            for(int j:pnbMap.get(i)){
                t[count]=j;
                count+=1;
            }
            temp.put(i, t);
        }
        return temp;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(1);
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }
}
