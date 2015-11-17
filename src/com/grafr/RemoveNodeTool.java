package com.grafr;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mxgraph.model.mxCell;

public class RemoveNodeTool implements AbstractTool {
	mxCell cell;
	
	@Override
	public void onClickVertex() {
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
	public void onClickEdge() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClickCanvas() {
		// TODO Auto-generated method stub

	}

}
