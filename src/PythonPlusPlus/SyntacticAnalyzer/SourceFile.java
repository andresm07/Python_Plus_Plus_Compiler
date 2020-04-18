/*
 * @(#)SourceFile.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package PythonPlusPlus.SyntacticAnalyzer;

import java.io.File;
import java.io.FileInputStream;

public class SourceFile {
    
    public static final char EOL = '\n';
    public static final char EOT = '\u0000';
    
    File sourceFile;
    FileInputStream source;
    int currentLine;
    
    public SourceFile(String fileName) {
        try {
            this.sourceFile = new File(fileName);
            this.source = new FileInputStream(this.sourceFile);
            this.currentLine = 1;
        } catch (java.io.IOException s) {
            this.sourceFile = null;
            this.source = null;
            this.currentLine = 0;
        }
    }
    
    public char getSource() {
        try {
            int c = this.source.read();
            if(c == -1) {
                c = EOT;
            } else if (c == EOL) {
                this.currentLine++;
            }
            return (char) c;
        } catch (java.io.IOException s) {
            return EOT;
        }
    }
    
    public int getCurrentLine() {
        return this.currentLine;
    }
    
}
