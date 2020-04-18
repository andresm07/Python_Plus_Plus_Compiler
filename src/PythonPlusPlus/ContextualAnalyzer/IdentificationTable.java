/*
 * @(#)IdentificationTable.java                2.1 2003/10/07
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

package PythonPlusPlus.ContextualAnalyzer;

import PythonPlusPlus.AbstractSyntaxTrees.Declaration;

public final class IdentificationTable {
    
    private int level;
    private IdEntry latest;
    
    public IdentificationTable() {
        this.level = 0;
        this.latest = null;
    }
    
    /**
     * Opens a new level in the identification table, 1 higher than the
     * current topmost level
     */
    public void openScope() {
        this.level++;
    }
    
    /**
     * Closes the topmost level in the identification table, discarding
     * all entries belonging to that level
     */
    public void closeScope() {
        IdEntry entry, local;
        
        // Presumably, idTable.level > 0
        entry = this.latest;
        while(entry.level == this.level) {
            local = entry;
            entry = local.previous;
        }
        this.level--;
        this.latest = entry;
    }
    
    /**
     * Makes a new entry in the identification table for the given identifier
     * and attribute. The new entry belongs to the current level.
     * Duplicated is set to true iff there is already an entry for the same 
     * identifier at the current level
     * 
     * @param id
     * @param attr
     */
    public void enter(String id, Declaration attr) {
        IdEntry entry = this.latest;
        boolean present = false, searching = true;
        
        // Check for duplicate entry ...
        
        while(searching) {
            if(entry == null || entry.level < this.level) {
                searching = false;
            } else if(entry.id.equals(id)) {
                present = true;
                searching = false;
            } else {
                entry = entry.previous;
            }
        }
        
        attr.duplicated = present;
        
        // Add new entry ...
        
        entry = new IdEntry(id, attr, this.level, this.latest);
        this.latest = entry;
    }
    
    /**
     * Finds an entry for the given identifier in the identification table,
     * if any. If there are several entries for that identifier, finds the 
     * entry at the highest level, in accordance with the scope rules.
     * 
     * @param id
     * @return Null iff no entry is found. Otherwise returns the attribute 
     *         field of the entry
     */
    public Declaration retrieve(String id) {
        IdEntry entry;
        Declaration attr = null;
        boolean present = false, searching = true;
        
        entry = this.latest;
        while(searching) {
            if(entry == null) {
                searching = false;
            } else if(entry.id.equals(id)) {
                present = true;
                searching = false;
                attr = entry.attr;
            } else {
                entry = entry.previous;
            }
        }
        
        return attr;
    }
}
