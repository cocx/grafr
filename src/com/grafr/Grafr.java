package com.grafr;

public class Grafr {
	
	static GUIHandeler window;
	static GraphHandeler graph;
	static ToolHandeler toolhandeler;
	static ScreenshotHandeler screenshotHandeler;
	public static void main(String[] args) {
		window = new GUIHandeler();
		graph = new GraphHandeler();
		toolhandeler = new ToolHandeler();
		screenshotHandeler = new ScreenshotHandeler();
		window.show();
	}
}
