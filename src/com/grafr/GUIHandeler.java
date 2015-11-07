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
	JPanel panelE;
	JButton tstBtnW;
	JButton btnAlgo2;
	JButton btnAlgo1;
	JMenuItem file;
	
	public GUIHandeler() {
        frame = new JFrame();
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        GraphHandeler graph = new GraphHandeler();
        
        //center panel
        frame.getContentPane().add(graph, BorderLayout.CENTER);
    	
    	
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
        panelE = new JPanel();
    	frame.getContentPane().add(panelE, BorderLayout.EAST);
    	panelE.setLayout(new GridBagLayout());
    	GridBagConstraints gc = new GridBagConstraints();
    	gc.gridx = 0;
    	gc.gridy = 0;
    	gc.anchor = GridBagConstraints.NORTHEAST;
    	btnAlgo1 = new JButton("Algoritme1");
    	panelE.add(btnAlgo1, gc);    	
    	
    	
    	gc.gridx = 0;
    	gc.gridy = 1;
    	gc.weightx = 1;
    	gc.weighty = 1;
    	gc.insets = new Insets(2, 2, 2, 2);
    	btnAlgo2 = new JButton("Algoritme2");
    	panelE.add(btnAlgo2, gc);
        
        frame.setTitle("Grafr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //setLocation MUST be done after .pack()
        frame.setLocationRelativeTo( null );
	}

}
