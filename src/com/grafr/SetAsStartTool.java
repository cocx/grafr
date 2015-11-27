package com.grafr;

import java.awt.Color;

import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;

public class SetAsStartTool implements AbstractTool {

	@Override
	public void onClickVertex(mxCell c) {
		Grafr.graph.setAsStart(Grafr.graph.graphBackend.getVertex(c));
		Grafr.toolhandeler.setTool(new SelecterTool());
		Grafr.window.setButtonEnabled();
		Grafr.window.getSelectorToolButton().setBackground(Color.green);
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

	@Override
	public void onDoubleClickVertex(mxCell c) {
		String name;
		name = JOptionPane.showInputDialog(Grafr.graph,
				"Please write the name for the node.",
				"",
				JOptionPane.QUESTION_MESSAGE);
		if (name != null){
			c.setValue(name);
			Grafr.graph.graph.refresh();
		}
	}

	@Override
	public void onDoubleClickEdge(mxCell e) {
		// TODO Auto-generated method stub

	}

}
