package com.grafr;

import java.awt.FileDialog;
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
	FileDialog file = new FileDialog(Grafr.window.frame,"Choose a file.");

	public void save() {
		File save;
		file.setMode(FileDialog.SAVE);
		file.setVisible(true);
		if(file.getDirectory() != null){
			try{
				save = new File(file.getDirectory()+file.getFile());
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			saveContentToFile(graphToString(), save);
		}
	}

	public void load() {
		File save;
		file.setMode(FileDialog.LOAD);
		file.setVisible(true);
		if(file.getDirectory() != null){
			Grafr.graph.clear();
			try{
				save = new File(file.getDirectory()+file.getFile());
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
			fileToGraph(save);
		}
	}

	private String graphToString() {
		String stringGraph = null;
		Edge e;
		stringGraph = "NODES: " + System.lineSeparator();
		for (Vertex c: Grafr.graph.graphBackend.nodes.values()) {
			//c = Grafr.graph.graphBackend.nodes.get(i);
			if (c != null) {
				stringGraph += "Id: " + c.id;
				stringGraph += ", Name: " + c.vertex.getValue();
				stringGraph += ", Position: ";
				stringGraph += "x: " + c.vertex.getGeometry().getX();
				stringGraph += " y: " + c.vertex.getGeometry().getY();
				stringGraph += System.lineSeparator();
			}
		}
		stringGraph += "EDGES: " + System.lineSeparator();
		for (Vertex c: Grafr.graph.graphBackend.nodes.values()) {
			if (c != null) {
				for (int j = 0; j < c.edges_from.size(); j++) {
					e = c.edges_from.get(j);
					if (e != null) {
						stringGraph += "IDFrom: " + e.from.id;
						stringGraph += ", IDTo: " + e.to.id;
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

	private void saveContentToFile(String content, File save) {
		try {
			save.createNewFile();
			FileWriter fw = new FileWriter(save.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fileToGraph(File save) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(save));
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
			if (line != null) {
				if (line.startsWith("NODES: ")) {
					line = br.readLine();
					while (line != null && !line.startsWith("EDGES: ")) {
						id = Integer.parseInt(line.substring(line.indexOf("Id: ") + 4, line.indexOf(", Name: ")));
						name = line.substring(line.indexOf("Name: ") + 6, line.indexOf(",", line.indexOf("Name: ")));
						x = Double.parseDouble(
								line.substring(line.indexOf("x: ") + 3, line.indexOf("y: ", line.indexOf("x: "))));
						y = Double.parseDouble(line.substring(line.indexOf("y: ") + 3, line.length()));
						v = Grafr.graph.addVertex(name, x, y);
						idOldToNew.put(id, v.id);
						line = br.readLine();
					}
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
