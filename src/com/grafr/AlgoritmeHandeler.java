package com.grafr;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;

public class AlgoritmeHandeler {
	
	private AbstractAlgoritme current; 
	private boolean running;
	
	public AlgoritmeHandeler() {
		current = new Dijkstra();
		running = false;
	}
	
	public void setAlgoritme(AbstractAlgoritme algo){
		if(running){
			clear();
		}
		current = algo;
	}
			
	public void next(){
		if (!running){
			running = true;
			Grafr.toolhandeler.disable();
			current.init(Grafr.graph.graphBackend,Grafr.graph.startVertex,Grafr.graph.endVertex);
		}
		current.next();
	}
	
	public void clear(){
		running = false;
		current.clear();
		
		Grafr.toolhandeler.enable();
		
		for(Edge e: Grafr.graph.graphBackend.edgesByGraphx.values()){
			Grafr.graph.resetEdgeStyle(e);
		}
		for(Vertex e: Grafr.graph.graphBackend.nodes.values()){
			Grafr.graph.resetVertexStyle(e);
		}
		Grafr.graph.graph.setCellStyle("STARTvertex", new Object[] { Grafr.graph.startVertex.vertex });
		Grafr.graph.graph.setCellStyle("ENDvertex", new Object[] { Grafr.graph.endVertex.vertex });
		Grafr.graph.refresh();
	}
	
	public boolean isAlgoRunning(){
		return running;
	}
	
}
