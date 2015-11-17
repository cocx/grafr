package com.grafr;

import com.grafr.GraphBackend.Vertex;
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


public class GraphHandeler
    extends JPanel
{
    private JGraphXAdapter<String, CustomEdge> jgxAdapter;
    mxStylesheet stylesheet;
    Hashtable<String, Object> stdStyle;
    Hashtable<String, Object> endStyle;
    Hashtable<String, Object> startStyle;
    Hashtable<String, Object> edgeStyle;
    
    Vertex startVertex;
    Vertex endVertex;
    
	mxGraphComponent graphComponent;
	mxGraph xgraph;
	ListenableUndirectedWeightedGraph<String, CustomEdge> graph;
	
	Object parent;
	
	GraphBackend graphBackend;
	
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
        
        this.graphBackend = new GraphBackend();
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
        
        graph.getModel().beginUpdate();
        try
        {
            GraphBackend.Vertex v1 = addVertex("A", 40, 20, 80, 80);
            GraphBackend.Vertex v2 = addVertex("B", 340, 250, 80, 80);
            GraphBackend.Vertex v3 = addVertex("C", 320, 20, 80, 80);
            GraphBackend.Vertex v4 = addVertex("D", 20, 250, 80, 80);
            GraphBackend.Vertex v5 = addVertex("E", 170, 450, 80, 80);
            
            setAsEnd(v5);
            setAsStart(v1);
            
            this.addEdge(v1, v2);
            addEdge(v2, v3);
            addEdge(v2, v4);
            addEdge(v4, v3);
            addEdge(v4, v1);
            addEdge(v5, v1);
            addEdge(v5, v2);
            addEdge(v5, v3);
            addEdge(v5, v4);
            
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        
        this.setBackground(new Color(1, 0, 0));
        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(xgraph);
        layout.execute(xgraph.getDefaultParent());
        
    }
    
    public GraphBackend.Vertex addVertex(String name,double x,double y,double width,double height){
    	Object vertex = graph.insertVertex(graph.getDefaultParent(), null,name, x, y, width, height,"STDvertex");
    	return this.graphBackend.addVertex(vertex);
    }
    
    public GraphBackend.Edge addEdge(GraphBackend.Vertex from,GraphBackend.Vertex to){
    	Object edge = graph.insertEdge(graph.getDefaultParent(), null, null, from.graphXVertex, to.graphXVertex, "Edge");
    	return this.graphBackend.addEdge(from, to, edge);
    }
    
    public GraphBackend.Edge addEdge(GraphBackend.Vertex from,GraphBackend.Vertex to,float weight){
    	Object edge = graph.insertEdge(graph.getDefaultParent(), null, null, from.graphXVertex, to.graphXVertex, "Edge");
    	return this.graphBackend.addEdge(from, to, weight, edge);
    }
    
    public void setAsEnd(Vertex vertex){
    	if(this.endVertex!= null){
    		graph.setCellStyle("STDvertex", new Object[]{endVertex.graphXVertex});
    	}
    	this.endVertex = vertex;
    	graph.setCellStyle("ENDvertex",new Object[]{vertex.graphXVertex});
    }
    
    public void setAsStart(Vertex vertex){
    	if(this.startVertex != null){
    		graph.setCellStyle("STDvertex", new Object[]{startVertex.graphXVertex});
    	}
    	this.startVertex = vertex;
    	graph.setCellStyle("STARTvertex",new Object[]{vertex.graphXVertex});
    }
    
}

