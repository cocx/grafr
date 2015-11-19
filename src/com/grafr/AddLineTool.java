package com.grafr;

import com.grafr.GraphBackend.Vertex;
import com.mxgraph.model.mxCell;

public class AddLineTool implements AbstractTool {
	mxCell currentSelected;
	
	public AddLineTool() {
		currentSelected = null;
	}
	
	@Override
	public void onClickVertex(mxCell c) {
		if (currentSelected == null){
			currentSelected = c;
		}else{
			Vertex from = Grafr.graph.graphBackend.getVertex(currentSelected);
			Vertex to = Grafr.graph.graphBackend.getVertex(c);
			Grafr.graph.addEdge(from, to);
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

}
