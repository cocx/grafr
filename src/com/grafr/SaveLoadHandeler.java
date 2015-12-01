package com.grafr;

import java.io.File;

public class SaveLoadHandeler {
	void saveToFile(String filename, String [] content){
		int savecount = 0;
		File save = new File("/saves/test"+ savecount +".txt");
		while (save.exists()){
			
		}
	}
}
