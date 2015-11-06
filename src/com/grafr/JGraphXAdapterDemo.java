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
    	
        JPanel panel_E = new JPanel();
    	frame.getContentPane().add(panel_E, BorderLayout.EAST);
    	JButton tstBtnE = new JButton("TestE");
    	panel_E.add(tstBtnE);    	
    	
        JPanel panel_W = new JPanel();
    	frame.getContentPane().add(panel_W, BorderLayout.WEST);
    	JButton tstBtnW = new JButton("TestW");
    	panel_W.add(tstBtnW);

        
        frame.setVisible(true);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        

    }
    
    


    /*
	Canvas drawingFrame = new Canvas();
	frame.getContentPane().add(drawingFrame, BorderLayout.CENTER);

	drawingFrame.addMouseListener(new MouseAdapter(){
		@Override
		public void mousePressed(MouseEvent e){
			Canvas draw = (Canvas) e.getComponent();
			draw.getGraphics().drawOval(e.getX()-50, e.getY()-50, 100, 100);
		}
	});*/

	

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
        resize(DEFAULT_SIZE);
        
        JMenuBar menubar = new JMenuBar();

        JMenu menuOption = new JMenu("File");

        JMenuItem NewGame = new JMenuItem("Add stuff");
        menuOption.add(NewGame);

        JMenuItem exitGame = new JMenuItem("stuff");
        menuOption.add(exitGame);

        JMenu menuLevel = new JMenu("Algoritme");

        JMenuItem Dijkstra = new JMenuItem("Dijkstra");
        JMenuItem ASter = new JMenuItem("A*");
        JMenuItem Kruskal = new JMenuItem("Kruskal");

        // the menu items, menus and menu bar all need
        // to be ADDED to something!
        menubar.add(menuOption);
        menuOption.add(NewGame);
        menuOption.add(exitGame);
        menubar.add(menuLevel);
        menuLevel.add(Dijkstra);
        menuLevel.add(ASter);
        menuLevel.add(Kruskal);
        setJMenuBar(menubar);
        
        
        
        graph.getModel().beginUpdate();
        try
        {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
                    30, "ROUNDED");
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                    80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    
        // positioning via jgraphx layouts
        mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);
        
        layout.execute(jgxAdapter.getDefaultParent());

    }
}

//End JGraphXAdapterDemo.java
