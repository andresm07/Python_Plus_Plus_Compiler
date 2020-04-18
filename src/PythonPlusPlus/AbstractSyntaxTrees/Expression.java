/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PythonPlusPlus.AbstractSyntaxTrees;

import PythonPlusPlus.SyntacticAnalyzer.SourcePosition;

public abstract class Expression extends AST{
    
    public Expression(SourcePosition thePosition) {
        super(thePosition);
        this.type = null;
    }
    
    public TypeDenoter type;
}
