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
package plptool.visualizer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import plptool.Config;
import plptool.visualizer.communication.BackendProducer;
import plptool.visualizer.communication.FrontendConsumer;
import plptool.visualizer.event.SnapshotEventHandler;
import plptool.visualizer.graphs.ValuePopupMenu;
import plptool.visualizer.graphs.plpGraph;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;

/**
 * Main class of PLP data path visualizer.
 * Please remain in singleton model!
 * Using getInstance method to get an instance.
 */
public class PLPVisualizer extends JFrame
{
	/**
	 * Randomly generated Serial ID.
	 */
	private static final long serialVersionUID = -2707712944901661771L;
	
	/**
	 * The instance of this class.
	 */
	private static PLPVisualizer instance = null;
	
	/**
	 * The data path graph frame.
	 */
	private final plpGraph graph;
	
	/**
	 * The data path graph component.
	 */
	private final mxGraphComponent graphComponent;
	
	/**
	 * The path of configuration file to draw the data path.
	 */
	private static String conf_file = null;

	/** 
	 * Get one valid instance.
	 * @param pipelined True pipeline enabled, False pipeline disabled.
	 *		This flag bit stored in Config.java "simFunctional".
	 */
	public static PLPVisualizer getInstance(boolean pipelined)
	{
		if(instance == null)
		{
			instance = new PLPVisualizer();
		}
		if (pipelined) {
			conf_file = Config.pipeLinedBlueprint;
			instance.drawGraph(1.0);
		}
		else {
			conf_file = Config.nonPipelinedBlueprint;
			instance.drawGraph(1.0);
		}

		return instance;
	}
	
	/**
	 * Load customized shape.
	 * We customized the shape of ALU and MUX in shape.xml
	 */
	private void initializeVertexStyle() {
		try {
			Document doc = mxXmlUtils.parseXml(mxUtils.readFile("config/shape.xml"));
	
			Element shapes = (Element) doc.getDocumentElement();
			NodeList list = shapes.getElementsByTagName("shape");
	
			for (int i = 0; i < list.getLength(); i++)
			{
				Element shape = (Element) list.item(i);
				mxStencilRegistry.addStencil(shape.getAttribute("name"),
						new mxStencil(shape)
						{
							protected mxGraphicsCanvas2D createCanvas(
									final mxGraphics2DCanvas gc)
							{
								// Redirects image loading to graphics canvas
								return new mxGraphicsCanvas2D(gc.getGraphics())
								{
									protected Image loadImage(String src)
									{
										// Adds image base path to relative image URLs
										if (!src.startsWith("/")
												&& !src.startsWith("http://")
												&& !src.startsWith("https://")
												&& !src.startsWith("file:"))
										{
											src = gc.getImageBasePath() + src;
										}
	
										// Call is cached
										return gc.loadImage(src);
									}
								};
							}
						});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Default Constructor, please remain singleton.
	 * Don't change the protected keyword.
	 */
	protected PLPVisualizer()
	{
		super("PLP Visualizer");

		this.initializeVertexStyle();
		// Listen to the component change event.
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				Dimension newSize = e.getComponent().getBounds().getSize();
				double newFactor = newSize.getHeight() / 600;
				if (newSize.getWidth() / 800 > newFactor)
					newFactor = newSize.getWidth() / 800;
				drawGraph(newFactor);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		graph = new plpGraph();

		graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		// lock all cells, avoid user to move them.
		graph.setCellsLocked(true);
		// disable add new edges.
		graphComponent.setConnectable(false);
		// listen to the hover event.
		graphComponent.setToolTips(true);
		// listen to the mouse event.
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				mxCell cell = (mxCell)graphComponent.getCellAt(e.getX(), e.getY());
				if (cell != null)
				{
					showGraphPopupMenu(e, cell);
				}
			}
			public void mouseEntered(MouseEvent e)
			{
			}
			public void mouseExited(MouseEvent e)
			{
			}
			public void mouseMoved(MouseEvent e)
			{
			}
		});
		// Create a new thread to listen to the message.
		FrontendConsumer frontend = new FrontendConsumer();
		// Handle the message.
		frontend.addListener(new SnapshotEventHandler(){
			ArrayList<Object> enabled_list = null;

			@Override
			public void receiveSnapshot(String jsonString)
			{
				try {
					JSONObject snapshot = new JSONObject(jsonString);
					JSONObject vertices = snapshot.getJSONObject("vertices_values");
					Iterator<?> json_keys = vertices.keys();
					if (enabled_list == null)
						enabled_list = new ArrayList<Object>();
					
					while( json_keys.hasNext() ){
						String json_key = (String)json_keys.next();
						JSONObject data = vertices.getJSONObject(json_key);
						mxCell myCell = (mxCell) ((mxGraphModel)graph.getModel()).getCell(json_key);
						if (myCell != null)
							myCell.setValue(data);
					}
					
					JSONObject edges = snapshot.getJSONObject("enabled_edges");
					json_keys = edges.keys();
					// Set previous enabled edges to disabled.
					graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", enabled_list.toArray());
					for (Object myCell : enabled_list) {
						((mxCell)myCell).setValue("");
					}
					// Then clear whole list to store new enabled edges.
					enabled_list.clear();
					while( json_keys.hasNext() ){
						String json_key = (String)json_keys.next();
						String data = edges.getString(json_key);
						mxCell myCell = (mxCell)((mxGraphModel)graph.getModel()).getCell(json_key);
						if (myCell != null) {
							enabled_list.add(myCell);
							myCell.setValue(data);
						}
					}
					graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", enabled_list.toArray());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		thread(frontend, false);
	}
	
	/**
	 * Respond to mouse click event, pop up a menu to display value in the cell.
	 * @param e the source of mouse event.
	 * @param cell the cell needs to display value.
	 */
	private void showGraphPopupMenu(MouseEvent e, mxCell cell)
	{
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		ValuePopupMenu menu = new ValuePopupMenu(cell);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}
	
	/**
	 * Construct the data path graph based on the blue_print.
	 * @param rescale When window resized, Using a new scale factor 
	 * 				to reconstruct the graph.
	 */
	private void drawGraph(double rescale)
	{
		Object parent = graph.getDefaultParent();
		this.graph.removeCells(graph.getChildVertices(parent));
		// rescale the font size.
		graph.getStylesheet().getDefaultVertexStyle().put(mxConstants.STYLE_FONTSIZE, Double.toString(22 * rescale));

		// read the blueprint file.
		BufferedReader reader;
		String line = null;
		String jsonString = "";
		try {
			reader = new BufferedReader(new FileReader (conf_file));
			while((line = reader.readLine()) != null) {
				jsonString += line;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject conf = new JSONObject(jsonString);
		JSONObject vertices = conf.getJSONObject("vertices");
		JSONObject edges = conf.getJSONObject("edges");

		// start drawing the graph
		graph.getModel().beginUpdate();
		try
		{
			// create nodex
			Iterator<?> json_keys = vertices.keys();
			ArrayList<Object> cells = new ArrayList<Object>();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = vertices.getJSONObject(json_key);
				Object vertex = graph.insertVertex(parent, node.getString("id"),
									(Object)node.getString("name"),
											node.getDouble("pos_x") * rescale,
											node.getDouble("pos_y") * rescale,
											node.getDouble("width") * rescale,
											node.getDouble("height") * rescale,
											node.getString("shape"));
				cells.add(vertex);
			}

			graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "0xb2aaff", cells.toArray());
			cells.clear();
			// create edges
			json_keys = edges.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = edges.getJSONObject(json_key);
				Object first_node = ((mxGraphModel)graph.getModel()).getCell(
											node.getString("vertex_id_from"));
				Object second_node = ((mxGraphModel)graph.getModel()).getCell(
											node.getString("vertex_id_to"));
				Object edge = graph.insertEdge(parent, node.getString("id"),
										(Object)node.getString("name"),
										first_node, second_node,
										node.getString("style")+"noLabel=1;");
				cells.add(edge);
				// customize way points
				/**
				 * Some edges may overlap the vertex, so we using "way points" 
				 * to specified an edge's route.
				 */
				if (node.has("way_points")) {
					JSONArray way_points = node.getJSONArray("way_points");
					Iterator<?> array_iterator = way_points.iterator();
					mxGeometry geo = graph.getModel().getGeometry(edge);
					List<mxPoint> waypoint = new ArrayList<mxPoint>();
					while(array_iterator.hasNext()) {
						JSONArray point = (JSONArray)array_iterator.next();
						waypoint.add(new mxPoint(point.getInt(0) * rescale,
												point.getInt(1) * rescale));
					}
					geo.setPoints(waypoint);
				}
			}
			graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", cells.toArray());
		}
		finally
		{
			graph.getModel().endUpdate();
		}
	}

	/**
	 * Test function, you can run front end separately to test it.
	 * @param args no arguments required.
	 */
	public static void main(String[] args)
	{
		PLPVisualizer frame = PLPVisualizer.getInstance(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		thread(new BackendProducer(), false);
	}
	
	/**
	 * Create a new thread.
	 * @param runnable A thread instance.
	 * @param daemon In daemon model or not.
	 */
	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
