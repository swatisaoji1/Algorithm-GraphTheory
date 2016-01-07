import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class PrimsAlgorithm {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodesCount = sc.nextInt();
		HashMap<Integer, ArrayList<Edge>> graph = new HashMap<Integer, ArrayList<Edge>>(nodesCount);
		int edgesCount = sc.nextInt();
		int start, end, weight;
		Edge e;
		for(int i = 0; i < edgesCount; i++){
			start = sc.nextInt();
			end = sc.nextInt();
			weight = sc.nextInt();		
			e = new Edge(start, end, weight);
			addEdge(graph, e, start);
			addEdge(graph, e, end);
		}
		int startingVertex = sc.nextInt();
		System.out.println(primsMST(graph, startingVertex));
	}

	private static int primsMST(HashMap<Integer, ArrayList<Edge>> graph, int startingVertex) {
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		Set<Integer> visited = new HashSet<Integer>();
		ArrayList<Edge> MSTEdges = new ArrayList<Edge>();
		visited.add(startingVertex);
		int currentVertex = startingVertex;
		Edge smallest;
		
		// till all vertices are visited
		ArrayList<Edge> adjEdges;
		while(visited.size() != graph.size()){
			adjEdges = graph.get(currentVertex);
			edges.addAll(adjEdges);
			smallest = edges.poll();
			
			// get the vertex not in visited
			currentVertex = getUnvisitedVertex(visited, smallest);
			while(currentVertex == -1){
				smallest = edges.poll();
				currentVertex = getUnvisitedVertex(visited, smallest);
			}
			MSTEdges.add(smallest);
			visited.add(currentVertex);
		}
		return sumOfWeights(MSTEdges);
		
	}

	private static int sumOfWeights(ArrayList<Edge> mSTEdges) {
		int sum = 0;
		for(Edge e :mSTEdges){
			sum += e.getWeight();
		}
		return sum;
	}

	private static int getUnvisitedVertex(Set<Integer> visited, Edge smallest) {
		int[] vertices = smallest.getVertices();
		for(int vertex : vertices){
			if(!visited.contains(vertex)){
				return vertex;
			}
		}
		return -1;
	}

	private static void addEdge(HashMap<Integer, ArrayList<Edge>> graph, Edge e, int start) {
		ArrayList<Edge> edges = graph.get(start);
		if(edges == null){
			edges = new ArrayList<Edge>();
		}
		edges.add(e);
		graph.put(start, edges);
		
	}


}
class Edge implements Comparable<Edge>{
	private int weight;
	private int start;
	private int end;
	
	public Edge(int s, int e, int w){
		this.start = s;
		this.end = e;
		this.weight = w;	
	}
	
	public int getWeight() {
		return this.weight;
	}

	public int[] getVertices() {
		int[] vs = new int[2];
		vs[0] = this.start;
		vs[1] = this.end;
		return vs;
	}


	@Override
	public boolean equals(Object obj) {
		Edge o = (Edge)obj;
		if(o.end==this.end && o.start == this.start && o.weight == this.weight){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.start + "--" + this.end + "(" + this.weight + ")";
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight < o.weight){
			return -1;
		}
		if(this.weight > o.weight){
			return 1;
		}
		return 0;
	}
	
	
}
