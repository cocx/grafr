package com.grafr;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;

public class AlgoritmeHandeler {
	
	private AbstractAlgoritme current; 
	private boolean running;
	private int steps_taken;
	
	public AlgoritmeHandeler() {
		current = new Dijkstra();
		running = false;
		steps_taken = 0;
	}
	
	public void setAlgoritme(AbstractAlgoritme algo){
		if(running){
			clear();
		}
		current = algo;
	}
			
	public void next(){
		steps_taken++;
		if (!running){
			if(Grafr.graph.startVertex == null){
				JOptionPane.showMessageDialog(Grafr.graph,"Please add a start node.","",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(Grafr.graph.endVertex == null){
				JOptionPane.showMessageDialog(Grafr.graph,"Please add a end node.","",JOptionPane.ERROR_MESSAGE);
				return;
			}
			running = true;
			Grafr.toolhandeler.disable();
			current.init(Grafr.graph.graphBackend,Grafr.graph.startVertex,Grafr.graph.endVertex);
		}
		current.next();
	}
	
	public void clear(){
		steps_taken = 0;
		running = false;
		current.clear();
		
		Grafr.toolhandeler.enable();
		
		for(Edge e: Grafr.graph.graphBackend.edgesByGraphx.values()){
			Grafr.graph.resetEdgeStyle(e);
			Grafr.graph.setEdgeWeight(e, e.weight);
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
	
	public void previous(){
		//another lazy hack
		if(running){
			int steps = steps_taken-1;
			this.clear();
			for (int i = 0; i < steps;i++){
				this.next();
			}
		}
	}
}
