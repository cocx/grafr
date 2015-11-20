package com.grafr;

import com.grafr.GraphBackend.Vertex;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;

public class AddLineTool implements AbstractTool {
	mxCell currentSelected;
	
	public void create() {
		currentSelected = null;
	}
	
	@Override
	public void onClickVertex(mxCell c) {
		if (currentSelected == null){
			currentSelected = c;
			Grafr.graph.setVertexColor(c, "#EEEEEE");
		}else{
			Vertex from = Grafr.graph.graphBackend.getVertex(currentSelected);
			Vertex to = Grafr.graph.graphBackend.getVertex(c);
			Grafr.graph.addEdge(from, to);
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

}
