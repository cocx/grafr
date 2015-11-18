package com.grafr;

import com.mxgraph.model.mxCell;

public class RemoveNodeTool implements AbstractTool {
	
	@Override
	public void onClickVertex(mxCell c) {
		GraphHandeler.graph.removeCells(new Object[]{c}, true);
	}

	@Override
	public void onClickEdge(mxCell e) {

	}

	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

}
