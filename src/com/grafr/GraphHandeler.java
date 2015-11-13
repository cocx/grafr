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


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class GraphHandeler
    extends JPanel
{
    private JGraphXAdapter<String, CustomEdge> jgxAdapter;
    mxStylesheet stylesheet;
    Hashtable<String, Object> stdStyle;
    Hashtable<String, Object> endStyle;
    Hashtable<String, Object> startStyle;
    Hashtable<String, Object> edgeStyle;
    
    mxCell startVertex;
    mxCell endVertex;
    
	mxGraphComponent graphComponent;
	mxGraph xgraph;
	ListenableUndirectedWeightedGraph<String, CustomEdge> graph;
	
	Object parent;
	
    public GraphHandeler()
    {
        // create a JGraphT graph
        graph = new ListenableUndirectedWeightedGraph<String, CustomEdge>(CustomEdge.class);
        
        // create a visualization using JGraph, via an adapter
        xgraph = new JGraphXAdapter<String, CustomEdge>(graph);
        
        //add component to panel
        this.setLayout(new BorderLayout());
        graphComponent = new mxGraphComponent(xgraph);
        graphComponent.setConnectable(false);
        this.add(graphComponent,BorderLayout.CENTER);
        
        //disable a few features of jgraph
        xgraph.setAllowDanglingEdges(false);
        xgraph.setCellsResizable(false);
        xgraph.setCellsEditable(false);
        xgraph.setEdgeLabelsMovable(false);
        
        parent = xgraph.getDefaultParent();
        stylesheet = xgraph.getStylesheet();
        stdStyle = new Hashtable<String, Object>(stylesheet.getDefaultVertexStyle());
        //text style
        stdStyle.put(mxConstants.DEFAULT_FONTFAMILIES, "Arial");
        stdStyle.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        stdStyle.put(mxConstants.STYLE_FONTSIZE, 20);
        stdStyle.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        //label position
        stdStyle.put(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        stdStyle.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_MIDDLE);
        stdStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        //vertex style
        stdStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        stdStyle.put(mxConstants.STYLE_STROKEWIDTH, 5);
        stdStyle.put(mxConstants.STYLE_OPACITY, 50);
        stdStyle.put(mxConstants.STYLE_EDITABLE, false);
        stdStyle.put(mxConstants.STYLE_RESIZABLE, false);
        
        
        
        //end vertex
        endStyle = new Hashtable<String, Object>(stdStyle);
        //vertex style
        endStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
        startStyle = new Hashtable<>(stdStyle);
        startStyle.put(mxConstants.STYLE_FILLCOLOR, "green");
        edgeStyle = new Hashtable<String, Object>(stylesheet.getDefaultEdgeStyle());
        edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 6);
        
        
        //assign style to string
        stylesheet.setDefaultVertexStyle(stdStyle);
        stylesheet.putCellStyle("STDvertex", stdStyle);
        stylesheet.putCellStyle("ENDvertex", endStyle);
        stylesheet.putCellStyle("STARTvertex", startStyle);
        stylesheet.setDefaultEdgeStyle(edgeStyle);
        stylesheet.putCellStyle("Edge", edgeStyle);

        //the above is a bit double but useful example code for our tool selection
        
        String c1 = "v1";
        String c2 = "v2";
        String c3 = "v3";
        String c4 = "v4";
        
        graph.addVertex(c1);
        graph.addVertex(c2);
        graph.addVertex(c3);
        graph.addVertex(c4);
        
        graph.addEdge(c1, c2);
        graph.addEdge(c3, c2);
        graph.addEdge(c4, c1);

        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(xgraph);
        layout.execute(xgraph.getDefaultParent());
        
    }
    
    public Object addVertex(String name,double x,double y,double width,double height){
    	return xgraph.insertVertex(xgraph.getDefaultParent(), null,name, x, y, width, height,"STDvertex");
    }
    
    public void setAsEnd(mxCell vertex){
    	if(this.endVertex!= null){
    		xgraph.setCellStyle("STDvertex", new Object[]{endVertex});
    	}
    	this.endVertex = vertex;
    	xgraph.setCellStyle("ENDvertex",new Object[]{vertex});
    }
    
    public void setAsStart(mxCell vertex){
    	if(this.startVertex != null){
    		xgraph.setCellStyle("STDvertex", new Object[]{startVertex});
    	}
    	this.startVertex = vertex;
    	xgraph.setCellStyle("STARTvertex",new Object[]{vertex});
    }
    
}

//End JGraphXAdapterDemo.java
