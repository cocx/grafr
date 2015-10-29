package grafr;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window {

	private JFrame frame;
	private int height;
	private int width;


	/**
	 * Create the application.
	 */
	public Window(int width,int height) {
		this.width = width;
		this.height= height;
		initialize();
	}

	public static void startWindow(final int width,final int heigth){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window(width,heigth);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, this.width, this.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mntmOpenFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Open File");
			}
		});
		mnFile.add(mntmOpenFile);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);


		Canvas drawingFrame = new Canvas();
		frame.getContentPane().add(drawingFrame, BorderLayout.CENTER);

		drawingFrame.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				Canvas draw = (Canvas) e.getComponent();
				draw.getGraphics().drawOval(e.getX()-50, e.getY()-50, 100, 100);
			}
		});

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
	}

}
