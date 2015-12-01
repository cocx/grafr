package com.grafr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private JButton openButton;
	private JButton saveButton;
	private JButton screenshotButton;
	private JButton resetGraphButton;
	private JButton resetAlgorithmButton;
	private JButton previousStepButton;
	private JButton nextStepButton;
	
	public GUIHandeler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Grafr");
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage("res/head.png"));
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
		selectorToolButton.setBackground(Color.green);
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
				selectorToolButton.setBackground(Color.green);
				Grafr.toolhandeler.setTool(new SelecterTool());
			}
		});

		addNodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				addNodeButton.setBackground(Color.green);

				Grafr.toolhandeler.setTool(new AddNodeTool());
			}
		});

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				removeButton.setBackground(Color.green);
				Grafr.toolhandeler.setTool(new RemoveTool());
			}
		});

		addLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				addLineButton.setBackground(Color.green);
				Grafr.toolhandeler.setTool(new AddLineTool());
			}
		});


		setAsStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				setAsStartButton.setBackground(Color.green);
				Grafr.toolhandeler.setTool(new SetAsStartTool());
			}
		});

		setAsEndButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtonEnabled();
				setAsEndButton.setBackground(Color.green);
				Grafr.toolhandeler.setTool(new SetAsEndTool());
			}
		});


		rightPanel = new JPanel();
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rightPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);

		dijkstraButton = new JButton("Dijkstra's Algorithm");
		rightPanel.add(dijkstraButton);
		
		dijkstraButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Grafr.algoHandeler.setAlgoritme(new Dijkstra());
			}
		});

		// south panel
		bottomPanel = new JPanel(new GridLayout(1,9));
		bottomPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		GridBagConstraints gbc_south = new GridBagConstraints();
		gbc_south.insets = new Insets(5, 5, 5, 5);
		gbc_south.gridx = 0;
		gbc_south.gridy = 0;
		
		saveButton = new JButton("Save");
		saveButton.setIcon(new ImageIcon("res/save.png"));
		bottomPanel.add(saveButton, gbc_south);
		
		openButton = new JButton("Open");
		openButton.setIcon(new ImageIcon("res/open.png"));
		gbc_south.gridx++;
		bottomPanel.add(openButton, gbc_south);
		
		screenshotButton = new JButton("Upload Graph");
		screenshotButton.setIcon(new ImageIcon("res/screenshot.png"));
		gbc_south.gridx++;
		bottomPanel.add(screenshotButton, gbc_south);
		
		resetGraphButton = new JButton("Reset Graph");
		resetGraphButton.setIcon(new ImageIcon("res/reset_graph.png"));
		gbc_south.gridx++;
		bottomPanel.add(resetGraphButton, gbc_south);
		
		resetAlgorithmButton = new JButton("Reset Algorithm");
		resetAlgorithmButton.setIcon(new ImageIcon("res/reset_algoritme.png"));
		gbc_south.gridx++;
		bottomPanel.add(resetAlgorithmButton, gbc_south);
		
		resetAlgorithmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Grafr.algoHandeler.clear();
				
			}
		});

		previousStepButton = new JButton("Previous Step");
		previousStepButton.setIcon(new ImageIcon("res/prev_step.png"));
		bottomPanel.add(previousStepButton, gbc_south);

		nextStepButton = new JButton("Next Step");
		nextStepButton.setIcon(new ImageIcon("res/next_step.png"));
		bottomPanel.add(nextStepButton, gbc_south);
		
		nextStepButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Grafr.algoHandeler.next();
			}
		});
		

		// button listeners south panel
		screenshotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grafr.screenshotHandeler.upload(Grafr.graph.graphComponent);
			}
		});
		
		resetGraphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp=JOptionPane.showConfirmDialog(Grafr.graph, 
				"Are you sure you want to reset the whole graph?","",2);
				if (temp==JOptionPane.OK_OPTION) {
                                    Grafr.graph.clear();
                                    if(Grafr.algoHandeler.isAlgoRunning()){
                                            Grafr.algoHandeler.clear();
				}
			}
		}});

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
		getSelectorToolButton().setBackground(new JButton().getBackground());
		addNodeButton.setBackground(new JButton().getBackground());
		removeButton.setBackground(new JButton().getBackground());
		addLineButton.setBackground(new JButton().getBackground());
		setAsStartButton.setBackground(new JButton().getBackground());
		setAsEndButton.setBackground(new JButton().getBackground());
	}
	
	public void setButtonDisable() {
		getSelectorToolButton().setEnabled(false);
		addNodeButton.setEnabled(false);
		removeButton.setEnabled(false);
		addLineButton.setEnabled(false);
		setAsStartButton.setEnabled(false);
		setAsEndButton.setEnabled(false);
		getSelectorToolButton().setBackground(new JButton().getBackground());
		addNodeButton.setBackground(new JButton().getBackground());
		removeButton.setBackground(new JButton().getBackground());
		addLineButton.setBackground(new JButton().getBackground());
		setAsStartButton.setBackground(new JButton().getBackground());
		setAsEndButton.setBackground(new JButton().getBackground());
	}

	public JButton getSelectorToolButton() {
		return selectorToolButton;
	}

	public void setSelectorToolButton(JButton selectorToolButton) {
		this.selectorToolButton = selectorToolButton;
	}
}
