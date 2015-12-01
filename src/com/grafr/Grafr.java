package com.grafr;

public class Grafr {
	
	static GUIHandeler window;
	static GraphHandeler graph;
	static ToolHandeler toolhandeler;
	static AlgoritmeHandeler algoHandeler;
	static ScreenshotHandeler screenshotHandeler;
	static SaveLoadHandeler SLHandeler;
	public static void main(String[] args) {
		window = new GUIHandeler();
		graph = new GraphHandeler();
		toolhandeler = new ToolHandeler();
		algoHandeler = new AlgoritmeHandeler();
		screenshotHandeler = new ScreenshotHandeler();
		SLHandeler = new SaveLoadHandeler();
		window.show();
	}
}
