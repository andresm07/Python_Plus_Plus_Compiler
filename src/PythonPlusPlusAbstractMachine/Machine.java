/*
 * @(#)Machine.java                        2.1 2003/10/07
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

/**
 *
 * @author Andrés Miranda Arias
 */
public final class Machine {
    
    public final static int maxRoutineLevel = 7;
    
// WORDS AND ADDRESSES
    
/**
 * Java has no type synonyms, so the following representations are assumed:
 *      
 * type
 *      Word = -32767 ... +32767 {16 bits signed}
 *      DoubleWord = -2147483648 ... +2147483647; {32 bits signed}
 *      CodeAddress = 0 ... +32767; {15 bits unsigned}
 *      DataAddress = 0 ... +32767; {15 bits unsigned}
 */
    
// INSTRUCTIONS
    
    // Operation Codes
    public final static int LOADop = 0;
    public final static int LOADAop = 1;
    public final static int LOADIop = 2;
    public final static int LOADLop = 3;
    public final static int STOREop = 4;
    public final static int STOREIop = 5;
    public final static int CALLop = 6;
    public final static int CALLIop = 7;
    public final static int RETURNop = 8;
    public final static int PUSHop = 10;
    public final static int POPop = 11;
    public final static int JUMPop = 12;
    public final static int JUMPIop = 13;
    public final static int JUMPIFop = 14;
    public final static int HALTop = 15;
    
// CODE STORE
    
    public static Instruction[] code = new Instruction[1024];
    
// CODE STORE REGISTERS
    
    public final static int CB = 0;
    public final static int PB = 1024; // = Upper bound of code array + 1
    public final static int PT = 1052; // = PB + 28
    
// REGISTER NUMBERS
    
    public final static int CBr = 0;
    public final static int CTr = 1;
    public final static int PBr = 2;
    public final static int PTr = 3;
    public final static int SBr = 4;
    public final static int STr = 5;
    public final static int HBr = 6;
    public final static int HTr = 7;
    public final static int LBr = 8;
    public final static int L1r = LBr + 1;
    public final static int L2r = LBr + 2;
    public final static int L3r = LBr + 3;
    public final static int L4r = LBr + 4;
    public final static int L5r = LBr + 5;
    public final static int L6r = LBr + 6;
    public final static int CPr = 15;
    
// DATA REPRESENTATION
    
    public final static int booleanSize = 1;
    public final static int characterSize = 1;
    public final static int integerSize = 1;
    public final static int addressSize = 1;
    public final static int closureSize = 2 * addressSize;
    public final static int linkDataSize = 3 * addressSize;
    public final static int falseRep = 0;
    public final static int trueRep = 1;
    public final static int maxintRep = 32767;
    
// ADDRESSES OF PRIMITIVE ROUTINES
    
    public final static int idDisplacement = 1;
    public final static int notDisplacement = 2;
    public final static int andDisplacement = 3;
    public final static int orDisplacement = 4;
    public final static int succDisplacement = 5;
    public final static int predDisplacement = 6;
    public final static int negDisplacement = 7;
    public final static int addDisplacement = 8;
    public final static int subDisplacement = 9;
    public final static int multDisplacement = 10;
    public final static int divDisplacement = 11;
    public final static int modDisplacement = 12;
    public final static int ltDisplacement = 13;
    public final static int leDisplacement = 14;
    public final static int geDisplacement = 15;
    public final static int gtDisplacement = 16;
    public final static int eqDisplacement = 17;
    public final static int neDisplacement = 18;
    public final static int eolDisplacement = 19;
    public final static int eofDisplacement = 20;
    public final static int getDisplacement = 21;
    public final static int putDisplacement = 22;
    public final static int geteolDisplacement = 23;
    public final static int puteolDisplacement = 24;
    public final static int getintDisplacement = 25;
    public final static int putintDisplacement = 26;
    public final static int newDisplacement = 27;
    public final static int disposeDisplacement = 28;
}
