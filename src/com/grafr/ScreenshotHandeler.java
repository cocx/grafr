package com.grafr;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

public class ScreenshotHandeler {
	
	String format = "png";
	String name = "Default";
	
	public void upload(Component c) {
		BufferedImage image;
		image = getBufferedImage(c);
		try {
			upload(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void upload(BufferedImage image) throws Exception {
	    String IMGUR_POST_URI = "http://api.imgur.com/2/upload.xml";
	    String IMGUR_API_KEY = "KEY";

	    String file = "imgs/default.png";
	    
	    try {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        System.out.println("Writing image...");
	        ImageIO.write(image, "png", baos);
	        URL url = new URL(IMGUR_POST_URI);
	        System.out.println("Encoding...");

	        String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encodeBase64String(baos.toByteArray()).toString(), "UTF-8");
	        data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(IMGUR_API_KEY, "UTF-8");
	        System.out.println("Connecting...");

	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        System.out.println("Sending data...");
	        wr.write(data);
	        wr.flush();
	        System.out.println("Finished.");
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}


	private BufferedImage getBufferedImage(Component c){
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		return image;
	}
	
	public void writeToDisc(Component c) {
		try {
			printComponent(c, format, name);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Grafr.graph, "Error", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void printComponent(Component c, String format, String filename) throws IOException {
		// Create a renderable image with the same width and height as the
		// component
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// Render the component and all its sub components
		c.paintAll(image.getGraphics());
        //Write to disc
		ImageIO.write(image, format, new File("imgs/" + filename));
	}
	
	
}
