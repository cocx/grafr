package com.grafr;

import java.util.ArrayList;
import java.util.HashMap;

import com.mxgraph.model.mxCell;

public class GraphBackend {
	
	static public class Vertex{
		mxCell vertex;
		int id;
		
		ArrayList<Edge> edges_from;
		
	public Vertex(mxCell vertex){
			this.vertex = vertex;
			id = NextID++;
			this.edges_from = new ArrayList<>();
		}
		static int NextID = 0;
	}
	
	static public class Edge{
		Vertex from;
		Vertex to;
		float weight;
		mxCell edge;
		
		public Edge(mxCell edge,Vertex from,Vertex to,float weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
			this.edge = edge;
		}
	}
	
	HashMap<Integer, Vertex> nodes;
	HashMap<Object, Vertex> nodesByGraphx;
	HashMap<mxCell, Edge> edgesByGraphx;
	
	public GraphBackend(){
		nodes = new HashMap<>();
		nodesByGraphx = new HashMap<>();
		edgesByGraphx = new HashMap<>();
	}
	
	public Vertex addVertex(mxCell vertex){
		Vertex node = new Vertex(vertex);
		nodes.put(node.id, node);
		nodesByGraphx.put(vertex, node);
		return node;
	}
	
	public Edge addEdge(Vertex from,Vertex to,mxCell edge){
		Edge n_edge = new Edge(edge,from, to, 1);
		from.edges_from.add(n_edge);
		this.edgesByGraphx.put(edge, n_edge);
		return n_edge;
	}
	
	public Edge addEdge(Vertex from,Vertex to,float weight,mxCell edge){
		Edge n_edge = new Edge(edge,from, to, weight);
		from.edges_from.add(n_edge);
		this.edgesByGraphx.put(edge, n_edge);
		return n_edge;
	}
	
	public Vertex getVertex(int id){
		return this.nodes.get(id);
	}
	
	public Vertex getVertex(mxCell vertex){
		return this.nodesByGraphx.get(vertex);
	}
	
	public Edge getEdge(mxCell edge){
		return this.edgesByGraphx.get(edge);
	}
	
	public void removeVertex(int id){
		Vertex vertex = this.nodes.get(id);
		for(Edge e: vertex.edges_from){
			this.edgesByGraphx.remove(e.edge);
		}
		nodesByGraphx.remove(vertex.vertex);
		this.nodes.remove(id);
	}
	
	public void removeVertex(mxCell vert){
		Vertex vertex = this.nodesByGraphx.get(vert);
		this.nodes.remove(vertex.id);
		this.nodesByGraphx.remove(vertex);
	}
	
	public void removeEdge(mxCell edge){
		Edge ed = this.edgesByGraphx.get(edge);
		ed.from.edges_from.remove(ed);
		edgesByGraphx.remove(edge);
	}
	
	public void clear(){
		this.edgesByGraphx.clear();
		this.nodes.clear();
		this.nodesByGraphx.clear();
	}
}