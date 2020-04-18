/*
 * @(#)Scanner.java                        2.1 2003/10/07
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

public final class Scanner {
    
     private SourceFile sourceFile;
    private boolean debug;
    
    private char currentChar;
    private StringBuffer currentSpelling;
    private boolean currentlyScanningToken;
    
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    
    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' ||
                c == '=' || c == '<' || c == '>' || c == '\\' ||
                c == '&' || c == '@' || c == '%' || c == '^' ||
                c == '?');
    }
    
    public Scanner(SourceFile source) {
        this.sourceFile = source;
        this.currentChar = this.sourceFile.getSource();
        this.debug = false;
    }
    
    public void enableDebuggin() {
        this.debug = true;
    }
    
    /**
     * Appends the current character to the current token, and gets
     * the next character from the source program
     */
    private void takeIt() {
        if(this.currentlyScanningToken) {
            this.currentSpelling.append(this.currentChar);
        }
        this.currentChar = this.sourceFile.getSource();
    }
    
    /**
     * Skips a single separator
     */
    private void scanSeparator() {
        switch(this.currentChar) {
            case '!':
            {
                takeIt();
                while((this.currentChar != SourceFile.EOL) && (this.currentChar != SourceFile.EOT)) {
                    takeIt();
                }
                if(this.currentChar == SourceFile.EOL) {
                    takeIt();
                }
            }
            break;
            case ' ':
            case '\n':
            case '\r':
            case '\t':
                takeIt();
                break;
        }
    }
    
    private int scanToken() {
        switch(this.currentChar) {
            case 'a':  case 'b':  case 'c':  case 'd':  case 'e':
            case 'f':  case 'g':  case 'h':  case 'i':  case 'j':
            case 'k':  case 'l':  case 'm':  case 'n':  case 'o':
            case 'p':  case 'q':  case 'r':  case 's':  case 't':
            case 'u':  case 'v':  case 'w':  case 'x':  case 'y':
            case 'z':
            case 'A':  case 'B':  case 'C':  case 'D':  case 'E':
            case 'F':  case 'G':  case 'H':  case 'I':  case 'J':
            case 'K':  case 'L':  case 'M':  case 'N':  case 'O':
            case 'P':  case 'Q':  case 'R':  case 'S':  case 'T':
            case 'U':  case 'V':  case 'W':  case 'X':  case 'Y':
            case 'Z':
                takeIt();
                while(isLetter(this.currentChar) || isDigit(this.currentChar)) {
                    takeIt();
                }
                return Token.IDENTIFIER;
                
            case '0':  case '1':  case '2':  case '3':  case '4':
            case '5':  case '6':  case '7':  case '8':  case '9':
                takeIt();
                while(isDigit(this.currentChar)) {
                    takeIt();
                }
                return Token.INTLITERAL;
                
            case '+':  case '-':  case '*': case '/':  case '=':
            case '<':  case '>':  case '\\':  case '&':  case '@':
            case '%':  case '^':  case '?':
                takeIt();
                while(isOperator(this.currentChar)) {
                    takeIt();
                }
                return Token.OPERATOR;
                
            case '\'':
                takeIt();
                takeIt(); // the quoted character
                if (this.currentChar == '\'') {
                    takeIt();
                    return Token.CHARLITERAL;
                } else
                    return Token.ERROR;

            case '.':
                takeIt();
                return Token.DOT;

            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.BECOMES;
                } else
                    return Token.COLON;

            case ';':
                takeIt();
                return Token.SEMICOLON;

            case ',':
                takeIt();
                return Token.COMMA;

            case '~':
                takeIt();
                return Token.IS;

            case '(':
                takeIt();
                return Token.LPAREN;

            case ')':
                takeIt();
                return Token.RPAREN;

            case '[':
                takeIt();
                return Token.LBRACKET;

            case ']':
                takeIt();
                return Token.RBRACKET;

            case '{':
                takeIt();
                return Token.LCURLY;

            case '}':
                takeIt();
                return Token.RCURLY;

            case SourceFile.EOT:
                return Token.EOT;

            default:
                takeIt();
                return Token.ERROR;
        }   
        
    }
    
    public Token scan() {
        Token token;
        SourcePosition pos;
        int kind;
        
        this.currentlyScanningToken = false;
        while(this.currentChar == '!' || this.currentChar == ' ' ||
              this.currentChar == '\n' || this.currentChar == '\r' ||
              this.currentChar == '\t') {
            scanSeparator();
        }
        
        this.currentlyScanningToken = true;
        this.currentSpelling = new StringBuffer("");
        pos = new SourcePosition();
        pos.start = this.sourceFile.getCurrentLine();
        
        kind = scanToken();
        
        pos.finish = this.sourceFile.getCurrentLine();
        token = new Token(kind, this.currentSpelling.toString(), pos);
        if(this.debug) {
            System.out.println(token);
        }
        return token;
    }
    
}
