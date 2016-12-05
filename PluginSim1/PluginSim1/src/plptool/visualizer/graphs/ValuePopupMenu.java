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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;

import com.mxgraph.model.mxCell;

/**
 * Create a popup menu to display value inside the cell.
 */
public class ValuePopupMenu extends JPopupMenu {

	private static final long serialVersionUID = -8439317231714112220L;
	
	/**
	 * Constructor, used for populating the PopupMenu with the component values
	 * @param cell The cell needs to display.
	 */
	public ValuePopupMenu(mxCell cell) {
		String []values = cell.getValue().toString().split(",");
		// for each value, create an item on menu.
		for (String value: values) {
			AbstractAction newAction = new AbstractAction(value)
			{
				private static final long serialVersionUID = 3604632160824380941L;
				public void actionPerformed(ActionEvent e)
				{
					// do nothing when select the value.
				}
			};
			add(newAction);
		}
	}
}
