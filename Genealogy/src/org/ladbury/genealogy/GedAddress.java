package org.ladbury.genealogy;

import java.util.Vector;

//
//
// Address
//
//
public class GedAddress
    extends Object
    implements Cloneable {
    // constants
    public static final String LINE1 = "Line 1";
    public static final String LINE2 = "Line 2";
    public static final String CITY = "City";
    public static final String COUNTY = "County";
    public static final String POSTCODE = "Post Code";
    public static final String COUNTRY = "Country";

    private static final int INITIAL_SIZE = 6;
    private static final int INCREMENT = 1;

    // variables
    private Vector<String> m_address_lines = null;
    private Vector<String> m_meaning = null;
    private GenDate m_in_date = null;
    private GenDate m_out_date = null;

    // Constructor
    public GedAddress() {
    	
        this.m_address_lines = new Vector<String>(GedAddress.INITIAL_SIZE,
                                                  GedAddress.INCREMENT);
        this.m_meaning = new Vector<String>(GedAddress.INITIAL_SIZE, 
        		                            GedAddress.INCREMENT);
        this.m_in_date = new GenDate();
        this.m_out_date = new GenDate();
    }

    public void move_in(GenDate date) {
        m_in_date = date;
    }

    public void move_out(GenDate date) {
        m_out_date = date;
    }

    public void insert_line(String address_line, String meaning) {
        int index = -1;

        index = m_meaning.indexOf(meaning);

        if (index == -1) {
            // no line of this type in the address so add one
            m_meaning.addElement(meaning);
            m_address_lines.addElement(address_line);
        }
        else {
            m_address_lines.setElementAt(address_line, index);
        }
    }

    public String get_line(String meaning) {
        int index = -1;

        index = m_meaning.indexOf(meaning);

        if (index == -1) {
            // line of specified type not found
            return null;
        }
        else {
            return (String) m_address_lines.elementAt(index);
        }
    }

    public void clear() {
        m_address_lines.removeAllElements();
        m_address_lines.setSize(GedAddress.INITIAL_SIZE);
        m_meaning.removeAllElements();
        m_meaning.setSize(GedAddress.INITIAL_SIZE);
        m_in_date.clear();
        m_out_date.clear();
    }
}
