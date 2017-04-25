/*
    Copyright 2014 PLP Contributors

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

package plptool;

/**
 * This class represents an error that occurred during a build process of a
 * program (either with an assembler or compiler). The assembler abstract
 * maintains a list of this class' objects so a user interface can relate 
 * the errors back to the source files.
 *
 * @author Wira
 */
public class PLPBuildError {
	
	public static final int INVALID_LABEL_ERROR = 1;
	public static final int DUPLICATE_LABEL_ERROR = 1;
	public static final int INVALID_TARGET_ERROR = 2;
	
	public static final int INVALID_TOKEN_ERROR = 2;
	public static final int INVALID_INSTRUCTION_ERROR = 1;
	public static final int INVALID_LABEL_DECLARATION_ERROR = 2;
	
	public static final int INVALID_NUMBER_OF_TOKENS_ERROR = 3;
	public static final int MISSING_TOKENS_ERROR = 1;
	public static final int EXTRA_TOKENS_ERROR = 2;
	public static final int NOT_MATCHING_ERROR = 3;
	
	public static final int INVALID_OPERAND_ERROR = 4;
	public static final int NOT_REGISTER_ERROR = 1;
	public static final int NOT_NUMBER_ERROR = 2;
	public static final int NOT_STRING_ERROR = 3;
	public static final int INVALID_ADDRESS = 4;
	
	
	public static final String ErrorMessagingSystemKey = "ERRORMESSAGESYSTEM";
	public static final String DescriptionKey = "DESCRIPTION";
	public static final String LinksKey = "LINKS";
	public static final String ExamplesKey = "EXAMPLES";
	public static final String BeforeCorrectionKey = "BEFORE_CORRECTION";
	public static final String AfterCorrectionKey = "AFTER_CORRECTION";
	public static final String ErrorSystemTypeKey = "ERRORSYSTEMTYPE";
	
	public static final String ERROR_INVALID_LABEL = "INVALID_LABEL_ERROR";
	public static final String ERROR_DUPLICATE_LABEL = "DUPLICATE_LABEL_ERROR";
	public static final String ERROR_INVALID_TARGET = "INVALID_TARGET_ERROR";
	public static final String ERROR_INVALID_TOKEN = "INVALID_TOKEN_ERROR";
	public static final String ERROR_INVALID_INSTRUCTION = "INVALID_INSTRUCTION_ERROR";
	public static final String ERROR_INVALID_LABEL_DECLARATION = "INVALID_LABEL_DECLARATION_ERROR";
	public static final String ERROR_INVALID_NUMBER_OF_TOKENS = "INVALID_NUMBER_OF_TOKENS_ERROR";
	public static final String ERROR_EXTRA_TOKENS = "EXTRA_TOKENS_ERROR";
	public static final String ERROR_MISSING_TOKENS = "MISSING_TOKENS_ERROR";
	public static final String ERROR_NOT_MATCHING = "NOT_MATCHING_ERROR";
	public static final String ERROR_INVALID_OPERAND = "INVALID_OPERAND_ERROR";
	public static final String ERROR_NOT_REGISTER = "NOT_REGISTER_ERROR";
	public static final String ERROR_NOT_NUMBER = "NOT_NUMBER_ERROR";
	public static final String ERROR_NOT_STRING = "NOT_STRING_ERROR";
	public static final String ERROR_INVALID_ADDRESS = "INVALID_ADDRESS";

	
	
	
	
    private int sourceFileIndex;
    private int sourceLineNumber;
    private String errorMessage;

    /**
     * The constructor for the class takes the index of the source file, the
     * line number of where the offending expression is in the file, and the
     * error message emitted by the build tool
     *
     * @param index Index of the source file
     * @param number Line number where the source of the error is
     * @param message Error message emitted by the build tool
     */
    public PLPBuildError(int index, int number, String message) {
        this.sourceFileIndex = index;
        this.sourceLineNumber = number;
        this.errorMessage = message;
    }

    /**
     * Get the index of the source file where the error originated
     *
     * @return Index of the source file
     */
    public int getSourceIndex() {
        return sourceFileIndex;
    }

    /**
     * Get the line number in the source file where the error originated
     *
     * @return Line number of the error originator
     */
    public int getLineNumber() {
        return sourceLineNumber;
    }

    /**
     * Get the error message emitted by the build tool
     *
     * @return Error message emitted by the build tool
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
