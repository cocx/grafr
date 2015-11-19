package com.grafr;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphBackend {
	
	static public class Vertex{
		Object graphXVertex;
		int id;
		
		ArrayList<Edge> edges_from;
		
	public Vertex(Object vertex){
			this.graphXVertex = vertex;
			id = NextID++;
			this.edges_from = new ArrayList<>();
		}
		static int NextID = 0;
	}
	
	static public class Edge{
		Vertex from;
		Vertex to;
		float weight;
		Object Edge;
		
		public Edge(Object Edge,Vertex from,Vertex to,float weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	HashMap<Integer, Vertex> nodes;
	HashMap<Object, Vertex> nodesByGraphX;
	
	public GraphBackend(){
		nodes = new HashMap<>();
		nodesByGraphX = new HashMap<>();
	}
	
	public Vertex addVertex(Object vertex){
		Vertex node = new Vertex(vertex);
		nodes.put(node.id, node);
		return node;
	}
	
	public Edge addEdge(Vertex from,Vertex to,Object edge){
		Edge n_edge = new Edge(edge,from, to, 1);
		from.edges_from.add(n_edge);
		return n_edge;
	}
	
	public Edge addEdge(Vertex from,Vertex to,float weight,Object edge){
		Edge n_edge = new Edge(edge,from, to, weight);
		from.edges_from.add(n_edge);
		return n_edge;
	}
	
	public Vertex getVertex(int id){
		return this.nodes.get(id);
	}
	
	public Vertex getVertex(Object vertex){
		return this.nodesByGraphX.get(vertex);
	}
	
	public void removeVertex(int id){
		Vertex vertex = this.nodes.get(id);
		nodesByGraphX.remove(vertex.graphXVertex);
		this.nodes.remove(id);
	}
	
	public void removeVertex(Object vert){
		Vertex vertex = this.nodesByGraphX.get(vert);
		this.nodes.remove(vertex.id);
		this.nodesByGraphX.remove(vertex);
	}
}
