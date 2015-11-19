package com.grafr;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;

public class ToolHandeler {
	static private AbstractTool currentTool;
	mxGraphComponent g;
	mxCell cell;

	public ToolHandeler() {
		start();
	}

	//tool selection
	static void setTool(AbstractTool tool){
		currentTool = tool;
	}
	
	
	
	//starts all listeners
	public void start(){
		g = GraphHandeler.getGraphComponent();
		currentTool = new SelecterTool();
		g.getGraphControl().addMouseListener(new MouseAdapter(){//gives shit ton of errors if you click canvas
			public void mouseReleased(MouseEvent e){
				cell = (mxCell) g.getCellAt(e.getX(), e.getY());
				if (cell != null){
					
				    if (cell.isVertex()){
					    currentTool.onClickVertex(cell);
			    	    System.out.println("is vertex");
				    }
				    if (cell.isEdge()){
				  	    currentTool.onClickEdge(cell);
					    System.out.println("is edge");
				    }
				}else{
					System.out.print(e.getX());
					System.out.print(" - ");
					System.out.println(e.getY());
					currentTool.onClickCanvas(e.getX(), e.getY());
				}
			}
		});
	}
}
//related to showing lines while moving vertices?
//https://jgraph.github.io/mxgraph/java/docs/com/mxgraph/swing/handler/mxMovePreview.html