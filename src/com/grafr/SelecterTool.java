package com.grafr;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;

public class SelecterTool implements AbstractTool {
	
	@Override
	public void create() {
		Grafr.graph.graph.setCellsSelectable(true);
		Grafr.graph.graph.setCellsMovable(true);
		Grafr.graph.graph.refresh();
	}
	
	@Override
	public void onClickVertex(mxCell c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEdge(mxCell e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clean() {
		Grafr.graph.graph.setCellsSelectable(false);
		Grafr.graph.graph.setCellsMovable(false);
		Grafr.graph.graph.refresh();
	}

	@Override
	public void onDoubleClickVertex(mxCell c) {
		String name;
		name = JOptionPane.showInputDialog(Grafr.graph,
		                "Please write the name for the node.",
		                "",
		                JOptionPane.QUESTION_MESSAGE);
		if (name != null){
		c.setValue(name);
		Grafr.graph.graph.refresh();
		}
	}
	
	@Override
	public void onDoubleClickEdge(mxCell e) {
		int weight;
		try {
		weight = Integer.parseInt(JOptionPane.showInputDialog(Grafr.graph,
			    "Please give the weight for the edge.",
			    "",
			    JOptionPane.QUESTION_MESSAGE));
			if (weight > 0){
				Grafr.graph.setEdgeWeight(Grafr.graph.graphBackend.getEdge(e), weight);
				Grafr.graph.graph.refresh();
			}else{
				JOptionPane.showMessageDialog(Grafr.graph,
					    "Weights must be larger than zero",
					    "",
					    JOptionPane.ERROR_MESSAGE);
			}
		}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(Grafr.graph,
				    "Please insert a valid number",
				    "",
				    JOptionPane.ERROR_MESSAGE);
		}		
	}

	
}
