/*
 * @(#)Token.java                        2.1 2003/10/07
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

public final class Token extends Object{
    
    protected int kind;
    protected String spelling;
    protected SourcePosition position;
    
    public Token(int kind, String spelling, SourcePosition position) {
        if(kind == Token.IDENTIFIER) {
            int currentKind = firstReservedWord;
            boolean searching = true;
            
            while(searching) {
                int comparison = tokenTable[currentKind].compareTo(spelling);
                if(comparison == 0) {
                    this.kind = currentKind;
                    searching = false;
                } else if(comparison > 0 || currentKind == lasReservedWord) {
                    this.kind = Token.IDENTIFIER;
                    searching = false;
                } else {
                    currentKind++;
                }
            }
        } else {
            this.kind = kind;
        }
        
        this.spelling = spelling;
        this.position = position;
    }
    
    public static String spell(int kind) {
        return tokenTable[kind];
    }
    
    @Override
    public String toString() {
        return "Kind = " + this.kind + ", Spelling = " + this.spelling + 
                ", Position = " + this.position;
    }
    
// TOKEN CLASSES
    
    // Literals, identifiers, operators ...
    
    public static final int INTLITERAL = 0;
    public static final int CHARLITERAL = 1;
    public static final int IDENTIFIER = 2;
    public static final int OPERATOR = 3;
    
    // Reserved words - must be in alphabetical order ...
    
    public static final int ARRAY = 4;
    public static final int BEGIN = 5;
    public static final int CONST = 6;
    public static final int DO = 7;
    public static final int ELSE = 8;
    public static final int END = 9;
    public static final int FUNC = 10;
    public static final int IF = 11;
    public static final int IN = 12;
    public static final int LET = 13;
    public static final int OF = 14;
    public static final int PROC = 15;
    public static final int RECORD = 16;
    public static final int THEN = 17;
    public static final int TYPE = 18;
    public static final int VAR = 19;
    public static final int WHILE = 20;
    
    // Punctuation ...
    
    public static final int DOT = 21;
    public static final int COLON = 22;
    public static final int SEMICOLON = 23;
    public static final int COMMA = 24;
    public static final int BECOMES = 25;
    public static final int IS = 26;
    
    // Brackets ...
    
    public static final int LPAREN = 27;
    public static final int RPAREN = 28;
    public static final int LBRACKET = 29;
    public static final int RBRACKET = 30;
    public static final int LCURLY = 31;
    public static final int RCURLY = 32;
    
    // Special tokens ...
    
    public static final int EOT = 33;
    public static final int ERROR = 34;
    
    private static String[] tokenTable = new String[] {
        "<int>",
        "<char>",
        "<identifier>",
        "<operator>",
        "array",
        "begin",
        "const",
        "do",
        "else",
        "end",
        "func",
        "if",
        "in",
        "let",
        "of",
        "proc",
        "record",
        "then",
        "type",
        "var",
        "while",
        ".",
        ":",
        ";",
        ",",
        ":=",
        "~",
        "(",
        ")",
        "[",
        "]",
        "{",
        "}",
        "",
        "<error>"
    };
    
    private final static int firstReservedWord = Token.ARRAY;
    private final static int lasReservedWord = Token.WHILE;
}
