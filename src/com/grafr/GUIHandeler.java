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

	public JFrame frame;
	private JPanel leftPanel;
	private JButton AddNodeButton;
	private JButton RemoveNodeButton;
	private JButton btnNewButton_2;
	private JButton btnAddLine;
	private JPanel rightPanel;
	

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
		frame.setBounds(600, 400, 800, 600);
		//frame.setPreferredSize(new Dimension(800, 600));
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
		AddNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		RemoveNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		RemoveNodeButton.setIcon(new ImageIcon("res/remove_node.png"));
		
		btnAddLine = new JButton("     Add Line     ");
		GridBagConstraints gbc_btnAddLine = new GridBagConstraints();
		gbc_btnAddLine.gridx = 0;
		gbc_btnAddLine.gridy = 2;
		btnAddLine.setIcon(new ImageIcon("res/Line.png"));
		leftPanel.add(btnAddLine, gbc_btnAddLine);
		
		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		btnNewButton_2 = new JButton("New button");
	
		rightPanel.add(btnNewButton_2);
		
        GraphHandeler graph = new GraphHandeler();    

        frame.getContentPane().add(graph, BorderLayout.CENTER);
        frame.setVisible(true);
	}

}
