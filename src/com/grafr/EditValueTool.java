package com.grafr;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;

public class EditValueTool implements AbstractTool {

	@Override
	public void onClickVertex(mxCell c) {
		String name;
		name = JOptionPane.showInputDialog(Grafr.graph,
		                "Please write the name for the node.",
		                "",
		                JOptionPane.QUESTION_MESSAGE);
		if (name != null){
		c.setValue(name);
		GraphHandeler.graph.refresh();
		}
	}

	@Override
	public void onClickEdge(mxCell e) {
		int weight;
		weight = Integer.parseInt(JOptionPane.showInputDialog(Grafr.graph,
			    "Please give the weight for the edge.",
			    "",
			    JOptionPane.QUESTION_MESSAGE));
			if (weight > 0){
				Grafr.graph.setEdgeWeight(Grafr.graph.graphBackend.getEdge(e), weight);
				GraphHandeler.graph.refresh();
			}else{
				JOptionPane.showMessageDialog(Grafr.graph,
					    "Weights must be larger than zero",
					    "",
					    JOptionPane.ERROR_MESSAGE);
			}
	}
	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

}
