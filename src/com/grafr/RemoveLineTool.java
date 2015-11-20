package com.grafr;

import com.mxgraph.model.mxCell;

public class RemoveLineTool implements AbstractTool {

	@Override
	public void onClickVertex(mxCell c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEdge(mxCell e) {
		Grafr.graph.removeEdge(Grafr.graph.graphBackend.getEdge(e));
	}

	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

}
