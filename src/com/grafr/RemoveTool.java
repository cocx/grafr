package com.grafr;

import java.awt.Color;

import com.mxgraph.model.mxCell;

public class RemoveTool implements AbstractTool {
	
	@Override
	public void onClickVertex(mxCell c) {
		Grafr.graph.removeVertex(Grafr.graph.graphBackend.getVertex(c));
		Grafr.toolhandeler.setTool(new SelecterTool());
		Grafr.window.setButtonEnabled();
		Grafr.window.getSelectorToolButton().setBackground(Color.green);
	}

	@Override
	public void onClickEdge(mxCell e) {
		Grafr.graph.removeEdge(Grafr.graph.graphBackend.getEdge(e));
		Grafr.toolhandeler.setTool(new SelecterTool());
		Grafr.window.setButtonEnabled();
		Grafr.window.getSelectorToolButton().setBackground(Color.green);
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

	@Override
	public void onDoubleClickVertex(mxCell c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoubleClickEdge(mxCell e) {
		// TODO Auto-generated method stub
		
	}

}
