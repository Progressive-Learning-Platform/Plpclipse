package plptool.visualizer;

import java.awt.Image;
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

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import plptool.visualizer.communication.BackendProducer;
import plptool.visualizer.communication.FrontendConsumer;
import plptool.visualizer.event.SnapshotEventHandler;
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

public class PLPVisualizer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;
	private static PLPVisualizer instance = null;
	private final plpGraph graph;

	/** 
	 * Default Constructor
	 */
	public static PLPVisualizer getInstance(boolean pipelined)
	{
		if(instance == null)
		{
			instance = new PLPVisualizer();
		}
		if (pipelined)
			instance.drawGraph("config/graph_pipelined.json");
		else
			instance.drawGraph("config/graph_non_pipelined.json");
		return instance;
	}
	
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
	
	protected PLPVisualizer()
	{
		super("PLP Visualizer");

		this.initializeVertexStyle();
		graph = new plpGraph();

		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		graph.setCellsLocked(true);
		graphComponent.setConnectable(false);
		graphComponent.setToolTips(true);
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent e)
			{
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				if (cell != null)
				{
					System.out.println("cell="+graph.getLabel(cell));
				}
			}
			public void mouseEntered(MouseEvent e)
			{
				System.out.println("Hover");
			}
			public void mouseExited(MouseEvent e)
			{
				System.out.println("Leave");
			}
			public void mouseMoved(MouseEvent e)
			{
				System.out.println("Moved");
			}
		});
		FrontendConsumer frontend = new FrontendConsumer();
		frontend.addListener(new SnapshotEventHandler(){
			ArrayList<Object> enabled_list = null;
			public void receiveSnapshot(String jsonString)
			{
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
				// Then clear whole list to store new enabled edges.
				enabled_list.clear();
				while( json_keys.hasNext() ){
					String json_key = (String)json_keys.next();
					JSONObject data = edges.getJSONObject(json_key);
					mxCell myCell = (mxCell)((mxGraphModel)graph.getModel()).getCell(json_key);
					if (myCell != null) {
						enabled_list.add(myCell);
						myCell.setValue(data);
					}
				}
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", enabled_list.toArray());
			}
		});
		thread(frontend, false);
	}
	
	public void drawGraph(String conf_file)
	{
		Object parent = graph.getDefaultParent();
		this.graph.removeCells(graph.getChildVertices(parent));

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

		graph.getModel().beginUpdate();
		try
		{
			// create nodex
			Iterator<?> json_keys = vertices.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = vertices.getJSONObject(json_key);
				graph.insertVertex(parent, node.getString("id"),
									(Object)node.getString("name"),
											node.getDouble("pos_x"),
											node.getDouble("pos_y"),
											node.getDouble("width"),
											node.getDouble("height"));
			}

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
						waypoint.add(new mxPoint(point.getInt(0),
												point.getInt(1)));
					}
					geo.setPoints(waypoint);
				}
			}
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
	
	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
