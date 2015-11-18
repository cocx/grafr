package com.grafr;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mxgraph.model.mxCell;

public class RemoveNodeTool implements AbstractTool {
	mxCell cell;
	
	@Override
	public void onClickVertex(mxCell c) {
		/*graphComponent.getGraphControl().addMouseListener(new MouseAdapter() 
		{
		@Override
		    public void mouseReleased(MouseEvent e) 
		    {    
		        mxCell cell =(mxCell) getGraphComponent().getCellAt(e.getX(), e.getY());
		        if(cell != null && cell.equals(YOUR_VERTEX))
		        {
		            //specific thing you want to do on click
		        }
		    }
		});
*/
	}

	@Override
	public void onClickEdge(mxCell e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickCanvas(int x, int y) {
		// TODO Auto-generated method stub

	}

}
