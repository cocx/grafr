package com.grafr;

import java.util.HashMap;
import java.util.HashSet;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;

public class Dijkstra implements AbstractAlgoritme {
	
	GraphBackend g;
	Vertex start;
	Vertex end;
	
	public Dijkstra() {
		System.out.println("Algoritme set to dijkstra");
	}
	
	enum AlgoState{
		Init,
		Measure,
		Select,
	};
	AlgoState state;
	
	HashMap<Edge, Integer> weigths;
	HashSet<Vertex> visited;
	
	Vertex current;
	int currentPathLength;
	
	Edge currentMeasure;
	int lowest_weight;
	
	
	@Override
	public void init(GraphBackend g,Vertex start,Vertex end) {
		// TODO Auto-generated method stub
		this.g = g;
		this.start = start;
		this.end = end;
		this.state = AlgoState.Init;
		this.lowest_weight = Integer.MIN_VALUE;
		weigths = new HashMap<>();
		visited = new HashSet<>();
		for(Edge e: g.edgesByGraphx.values()){
			weigths.put(e, Integer.MAX_VALUE);
		}
	}

	@Override
	public void clear() {
		g = null;
		start = null;
		end = null;
		this.current = null;
		this.currentMeasure = null;
		this.visited = null;
		this.weigths = null;
	}

	@Override
	public void next() {
		System.out.print("Step!: ");
		switch (state) {
		case Init:
			System.out.println("init");
			setCurrent(start);
			state = AlgoState.Measure;
			break;
		case Measure:
		{
			System.out.println("measure");
			Edge newMeasure;
			if(currentMeasure == null){
				//start of measure cycle
				newMeasure = current.edges_from.get(0);
			}else{
				int index = current.edges_from.indexOf(currentMeasure);
				if(index+1 == current.edges_from.size()){
					//end of measure cycle
					state = AlgoState.Select;
					Grafr.graph.setEdgeColor(currentMeasure, "#888888");
					currentMeasure = null;
					break;
				}else{
					//there are more
					newMeasure = current.edges_from.get(index+1);
				}
				//set the old edge color to gray
				Grafr.graph.setEdgeColor(currentMeasure, "#888888");
			}
			//show which one were use in to measure
			Grafr.graph.setEdgeColor(newMeasure, "green");
			if(newMeasure.weight < this.lowest_weight)
				lowest_weight = newMeasure.weight;
			currentMeasure = newMeasure;
			
		}
			break;
		case Select:
		{
			System.out.println("Select");
			Edge path = current.edges_from.get(0);
			for(Edge e: current.edges_from){
				if(e.weight == lowest_weight){
					path = e;
					break;
				}
			}
			Grafr.graph.setVertexColor(current, "#888888");
			Grafr.graph.setEdgeColor(path, "yellow");
			setCurrent(path.to);
			state = AlgoState.Measure;
		}
		break;
		default:
			System.out.println("other");
			break;
		}
	}

	private Vertex setCurrent(Vertex v){
		Vertex old = current;
		current = v;
		Grafr.graph.setVertexColor(v, "green");
		visited.add(v);
		return old;
	}

	private void setMeasured(Vertex v){
		current = v;
	}
}
