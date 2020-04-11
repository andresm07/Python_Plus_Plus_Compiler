/*
 * @(#)Instruction.java                        2.1 2003/10/07
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

package PythonPlusPlusAbstractMachine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class Instruction {
    
    public Instruction() {
        this.op = 0;
        this.r = 0;
        this.n = 0;
        this.d = 0;
    }
    
    // Java has no type synonyms, so the following representations are
    // assumed:
    //
    //  type
    //    OpCode = 0..15;  {4 bits unsigned}
    //    Length = 0..255;  {8 bits unsigned}
    //    Operand = -32767..+32767;  {16 bits signed}

    // Represents PPPAM instructions.
    public int op; // OpCode
    public int r;  // RegisterNumber
    public int n;  // Length
    public int d;  // Operand
    
    public void write(DataOutputStream output) throws IOException {
        output.writeInt(this.op);
        output.writeInt(this.r);
        output.writeInt(this.n);
        output.writeInt(this.d);
    }
    
    public static Instruction read(DataInputStream input) throws IOException {
        Instruction inst = new Instruction();
        try {
            inst.op = input.readInt();
            inst.r = input.readInt();
            inst.n = input.readInt();
            inst.d = input.readInt();
            return inst;
        } catch (EOFException s) {
            return null;
        }
    }
}
