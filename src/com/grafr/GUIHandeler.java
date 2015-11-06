package com.grafr;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUIHandeler {
	
	JFrame frame;
	JMenuBar menuBar;
	JPanel panelW;
	JButton tstBtnW;
	JButton btnAlgo2;
	JButton btnAlgo1;
	JMenuItem file;
	
	public GUIHandeler() {
        frame = new JFrame();
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        GraphHandeler graph = new GraphHandeler();
        
        graph.addComponentListener(new ComponentAdapter(){
        	public void componentResized(ComponentEvent e){
        		System.out.println("Resize");
        	}
        });
        
        frame.getContentPane().add(graph, BorderLayout.CENTER);
    	
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        file = new JMenuItem("File");
        menuBar.add(file);
    	
    	//west panel
        panelW = new JPanel();
    	frame.getContentPane().add(panelW, BorderLayout.WEST);
    	panelW.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.gridx = 0;
    	c.gridy = 0;
    	c.anchor = GridBagConstraints.NORTHWEST;
    	//buttons
    	tstBtnW = new JButton("add vertex");
    	panelW.add(tstBtnW, c);
    	
    	c.gridx = 0;
    	c.gridy = 1;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.insets = new Insets(2, 2, 2, 2);
    	c.anchor = GridBagConstraints.NORTHWEST;
    	JButton tstBtnW2 = new JButton("add edge");
    	panelW.add(tstBtnW2, c);
    	
    	
    	//East panel
        JPanel panel_E = new JPanel();
    	frame.getContentPane().add(panel_E, BorderLayout.EAST);
    	panel_E.setLayout(new GridBagLayout());
    	GridBagConstraints gbcE = new GridBagConstraints();
    	gbcE.gridx = 0;
    	gbcE.gridy = GridBagConstraints.RELATIVE;
    	//buttons
    	btnAlgo1 = new JButton("Algoritme1");
    	panel_E.add(btnAlgo1, gbcE);    	
    	btnAlgo2 = new JButton("Algoritme2");
    	panel_E.add(btnAlgo2, gbcE);
        
        frame.setVisible(true);
        frame.setTitle("JGraph Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
	}

}
