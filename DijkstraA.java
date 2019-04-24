
//Spandan Fadnis

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

	public static class Vertex implements Comparable<Vertex> {
		public final String name;
		public ArrayList<Edge> Neighbors;
		public LinkedList<Vertex> path;
		public int minDistance = 999;
		public Vertex previousVertex;

		public int compareTo(Vertex v) {
			return Integer.compare(minDistance, v.minDistance);
		}

		public Vertex(String name) {
			this.name = name;
			Neighbors = new ArrayList<Edge>();
			path = new LinkedList<Vertex>();
		}

		public String toString() {
			return name;
		}

		public List<Edge> getNeighbors() {
			return Neighbors;
		}

		public Vertex getPreviousVertex() {
			return previousVertex;
		}

		public void setPreviousVertex(Vertex previousVertex) {
			this.previousVertex = previousVertex;
		}

		public int getMinDistance() {
			return minDistance;
		}

		public void setMinDistance(int minDistance) {
			this.minDistance = minDistance;
		}
	}

	public static class Graph {
		private ArrayList<Vertex> vertices;

		public Graph(int numVertices) {
			vertices = new ArrayList<Vertex>(numVertices);
			for (int i = 0; i < numVertices; i++) {
				vertices.add(new Vertex(Integer.toString(i)));
			}
		}

		public void addEdge(int sourceVertex, int targetVertex) {
			Vertex v = vertices.get(sourceVertex);
			Edge newEdge = new Edge(vertices.get(targetVertex));
			v.Neighbors.add(newEdge);
		}

		public Vertex getVertex(int vertex) {
			return vertices.get(vertex);
		}
	}

	public static class Edge {
		public final Vertex targetVertex;

		public Edge(Vertex target) {
			this.targetVertex = target;
		}

		public Vertex getTargetVertex() {
			return targetVertex;
		}

	}

	public static class Algorithm {

		public int calculateFees(Vertex sourceVertex, Vertex targetVertex) {

			int cost = -1;
			sourceVertex.minDistance = 0;
			PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
			priorityQueue.add(sourceVertex);

			while (!priorityQueue.isEmpty()) {
				Vertex actualVertex = priorityQueue.poll();

				for (Edge edge : actualVertex.getNeighbors()) {
					Vertex v = edge.getTargetVertex();
					int minDistance = actualVertex.getMinDistance();

					if (minDistance < v.getMinDistance()) {
						priorityQueue.remove(v);
						v.setPreviousVertex(actualVertex);
						v.setMinDistance(minDistance);
						priorityQueue.add(v);
					}
				}
			}

			for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPreviousVertex()) {
				cost++;
			}

			return cost;
		}
	}

	public static void main(String[] arg) {

		Algorithm algorithm = new Algorithm();
		Graph graph = new Graph(118); // 118 discovered elements

		Scanner sc = new Scanner(System.in);
		int numPermutations, metalX, metalY;
		numPermutations = sc.nextInt();

		for (int i = 0; i < numPermutations; i++) {
			metalX = sc.nextInt();
			metalY = sc.nextInt();
			graph.addEdge(metalX, metalY);
		}

		System.out.println(algorithm.calculateFees(graph.getVertex(47), graph.getVertex(79)));

		sc.close();
	}
}
