package com.grafr;

import com.grafr.GraphBackend.Vertex;

public interface AbstractAlgoritme {
	public void init(GraphBackend g,Vertex start,Vertex end);
	
	public void clear();
	
	public void next();
}
