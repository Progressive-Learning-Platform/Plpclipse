package plptool.mips;

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
	
	/**
	 * This is the key whose value says whether pipeline is enable or disabled
	 */
	public static String PIPELINE = "pipeline";
	
	/**
	 * This key's value tells which is the next instruction
	 */
	public static String NEXT_INSTRUCTION = "next_instruction";
	
	/**
	 * This key's value tells the current instruction which is being executed
	 */
	public static String CURRENT_INSTRUCTION = "current_instruction";
	
	/**
	 * This key's value tells the current instruction address
	 */
	public static String CURRENT_INSTRUCTION_ADDRESS = "current_instruction_address";
	
	/**
	 * This key's value tells the result after ALU operation
	 */
	public static String ALU_RESULT = "alu_result";
	
	/**
	 * This key's value tells the first input for the ALU
	 */
	public static String ALU_SOURCE_1 = "alu_source_1";
	
	/**
	 * This key's value tells the second input for the ALU
	 */
	public static String ALU_SOURCE_2 = "alu_source_2"; 
	
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
	
	/**
	 * This key's value gives the info regarding current active signals
	 */
	public static String ACTIVE_CONTROL_SIGNAL_1 = "active_control_signal_1";
	
	/**
	 * This key's value gives the address of the data which is being used now
	 */
	public static String DATA_MEMORY_1_ADDRESS = "data_memory_1_address";
	/**
	 * This key's value gives the value of the data in memory which is used now
	 */
	public static String DATA_MEMORY_1_VALUE = "data_memory_1_value";
	
	/**
	 * This key's value is the input to the sign extension unit used in case immediate values
	 */
	public static String SIGN_EXTENSION_INPUT = "sign_extension_input";
	
	/**
	 * This key's value is the output of the sign extension unit used in case of immediate values
	 */
	public static String SIGN_EXTENSION_OUTPUT = "sign_extension_output";
	
	/**
	 * This key's value is the alu operations indicator.
	 */
	public static String ALU_CONTROL = "alu_control";
	
	/**
	 * This key's value will indicate the value of the mux which chooses registers
	 */
	public static String REGISTER_MUX = "reg_mux";
	
	/**
	 * This key's value will indicate which input is used for ALU
	 */
	public static String ALU_MUX = "alu_mux";
	
	/**
	 * This key's value will indicate which memory unit is used read or write
	 */
	public static String MEM_MUX = "mem_mux";
	
	/**
	 * This key's value will give the first input to the Adder which calculates the PC
	 */
	public static String ADD_PC_SOURCE_1 = "add_pc_source_1";
	
	/**
	 * This key's value will give the second input to the Adder which calculates the PC
	 */
	public static String ADD_PC_SOURCE_2 = "add_pc_source_2";
	
	/**
	 * This key's value will give the result of PC after its adder operation
	 */
	public static String ADD_PC_RESULT = "add_pc_result";
	
	/**
	 * This key's value will give the shift left operations input
	 */
	public static String SHIFT_LEFT_PC_INPUT = "shift_left_pc_input";
	
	/**
	 * This key's value will give the shift left operations output
	 */
	public static String SHIFT_LEFT_PC_OUTPUT = "shift_left_pc_output";
	
	/**
	 * This key's value will give the first input of the branch adder
	 */
	public static String ADD_BRANCH_SOURCE_1 = "add_branch_source_1";
	/**
	 * This key's value will give the second input of the branch adder
	 */
	public static String ADD_BRANCH_SOURCE_2 = "add_branch_source_2";
	/**
	 * This key's value will give the result of the branch adder
	 */
	public static String ADD_BRANCH_RESULT = "add_branch_result";
	
	/**
	 * This key's value will give the first mux value of branch
	 */
	public static String BRANCH_MUX_1 = "branch_mux_1";
	/**
	 * This key's value will give the second mux value of branch
	 */
	public static String BRANCH_MUX_2 = "branch_mux_2";
	
	/**
	 * This key's value will give the instruction which is getting executed in id stage
	 */
	public static String ID_INSTRUCTION = "id_instruction";
	
	/**
	 * This key's value will give the instruction address which is getting executed in id-stage
	 */
	public static String ID_INSTRUCTION_ADDRESS = "id_instruction_address";
	/**
	 * This key's value will give the instruction which is getting executed in ex stage
	 */
	public static String EX_INSTRUCTION = "ex_instruction";
	/**
	 * This key's value will give the instruction address which is getting executed in ex stage
	 */
	public static String EX_INSTRUCTION_ADDRESS = "ex_instruction_address";
	/**
	 * This key's value will give the instruction which is getting executed in mem stage
	 */
	public static String MEM_INSTRUCTION = "mem_instruction";
	/**
	 * This key's value will give the instruction address which is getting executed in mem stage
	 */
	public static String MEM_INSTRUCTION_ADDRESS = "mem_instruction_address";
	/**
	 * This key's value will give the instruction address which is getting executed in wb stage
	 */
	public static String WB_INSTRUCTION_ADDRESS = "wb_instruction_address";
	/**
	 * This key's value will give the instruction which is getting executed in wb stage
	 */
	public static String WB_INSTRUCTION = "wb_instruction";
	
	/**
	 * this key's value will give the 2nd data memory address
	 */
	public static String DATA_MEMORY_2_ADDRESS = "data_memory_2_address";
	/**
	 * this key's value will give the 2nd data memory value
	 */
	public static String DATA_MEMORY_2_VALUE = "data_memory_2_value";
	/**
	 * this key's value will give the 3rd data memory address
	 */
	public static String DATA_MEMORY_3_ADDRESS = "data_memory_3_address";
	/**
	 * This key's value will give the 3rd data memory value
	 */
	public static String DATA_MEMORY_3_VALUE = "data_memory_3_value";
	/**
	 * This key's value will give 4th data memory address
	 */
	public static String DATA_MEMORY_4_ADDRESS = "data_memory_4_address";
	/**
	 * This key's value will give 4th data memory value
	 */
	public static String DATA_MEMORY_4_VALUE = "data_memory_4_value";
	/**
	 * This key's value will give 5th data memory address
	 */
	public static String DATA_MEMORY_5_ADDRESS = "data_memory_5_address";
	/**
	 * This key's value will give 5th data memory value
	 */
	public static String DATA_MEMORY_5_VALUE = "data_memory_5_value";
	
	/**
	 * This key's value will give one of the control signal which is active now
	 */
	public static String ACTIVE_CONTROL_SIGNAL_2 = "active_control_signal_2";
	/**
	 * This key's value will give one of the control signal which is active now
	 */
	public static String ACTIVE_CONTROL_SIGNAL_3 = "active_control_signal_3";
	/**
	 * This key's value will give one of the control signal which is active now
	 */
	public static String ACTIVE_CONTROL_SIGNAL_4 = "active_control_signal_4";
	/**
	 * This key's value will give one of the control signal which is active now
	 */
	public static String ACTIVE_CONTROL_SIGNAL_5 = "active_control_signal_5";
	
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for IF and ID Stage
	 */
	public static String IF_ID_INTERMEDIATE = "if_id_intermediate";
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for ID and EX Stage
	 */
	public static String ID_EX_INTERMEDIATE = "id-ex_intermediate";
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for EX and MEM Stage
	 */
	public static String EX_MEM_INTERMEDIATE = "ex_mem_intermediate";
	/**
	 * This key's value will give the content of the intermediate pipeline buffer for MEM and WB Stage
	 */
	public static String MEM_WB_INTRMEDIATE = "mem_wb_intermediate";
	
}