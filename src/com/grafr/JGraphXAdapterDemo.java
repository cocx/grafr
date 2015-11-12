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
import java.awt.event.KeyAdapter;
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
public class JGraphXAdapterDemo
    extends JApplet
{
    

    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    
    public static final String CUSTOM_STYLE = "CUSTUM_STYLE";

    

    /**
     * An alternative starting point for this demo, to also allow running this
     * applet as an application.
     *
     * @param args ignored.
     */
    public static void main(String [] args)
    {
        JGraphXAdapterDemo applet = new JGraphXAdapterDemo();
        applet.init();
        
        JFrame frame = new JFrame();
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        
        frame.getContentPane().add(applet, BorderLayout.CENTER);
    	
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
    	
    	//west panel
        JPanel panel_W = new JPanel();
    	frame.getContentPane().add(panel_W, BorderLayout.WEST);
    	panel_W.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.gridx = 0;
    	c.gridy = 0;
    	c.anchor = GridBagConstraints.NORTHWEST;
    	//buttons
    	JButton tstBtnW = new JButton("add vertex");
    	panel_W.add(tstBtnW, c);

    	c.gridx = 0;
    	c.gridy = 1;
    	c.weightx = 1;
    	c.weighty = 1;
//    	c.insets = new Insets(arg0, arg1, arg2, arg3);
    	c.anchor = GridBagConstraints.NORTHWEST;
    	JButton tstBtnW2 = new JButton("add edge");
    	panel_W.add(tstBtnW2, c);
    	c.gridx = 0;
    	c.gridy = 2;
    	c.weightx = 2;
    	c.weighty = 2;
    	c.anchor = GridBagConstraints.NORTHWEST;
    	
    	
    	//East panel
        JPanel panel_E = new JPanel();
    	frame.getContentPane().add(panel_E, BorderLayout.EAST);
    	panel_E.setLayout(new GridBagLayout());
    	GridBagConstraints gbcE = new GridBagConstraints();
    	gbcE.gridx = 0;
    	gbcE.gridy = GridBagConstraints.RELATIVE;
    	//buttons
    	JButton btnAlgo1 = new JButton("Algoritme1");
    	panel_E.add(btnAlgo1, gbcE);    	
    	JButton btnAlgo2 = new JButton("Algoritme2");
    	panel_E.add(btnAlgo2, gbcE);
        
        frame.setVisible(true);
        frame.setTitle("JGraph Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        

    }
    
public void colorNode(mxGraph graph, Object cell, String color){
	switch(color){
	case "green":
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[] {cell});
	    graph.refresh();
	    break;
	case "red":
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[] {cell});
	    graph.refresh();
	    break;
	case "blue":
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "blue", new Object[] {cell});
	    graph.refresh();
	    break;
	case "yellow":
		graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "yellow", new Object[] {cell});
	    graph.refresh();
	    break;
	}
}
public void colorLine(mxGraph graph, Object line, String color){
	switch(color){
	case "green":
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", new Object[] {line});
	    graph.refresh();
	    break;
	case "red":
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", new Object[] {line});
	    graph.refresh();
	    break;
	case "blue":
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "blue", new Object[] {line});
	    graph.refresh();
	    break;
	case "yellow":
		graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "yellow", new Object[] {line});
	    graph.refresh();
	    break;
	}
}
	

    /**
     * {@inheritDoc}
     */
    public void init()
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableDirectedGraph<String, DefaultEdge>(
                DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        style.put(mxConstants.STYLE_EDITABLE, false);
        stylesheet.putCellStyle("ROUNDED", style);
        mxStylesheet stylesheetEdge = graph.getStylesheet();
        Hashtable<String, Object> EdgeStyle = new Hashtable<String, Object>();
        EdgeStyle.put(mxConstants.STYLE_DASHED, true);
        stylesheetEdge.putCellStyle("DASHED", EdgeStyle);
        resize(DEFAULT_SIZE);
        Object v1=null;
        Object v2=null;
        Object v3=null;
        
        graph.getModel().beginUpdate();
        try
        {
            v1 = graph.insertVertex(parent, null, "Test", 20, 20, 80,
                    30, "ROUNDED;fillColor=red");
            v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            v3 = graph.insertEdge(parent, null, "Edge", v1, v2);
            
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        colorNode(graph, v1, "red");
        colorNode(graph, v2, "yellow");
        colorLine(graph, v3, "green");
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

    
        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

    }
}

//End JGraphXAdapterDemo.java
