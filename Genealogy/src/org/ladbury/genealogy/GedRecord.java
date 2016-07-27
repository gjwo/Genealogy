package org.ladbury.genealogy;

//
//
// GedRecord
//
//
import java.util.Vector;

public class GedRecord
    extends Vector {
    static public final int GED_RECORD_UNDEFINED = 0;

    int m_record_type = GED_RECORD_UNDEFINED;
    private boolean m_contains_level_0 = false;
    int m_no_of_lines = 0;
    int m_reference = 0;
    String m_xref = null;
    GedIndividual m_person = null;

    // Vector will contain all the GedLine elements

    //
    // constructor
    //
    public GedRecord() {
        super(4, 1);
        m_contains_level_0 = false;
        m_record_type = GED_RECORD_UNDEFINED;
        m_no_of_lines = 0;
    }

    //
    // This method adds a line to the current record
    // provided the line does not signal the start of a new record
    //
    public boolean add(GedLine gl) {
        if (gl.getLineLevel() == 0) {
            if (m_contains_level_0) {
                return false;
            }
            else {
                m_contains_level_0 = true;
            }
        }
        super.addElement(gl);
        m_no_of_lines++;
        return true;
    }

    //
    // This method parses a record, the individual lines of which
    // have already been parsed
    //
    public boolean parse() {
        int current_line = 0;
        int current_level = 0;
        GedLine gl = null;
        boolean return_value = false;

        try {
            if (this.isEmpty()) {
                return false;
            }

            gl = (GedLine)this.elementAt(current_line);

            // first line must be a level 0 line
            current_level = gl.getLineLevel();
            if (current_level != 0) {
                return false;
            }

            // The subtype of the level 0 line defines what kind of record this is
            this.m_record_type = gl.m_subtype;

            switch (this.m_record_type) {
                case GedTag.T_HEADER: // we have a file header
                    return_value = handle_header();
                    break;

                case GedTag.T_INDIVIDUAL: // we have a person
                    return_value = handle_individual();
                    break;

                case GedTag.T_FAMILY: // we have a family record
                    return_value = handle_family();
                    break;

                case GedTag.T_SOURCE: // we have a source record
                    //return_value = handle_source();
                    break;

                case GedTag.T_SUBMITTER: // we have a submitter
                    //return_value = handle_submitter();
                    break;

                case GedTag.T_TRAILER: // we have the last record in the file
                    return_value = handle_trailer();
                    break;

                default:
                    System.out.println(
                        "GedRecord.parse() Unhandled record type " +
                        this.m_record_type);
                    return_value = false;
            }
            //this.print(); //debug
            return return_value;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("GedRecord.parse " + e);
        }
        return false;
    }


                // now the basic information is in the correct form
                // do any tag related type translation
// this should be done in record processing !!!!
//            current_token = (this.size() - 1);
//            gt = (GedToken)this.elementAt(current_token);
//            switch (this.m_subtype) {
//                case GedTag.T_DATE:
//                    try {
//                        m_date = new GenDate(gt.m_sval);
//                    }
//                    catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                default:
                        ;
//            }

                // more to do here in the future





    //
    // This method handles a header record
    //
    boolean handle_header() {
        return true;
    }

    //
    // This method handles an individual record by creating a person
    //  a new person in the population
    //
    boolean handle_individual() {
        int current_line = 0;
        int current_token = 0;
        GedLine gl = null;
        GedToken gt = null;
        int subtype = GedTag.T_BAD_TAG;

        gl = (GedLine)this.elementAt(current_line);
        this.m_reference = gl.m_reference; // probably redundant
        this.m_xref = new String(gl.m_xref);
        this.print(); //debug line
        m_person = Jtree.m_main.m_population.register();
        m_person.setXref(this.m_xref);
        current_line++;

        while (this.size() > current_line) {
            gl = (GedLine)this.elementAt(current_line); // temporary pointer
            subtype = gl.m_subtype;
            switch (subtype) {
                case GedTag.T_NAME:
                    handle_name(gl);
                    break;
                default:
                    // ignore the line
            }
            current_line++;
        }

        return true;
    }

    //
    // This method handles a family record
    //
    boolean handle_family() {
        return true;
    }

    //
    // This method handles a trailer record
    //
    boolean handle_trailer() {
        return true;
    }

    //
    // Handle persons name
    //
    // expecting tokens 'n' 'NAME' 'forename1..' 'forename n' '/' 'surname' '/'
    //					 0		1		2			n-1		   n   n+1		n+2
    // updates m_person
    boolean handle_name(GedLine gl) {
        boolean found_surname = false;
        int current_token = 2;
        GedToken gt = null;

        this.m_person.clear_forenames();
        while ( (gl.size() > current_token) & !found_surname) {
            gt = (GedToken) gl.elementAt(current_token); // temp pointer
            if (gt.m_ttype == GedToken.GT_STRING) {
                this.m_person.add_forename(gt.m_sval);
            }
            else if (gt.m_ttype == GedToken.GT_CHAR
                     & gt.m_cval == GedToken.GT_SURNAME_DELIM) {
                found_surname = true;
            }
            current_token++;
        }
        if (gl.size() > current_token & found_surname) {
            gt = (GedToken) gl.elementAt(current_token);
            if (gt.m_ttype == GedToken.GT_STRING) {
                this.m_person.setSurname(gt.m_sval);
            }
        }
        return true;
    }

    public void print() {
        int i = 0;

        System.out.println("Start of record, " + m_no_of_lines + " lines");
        for (i = 0; i < this.size(); i++) {
            ( (GedLine)this.elementAt(i)).print();
        }
        System.out.println("End of record");
    }

}
