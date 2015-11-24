package com.grafr;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ScreenshotHandeler {

	public void writeToDisc(Component c) {
		String format = "png";
		String name = "Default";
		try {
			printComponent(c, format, name);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Grafr.graph, "Error", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void printComponent(Component c, String format, String filename) throws IOException {
		//File f = new File(filename);
		// Create a renderable image with the same width and height as the
		// component
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// Render the component and all its sub components
		c.paintAll(image.getGraphics());
        //Write to disc
		ImageIO.write(image, format, new File("imgs/" + filename));
	}
}
