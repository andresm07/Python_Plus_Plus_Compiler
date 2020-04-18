/*
 * Python++ Compiler & IDE v1.0
 * IDECompiler.java
 */

package PythonPlusPlus;

import PythonPlusPlus.CodeGenerator.Frame;
import java.awt.event.ActionListener;
import PythonPlusPlus.SyntacticAnalyzer.SourceFile;
import PythonPlusPlus.SyntacticAnalyzer.Scanner;
import PythonPlusPlus.AbstractSyntaxTrees.Program;
import PythonPlusPlus.SyntacticAnalyzer.Parser;
import PythonPlusPlus.ContextualAnalyzer.Checker;
import PythonPlusPlus.CodeGenerator.Encoder;

/**
 * This is merely a re-implementation of the PythonPlusPlus.Compiler class. We need
 * to get to the ASTs in order to draw them in the IDE without modifying the 
 * original Python++ code
 * 
 * @author Andrés Miranda Arias <andres.mirandaarias@gmail.com>
 */
public class IDECompiler {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    
    /**
     * Creates a new instance of IDECompiler
     */
    public IDECompiler() {
        
    }
    
    /**
     * Particularly the same compileProgram method from the PythonPlusPlus.Compiler 
     * class
     * 
     * @param sourceName        Path to the source file
     * @return                  True if compilation was successful
     */
    public boolean compileProgram(String sourceName) {
        System.out.println("********** " +
                           "Python++ Compiler (IDE-Python++ 1.0)" +
                           " **********");
        
        System.out.println("Syntactic Analysis ...");
        SourceFile source = new SourceFile(sourceName);
        Scanner scanner = new Scanner(source);
        this.report = new IDEReporter();
        Parser parser = new Parser(scanner, this.report);
        boolean success = false;
        
        this.rootAST = parser.parseProgram();
        
        if(this.report.numErrors == 0) {
            System.out.println("Contextual Analysis ...");
            Checker checker = new Checker(this.report);
            checker.check(this.rootAST);
            if(this.report.numErrors == 0) {
                System.out.println("Code Generation ...");
                Encoder encoder = new Encoder(this.report);
                encoder.encodeRun(this.rootAST, false);
                
                if(this.report.numErrors == 0) {
                    encoder.saveObjectProgram(sourceName.replace(".ppp", ".pppam"));
                    success = true;
                }
            }
        }
        
        if(success) {
            System.out.println("Compilation was successful");
        } else {
            System.out.println("Compilation was unsuccessful");
        }
        
        return (success);
    }
    
    /**
     * Returns the root AST
     * 
     * @return Program AST (root)
     */
    public int getErrorPosition() {
        return (this.report.getFirstErrorPosition());
    }
    
    /**
     * Returns the root AST
     * 
     * @return Program AST (root)
     */
    public Program getAST() {
        return (this.rootAST);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    
    private Program rootAST;            // The root AST
    private IDEReporter report;         // Our ErrorReporter class
    
    // </editor-fold>
    
}
