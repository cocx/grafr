package com.grafr;

import com.mxgraph.model.mxCell;

public class SetAsStartTool implements AbstractTool {

	@Override
	public void onClickVertex(mxCell c) {
		 Grafr.graph.setAsStart(Grafr.graph.graphBackend.getVertex(c)); 

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
	public void create() {
		// TODO Auto-generated method stub
		
	}

}
