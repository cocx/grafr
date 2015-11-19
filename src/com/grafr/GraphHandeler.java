/* This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package com.grafr;

import com.mxgraph.layout.*;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.*;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import java.util.HashMap;
import java.util.Hashtable;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class GraphHandeler extends JPanel {
	private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
	mxStylesheet stylesheet;
	Hashtable<String, Object> stdStyle;
	Hashtable<String, Object> endStyle;
	Hashtable<String, Object> startStyle;
	Hashtable<String, Object> edgeStyle;

	Vertex startVertex;
	Vertex endVertex;

	static mxGraphComponent graphComponent;
	static mxGraph graph;
	GraphBackend graphBackend;
	Object parent;

	public GraphHandeler() {
		// create a JGraphT graph
		ListenableGraph<String, DefaultEdge> g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		// create a visualization using JGraph, via an adapter
		jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
		graph = new mxGraph();
		
		this.graphBackend = new GraphBackend();

		// disable a few features of jgraph
		graph.setAllowDanglingEdges(false);
		graph.setCellsResizable(false);
		graph.setCellsEditable(false);

		parent = graph.getDefaultParent();
		stylesheet = graph.getStylesheet();
		stdStyle = new Hashtable<String, Object>(stylesheet.getDefaultVertexStyle());
		// text style
		
		stdStyle.put(mxConstants.DEFAULT_FONTFAMILIES, "Arial");
		stdStyle.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
		stdStyle.put(mxConstants.STYLE_FONTSIZE, 20);
		stdStyle.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		// label position
		stdStyle.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
		stdStyle.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_MIDDLE);
		stdStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
		// vertex style
		stdStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		stdStyle.put(mxConstants.STYLE_STROKEWIDTH, 5);
		stdStyle.put(mxConstants.STYLE_OPACITY, 50);
		stdStyle.put(mxConstants.STYLE_EDITABLE, false);
		stdStyle.put(mxConstants.STYLE_RESIZABLE, false);

		// end vertex
		endStyle = new Hashtable<String, Object>(stdStyle);
		// vertex style
		endStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
		endStyle.put(mxConstants.STYLE_STROKECOLOR, "#000066");

		// start vertex
		startStyle = new Hashtable<>(stdStyle);
		startStyle.put(mxConstants.STYLE_STROKEWIDTH, 12);
		startStyle.put(mxConstants.STYLE_STROKECOLOR, "#000066");
		// edges
		edgeStyle = new Hashtable<String, Object>(stylesheet.getDefaultEdgeStyle());
		edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 6);
		// edge text
		edgeStyle.put(mxConstants.DEFAULT_FONTFAMILIES, "Arial");
		edgeStyle.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
		edgeStyle.put(mxConstants.STYLE_FONTSIZE, 20);
		edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		edgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);

		// assign style to string
		stylesheet.setDefaultVertexStyle(stdStyle);

		stylesheet.putCellStyle("STDvertex", stdStyle);
		stylesheet.putCellStyle("ENDvertex", endStyle);
		stylesheet.putCellStyle("STARTvertex", startStyle);

		stylesheet.setDefaultEdgeStyle(edgeStyle);
		stylesheet.putCellStyle("Edge", edgeStyle);

		// the above is a bit double but useful example code for our tool
		// selection

		graph.getModel().beginUpdate();
		try {
			Vertex v1 = addVertex("A", 40, 20);
			Vertex v2 = addVertex("B", 340, 250);
			Vertex v3 = addVertex("C", 320, 20);
			Vertex v4 = addVertex("D", 20, 250);
			Vertex v5 = addVertex("E", 170, 450);

			setAsEnd(v5);
			setAsStart(v1);
			setVertexColor(v3, "#FF0000");
			v1.vertex.setValue("K");

			int x = 5;
			Edge e1 = addEdge(v1, v2, x);
			addEdge(v2, v3, x);
			addEdge(v2, v4, x);
			addEdge(v4, v3, x);
			addEdge(v4, v1, x);
			addEdge(v5, v1, x);
			addEdge(v5, v2, x);
			addEdge(v5, v3, x);
			addEdge(v5, v4, x);

			setEdgeColor(e1, "#FF0000");
			setEdgeWeight(e1, 20);
			graph.setKeepEdgesInBackground(true);

		} finally {
			graph.getModel().endUpdate();
		}

		this.setBackground(new Color(1, 0, 0));

		this.setLayout(new BorderLayout());

		graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);
		this.add(graphComponent, BorderLayout.CENTER);

		// positioning via jgraphx layouts
		mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);

		layout.execute(jgxAdapter.getDefaultParent());
		
		 Grafr.window.frame.getContentPane().add(this, BorderLayout.CENTER);
	}

	public mxGraphComponent getGraphComponent() {
		return graphComponent;
	}

	public Vertex addVertex(String name, double x, double y) {
		Object vertex = graph.insertVertex(graph.getDefaultParent(), null, name, x, y, 80, 80, "STDvertex");
		return graphBackend.addVertex((mxCell) vertex);
	}

	public Edge addEdge(Vertex from, Vertex to) {
		Object edge = graph.insertEdge(graph.getDefaultParent(), null, null, from.vertex, to.vertex, "Edge");
		return graphBackend.addEdge(from, to, (mxCell) edge);
	}

	public Edge addEdge(Vertex from, Vertex to, float weight) {
		Object edge = graph.insertEdge(graph.getDefaultParent(), null, null, from.vertex, to.vertex, "Edge");
		return graphBackend.addEdge(from, to, weight, (mxCell) edge);
	}

	public void setAsEnd(Vertex vertex) {
		if (this.endVertex != null) {
			graph.setCellStyle("STDvertex", new Object[] { endVertex.vertex });
		}
		this.endVertex = vertex;
		graph.setCellStyle("ENDvertex", new Object[] { vertex.vertex });
	}

	public void setAsStart(Vertex vertex) {
		if (this.startVertex != null) {
			graph.setCellStyle("STDvertex", new Object[] { startVertex.vertex });
		}
		this.startVertex = vertex;
		graph.setCellStyle("STARTvertex", new Object[] { vertex.vertex });
	}

	// colors vertex with given color
	public void setVertexColor(Vertex vertex, String color) {
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[] { vertex.vertex });
		graph.refresh();// seems useless
	}
	
	public void setVertexColor(mxCell vertex, String color) {
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[] { vertex });
		graph.refresh();// seems useless
	}

	// colors edge with given color
	public void setEdgeColor(Edge edge, String color) {
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, color, new Object[] { edge.edge });
		graph.refresh();// seems useless
	}
	
	public void setEdgeColor(mxCell edge, String color) {
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, color, new Object[] { edge });
		graph.refresh();// seems useless
	}

	// sets the weight for a given edge
	public void setEdgeWeight(Edge edge, int weight) {
		edge.edge.setValue(weight);
	}
	
	public void removeVertex(Vertex vertex){
		graphBackend.removeVertex(vertex.id);
		graph.removeCells(new Object[]{vertex.vertex});
	}
	
	public void removeEdge(Edge edge){
		graphBackend.removeEdge(edge.edge);
		graph.removeCells(new Object[]{edge.edge});
	}
	
	public void clear(){
		graph.removeCells(graph.getChildCells(graph.getDefaultParent(), true, true));
	}
}
