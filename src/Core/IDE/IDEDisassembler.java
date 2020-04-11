/*
 * Python++ Compiler & IDE v1.0
 * IDEDisassembler.java
 */

package Core.IDE;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Just a small class to call the Python++ disassembler.
 * 
 * @author Andrés Miranda Arias <andres.mirandaarias@gmail.com>
 */
public class IDEDisassembler {
    
    // <editor-fold defaultstate="collapsed" desc=" Methods ">
    /**
     * Creates a new instance of IDEDisassembler
     */
    public IDEDisassembler() {
    }
    
    /**
     * Runs the Python++ disassembler static method as a separate thread.
     * @param fileName Path to the PPPAM Object File.
     */
    public void Disassemble(final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PythonPlusPlusAbstractMachine.Disassembler.main(new String[] {fileName});
                } catch (IOException ex) {
                    Logger.getLogger(IDEDisassembler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    // </editor-fold>
}
