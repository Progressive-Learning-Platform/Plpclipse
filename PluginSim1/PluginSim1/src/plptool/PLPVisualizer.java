package plptool;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;

import plptool.BackendProducer;
import plptool.FrontendConsumer;
import plptool.SnapshotEventHandler;
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

	/** 
	 * Default Constructor
	 * @throws JSONException 
	 */
	public PLPVisualizer() throws JSONException
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

		// nodes
		Object pc;
		Object add1;
		Object instruction_memory;
		Object control;
		Object alu_control;
		Object alu;
		Object mux3;
		Object mux4;
		Object and_gate;

		// edges
		Object pc_add1;
		Object pc_im;
		Object im_control;
		Object im_aluc;
		Object control_mux4;
		Object add1_mux4;
		Object aluc_alu;
		Object alu_andgate;
		Object andgate_mux3;
		
		graph.getModel().beginUpdate();
		try
		{
			Iterator<?> json_keys = vertices.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = vertices.getJSONObject(json_key);
				graph.insertVertex(parent, node.getString("id"), (Object)node.getString("name"), node.getDouble("pos_x"), node.getDouble("pos_y"), node.getDouble("width"), node.getDouble("height"));
			}
			pc = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc");
			add1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("add1");
			instruction_memory = (mxCell) ((mxGraphModel)graph.getModel()).getCell("instruction_memory");
			control = (mxCell) ((mxGraphModel)graph.getModel()).getCell("control");
			alu_control = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu_control");
			alu = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu");
			mux3 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux3");
			mux4 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("mux4");
			and_gate = (mxCell) ((mxGraphModel)graph.getModel()).getCell("and_gate");


			json_keys = edges.keys();
			
			while( json_keys.hasNext() ){
				String json_key = (String)json_keys.next();
				JSONObject node = edges.getJSONObject(json_key);
				Object first_node = (mxCell) ((mxGraphModel)graph.getModel()).getCell(node.getString("vertex_id_from"));
				Object second_node = (mxCell) ((mxGraphModel)graph.getModel()).getCell(node.getString("vertex_id_to"));
				graph.insertEdge(parent, node.getString("id"), (Object)node.getString("name"), first_node, second_node, node.getString("style"));
			}
			pc_add1 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc_add1");
			pc_im = (mxCell) ((mxGraphModel)graph.getModel()).getCell("pc_im");
			im_control = (mxCell) ((mxGraphModel)graph.getModel()).getCell("im_control");
			im_aluc = (mxCell) ((mxGraphModel)graph.getModel()).getCell("im_aluc");
			control_mux4 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("control_mux4");
			aluc_alu = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu_control_alu");
			add1_mux4 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("add1_mux4");
			alu_andgate = (mxCell) ((mxGraphModel)graph.getModel()).getCell("alu_and_gate");
			andgate_mux3 = (mxCell) ((mxGraphModel)graph.getModel()).getCell("and_gate_mux3");
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
				//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{pc, add1, alu, mux3, mux4, and_gate, instruction_memory, control, alu_control});
				//graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
			}
			public void mouseExited(MouseEvent e)
			{
				System.out.println("Leave");
				//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", new Object[]{pc, add1, mux3, alu, mux4, and_gate, instruction_memory, control, alu_control});
				//graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
			}
			public void mouseMoved(MouseEvent e)
			{
				System.out.println("Moved");
			}
		});
		FrontendConsumer frontend = new FrontendConsumer();
		frontend.addListener(new SnapshotEventHandler(){
			public void receiveSnapshot(String jsonString) throws JSONException
			{
				JSONObject conf = new JSONObject(jsonString);
				JSONObject vertices = conf.getJSONObject("vertices_values");
				Iterator<?> json_keys = vertices.keys();
				ArrayList<Object> enabled_list = new ArrayList<Object>();
				ArrayList<Object> disabled_list = new ArrayList<Object>();
				
				while( json_keys.hasNext() ){
					String json_key = (String)json_keys.next();
					JSONObject node = vertices.getJSONObject(json_key);
					mxCell myCell = (mxCell) ((mxGraphModel)graph.getModel()).getCell(json_key);
					myCell.setValue(node);
				}
				
				JSONObject edges = conf.getJSONObject("enabled_edges");
				json_keys = edges.keys();
				
				while( json_keys.hasNext() ){
					String json_key = (String)json_keys.next();
					int enabled = edges.getInt(json_key);
					mxCell myCell = (mxCell) ((mxGraphModel)graph.getModel()).getCell(json_key);
					if (enabled == 1)
						enabled_list.add(myCell);
					else
						disabled_list.add(myCell);
				}
				//graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, color, new Object[]{myCell, add1, mux3, alu, mux4, and_gate, instruction_memory, control, alu_control});
				//graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, color, new Object[]{pc_add1, add1_mux4, pc_im, im_aluc, im_control, control_mux4, aluc_alu, alu_andgate, andgate_mux3});
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "red", enabled_list.toArray());
				graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, "green", disabled_list.toArray());
			}
		});
		thread(frontend, false);
	}

	public static void main(String[] args) throws JSONException
	{
		thread(new BackendProducer(), false);
	}
	
	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}