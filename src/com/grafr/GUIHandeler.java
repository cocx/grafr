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
	private JButton selectorToolButton;
	private JButton AddNodeButton;
	private JButton RemoveNodeButton;
	private JButton AddLineButton;
	private JButton RemoveLineButton;
	private JButton setAsStartButton;
	private JButton setAsEndButton;
	//right panel
	private JPanel rightPanel;
	private JButton DijkstraButton;	
    //center panel
	GraphHandeler graph;
	ToolHandeler toolhandeler;
	//bottom panel
	private JPanel bottomPanel;
	private JButton resetGraphButton;
	private JButton resetAlgorithmButton;
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
		frame.setMinimumSize(new Dimension(1000, 800));
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
        //Starting values GridBag left panel
		GridBagConstraints gbc_west = new GridBagConstraints();
		gbc_west.anchor = GridBagConstraints.NORTH;
		gbc_west.insets = new Insets(0, 0, 5, 0);
		gbc_west.gridy = 0;
		
		selectorToolButton = new JButton ("Move Nodes");
		selectorToolButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
		leftPanel.add(selectorToolButton, gbc_west);
		
		AddNodeButton = new JButton("    Add Node    ");
		AddNodeButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
        gbc_west.gridy++;
		leftPanel.add(AddNodeButton, gbc_west);
		
		RemoveNodeButton = new JButton("Remove Node");
		RemoveNodeButton.setIcon(new ImageIcon("res/remove_node.png"));
		gbc_west.gridy++;
		leftPanel.add(RemoveNodeButton, gbc_west);

		
		AddLineButton = new JButton("     Add Line     ");
		AddLineButton.setIcon(new ImageIcon("res/Line.png"));
		gbc_west.gridy++;
		leftPanel.add(AddLineButton, gbc_west);

		RemoveLineButton = new JButton("  Remove Line   ");
		RemoveLineButton.setIcon(new ImageIcon("res/Line.png"));
		gbc_west.gridy++;
		leftPanel.add(RemoveLineButton, gbc_west);

		
		setAsStartButton = new JButton("set Node as Start");
		setAsStartButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsStartButton, gbc_west);
		
		setAsEndButton = new JButton("set Node as Endt");
		setAsEndButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsEndButton, gbc_west);
		
		
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
		
		setAsStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new SetAsStartTool());
			}
		});
		
		setAsEndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolHandeler.setTool(new SetAsEndTool());
			}
		});
		
		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.setLayout(gbl_panel);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		DijkstraButton = new JButton("Dijkstra's Algorithm");
		rightPanel.add(DijkstraButton);
		
		
		bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		resetGraphButton = new JButton("Reset Graph");
		resetGraphButton.setIcon(new ImageIcon("res/remove_node.png"));
		GridBagConstraints gbc_south = new GridBagConstraints();
		gbc_south.insets = new Insets(0, 0, 5, 0);
		gbc_south.gridx = 1;
		gbc_south.gridy = 0;
		bottomPanel.add(resetGraphButton, gbc_south);
        
		resetAlgorithmButton = new JButton("Reset Algorithm");
		resetAlgorithmButton.setIcon(new ImageIcon("res/remove_node.png"));
		bottomPanel.add(resetAlgorithmButton, gbc_south);		
		
		JButton testbutton = new JButton("Reset Algorithm");
		testbutton.setIcon(new ImageIcon("res/remove_node.png"));
		bottomPanel.add(testbutton, gbc_south);
		
		
		//button listeners south panel
		resetGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphHandeler.clear();
			}
		});
		
		
        graph = new GraphHandeler();    
        toolhandeler = new ToolHandeler();
        frame.getContentPane().add(graph, BorderLayout.CENTER);
        frame.setVisible(true);
	}    
}
