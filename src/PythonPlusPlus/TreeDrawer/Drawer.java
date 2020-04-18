/*
 * @(#)Drawer.java                        2.1 2003/10/07
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

package PythonPlusPlus.TreeDrawer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import PythonPlusPlus.AbstractSyntaxTrees.Program;

public class Drawer {
    
    private DrawerFrame frame;
    private DrawerPanel panel;
    
    private Program theAST;
    private DrawingTree theDrawing;
    
    // Draw the AT representing a complete program
    
    public void draw(Program ast) {
        this.theAST = ast;
        this.panel = new DrawerPanel(this);
        this.frame = new DrawerFrame(this.panel);
        
        Font font = new Font("SansSerif", Font.PLAIN, 12);
        this.frame.setFont(font);
        
        FontMetrics fontMetrics = this.frame.getFontMetrics(font);
        
        LayoutVisitor layout = new LayoutVisitor(fontMetrics);
        this.theDrawing = (DrawingTree) this.theAST.visit(layout, null);
        this.theDrawing.position(new Point(2048, 10));
        
        this.frame.show();
    }
    
    public void paintAST(Graphics g) {
        g.setColor(this.panel.getBackground());
        Dimension d = this.panel.getSize();
        g.fillRect(0, 0, d.width, d.height);
        if(this.theDrawing != null) {
            this.theDrawing.paint(g);
        }
    }
}
