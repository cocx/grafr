package com.grafr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;

public class SaveLoadHandeler {
	String filename = "save";

	public void save() {
		String number;
		number = JOptionPane.showInputDialog(Grafr.graph, "Choose a number to store your graph under.", "",
				JOptionPane.QUESTION_MESSAGE);
		if (Integer.parseInt(number) > 99) {
			JOptionPane.showMessageDialog(Grafr.graph, "Pick a number below 100.", "", JOptionPane.ERROR_MESSAGE);
		} else if (number != null) {
			saveContentToFile(graphToString(), Integer.parseInt(number));
		}
	}

	public void load() {
		String number;
		number = JOptionPane.showInputDialog(Grafr.graph, "Choose a number to load your graph from.", "",
				JOptionPane.QUESTION_MESSAGE);
		if (Integer.parseInt(number) > 99) {
			JOptionPane.showMessageDialog(Grafr.graph, "Pick a number below 100.", "", JOptionPane.ERROR_MESSAGE);
		} else if (number != null) {
			fileToGraph(Integer.parseInt(number));
		}
	}

	private String graphToString() {
		String stringGraph = null;
		Vertex c;
		Edge e;
		stringGraph = "NODES: " + System.lineSeparator();
		for (int i = 0; i < Grafr.graph.graphBackend.nodes.size(); i++) {
			c = Grafr.graph.graphBackend.nodes.get(i);
			stringGraph += "Id: " + c.vertex.getId();
			stringGraph += ", Name: " + c.vertex.getValue();
			stringGraph += ", Position: ";
			stringGraph += "x: " + c.vertex.getGeometry().getX();
			stringGraph += " y: " + c.vertex.getGeometry().getY();
			stringGraph += System.lineSeparator();
		}
		stringGraph += "EDGES: " + System.lineSeparator();
		for (int i = 0; i < Grafr.graph.graphBackend.nodes.size(); i++) {
			c = Grafr.graph.graphBackend.nodes.get(i);
			for (int j = 0; j < c.edges_from.size(); j++) {
				e = c.edges_from.get(j);
				stringGraph += "IDFrom: " + e.from.vertex.getId();
				stringGraph += ", IDTo: " + e.to.vertex.getId();
				stringGraph += ", Weight: " + e.weight;
				stringGraph += System.lineSeparator();
			}
		}
		stringGraph += "START VERTEX: " + System.lineSeparator();
		if (Grafr.graph.startVertex != null) {
			stringGraph += Grafr.graph.startVertex.id + System.lineSeparator();
		} else {
			stringGraph += "NULL" + System.lineSeparator();
		}
		stringGraph += "END VERTEX: " + System.lineSeparator();
		if (Grafr.graph.endVertex != null) {
			stringGraph += Grafr.graph.endVertex.id + System.lineSeparator();
		} else {
			stringGraph += "NULL" + System.lineSeparator();
		}
		System.out.println(stringGraph);
		return stringGraph;
	}

	private void saveContentToFile(String content, int number) {
		File save;
		try {
			save = new File("saves/" + filename + number + ".txt");
			if (save.exists()) {
				int a = JOptionPane.showConfirmDialog(null,
						"Save already in use" + System.lineSeparator() + "Do you wish to overwrite?", "",
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

	private void fileToGraph(int number) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saves/" + filename + number + ".txt"));
			String line = br.readLine();
			String name;
			double x;
			double y;
			int from;
			int to;
			int weight;
			if (line != null) {
				if (line.startsWith("NODES: ")){
					line = br.readLine();
					while (line != null && !line.startsWith("EDGES: ")){
						name = line.substring(line.indexOf("Name: ") + 6, line.indexOf(",",line.indexOf("Name: ")));
						x = Double.parseDouble(line.substring(line.indexOf("x: ") + 3, line.indexOf("y: ", line.indexOf("x: "))));
						y = Double.parseDouble(line.substring(line.indexOf("y: ") + 3, line.length()));
						Grafr.graph.addVertex(name, x, y);
						line = br.readLine();
					}
				}
				if (line.startsWith("EDGES: ")){
					line = br.readLine();
					while (line != null && !line.startsWith("START VERTEX: ")){
						System.out.println(line);
						from = Integer.parseInt(line.substring(line.indexOf("IDFrom: ") + 8, line.indexOf(", IDTo: ")));						
						to = Integer.parseInt(line.substring(line.indexOf("IDTo: ") + 6, line.indexOf(", Weight: ")));
						weight = Integer.parseInt(line.substring(line.indexOf("Weight: ") + 8, line.length()));
						//Grafr.graph.addEdge(from, weight, to);
						line = br.readLine();
					}
				}
				if (line.startsWith("START VERTEX: ")){
					line = br.readLine();
					if (line != null && !line.startsWith("END VERTEX: ")){
						//Grafr.graph.setAsStart(Integer.parseInt(line));
					}
				}
				if (line.startsWith("END VERTEX: ")){
					line = br.readLine();
					if (line != null){
						//Grafr.graph.setAsEnd(Integer.parseInt(line));
					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
