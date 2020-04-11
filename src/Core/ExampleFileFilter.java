/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.filechooser.*;

/**
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that is knows about.
 * 
 * Extensions are of the type ".foo", which is typically found on
 * Windows and Unix boxes, but not on Macintosh. Case is ignored.
 * 
 * Example - create a new filter that filters out all the files
 * but .gif and .jpg image files:
 * 
 *      JFileChooser chooser = new JFileChooser();
 *      ExampleFileFilter filter = new ExampleFileFilter(
 *                      new String{"gif", "jpg"}, "JPEG & GIF Images")
 *      chooser.addChoosableFileFilter(filter);
 *      chooser.showOpenDialog(this);
 * 
 * @version 1.9 04/23/99
 * @author Jeff Dinkins
 */

public class ExampleFileFilter extends FileFilter{
    
    private static String TYPE_UKNOWN = "Type Unkown";
    private static String HIDDEN_FILE = "Hidden File";
    
    private Hashtable filters = null;
    private String description = null;
    private String fullDescription = null;
    private boolean useExtensionsInDescription = true;
    
    /**
     * Creates a file filter. If no filters are added, then all
     * files are accepted.
     * 
     * //@see #addExtension
     */
    public ExampleFileFilter() {
        this.filters = new Hashtable();
    }
    
    /**
     * Create a file filter that accepts files with the given extension.
     * Example: new ExampleFileFilter("jpg");
     * 
     * //@see #addExtension
     * @param extension
     */
    public ExampleFileFilter(String extension) {
        this(extension, null);
    }
    
    /**
     * Create a file filter that accepts the given file type.
     * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
     * 
     * Note that the "." before the extension is not needed. If 
     * provided, it will be ignored.
     * 
     * //@see #addExtension
     * @param extension
     * @param description
     */
    public ExampleFileFilter(String extension, String description) {
        this();
        if(extension != null) addExtension(extension);
        if(description != null) setDescription(description);
    }
    
    /**
     * Creates a file filter from the given string array.
     * Example: new ExampleFileFilter(String {"gif", "jpg"});
     * 
     * Note that the "." before the extension is not needed and
     * will be ignored.
     * 
     * //@see #addExtension
     * @param filters
     */
    public ExampleFileFilter(String[] filters) {
        this(filters, null);
    }
    
    /**
     * Creates a file filter from the given string array and description.
     * Example: new ExampleFileFilter(String {"gif", "jpg"}, "GIF and JPG Images");
     * 
     * Note that the "." before the extension is not needed and will be ignored.
     * 
     * //@see #addExtension
     * @param filters
     * @param description
     */
    public ExampleFileFilter(String[] filters, String description) {
        this();
        for(int i = 0; i < filters.length; i++) {
            // Add filters one by one
            addExtension(filters[i]);
        }
        if(description != null) setDescription(description);
    }
    
    /**
     * Return true if this file should be shown in the directory pane,
     * false if it shouldn't
     * 
     * Files that begin with "." are ignored.
     * 
     * //@see #getExtension
     * //@see FileFilter#accepts
     * @param f
     * @return 
     */
    public boolean accept(File f) {
        if(f != null) {
            if(f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            if(extension != null && this.filters.get(getExtension(f)) != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return the extension portion of the file's name
     * 
     * //@see #getExtension
     * //@see FileFilter#accept
     * @param f
     * @return 
     */
    public String getExtension(File f) {
        if(f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if(i > 0 && i < filename.length()-1) {
                return filename.substring(i+1).toLowerCase();
            }
        }
        return null;
    }
    
    /**
     * Adds a filetype "dot" extension to the filter against.
     * 
     * For example: the following code will create a filter that filters
     * out all files except those that end in ".jpg" and ".tif":
     * 
     *      ExampleFileFilter filter = new ExampleFileFilter();
     *      filter.addExtension("jpg");
     *      filter.addExtension("tif");
     * 
     * Note that the "." before the extension is not needed and will be ignored.
     * @param extension
     */
    public void addExtension(String extension) {
        if(this.filters == null) {
            this.filters = new Hashtable(5);
        }
        this.filters.put(extension.toLowerCase(), this);
        this.fullDescription = null;
    }
    
    /**
     * Returns the human readable description of this filter. 
     * For example: "JPEG and GIF Image Files (*.jpg, *.gif)"
     * 
     * //@see setDescription
     * //@see setExtensionListInDescription
     * //@see isExtensionListInDescription
     * //@see FileFilter#getDescription
     * @return 
     */
    public String getDescription() {
        if(this.fullDescription == null) {
            if(this.description == null || isExtensionListInDescription()) {
                this.fullDescription = this.description == null ? "(" : this.description +
                        " (";
                // Build the description from the extension list
                Enumeration extensions = this.filters.keys();
                if(extensions != null) {
                    this.fullDescription += "." + (String) extensions.nextElement();
                    while(extensions.hasMoreElements()) {
                        this.fullDescription += ", " + (String) extensions.nextElement();
                    }
                }
                this.fullDescription += ")";
            } else {
                this.fullDescription = this.description;
            }
        }
        return this.fullDescription;
    }
    
    /**
     * Sets the human readable description of this filter.
     * For example: filter.setDescription("GIF and JPG Images");
     * 
     * //@see setDescription
     * //@see setExtensionListInDescription
     * //@see isExtensionListInDescription
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
        this.fullDescription = null;
    }
    
    /**
     * Determines whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     * 
     * only relevant if a description was provided in the constructor 
     * or using setDescription();
     * 
     * //@see getDescription
     * //@see setDescription
     * //@see isExtensionListInDescription
     * @param b
     */
    public void setExtensionListInDescription(boolean b) {
        this.useExtensionsInDescription = b;
        this.fullDescription = null;
    }
    
    /**
     * Returns whether the extension list (.jpg, .gif, etc) should
     * show up in the human readable description.
     * 
     * Only relevant if a description was provided in the constructor
     * or using setDescription();
     * 
     * //@see getDescription
     * //@see setDescription
     * //@see setExtensionListInDescription
     * @return 
     */
    public boolean isExtensionListInDescription() {
        return this.useExtensionsInDescription;
    }
}
