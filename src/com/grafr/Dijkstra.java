package com.grafr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
		End,
		Found,
	};
	AlgoState state;
	
	HashMap<Edge, Integer> weigths;
	HashMap<Vertex, Edge> selectedFrom;
	HashSet<Vertex> visited;
	
	Vertex current;
	int currentPathLength;
	
	Edge currentMeasure;
	
	Edge path;
	
	
	@Override
	public void init(GraphBackend g,Vertex start,Vertex end) {
		// TODO Auto-generated method stub
		this.g = g;
		this.start = start;
		this.end = end;
		this.state = AlgoState.Init;
		weigths = new HashMap<>();
		visited = new HashSet<>();
		selectedFrom = new HashMap<>();
		for(Edge e: g.edgesByGraphx.values()){
			weigths.put(e, Integer.MAX_VALUE);
		}
		currentPathLength = 0;
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
			if(current.edges_from.size() == 0){
				state = AlgoState.Select;
				break;
			}else if(currentMeasure == null){
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
			this.weigths.put(newMeasure,this.currentPathLength + newMeasure.weight);
			Grafr.graph.setEdgeWeight(newMeasure, this.currentPathLength + newMeasure.weight);
			Grafr.graph.refresh();
			currentMeasure = newMeasure;
		}
			break;
		case Select:
		{
			System.out.println("Select");
			if(path != null)
				Grafr.graph.setEdgeColor(path, "#888888");
			path = null;
			int min = Integer.MAX_VALUE;
			for(Map.Entry<Edge, Integer> m: this.weigths.entrySet()){
				if (min > m.getValue()){
					if(!visited.contains(m.getKey().to)){
						min = m.getValue();
						path = m.getKey();
					}
				}
			}
			if(path == null){
				state = AlgoState.End;
			}else{
				if(path.to == end ){
					state = AlgoState.Found;
					selectedFrom.put(path.to, path);
					setCurrent(end);
				}else{
					this.currentPathLength = this.weigths.get(path).intValue();
					Grafr.graph.setVertexColor(current, "#888888");
					Grafr.graph.setEdgeColor(path, "yellow");
					setCurrent(path.to);
					selectedFrom.put(path.to, path);
					state = AlgoState.Measure;
				}
			}
		}
		break;
		case Found:
		{
			if(current == start){
				state = AlgoState.End;
				break;
			}
			Edge selected = selectedFrom.get(current);
			Grafr.graph.setEdgeColor(selected, "red");
			current = selected.from;

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
