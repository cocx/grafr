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
    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    mxStylesheet stylesheet;
    Hashtable<String, Object> stdStyle;
    Hashtable<String, Object> endStyle;
    Hashtable<String, Object> startStyle;
    Hashtable<String, Object> edgeStyle;
    
    Object startVertex;
    Object endVertex;
    
	mxGraphComponent graphComponent;
	mxGraph graph;
	Object parent;
	
    public GraphHandeler()
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableDirectedGraph<String, DefaultEdge>(
                DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
        graph = new mxGraph();
        
        //disable a few features of jgraph
        graph.setAllowDanglingEdges(false);
        graph.setCellsResizable(false);
        graph.setCellsEditable(false);
        
        parent = graph.getDefaultParent();
        stylesheet = graph.getStylesheet();
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
        endStyle.put(mxConstants.STYLE_STROKECOLOR, "#000066");
        
        //start vertex
        startStyle = new Hashtable<>(stdStyle);
        startStyle.put(mxConstants.STYLE_STROKEWIDTH, 12);
        startStyle.put(mxConstants.STYLE_STROKECOLOR, "#000066");
        
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
        
        graph.getModel().beginUpdate();
        try
        {
            Object v1 = addVertex("A", 40, 20, 80, 80);
            Object v2 = addVertex("B", 340, 250, 80, 80);
            Object v3 = addVertex("C", 320, 20, 80, 80);
            Object v4 = addVertex("D", 20, 250, 80, 80);
            Object v5 = addVertex("E", 170, 450, 80, 80);
            
            setAsEnd(v5);
            setAsStart(v1);
            
            graph.insertEdge(parent, null, null, v1, v2, "Edge");
            graph.insertEdge(parent, null, null, v2, v3, "Edge");
            graph.insertEdge(parent, null, null, v2, v4, "Edge");
            graph.insertEdge(parent, null, null, v4, v3, "Edge");
            graph.insertEdge(parent, null, null, v4, v1, "Edge");
            graph.insertEdge(parent, null, null, v5, v1, "Edge");
            graph.insertEdge(parent, null, null, v5, v2, "Edge");
            graph.insertEdge(parent, null, null, v5, v3, "Edge");
            graph.insertEdge(parent, null, null, v5, v4, "Edge");
            
            
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        
        this.setBackground(new Color(1, 0, 0));
        
        this.setLayout(new BorderLayout());
        
        graphComponent = new mxGraphComponent(graph);
        
        graphComponent.setConnectable(false);
        this.add(graphComponent,BorderLayout.CENTER);

        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);

        layout.execute(jgxAdapter.getDefaultParent());
    }
    
    public Object addVertex(String name,double x,double y,double width,double height){
    	return graph.insertVertex(graph.getDefaultParent(), null,name, x, y, width, height,"STDvertex");
    }
    
    public void setAsEnd(Object vertex){
    	if(this.endVertex!= null){
    		graph.setCellStyle("STDvertex", new Object[]{endVertex});
    	}
    	this.endVertex = vertex;
    	graph.setCellStyle("ENDvertex",new Object[]{vertex});
    }
    
    public void setAsStart(Object vertex){
    	if(this.startVertex != null){
    		graph.setCellStyle("STDvertex", new Object[]{startVertex});
    	}
    	this.startVertex = vertex;
    	graph.setCellStyle("STARTvertex",new Object[]{vertex});
    }
    
    //colors edge or vertex with given color
    public void colorComponent(Object target, String color){
    	graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[]{target});
    	
    	graphComponent.refresh();
    }
    
}


