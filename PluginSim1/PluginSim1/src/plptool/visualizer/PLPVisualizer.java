package plptool.visualizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import org.json.JSONObject;

import plptool.visualizer.communication.BackendProducer;
import plptool.visualizer.communication.FrontendConsumer;
import plptool.visualizer.event.SnapshotEventHandler;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

public class PLPVisualizer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;
	private static PLPVisualizer instance = null;
	/** 
	 * Default Constructor
	 */
	
	public static PLPVisualizer getInstance()
	{
		if(instance == null)
		{
			instance = new PLPVisualizer();
		}
		return instance;
	}
	
	protected PLPVisualizer()
	{
		super("PLP Visualizer");

		final myGraph graph = new myGraph();
		Object parent = graph.getDefaultParent();

		String conf_file = "config/graph_blue_print.json";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
				graph.insertEdge(parent, node.getString("id"),
										(Object)node.getString("name"),
										first_node, second_node,
										node.getString("style"));
			}
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
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
			public void receiveSnapshot(String jsonString)
			{
				JSONObject snapshot = new JSONObject(jsonString);
				JSONObject vertices = snapshot.getJSONObject("vertices_values");
				Iterator<?> json_keys = vertices.keys();
				ArrayList<Object> enabled_list = new ArrayList<Object>();
				ArrayList<Object> disabled_list = new ArrayList<Object>();
				
				while( json_keys.hasNext() ){
					String json_key = (String)json_keys.next();
					JSONObject node = vertices.getJSONObject(json_key);
					mxCell myCell = (mxCell) ((mxGraphModel)graph.getModel()).getCell(json_key);
					myCell.setValue(node);
				}
				
				JSONObject edges = snapshot.getJSONObject("enabled_edges");
				json_keys = edges.keys();
				
				while( json_keys.hasNext() ){
					String json_key = (String)json_keys.next();
					int enabled = edges.getInt(json_key);
					Object myCell = ((mxGraphModel)graph.getModel()).getCell(json_key);
					if (enabled == 1)
						enabled_list.add(myCell);
					else
						disabled_list.add(myCell);
				}
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", enabled_list.toArray());
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", disabled_list.toArray());
			}
		});
		thread(frontend, false);
	}

	public static void main(String[] args)
	{
		PLPVisualizer frame = PLPVisualizer.getInstance();
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
