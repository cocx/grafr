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
    Hashtable<String, Object> edgeStyle;
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
        
        
        edgeStyle = new Hashtable<String, Object>(stylesheet.getDefaultEdgeStyle());
        edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 6);
        
        
        
        
        
        //assign style to string
        stylesheet.setDefaultVertexStyle(stdStyle);
        stylesheet.putCellStyle("STDvertex", stdStyle);
        stylesheet.putCellStyle("ENDvertex", endStyle);
        stylesheet.setDefaultEdgeStyle(edgeStyle);
        stylesheet.putCellStyle("Edge", edgeStyle);

        //the above is a bit double but useful example code for our tool selection
        
   
        
        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent, null, "A", 20, 20, 
            		80, 80, "STDvertex");
            Object v2 = graph.insertVertex(parent, null, "B", 240, 150,
                    80, 80, "STDvertex");
            Object v3 = graph.insertVertex(parent, null, "C", 240, 20, 
            		80, 80, "STDvertex");
            Object v4 = graph.insertVertex(parent, null, "D", 20, 150,
                    80, 80, "ENDvertex");
          
            graph.insertEdge(parent, null, null, v1, v2, "Edge");
            graph.insertEdge(parent, null, null, v2, v3, "Edge");
            graph.insertEdge(parent, null, null, v2, v4, "Edge");
            graph.insertEdge(parent, null, null, v4, v3, "Edge");
            graph.insertEdge(parent, null, null, v4, v1, "Edge");
            
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        
        this.setBackground(new Color(1, 0, 0));
        
        this.setLayout(new BorderLayout());
        

        graphComponent = new mxGraphComponent(graph);
        this.add(graphComponent,BorderLayout.CENTER);

        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);

        layout.execute(jgxAdapter.getDefaultParent());

    }
}

//End JGraphXAdapterDemo.java
