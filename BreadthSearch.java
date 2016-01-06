import java.io.*;
import java.util.*;
public class BreadthSearch {
	   public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner sc = new Scanner(System.in);
	        int countOfCases = sc.nextInt();
	        int vertices, edges, start, end, startingVertex;
	        HashMap<Integer, ArrayList<Integer>> graphData ;
	        for(int i = 0; i < countOfCases; i++){
	            vertices = sc.nextInt();
	            graphData = new HashMap<Integer, ArrayList<Integer>>(vertices);
	            edges = sc.nextInt();
	            for(int j = 0; j < edges; j++){
	                start = sc.nextInt();
	                end = sc.nextInt();
	                System.err.println(start + " " + end);
	                addBothEdges(graphData, start, end);
	            }
	            // add vertices with empty edges 
	            for(int j = 0; j < vertices; j++){
	            	if (!graphData.containsKey(j)){
	            		graphData.put(j, new ArrayList<Integer>());
	            	}
	            }
	            startingVertex = sc.nextInt()-1;
	            int[] distances = bsg(graphData, startingVertex);
	            for(int j = 0; j < distances.length; j++){
	                if(j == startingVertex){ continue; }
	                System.out.print(distances[j] + " ");
	            }
	            
	        }
	        System.out.println();
	    }
	    
	 
	    
	    static int[] bsg(HashMap<Integer, ArrayList<Integer>> graphData, int start){
	        Queue<Integer> q = new LinkedList<Integer>();
	        System.err.println("size : " + graphData.size());
	        boolean[] marked = new boolean[graphData.size()];
	        int[] distance = new int[graphData.size()];
	        for(int i = 0; i < distance.length; i++){
	            distance[i] = -1;
	        }
	        marked[start] = true;
	        q.add(start);
	        distance[start] = 0;
	        while(!q.isEmpty()){
	            int v = q.remove();
	            System.err.println("v : " + v);
	            ArrayList<Integer> adjVertices = graphData.get(v);
	            for(Integer vertex: adjVertices){
	                System.err.println("vertex : " + vertex);
	                if(!marked[vertex]){
	                    distance[vertex] = distance[v] + 6;
	                    marked[vertex] = true;
	                    q.add(vertex);
	                }
	            }
	        }
	        
	        return distance;
	    }
	        
	    static void addBothEdges(HashMap<Integer, ArrayList<Integer>> graphData, int start, int end){
	        addEdge(graphData, start, end);
	        addEdge(graphData, end, start);
	    }
	    
	    static void addEdge(HashMap<Integer, ArrayList<Integer>> graphData, int start, int end){
	        ArrayList<Integer> adjencyList = graphData.get(start-1);
			
			if (adjencyList == null) {
				adjencyList = new ArrayList<Integer>();
			}
			adjencyList.add(end-1);
			graphData.put(start-1, adjencyList);
	    }
	    
}
