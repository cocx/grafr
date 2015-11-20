package com.grafr;

import com.mxgraph.model.mxCell;

public class SelecterTool implements AbstractTool {
	
	public SelecterTool() {
		Grafr.graph.graph.setCellsSelectable(true);
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
	}

}
