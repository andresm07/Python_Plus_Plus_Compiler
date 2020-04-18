/*
 * @(#)DrawingTree.java                        2.1 2003/10/07
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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawingTree {
    
    String caption;
    int width, height;
    Point pos, offset;
    Polygon contour;
    DrawingTree parent;
    DrawingTree[] children;
    
    public DrawingTree(String caption, int width, int height) {
        this.caption = caption;
        this.width = width;
        this.height = height;
        this.parent = null;
        this.children = null;
        this.pos = new Point(0, 0);
        this.offset = new Point(0, 0);
        this.contour = new Polygon();
    }
    
    public void setChildren(DrawingTree[] children) {
        this.children = children;
        for (DrawingTree children1 : children) {
            children1.parent = this;
        }
    }
    
    private final int FIXED_FONT_HEIGHT = 10;
    private final int FIXED_FONT_ASCENT = 3;
    private final Color nodeColor = new Color(250, 220, 100);
    
    public void paint (Graphics graphics) {
        graphics.setColor(this.nodeColor);
        graphics.fillRect(this.pos.x, this.pos.y, this.width, this.height);
        graphics.setColor(Color.black);
        graphics.drawRect(this.pos.x, this.pos.y, this.width-1, this.height-1);
        graphics.drawString(this.caption, this.pos.x+2, 
                this.pos.y + (this.height + this.FIXED_FONT_HEIGHT)/2);
        
        if(this.children != null) {
            for (DrawingTree children1 : this.children) {
                children1.paint(graphics);
            }
        }
        
        if(this.parent != null) {
            graphics.drawLine(this.pos.x+this.width/2, this.pos.y,
                    this.parent.pos.x+this.parent.width/2,
                    this.parent.pos.y+this.parent.height);
        }
    }
    
    public void position(Point pos) {
        this.pos.x = pos.x + this.offset.x;
        this.pos.y = pos.y + this.offset.y;
        
        Point temp = new Point(this.pos.x, this.pos.y);
        
        if(this.children != null) {
            for (DrawingTree children1 : this.children) {
                children1.position(temp);
                temp.x += children1.offset.x;
                temp.y += this.pos.y + this.children[0].offset.y;
            }
        }
    }
}
