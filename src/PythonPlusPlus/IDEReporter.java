/*
 * Python++ Compiler & IDE v1.0
 * IDEReporter.java
 */

package PythonPlusPlus;

import PythonPlusPlus.SyntacticAnalyzer.SourcePosition;
import PythonPlusPlus.*;

/**
 * Extends the PythonPlusPlus.ErrorReporter class. used to get the source lines
 * where errors are found
 * 
 * @author Andrés Miranda Arias <andres.mirandaarias@gmail.com>
 */

public class IDEReporter extends ErrorReporter{
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    
    /**
     * Creates a new instance of IDEReporter
     */
    public IDEReporter() {
        super();
    }
    
    /**
     * Overrides the reportError method, adding the line number to an array
     * 
     * @param message           Error message
     * @param tokenName         Name of the token
     * @param pos               Position in the source file
     */
    @Override
    public void reportError(String message, String tokenName, SourcePosition pos) {
        this.errorPositions[super.numErrors] = pos.start;
        super.reportError(message, tokenName, pos);
    }
    
    /**
     * Returns the line where the first error is
     * 
     * @return Line number
     */
    public int getFirstErrorPosition() {
        return (this.errorPositions[0]);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    
    private int errorPositions[] = new int[10];     // Array of error positions;
    
    // </editor-fold>
    
}
