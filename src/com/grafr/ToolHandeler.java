package com.grafr; 

public class ToolHandeler {
	static private AbstractTool currentTool;
	
	static void setTool(AbstractTool tool){
		currentTool = tool;
	}
	
}