package com.grafr;

import com.mxgraph.model.mxCell;

public class AddNodeTool implements AbstractTool {

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
		GraphHandeler.addVertex(GraphHandeler.vertexNames[GraphHandeler.nameCount], x-40, y-40, 80, 80);
		GraphHandeler.nameCount++;
	}

}
