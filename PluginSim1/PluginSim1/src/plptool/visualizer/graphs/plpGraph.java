/**
    Copyright 2016 PLP Contributors

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package plptool.visualizer.graphs;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.ToolTipManager;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

/**
 * Customized JGraph component
 * Only for tooltip so far.
 */
public class plpGraph extends mxGraph{
	
	public static final NumberFormat numberFormat = NumberFormat.getInstance();
	/**
	 * To enable this debug function, goto Run->Run Configuration->VM Arguments
	 * set: -Ddebug=true
	 */
	public static final String DEBUG = System.getProperty("debug");

	/**
	 * Override the tooltip for cell.
	 * When hover on the cell, it will display the value inside the cell.
	 * Debugging info contains the coordinate and size of the cell.
	 */
	@Override
	public String getToolTipForCell(Object cell)
	{
		ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
		String tip = "<html>";
		mxGeometry geo = getModel().getGeometry(cell);
		mxCellState state = getView().getState(cell);
		mxCell mycell = (mxCell)cell;

		tip += mycell.getValue().toString().replaceAll(",", "<br>");
		// if the debug model did not enabled, return.
		if(DEBUG == null || !DEBUG.equalsIgnoreCase("true")) {
			tip += "</html>";
			return tip;
		}

		// otherwise, append more information for debugging.
		tip += "<br>";
		if (getModel().isEdge(cell))
		{
			tip += "points={";

			if (geo != null)
			{
				List<mxPoint> points = geo.getPoints();

				if (points != null)
				{
					Iterator<mxPoint> it = points.iterator();

					while (it.hasNext())
					{
						mxPoint point = it.next();
						tip += "[x=" + numberFormat.format(point.getX())
								+ ",y=" + numberFormat.format(point.getY())
								+ "],";
					}

					tip = tip.substring(0, tip.length() - 1);
				}
			}

			tip += "}<br>";
			tip += "absPoints={";

			if (state != null)
			{

				for (int i = 0; i < state.getAbsolutePointCount(); i++)
				{
					mxPoint point = state.getAbsolutePoint(i);
					tip += "[x=" + numberFormat.format(point.getX())
							+ ",y=" + numberFormat.format(point.getY())
							+ "],";
				}

				tip = tip.substring(0, tip.length() - 1);
			}

			tip += "}<br>";
		}
		else
		{
			tip += "geo=[";
			if (geo != null)
			{
				tip += "x=" + numberFormat.format(geo.getX()) + ",y="
						+ numberFormat.format(geo.getY()) + ",width="
						+ numberFormat.format(geo.getWidth()) + ",height="
						+ numberFormat.format(geo.getHeight());
			}	
				
			tip += "]<br>";
			tip += "state=[";
			
			if (state != null)
			{
				tip += "x=" + numberFormat.format(state.getX()) + ",y="
						+ numberFormat.format(state.getY()) + ",width="
						+ numberFormat.format(state.getWidth())
						+ ",height="
						+ numberFormat.format(state.getHeight());
			}
			tip += "]<br>";
		}

		mxPoint trans = getView().getTranslate();
		tip += "<br>scale=" + numberFormat.format(getView().getScale())
				+ ", translate=[x=" + numberFormat.format(trans.getX())
				+ ",y=" + numberFormat.format(trans.getY()) + "]";
		tip += "</html>";
		return tip;
	}
}
