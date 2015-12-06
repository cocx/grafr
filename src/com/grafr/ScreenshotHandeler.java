package com.grafr;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

public class ScreenshotHandeler {
	
	class UploadThread extends Thread{
		BufferedImage image;
		
		public UploadThread(BufferedImage image) {
			this.image = image;
		}
		
		@Override
		public void run(){
			ScreenshotHandeler.upload(image);
		}
	}
	
	String format = "png";
	String name = "Default";
	
	public void upload(Component c) {
		BufferedImage image;
		image = getBufferedImage(c);
		UploadThread t = new UploadThread(image);
		t.start();
	}

	private BufferedImage getBufferedImage(Component c){
		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		c.paintAll(image.getGraphics());
		return image;
	}
	
	
	public static void upload(BufferedImage image) {
	    String IMGUR_POST_URI = "https://api.imgur.com/3/image";
	    String IMGUR_CLIENT_ID = "c48fa296e7c4fb8";
	    try {
	    	HttpClient client = HttpClients.createDefault();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        System.out.println("Writing image...");
	        ImageIO.write(image, "png", baos);
	        System.out.println("Encoding...");
	       
	        HttpPost post = new HttpPost(IMGUR_POST_URI);
	        
	        List<NameValuePair> values = new ArrayList<NameValuePair>(1);
	        System.out.println(Base64.encodeBase64String(baos.toByteArray()).toString());
	        values.add(new BasicNameValuePair("image", Base64.encodeBase64String(baos.toByteArray()).toString()));
	//        values.add(new BasicNameValuePair("type", "base64"));
	        
	        post.setEntity(new UrlEncodedFormEntity(values));
	       
	        post.addHeader("Authorization","Client-ID " + IMGUR_CLIENT_ID);
	        
	        System.out.println("Finished.");
	        
	        HttpResponse res = client.execute(post);
	        
	        for(Header h:res.getAllHeaders()){
	        	System.out.println(h.getName() + " = " + h.getValue()); 
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        BufferedReader read = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
	        
	        String line = null;
	        while ((line = read.readLine()) != null){
	        	sb.append(line);
	        }
	        
	        String body = sb.toString();
	        
	        int location = body.indexOf("id");
	        location = body.indexOf(":", location);
	        location = body.indexOf('"', location);
	        int last_location = body.indexOf('"', location+1);
	        
	        String id = body.substring(location+1, last_location);
	        System.out.println(id);
	        System.out.println(location);
	        System.out.println(last_location);
	        String imageLink = "http://imgur.com/" + id;
	        CostumPopUpWindow sWindow = new CostumPopUpWindow();
	        int c = sWindow.show(imageLink);
			if (c == 1){
			    StringSelection selection = new StringSelection(imageLink);
			    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clipboard.setContents(selection, selection);
			}
	    } catch(Exception e){
	        e.printStackTrace();
	    }
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
