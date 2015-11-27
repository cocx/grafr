package com.grafr;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;

public class ScreenshotHandeler {
	
	class UploadThread extends Thread{
		BufferedImage image;
		
		public UploadThread(BufferedImage image) {
			this.image = image;
		}
		
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
	    String IMGUR_API_KEY = "KEY";
	    String IMGUR_CLIENT_ID = "c48fa296e7c4fb8";
	    String IMGUR_CLIENT_SECRET = "401c5c2dbcca413609552e054ba1ecf318598735";

	    String file = "imgs/default.png";
        String charset = null;
        
	    try {
	    	HttpClient client = HttpClients.createDefault();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        System.out.println("Writing image...");
	        ImageIO.write(image, "png", baos);
	        URL url = new URL(IMGUR_POST_URI);
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
	        
	        JOptionPane.showInputDialog(Grafr.graph, "Image link", "", JOptionPane.OK_OPTION, null, null, "http://imgur.com/"+id);
	        
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
