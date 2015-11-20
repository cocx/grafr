package com.grafr;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

public class ToolHandeler {
	private AbstractTool currentTool;
	mxGraphComponent g;

	public ToolHandeler() {
		start();
	}

	// tool selection
	void setTool(AbstractTool tool) {
		if(currentTool != null)
			currentTool.clean();
		currentTool = tool;
		currentTool.create();
	}

	// starts all listeners
	public void start() {
		g = Grafr.graph.getGraphComponent();
		currentTool = new SelecterTool();
		g.getGraphControl().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				mxCell cell = (mxCell) g.getCellAt(e.getX(), e.getY());

				if (cell != null) {
					if (cell.isVertex()) {
						currentTool.onClickVertex(cell);
						System.out.println("is vertex");
						return;
					}
					if (cell.isEdge()) {
						currentTool.onClickEdge(cell);
						System.out.println("is edge");
						return;
					}
				}else{
					currentTool.onClickCanvas(e.getX(), e.getY());
				}
			}
		});
	}

}
// related to showing lines while moving vertices?
// https://jgraph.github.io/mxgraph/java/docs/com/mxgraph/swing/handler/mxMovePreview.html