package com.grafr;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

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
	// left panel
	private JPanel leftPanel;
	private JButton selectorToolButton;
	private JButton AddNodeButton;
	private JButton RemoveNodeButton;
	private JButton AddLineButton;
	private JButton RemoveLineButton;
	private JButton setAsStartButton;
	private JButton setAsEndButton;
	private JButton editValueButton;
	// right panel
	private JPanel rightPanel;
	private JButton DijkstraButton;
	// bottom panel
	private JPanel bottomPanel;
	private JButton resetGraphButton;
	private JButton resetAlgorithmButton;

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

		leftPanel = new JPanel(new GridLayout(8, 1));
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		leftPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		GridBagConstraints gbc_west = new GridBagConstraints();
		gbc_west.anchor = GridBagConstraints.NORTH;
		gbc_west.insets = new Insets(0, 5, 5, 5);
		gbc_west.weighty = 1;
		gbc_west.weightx = 1;
		gbc_west.gridy = 0;

		selectorToolButton = new JButton("Move Nodes");
		selectorToolButton.setIcon(new ImageIcon("res/move_node.png"));
		leftPanel.add(selectorToolButton, gbc_west);

		AddNodeButton = new JButton("Add Node");
		AddNodeButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
		gbc_west.gridy++;
		leftPanel.add(AddNodeButton, gbc_west);

		RemoveNodeButton = new JButton("Remove Node");
		RemoveNodeButton.setIcon(new ImageIcon("res/remove_node.png"));
		gbc_west.gridy++;
		leftPanel.add(RemoveNodeButton, gbc_west);

		AddLineButton = new JButton("Add Line");
		AddLineButton.setIcon(new ImageIcon("res/Line.png"));
		gbc_west.gridy++;
		leftPanel.add(AddLineButton, gbc_west);

		RemoveLineButton = new JButton("Remove Line");
		RemoveLineButton.setIcon(new ImageIcon("res/remove_line_2.png"));
		gbc_west.gridy++;
		leftPanel.add(RemoveLineButton, gbc_west);

		setAsStartButton = new JButton("set Node as Start");
		setAsStartButton.setIcon(new ImageIcon("res/start_node.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsStartButton, gbc_west);

		setAsEndButton = new JButton("  set Node as End  ");
		setAsEndButton.setIcon(new ImageIcon("res/finish_node.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsEndButton, gbc_west);

		editValueButton = new JButton("Edit values");
		editValueButton.setIcon(new ImageIcon("res/edit_values.png"));
		gbc_west.gridy++;
		leftPanel.add(editValueButton, gbc_west);
		
		// button listeners left panel
		selectorToolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				selectorToolButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new SelecterTool());
			}
		});

		AddNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				AddNodeButton.setEnabled(false);

				Grafr.toolhandeler.setTool(new AddNodeTool());
			}
		});

		RemoveNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				RemoveNodeButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new RemoveNodeTool());
			}
		});

		AddLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				AddLineButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new AddLineTool());
			}
		});

		RemoveLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				RemoveLineButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new RemoveLineTool());
			}
		});

		setAsStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				setAsStartButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new SetAsStartTool());
			}
		});

		setAsEndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				setAsEndButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new SetAsEndTool());
			}
		});

		editValueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				editValueButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new EditValueTool());
			}
		});

		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);

		DijkstraButton = new JButton("Dijkstra's Algorithm");

		rightPanel.add(DijkstraButton);

		// south panel
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

		JButton nextStepButton = new JButton("Next Step");
		nextStepButton.setIcon(new ImageIcon("res/remove_node.png"));
		bottomPanel.add(nextStepButton, gbc_south);

		// button listeners south panel
		resetGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grafr.graph.clear();
			}
		});

	}

	public void show() {
		frame.setVisible(true);
	}

	public void setButtonEnabled() {
		selectorToolButton.setEnabled(true);
		AddNodeButton.setEnabled(true);
		RemoveNodeButton.setEnabled(true);
		AddLineButton.setEnabled(true);
		RemoveLineButton.setEnabled(true);
		setAsStartButton.setEnabled(true);
		setAsEndButton.setEnabled(true);
		editValueButton.setEnabled(true);
	}
}
