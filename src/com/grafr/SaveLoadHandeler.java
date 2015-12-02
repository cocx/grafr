package com.grafr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.grafr.GraphBackend.Edge;
import com.grafr.GraphBackend.Vertex;
import com.mxgraph.model.mxCell;

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
			Vertex.NextID = 0;
			Grafr.graph.clear();
			if (Grafr.algoHandeler.isAlgoRunning()) {
				Grafr.algoHandeler.clear();
			}
			fileToGraph(Integer.parseInt(number));
		}
	}

	private String graphToString() {
		String stringGraph = null;
		Vertex c;
		Edge e;
		int miss = 0;
		stringGraph = "NODES: " + System.lineSeparator();
		for (int i = 0; i < Grafr.graph.graphBackend.nodes.size() + miss; i++) {
			c = Grafr.graph.graphBackend.nodes.get(i);
			if (c != null) {
				stringGraph += "Id: " + c.vertex.getId();
				stringGraph += ", Name: " + c.vertex.getValue();
				stringGraph += ", Position: ";
				stringGraph += "x: " + c.vertex.getGeometry().getX();
				stringGraph += " y: " + c.vertex.getGeometry().getY();
				stringGraph += System.lineSeparator();
			}else{
				miss++;
			}
		}
		stringGraph += "EDGES: " + System.lineSeparator();
		for (int i = 0; i < Grafr.graph.graphBackend.nodes.size(); i++) {
			c = Grafr.graph.graphBackend.nodes.get(i);
			if (c != null) {
				for (int j = 0; j < c.edges_from.size(); j++) {
					e = c.edges_from.get(j);
					if (e != null) {
						stringGraph += "IDFrom: " + e.from.vertex.getId();
						stringGraph += ", IDTo: " + e.to.vertex.getId();
						stringGraph += ", Weight: " + e.weight;
						stringGraph += System.lineSeparator();
					}
				}
			}
		}
		stringGraph += "START VERTEX: " + System.lineSeparator();
		if (Grafr.graph.startVertex != null) {
			stringGraph += Grafr.graph.startVertex.vertex.getId() + System.lineSeparator();
		} else {
			stringGraph += "NULL" + System.lineSeparator();
		}
		stringGraph += "END VERTEX: " + System.lineSeparator();
		if (Grafr.graph.endVertex != null) {
			stringGraph += Grafr.graph.endVertex.vertex.getId() + System.lineSeparator();
		} else {
			stringGraph += "NULL" + System.lineSeparator();
		}
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
			HashMap<Integer, Integer> idOldToNew = new HashMap<Integer, Integer>();
			Vertex v;
			String line = br.readLine();
			String name;
			int start;
			int end;
			int id;
			double x;
			double y;
			int from;
			int to;
			int weight;
			int max = 0;
			if (line != null) {
				if (line.startsWith("NODES: ")) {
					line = br.readLine();
					while (line != null && !line.startsWith("EDGES: ")) {
						id = Integer.parseInt(line.substring(line.indexOf("Id: ") + 4, line.indexOf(", Name: ")));
						if (id > max){
							max = id;
						}
						name = line.substring(line.indexOf("Name: ") + 6, line.indexOf(",", line.indexOf("Name: ")));
						x = Double.parseDouble(
								line.substring(line.indexOf("x: ") + 3, line.indexOf("y: ", line.indexOf("x: "))));
						y = Double.parseDouble(line.substring(line.indexOf("y: ") + 3, line.length()));
						v = Grafr.graph.addVertex(name, x, y);
						idOldToNew.put(id, v.id);
						line = br.readLine();
					}
					 Vertex.NextID = max + 1;
				}
				if (line.startsWith("EDGES: ")) {
					line = br.readLine();
					while (line != null && !line.startsWith("START VERTEX: ")) {
						from = Integer.parseInt(line.substring(line.indexOf("IDFrom: ") + 8, line.indexOf(", IDTo: ")));
						to = Integer.parseInt(line.substring(line.indexOf("IDTo: ") + 6, line.indexOf(", Weight: ")));
						weight = Integer.parseInt(line.substring(line.indexOf("Weight: ") + 8, line.length()));
						from = idOldToNew.get(from);
						to = idOldToNew.get(to);
						Grafr.graph.addEdge(Grafr.graph.graphBackend.nodes.get(from),
								Grafr.graph.graphBackend.nodes.get(to), weight);
						Grafr.graph.refresh();
						line = br.readLine();
					}
				}
				if (line.startsWith("START VERTEX: ")) {
					line = br.readLine();
					if (line != null && !line.startsWith("END VERTEX: ") && !line.startsWith("NULL")) {
						start = idOldToNew.get(Integer.parseInt(line));
						Grafr.graph.setAsStart(Grafr.graph.graphBackend.nodes.get(start));
						line = br.readLine();
					}
				}
				if (line.startsWith("END VERTEX: ")) {
					line = br.readLine();
					if (line != null && !line.startsWith("NULL")) {
						end = idOldToNew.get(Integer.parseInt(line));
						Grafr.graph.setAsEnd(Grafr.graph.graphBackend.nodes.get(end));
						line = br.readLine();
					}
				}
			}
			br.close();
			idOldToNew.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
