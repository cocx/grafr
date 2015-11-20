package com.grafr;

import com.mxgraph.model.mxCell;

public interface AbstractTool {
	public void clean();
	public void onClickVertex(mxCell c);
	public void onClickEdge(mxCell e);
	public void onClickCanvas(int x, int y);
}
