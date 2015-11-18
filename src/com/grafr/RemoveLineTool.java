package com.grafr;

import com.mxgraph.model.mxCell;

public class RemoveLineTool implements AbstractTool {

	@Override
	public void onClickVertex(mxCell c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickEdge(mxCell e) {
		GraphHandeler.graph.removeCells(new Object[]{e});

	}

	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

}
