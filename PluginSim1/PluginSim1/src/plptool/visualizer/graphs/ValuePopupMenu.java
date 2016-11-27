package plptool.visualizer.graphs;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

import com.mxgraph.model.mxCell;

public class ValuePopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439317231714112220L;

	public ValuePopupMenu(mxCell cell) {
		String []values = cell.getValue().toString().split(",");
		for (String value: values) {
			AbstractAction newAction = new AbstractAction(value)
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 3604632160824380941L;

				public void actionPerformed(ActionEvent e)
				{
				}
			};
			add(newAction);
		}
	}
}
