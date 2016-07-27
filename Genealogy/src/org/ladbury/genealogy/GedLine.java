package org.ladbury.genealogy;

import java.util.Vector;

//
// GedLine
//
// This class manages a line from a gedcom file.
// The line is viewed as a vector of tokens plus some additional
// line related information such as line number.
// Valid lines types are:
// <line level number> <tag>
// <line level number> <reference> <tag> // for some level 0 lines only
// <line level number> <tag> <reference> // for level > 0 only
// <line level number> <tag> <additional info> // all other lines
// additional interpretation is left to other classes.
// tag validation is performed by Gedtag
// reference extraction is done here without knowledge of the delimeters

public class GedLine
    extends Vector {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static public final int GED_SUBTYPE_UNDEFINED = 0;

    int m_subtype = GED_SUBTYPE_UNDEFINED;
    private int m_line_number = 0;
    private int m_line_level = 0;
    private int m_error_number = 0;
    int m_reference = 0; //stores a numeric reference (poss. redundant)
    String m_xref = null; //stores reference string
    String m_str_value = null; // stores other string values
    //GenDate m_date;

    // Vector will contain the tokens

    //
    // constructor
    //
    public GedLine() {
        super(2, 1); // minimum valid line has 2 tokens
        m_subtype = GED_SUBTYPE_UNDEFINED;
        m_line_number = 0;
        m_line_level = 0;
        m_xref = null;
        m_str_value = null;


    }

    //
    // Add a token to the current line, at the end
    //
    public void add(GedToken gt) {
        super.addElement(gt);
    }

    //
    // Define which line number this is
    //
    public void setLine(int line) {
        m_line_number = line;
    }
    public int getLine() {
        return m_line_number;
    }
    public void setLineLevel(int level) {
        m_line_level = level;
    }
    public int getLineLevel() {
        return m_line_level;
    }

    //
    // parse the line, return true if correct.
    // The level number of the line will be set.
    // Tags and references will be identified and subtitutions made
    //
    public boolean parse() {
        int current_token = 0;
        String s;
        GedToken gt, next_gt = null;
        try {
            if (this.isEmpty()) {
            	m_error_number = 1;
            	return false;
            }
            // this.print(); //debug print line
            // first token must be a level number
            gt = (GedToken)this.elementAt(current_token);
            if (gt.m_ttype == GedToken.GT_INT) {
                m_line_level = gt.m_nval;
            }
            else {
            	m_error_number = 2;
                return false;
            }
            current_token++;

            // the next 3 tokens could be a reference
            if (this.parse_ref(current_token)) { // it was, 3 tokens have been dealt with, (2 deleted)
                this.m_reference = ((GedToken)this.elementAt(current_token)).m_nval;
                this.m_xref = new String(((GedToken)this.elementAt(current_token)).m_sval);
                current_token++;
            }

            // the next token must be a tag
            if (this.size() <= (current_token)) {
                return false; // There must be 1 tag on each line
            }
            gt = (GedToken)this.elementAt(current_token);
            if (gt.parse(GedToken.GT_TAG)) {
                int size = 0;

                // set tag for line
                this.m_subtype = gt.m_nval;
                // found a tag, may be able to deal with the next token
                current_token++;

                if (this.size() <= (current_token)) {
                    return true; // need 1 element
                }
                size = this.size();
                next_gt = (GedToken)this.elementAt(current_token);
                switch (gt.nextType()) {
                    case GedToken.GT_REF:
                        if (this.parse_ref(current_token)) { // it was, 3 tokens have been dealt with, (2 deleted)
                            this.m_reference = ((GedToken)this.elementAt(current_token)).m_nval;
                            this.m_xref = new String(((GedToken)this.elementAt(current_token)).m_sval);
                            current_token++;
                        }
                        break;

                    case GedToken.GT_STRING: // the rest of the line is one string
                        s = new String(next_gt.toString());

                        while (this.size() > (current_token + 1)) {
                            size = this.size();
                            s = new String(s + " " +
                                           ( (GedToken)this.elementAt(current_token +
                                1)).toString());
                            this.removeElementAt(current_token + 1);
                        }

                        this.setElementAt(new GedToken(s), current_token);
                        m_str_value = new String(s);

                    default:
                        ;
                }
            }
            //this.print();
            return true;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("GedLine.parse " + e);
        }
        m_error_number = 3;
        return false;
    }

    //
    // This method will parse a reference, the 3 tokens scanned in
    // will be replaced by 1 if a reference is found, so don't count
    // tokens until this is complete.
    //
    public boolean parse_ref(int start) {
        int current_token = start;
        try {
            if (this.size() < (start + 2 + 1)) {
                return false; // need 3 elements
            }

            // current token could be a reference delimiter '@'
            // next token is a reference string ?? what if it is a number!
            // and the third another reference delimiter '@'
            if ( ( (GedToken)this.elementAt(current_token)).m_ttype ==
                GedToken.GT_CHAR
                &&
                ( (GedToken)this.elementAt(current_token)).m_cval ==
                GedToken.GT_REF_DELIM
                &&
                ( (GedToken)this.elementAt(current_token + 1)).m_ttype ==
                GedToken.GT_STRING
                &&
                ( (GedToken)this.elementAt(current_token + 2)).m_ttype ==
                GedToken.GT_CHAR
                &&
                ( (GedToken)this.elementAt(current_token + 2)).m_cval ==
                GedToken.GT_REF_DELIM
                ) { // have a candidate token, parse it to make sure
                if ( ( (GedToken)this.elementAt(current_token + 1)).parse(
                    GedToken.GT_REF)) {
                    // got a reference !
                    // the token now knows its a reference so we can loose the markers
                    this.removeElementAt(current_token + 2);
                    this.removeElementAt(current_token);
                    this.trimToSize();
                    m_xref = new String(((GedToken)this.elementAt(current_token)).m_sval);
                    return true;
                }
            }
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("GedLine.parse_ref " + e);
        }
        return false;
    }

    //
    // print of a line of tokens for debugging purposes
    //
    public void print() {
        int i = 0;

        System.out.print("[" + m_line_number + " L:" + m_line_level + "]:");
        for (i = 0; i < this.size(); i++) {
            ( (GedToken)this.elementAt(i)).print();
        }
        System.out.println();
    }
}
