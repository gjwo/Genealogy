package org.ladbury.genealogy;

import java.util.Vector;

public class GedPeople
    extends Vector {
// This class provides a pool for storing people
// The following rules are enforced
// Rule 1 every GedIndividual has a unique reference number
// Rule 2 the reference is between 1 and a specified maximum index
// Rule 3 the GedPeople has a header record at index 0

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int m_limit = 20000;

    private int m_max_index = 0;
    private int m_max_index_used = 0;
    private String m_header = null;

    //
    // Construct a new (limited) GedPeople
    //
    public GedPeople(int max_index) throws ArrayIndexOutOfBoundsException {
        super(1, 1);
        if ( (max_index > 0) && (max_index < m_limit)) {
            m_max_index = max_index;
            this.setSize(m_max_index);
            m_header = new String("Header record at index 0");
            this.addElement(m_header);
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    //
    // Register a new GedIndividual and allocate unique reference
    //
    public synchronized GedIndividual register() {
        Object o = new Object();
        int i;

        this.addElement(o);
        i = this.indexOf(o, 0);
        this.setElementAt(new GedIndividual(i), i);
        if (i > m_max_index_used) {
            m_max_index_used = i;
        }
        return (GedIndividual)this.elementAt(i);
    }

    //
    // Register a new GedIndividual at a specified reference
    //
    public synchronized GedIndividual register(int index) throws
        ArrayIndexOutOfBoundsException,
        IllegalArgumentException {
        if (index > 0 && index < m_max_index) { // legal index
//			if (this.elementAt(index) == null)
//			{ // index not already used
            this.setElementAt(new GedIndividual(index), index);
            if (index > m_max_index_used) {
                m_max_index_used = index;
            }
            return ( (GedIndividual)this.elementAt(index));
//			}
//			else throw new IllegalArgumentException("GedIndividual reference ("+index+") in use");
        }
        else {
            System.out.println("GedPeople.register( " + index + " )");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}