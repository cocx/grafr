package com.grafr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveLoadHandeler {
	String filename = "save";

	public void saveContentToFile(String content, int number) {
		File save;
		try {
			save = new File("saves/" + filename + number + ".txt");
			if (save.exists()) {
				int a = JOptionPane.showConfirmDialog(null,
						"Save already in use" + System.lineSeparator()
						+ "Do you wish to overwrite?", "",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(a);
				if (a == 0) {
					save.createNewFile();
				} else {
					return;
				}
			}
			FileWriter fw = new FileWriter(save.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readContentFromFile(int number) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saves/"
					+ filename + number + ".txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
