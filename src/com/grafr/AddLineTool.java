package com.grafr;

import javax.swing.JOptionPane;

import com.grafr.GraphBackend.Vertex;
import com.mxgraph.model.mxCell;

public class AddLineTool implements AbstractTool {
	mxCell currentSelected;

	@Override
	public void create() {
		currentSelected = null;
	}

	@Override
	public void onClickVertex(mxCell c) {
		if (currentSelected == null) {
			currentSelected = c;
			Grafr.graph.setVertexColor(c, "#EEEEEE");
		} else {
			if (currentSelected != c) {
				Vertex from = Grafr.graph.graphBackend
						.getVertex(currentSelected);
				Vertex to = Grafr.graph.graphBackend.getVertex(c);
				Grafr.graph.addEdge(from, to);
			}
			Grafr.graph.setVertexColor(currentSelected, "#C3D9FF");
			currentSelected = null;
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleClickVertex(mxCell c) {
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
