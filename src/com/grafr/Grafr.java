package com.grafr;

import java.awt.EventQueue;

public class Grafr {
	
	static GUIHandeler window;
	static GraphHandeler graph;
	static ToolHandeler toolhandeler;
	public static void main(String[] args) {
		window = new GUIHandeler();
		graph = new GraphHandeler();
		toolhandeler = new ToolHandeler();
		window.show();
	}
}
