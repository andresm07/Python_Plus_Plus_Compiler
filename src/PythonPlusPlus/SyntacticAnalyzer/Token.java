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
    
    public static final int AND = 4;
    public static final int BOOLEAN = 5;
    public static final int BREAK = 6;
    public static final int CHAR = 7;
    public static final int CONTINUE = 8;
    public static final int DEF = 9;
    public static final int ELIF = 10;
    public static final int ELSE = 11;
    public static final int EXCEPT = 12;
    public static final int FINALLY = 13;
    public static final int FLOAT = 14;
    public static final int FOR = 15;
    public static final int IF = 16;
    public static final int IN = 17;
    public static final int INT = 18;
    public static final int NOT = 19;
    public static final int OR = 20;
    public static final int PASS = 21;
    public static final int PRINT = 22;
    public static final int RETURN = 23;
    public static final int STRING = 24;
    public static final int TRY = 25;
    public static final int WHILE = 26;
    
    // Punctuation ...
    
    public static final int DOT = 27;
    public static final int COLON = 28;
    public static final int COMMA = 29;
    
    // Brackets ...
    
    public static final int LPAREN = 30;
    public static final int RPAREN = 31;
    public static final int LBRACKET = 32;
    public static final int RBRACKET = 33;
    
    // Special tokens ...
    
    public static final int EOT = 34;
    public static final int ERROR = 35;
    
    private static String[] tokenTable = new String[] {
        "<int>",
        "<char>",
        "<identifier>",
        "<operator>",
        "and",
        "boolean",
        "break",
        "char",
        "continue",
        "def",
        "elif",
        "else",
        "except",
        "finally",
        "float",
        "for",
        "if",
        "in",
        "int",
        "not",
        "or",
        "pass",
        "print",
        "return",
        "string",
        "try",
        "while",
        ".",
        ":",
        ",",
        "(",
        ")",
        "[",
        "]",
        "{",
        "}",
        "",
        "<error>"
    };
    
    private final static int firstReservedWord = Token.AND;
    private final static int lasReservedWord = Token.WHILE;
}
