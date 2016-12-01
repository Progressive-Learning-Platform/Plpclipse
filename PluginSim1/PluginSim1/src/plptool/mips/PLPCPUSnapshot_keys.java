package plptool.mips;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import plptool.Config;

/**
 * This class will have all the key's of the CPU_SNAPSHOT which will be passed to the Visualizer code
 * This is the single place where you can modify the key names if need be. Everywhere these variables are used
 * to use the key values
 */
public class PLPCPUSnapshot_keys
{
	/**
	 * This is the key for program counter
	 */
	public static String PC = "pc";
	public static String PC_ADDRESS = "address";
	
	
	/**
	 * this will give the values of the instructions which is in CPU
	 */
	public static String INSTRUCTION_MEMORY = "instruction_memory";
	public static String INST_MEM_IF = "if_instruction";
	public static String INST_MEM_IF_ADDR = "if_instruction_address";
	public static String INST_MEM_ID = "id_instruction";
	public static String INST_MEM_ID_ADDR = "id_instruction_address";
	public static String INST_MEM_EX = "ex_instruction";
	public static String INST_MEM_EX_ADDR = "ex_instruction_address";
	public static String INST_MEM_MEM = "mem_instruction";
	public static String INST_MEM_MEM_ADDR = "mem_instruction_address";
	public static String INST_MEM_WB = "wb_instruction";
	public static String INST_MEM_WB_ADDR = "wb_instruction_address";
	public static String INST_MEM_VALUE = "instruction";
	public static String INST_MEM_ADDR = "instruction value";
	
	public static String SHIFT_LEFT_PC = "shift1";
	public static String SHIFT_LEFT_PC_INPUT = "input";
	public static String SHIFT_LEFT_PC_OUTPUT = "output";
	
	public static String CONTROL = "control";
	public static String CONTROL_REGDST = "regdst";
	public static String CONTROL_JUMP = "jump";
	public static String CONTROL_MEMREAD = "memread";
	public static String CONTROL_MEMTOREG = "memtoreg";
	public static String CONTROL_ALUOP = "aluop";
	public static String CONTROL_MEMWRITE = "memwrite";
	public static String CONTROL_ALUSRC = "alusrc";
	public static String CONTROL_REGWRITE = "regwrite";
	public static String CONTROL_BRANCH = "branch";
	public static String CONTROL_SIGNALS = "control_signals";
	
	/**
	 * This key's value will indicate the value of the mux which chooses registers
	 */
	public static String REGISTER_MUX = "reg_mux";
	public static String REGISTER_MUX_VALUE = "value";
		
	public static String SIGN_EXTEND = "sign_extend";
	public static String SIGN_EXTEND_INPUT = "input";
	public static String SIGN_EXTEND_OUTPUT = "output";
	
	public static String REGISTERS = "registers";
	
	public static String SHIFT_BRANCH = "shift2";
	public static String SHIFT_BRANCH_INPUT = "input";
	public static String SHIFT_BRANCH_OUTPUT = "output";
	
	public static String ADD_PC = "add1";
	public static String ADD_PC_INPUT1 = "old_pc";
	public static String ADD_PC_INPUT2 = "4";
	public static String ADD_PC_OUTPUT = "new_pc";
	
	public static String ADD_BRANCH = "add2";
	public static String ADD_BRANCH_INPUT1 = "pc_input";
	public static String ADD_BRANCH_INPUT2 = "shift_left_input";
	public static String ADD_BRANCH_OUTPUT = "output";
	
	public static String ALU = "alu";
	public static String ALU_INPUT1 = "register_input";
	public static String ALU_INPUT2 = "register_or_immediate_input";
	public static String ALU_RESULT = "alu_result";
	public static String ALU_OP_TYPE = "alu_control_input";
	public static String ALU_ZERO = "zero";
	
	public static String MUX_BRANCH_1 = "mux_branch_1";
	public static String MUX_BRANCH_1_VALUE = "value";
	
	public static String MUX_BRANCH_2 = "mux_branch_2";
	public static String MUX_BRANCH_2_VALUE = "value";
	
	public static String AND_GATE = "and_gate";
	public static String AND_GATE_INPUT1 = "input1";
	public static String AND_GATE_INPUT2 = "input2";
	public static String AND_GATE_RESULT = "output";
		
	public static String DATA_MEMORY = "data_memory";
	public static String ADDRESS_1 = "address_1";
	public static String ADDRESS_1_VALUE = "address_1_VALUE";
	public static String ADDRESS_2 = "address_2";
	public static String ADDRESS_2_VALUE = "address_2_VALUE";
	public static String ADDRESS_3 = "address_3";
	public static String ADDRESS_3_VALUE = "address_3_VALUE";
	public static String ADDRESS_4 = "address_4";
	public static String ADDRESS_4_VALUE = "address_4_VALUE";
	public static String ADDRESS_5 = "address_5";
	public static String ADDRESS_5_VALUE = "address_5_VALUE";
	public static String DATA_MEM_ADDRESS = "address";
	public static String DATA_MEM_WRITE = "write data";
	public static String DATA_MEM_READ = "read data";
	
	/**
	 * This key's value is the alu operations indicator.
	 */
	public static String ALU_CONTROL = "alu_control";
	public static String ALU_CONTROL_INPUT = "input";
	public static String ALU_CONTROL_OUTPUT = "output";
	
	/**
	 * This key's value will indicate which input is used for ALU
	 */
	public static String ALU_MUX = "alu_mux";
	public static String ALU_MUX_VALUE = "value";
	
	/**
	 * This key's value will indicate which memory unit is used read or write
	 */
	public static String MEM_MUX = "mem_mux";
	public static String MEM_MUX_VALUE = "value";
	
	/**
	 * This is the key whose value says whether pipeline is enable or disabled
	 */
	public static String PIPELINE = "pipeline";
	
	
	
	/**
	 * This key's value gives the value stored in register 0
	 */
	public static String REGISTER_0 = "$0";
	/**
	 * This key's value gives the value stored in register 1
	 */
	public static String REGISTER_1 = "$1";
	/**
	 * This key's value gives the value stored in register 2
	 */
	public static String REGISTER_2 = "$2";
	/**
	 * This key's value gives the value stored in register 3
	 */
	public static String REGISTER_3 = "$3";
	/**
	 * This key's value gives the value stored in register 4
	 */
	public static String REGISTER_4 = "$4";
	/**
	 * This key's value gives the value stored in register 5
	 */
	public static String REGISTER_5 = "$5";
	/**
	 * This key's value gives the value stored in register 6
	 */
	public static String REGISTER_6 = "$6";
	/**
	 * This key's value gives the value stored in register 7
	 */
	public static String REGISTER_7 = "$7";
	/**
	 * This key's value gives the value stored in register 8
	 */
	public static String REGISTER_8 = "$8";
	/**
	 * This key's value gives the value stored in register 9
	 */
	public static String REGISTER_9 = "$9";
	/**
	 * This key's value gives the value stored in register 10
	 */
	public static String REGISTER_10 = "$10";
	/**
	 * This key's value gives the value stored in register 11
	 */
	public static String REGISTER_11 = "$11";
	/**
	 * This key's value gives the value stored in register 12
	 */
	public static String REGISTER_12 = "$12";
	/**
	 * This key's value gives the value stored in register 13
	 */
	public static String REGISTER_13 = "$13";
	/**
	 * This key's value gives the value stored in register 14
	 */
	public static String REGISTER_14 = "$14";
	/**
	 * This key's value gives the value stored in register 15
	 */
	public static String REGISTER_15 = "$15";
	/**
	 * This key's value gives the value stored in register 16
	 */
	public static String REGISTER_16 = "$16";
	/**
	 * This key's value gives the value stored in register 17
	 */
	public static String REGISTER_17 = "$17";
	/**
	 * This key's value gives the value stored in register 18
	 */
	public static String REGISTER_18 = "$18";
	/**
	 * This key's value gives the value stored in register 19
	 */
	public static String REGISTER_19 = "$19";
	/**
	 * This key's value gives the value stored in register 20
	 */
	public static String REGISTER_20 = "$20";
	/**
	 * This key's value gives the value stored in register 21
	 */
	public static String REGISTER_21 = "$21";
	/**
	 * This key's value gives the value stored in register 22
	 */
	public static String REGISTER_22 = "$22";
	/**
	 * This key's value gives the value stored in register 23
	 */
	public static String REGISTER_23 = "$23";
	/**
	 * This key's value gives the value stored in register 24
	 */
	public static String REGISTER_24 = "$24";
	/**
	 * This key's value gives the value stored in register 25
	 */
	public static String REGISTER_25 = "$25";
	/**
	 * This key's value gives the value stored in register 26
	 */
	public static String REGISTER_26 = "$26";
	/**
	 * This key's value gives the value stored in register 27
	 */
	public static String REGISTER_27 = "$27";
	/**
	 * This key's value gives the value stored in register 28
	 */
	public static String REGISTER_28 = "$28";
	/**
	 * This key's value gives the value stored in register 29
	 */
	public static String REGISTER_29 = "$29";
	/**
	 * This key's value gives the value stored in register 30
	 */
	public static String REGISTER_30 = "$30";
	/**
	 * This key's value gives the value stored in register 31
	 */
	public static String REGISTER_31 = "$31";
	
	public static String REGISTER1 = "read register 1";
	public static String REGISTER2 = "read register 2";
	public static String REGISTER_WRITE = "write register";
	public static String REGISTER1_READ = "read data 1";
	public static String REGISTER2_READ = "read data 2";
	public static String REGISTER_WRITE_DATA = "write data";
	
	
	
	
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for IF and ID Stage
	 */
	public static String IF_ID_INTERMEDIATE = "if_id_buffer";
	public static String IF_ID_PC_ADD = "if_id_pc_add";
	public static String IF_ID_INSTRUCTION = "if_id_instruction";
	public static String IF_ID_INSTRUCTION_ADDR = "if_id_instruction_addr";
	
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for ID and EX Stage
	 */
	public static String ID_EX_INTERMEDIATE = "id_ex_buffer";
	public static String ID_EX_INSTRUCTION = "id_ex_instruction";
	public static String ID_EX_INSTRUCTION_ADDR = "id_ex_instruction_addr";
	public static String ID_EX_INSTRUCTION_BUBBLE = "id_ex_bubble";
	public static String ID_EX_REGSOURCE = "register_source";
	public static String ID_EX_REGTRANS = "register_trans";
	public static String ID_EX_IMMEDIATE = "immediate_value";
	public static String ID_EX_REG_DEST = "register_destination";
	public static String ID_EX_FWD_CTL_MEMTOREG = "ctl_memtoreg";
	public static String ID_EX_FWD_CTL_REGWRITE = "ctl_regwrite";
	public static String ID_EX_FWD_CTL_MEMWRITE = "ctl_memwrite";
	public static String ID_EX_FWD_CTL_MEMREAD = "ctl_memread";
	public static String ID_EX_FWD_CTL_JAL = "ctl_jal";
	public static String ID_EX_CTL_REGDST = "ctl_regdst";
	public static String ID_EX_CTL_JUMP = "ctl_jump";
	public static String ID_EX_CTL_BRANCH = "ctl_branch";
	public static String ID_EX_CTL_ALUSRC = "ctl_alusrc";
	public static String ID_EX_CTL_ALUOP = "ctl_aluop";
	public static String ID_EX_FWD_CTL_LINKADDR = "ctl_fwd_linkaddr";
	
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for EX and MEM Stage
	 */
	public static String EX_MEM_INTERMEDIATE = "ex_mem_buffer";
	
	public static String EX_MEM_INSTRUCTION = "ex_mem_instruction";
	public static String EX_MEM_INSTRUCTION_ADDR = "ex_mem_instruction_addr";
	public static String EX_MEM_INSTRUCTION_BUBBLE = "ex_mem_instruction_bubble";
	public static String EX_MEM_ALU_RESULT = "ex_mem_alu_result";
	public static String EX_RT_FORWARDED = "ex_rt_forwarded";
	public static String EX_RS_FORWARDED = "ex_rs_forwarded";
	public static String EX_DATA_X = "ex_data_x";
	public static String EX_DATA_Y = "ex_data_y";
	public static String EX_DATA_RS = "ex_data_rs";
	public static String EX_DATA_RT = "ex_data_rt";
	public static String EX_MEM_CTL_MEMTOREG = "ex_mem_ctl_memtoreg";
	public static String EX_MEM_CTL_REGWRITE = "ex_mem_ctl_regwrite";
	public static String EX_MEM_CTL_DEST_REG_ADDR = "ex_mem_ctl_dest_reg_addr";
	public static String EX_MEM_CTL_MEMWRITE = "ex_mem_ctl_memwrite";
	public static String EX_MEM_CTL_MEMREAD = "ex_mem_ctl_memread";
	public static String EX_MEM_CTL_LINKADDRESS = "ex_mem_ctl_linkaddress";
	public static String EX_MEM_CTL_JAL = "ex_mem_ctl_jal";	
	public static String EX_MEM_MEMWRITEDATA = "ex_mem_memwritedata";
	public static String EX_IF_STALL_SET = "ex_if_stall_set";
	
	
	
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for MEM and WB Stage
	 */
	public static String MEM_WB_INTRMEDIATE = "mem_wb_buffer";
	public static String MEM_WB_INSTRUCTION = "mem_wb_instruction";
	public static String MEM_WB_INSTRUCTION_ADDR = "mem_wb_instruction_addr";
	public static String MEM_WB_BUBBLE = "mem_wb_bubble";
	public static String MEM_CTRL_WRITE = "control_memory_write";
	public static String MEM_CTRL_READ = "control_memory_read";
	public static String MEM_DATA_LOAD = "data_loaded_mem_stage";
	public static String MEM_DATA_STORE = "data_written_mem_stage";
	public static String FWD_MEM_TO_MEM = "forwarding_mem_mem";
	public static String MEM_WB_CTRL_MEMTOREG = "ctrl_memtoreg";
	public static String MEM_WB_CTRL_REGWRITE = "ctrl_regwrite";
	public static String WB_DST_REG_ADDR = "destination_reg_address";
	public static String MEM_WB_CTRL_LINKADDR = "link_address";
	public static String MEM_WB_ALU_RESULT = "alu_result";
	public static String MEM_WB_CTRL_JAL = "ctrl_jal";
	
	
	public static String PC_ADD_EDGE = "pc_add1";
	public static String PC_IMM_EDGE = "pc_im";
	public static String ADD_PC_2_ADD_BRANCH_EDGE = "add1_add2";
	public static String SHIFT_PC_EDGE = "im_shift1";
	public static String CONTROL_EDGE = "im_control";
	public static String IM_MUX1_LOWER_EDGE = "im_mux1_lower";
	public static String IM_MUX1_UPPER_EDGE = "im_mux1_upper";
	public static String IM_SIGN_EDGE = "im_sign";
	public static String IM_ALUC_EDGE = "im_aluc";
	public static String CONTROL_MUX1_EDGE = "control_mux1";
	public static String CONTROL_REGISTERS_EDGE = "control_registers";
	public static String CONTROL_MUX4_EDGE = "control_mux4";
	public static String CONTROL_ANDGATE_EDGE = "control_andgate";
	public static String CONTROL_MUX5_EDGE = "control_mux5";
	public static String CONTROL_DATAMEMORY_LEFT_EDGE = "control_datamemory_left";
	public static String CONTROL_DATAMEMORY_RIGHT_EDGE = "control_datamemory_right";
	public static String CONTROL_MUX2_EDGE = "control_mux2";
	public static String CONTROL_ALU_CONTROL_EDGE = "control_alu_control";
	public static String ALU_CONTROL_ALU_EDGE = "alu_control_alu";
	public static String MUX1_REGISTERS_EDGE = "mux1_registers";
	public static String REGISTERS_ALU_EDGE = "registers_alu";
	public static String REGISTERS_MUX2_EDGE = "registers_mux2_edge";
	public static String REGISTERS_DATA_MEMORY_EDGE = "registers_data_memory";
	public static String MUX3_MUX4_EDGE = "mux3_mux4";
	public static String SIGN_EXTEND_SHIFT2_EDGE = "sign_extend_shift2";
	public static String SIGN_EXTEND_MUX2_EDGE = "sign_extend_mux2";
	public static String MUX2_ALU_EDGE = "mux2_alu";
	public static String ADD1_MUX3_EDGE = "add1_mux3";
	public static String ADD1_MUX4_EDGE = "add1_mux4";
	public static String SHIFT1_MUX4_EDGE = "shift1_mux4";
	public static String SHIFT2_ADD2_EDGE = "shift2_add2";
	public static String ADD2_MUX3_EDGE = "add2_mux3";
	public static String ALU_AND_GATE_EDGE = "alu_and_gate";
	public static String ALU_DATA_MEMORY_EDGE = "alu_data_memory";
	public static String ALU_MUX5_EDGE = "alu_mux5";
	public static String DATA_MEMORY_MUX5_EDGE = "data_memory_mux5";
	public static String AND_GATE_MUX3_EDGE = "and_gate_mux3";
	public static String MUX5_REGISTERS_EDGE = "mux5_registers";
	public static String MUX4_PC_EDGE = "mux4_pc";
	
	public static String ADD1_IF_ID_BUFFER_EDGE = "add1_if_id_buffer";
	public static String IM_IF_ID_BUFFER_EDGE = "im_if_id_buffer";
	public static String EX_MEM_BUFFER_DATA_MEMORY_ADDRESS_EDGE = "ex_mem_buffer_data_left";
	public static String EX_MEM_BUFFER_DATA_MEMORY_WRITE_DATA_EDGE = "ex_mem_buffer_data_right";
	public static String DATA_MEMORY_MEM_WB_BUFFER_READ_DATA_EDGE = "data_memory_mem_wb_buffer";	
	
	/* Edges specific in ID Stage */
	public static String IF_ID_SHIFT_PC_EDGE = "if_id_buffer_shift1";
	//public static String SHIFT1_MUX4_EDGE = "shift1_mux4";
	public static String IF_ID_CONTROL_EDGE = "if_id_buffer_control";
	public static String CONTROL_ID_EX_EDGE_1 = "control_id_ex_buffer";
	public static String CONTROL_ID_EX_EDGE_2 = "control_id_ex_buffer2";
	public static String CONTROL_ID_EX_EDGE_3 = "control_id_ex_buffer3";
	public static String IF_ID_ID_EX_EDGE = "if_id_buffer_id_ex_buffer";
	public static String IF_ID_MUX1_UPPER_EDGE = "im_mux1_upper";
	public static String IF_ID_MUX1_LOWER_EDGE = "im_mux1_lower";
	//public static String MUX1_REGISTERS_EDGE = "mux1_registers";
	public static String REGISTERS_ID_EX_EDGE_1 = "registers_id_ex_buffer_1";
	public static String REGISTERS_ID_EX_EDGE_2 = "registers_id_ex_buffer_2";
	public static String ID_EX_REGISTERS_EDGE = "id_ex_buffer_registers";
	public static String ID_EX_MUX1_EDGE = "id_ex_buffer_mux1";
	public static String IF_ID_SIGN_EDGE = "im_sign";
	public static String SIGN_EXTEND_ID_EX_EDGE = "sign_extend_id_ex_buffer";
	
	
	public static String EDGE_ENABLE = "enabled_edges";
	
	public static void initializeKeysFromFile()
	{
		BufferedReader reader;
		String line = null;
		String jsonString = "";
		try {
			if(Config.simFunctional)
				reader = new BufferedReader(new FileReader (Config.pipeLinedBlueprint));
			else
				reader = new BufferedReader(new FileReader (Config.nonPipelinedBlueprint));
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
		Iterator<?> json_keys = vertices.keys();
		
		while( json_keys.hasNext() )
		{
			String json_key = (String)json_keys.next();
			JSONObject node = vertices.getJSONObject(json_key);
			String id = node.getString("id");
			
			switch(json_key)
			{
			case "pc":
			{
				PLPCPUSnapshot_keys.PC = id;
				break;
			}
			case "add1":
			{
				PLPCPUSnapshot_keys.ADD_PC = id;
				break;
			}
			case "instruction_memory":
			{
				PLPCPUSnapshot_keys.INSTRUCTION_MEMORY = id;
				break;
			}
			case "shift1":
			{
				PLPCPUSnapshot_keys.SHIFT_LEFT_PC = id;
				break;
			}
			case "control":
			{
				PLPCPUSnapshot_keys.CONTROL = id;
				break;
			}
			case "mux1":
			{
				PLPCPUSnapshot_keys.REGISTER_MUX = id;
				break;
			}
			case "sign_extend":
			{
				PLPCPUSnapshot_keys.SIGN_EXTEND = id;
				break;
			}
			case "registers":
			{
				PLPCPUSnapshot_keys.REGISTERS = id;
				break;
			}
			case "shift2":
			{
				PLPCPUSnapshot_keys.SHIFT_BRANCH = id;
				break;
			}
			case "mux2":
			{
				PLPCPUSnapshot_keys.ALU_MUX = id;
				break;
			}
			case "alu_control":
			{
				PLPCPUSnapshot_keys.ALU_CONTROL = id;
				break;
			}
			case "add2":
			{
				PLPCPUSnapshot_keys.ADD_BRANCH = id;
				break;
			}
			case "alu":
			{
				PLPCPUSnapshot_keys.ALU = id;
				break;
			}
			case "mux3":
			{
				PLPCPUSnapshot_keys.MEM_MUX = id;
				break;
			}
			case "mux4":
			{
				PLPCPUSnapshot_keys.MUX_BRANCH_1 = id;
				break;
			}
			case "mux5":
			{
				PLPCPUSnapshot_keys.MUX_BRANCH_2 = id;
				break;
			}
			case "and_gate":
			{
				PLPCPUSnapshot_keys.AND_GATE = id;
				break;
			}
			case "data_memory":
			{
				PLPCPUSnapshot_keys.DATA_MEMORY = id;
				break;
			}
			case "if_id_buffer":
			{
				PLPCPUSnapshot_keys.IF_ID_INTERMEDIATE = id;
			}
			case "id_ex_buffer":
			{
				PLPCPUSnapshot_keys.ID_EX_INTERMEDIATE = id;
			}
			/*case "ex_mem_buffer":
			{
				PLPCPUSnapshot_keys.EX_MEM_INTERMEDIATE = id;
			}
			case "mem_wb_buffer":
			{
				PLPCPUSnapshot_keys.MEM_WB_INTRMEDIATE = id;
			}*/
			}
			System.out.println(node.getString("name"));
		}
		
		
	}
	
}