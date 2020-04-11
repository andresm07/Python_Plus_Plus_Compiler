/*
 * Python++ Compiler & IDE v1.0
 * IDEInterpreter.java
 */

package Core.IDE;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Just another small class to call the Python++ interpreter.
 * 
 * @author Andrés Miranda Arias <andres.mirandaarias@gmail.com>
 */
public class IDEInterpreter {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    
    /**
     * Creates a new instance of IDEInterpreter
     * 
     * @param fileName 
     */
    public synchronized void Run(final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PythonPlusPlusAbstractMachine.Interpreter.main(new String[] {fileName});
                } catch (IOException ex) {
                    Logger.getLogger(IDEInterpreter.class.getName()).log(Level.SEVERE, null, ex);
                }
                delegate.actionPerformed(null);
            }
        }).start();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Attributes ">
    private ActionListener delegate;        // Gets triggered when the Interpreter stops.
    // </editor-fold>
    
    
}
