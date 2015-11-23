package com.grafr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GUIHandeler {

	public JFrame frame;
	// left panel
	private JPanel leftPanel;
	private JButton selectorToolButton;
	private JButton addNodeButton;
	private JButton removeButton;
	private JButton addLineButton;
	private JButton setAsStartButton;
	private JButton setAsEndButton;
	// right panel
	private JPanel rightPanel;
	private JButton dijkstraButton;
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
		frame.setTitle("Grafr");
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
		selectorToolButton.setEnabled(false);
		selectorToolButton.setHorizontalAlignment(SwingConstants.LEFT);
		leftPanel.add(getSelectorToolButton(), gbc_west);

		addNodeButton = new JButton("Add Node");
		addNodeButton.setIcon(new ImageIcon("res/Button-Blank-Green-icon.png"));
		addNodeButton.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_west.gridy++;
		leftPanel.add(addNodeButton, gbc_west);

		removeButton = new JButton("Remove");
		removeButton.setIcon(new ImageIcon("res/remove_node.png"));
		removeButton.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_west.gridy++;
		leftPanel.add(removeButton, gbc_west);

		addLineButton = new JButton("Add Line");
		addLineButton.setHorizontalAlignment(SwingConstants.LEFT);
		addLineButton.setIcon(new ImageIcon("res/Line.png"));
		gbc_west.gridy++;
		leftPanel.add(addLineButton, gbc_west);

		setAsStartButton = new JButton("set Node as Start");
		setAsStartButton.setHorizontalAlignment(SwingConstants.LEFT);
		setAsStartButton.setIcon(new ImageIcon("res/start_node.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsStartButton, gbc_west);

		setAsEndButton = new JButton("  set Node as End  ");
		setAsEndButton.setHorizontalAlignment(SwingConstants.LEFT);
		setAsEndButton.setIcon(new ImageIcon("res/finish_node.png"));
		gbc_west.gridy++;
		leftPanel.add(setAsEndButton, gbc_west);

		
		// button listeners left panel
		getSelectorToolButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				getSelectorToolButton().setEnabled(false);
				Grafr.toolhandeler.setTool(new SelecterTool());
			}
		});

		addNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				addNodeButton.setEnabled(false);

				Grafr.toolhandeler.setTool(new AddNodeTool());
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				removeButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new RemoveTool());
			}
		});

		addLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				addLineButton.setEnabled(false);
				Grafr.toolhandeler.setTool(new AddLineTool());
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


		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);

		dijkstraButton = new JButton("Dijkstra's Algorithm");

		rightPanel.add(dijkstraButton);

		// south panel
		bottomPanel = new JPanel();
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		GridBagConstraints gbc_south = new GridBagConstraints();
		gbc_south.insets = new Insets(0, 0, 5, 0);
		gbc_south.gridx = 1;
		gbc_south.gridy = 0;
		
		JButton screenshot = new JButton("Upload Graph");
		screenshot.setIcon(new ImageIcon("res/screenshot.png"));
		bottomPanel.add(screenshot, gbc_south);
		
		resetGraphButton = new JButton("Reset Graph");
		resetGraphButton.setIcon(new ImageIcon("res/reset_graph.png"));
		bottomPanel.add(resetGraphButton, gbc_south);
		
		resetAlgorithmButton = new JButton("Reset Algorithm");
		resetAlgorithmButton.setIcon(new ImageIcon("res/reset_algoritme.png"));
		bottomPanel.add(resetAlgorithmButton, gbc_south);

		JButton nextStepButton = new JButton("Next Step");
		nextStepButton.setIcon(new ImageIcon("res/next_step.png"));
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
		getSelectorToolButton().setEnabled(true);
		addNodeButton.setEnabled(true);
		removeButton.setEnabled(true);
		addLineButton.setEnabled(true);
		setAsStartButton.setEnabled(true);
		setAsEndButton.setEnabled(true);
	}

	public JButton getSelectorToolButton() {
		return selectorToolButton;
	}

	public void setSelectorToolButton(JButton selectorToolButton) {
		this.selectorToolButton = selectorToolButton;
	}
}
