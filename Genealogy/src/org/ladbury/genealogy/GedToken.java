package org.ladbury.genealogy;


//
// GedToken
//
public class GedToken {
    //
    // constants
    //

    // values for stream token type
    public static final int GT_UNDEFINED = 0;
    public static final int GT_INT = 1;
    public static final int GT_STRING = 2;
    public static final int GT_CHAR = 3;
    public static final int GT_TAG = 4;
    public static final int GT_REF = 5;
    public static final int GT_DONT_CARE = 6;

    public static final char GT_REF_DELIM = '@';
    public static final char GT_SURNAME_DELIM = '/';


    public int m_ttype; // raw data type
    int m_nval;
    String m_sval;
    char m_cval;

    //
    //	Constructors
    //
    public GedToken(String s) {
        m_sval = new String(s);
        m_ttype = GT_STRING;
    }

    public GedToken(int i) {
        m_nval = i;
        m_ttype = GT_INT;
    }

    public GedToken(char c) {
        m_cval = c;
        m_ttype = GT_CHAR;
    }

    //
    // Methods
    //

    //
    // next_type
    //
    public int nextType() {
        if (this.m_ttype == GT_TAG) {
            return GedTag.nextStreamToken(m_nval);
        }
        return GedTag.T_BAD_TAG;
    }

    //
    // parse the token, given some clues from above
    //
    public boolean parse(int expected) {
        switch (expected) {
            case GT_TAG:
                return this.setTag();
              case GT_REF:
                return this.setRef();
            default:
                ;
        }
        return true;
    }

    //
    // set tag
    //
    private boolean setTag() {
        if (m_ttype != GT_STRING) {
            return false;
        }
        m_nval = GedTag.tag_lookup(this.m_sval);
        if (m_nval == GedTag.T_BAD_TAG) {
            return false;
        }
        m_ttype = GT_TAG;
        return true;
    }

    private boolean setRef() {
        // need to look up the tag based on the string value
        // this needs revising as references can be alphas
        if (m_ttype != GT_STRING) {
            return false;
        }
          m_sval = m_sval;
          m_ttype = GT_REF;
          return true;
    }

    //
    // This method returns the value of the token as a string
    //
    public String toString() {

        switch (this.m_ttype) {
            case GT_INT:
                return Integer.toString(this.m_nval);
            case GT_STRING:
            case GT_REF:
                return this.m_sval;
            case GT_CHAR:
                char[] ca = new char[1];
                ca[0] = this.m_cval;
                return new String(ca);
            case GT_TAG:
                return this.m_sval;
          default:
                return "";
        }
    }
    //
    // print for debugging
    //
    public void print() {
        System.out.print("{" + this.m_ttype + ",");
        switch (this.m_ttype) {
            case GT_INT:
                System.out.print(this.m_nval + "}");
                break;
            case GT_STRING:
                System.out.print("\"" + this.m_sval + "\"}");
                break;
            case GT_CHAR:
                System.out.print("'" + this.m_cval + "'}");
                break;
            case GT_TAG:
                System.out.print("TAG:\"" + this.m_sval + "\"" + this.m_nval +
                                 "}");
                break;
            case GT_REF:
                System.out.print("REF:" + this.m_sval + "}");
                break;
            default:
                System.out.print("-}");
        }
    }
}