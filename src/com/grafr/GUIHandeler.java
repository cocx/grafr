package com.grafr;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;


public class GUIHandeler {

	public static JFrame frame;
	//left panel
	private JPanel leftPanel;
	private JButton AddNodeButton;
	private JButton RemoveNodeButton;
	private JButton AddLineButton;
	private JButton RemoveLineButton;
	//right panel
	private JPanel rightPanel;
	private JButton btnNewButton_2;	
    //center panel
	GraphHandeler graph;
	ToolHandeler toolhandeler;
	//bottom panel
	/**
	 * Create the application.
	 */
	public GUIHandeler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		leftPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{103, 0};
		gbl_panel.rowHeights = new int[]{23, 23, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_panel);
		
		AddNodeButton = new JButton("    Add Node    ");
		AddNodeButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		leftPanel.add(AddNodeButton, gbc_btnNewButton);
		
		RemoveNodeButton = new JButton("Remove Node");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		leftPanel.add(RemoveNodeButton, gbc_btnNewButton_1);

		RemoveNodeButton.setIcon(new ImageIcon("res/remove_node.png"));
		
		AddLineButton = new JButton("     Add Line     ");
		GridBagConstraints gbc_btnAddLine = new GridBagConstraints();
		gbc_btnAddLine.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddLine.gridx = 0;
		gbc_btnAddLine.gridy = 2;
		AddLineButton.setIcon(new ImageIcon("res/Line.png"));
		leftPanel.add(AddLineButton, gbc_btnAddLine);

		RemoveLineButton = new JButton("  Remove Line   ");
		GridBagConstraints gbc_btnRemoveLine = new GridBagConstraints();
		gbc_btnRemoveLine.gridx = 0;
		gbc_btnRemoveLine.gridy = 3;
		RemoveLineButton.setIcon(new ImageIcon("res/Line.png"));
		leftPanel.add(RemoveLineButton, gbc_btnRemoveLine);
		
		//button listeners left panel
		AddNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new AddNodeTool());
			}
		});
		
		
		RemoveNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new RemoveNodeTool());
			}
		});
		
		
		AddLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new AddLineTool());
			}
		});
		
		
		RemoveLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new RemoveLineTool());
			}
		});
		

		
		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		btnNewButton_2 = new JButton("New button");
	
		rightPanel.add(btnNewButton_2);
		
        graph = new GraphHandeler();    
        toolhandeler = new ToolHandeler();
        frame.getContentPane().add(graph, BorderLayout.CENTER);
        frame.setVisible(true);
	}    
}
