package org.ladbury.genealogy;

import java.util.Vector;

public class GedIndividual
    extends Object {
    // this class holds the details of a person the data held
    // falls into two categories, 'fundamental' i.e. necessary to
    // describe a person fully, and 'linkage' which has been included
    // to reduce the amount of searching & matching necessary.

    // Constants
    public static final String UNKNOWN_NAME = "Unknown Name";
    public static final int MAX_FORENAMES = 5;

    // Fundamental data

    @SuppressWarnings("unused")
	private String m_xref = null; // original reference from GED file
    private int m_reference = 0; // Mandatory, a unique reference number
    private GedGender m_sex = null; // Mandatory, Male Female or unknown
    private GenDate m_birth_date = null; // Optional
    private GenDate m_death_date = null; // Optional

    private GedIndividual m_mother = null; // Optional
    private GedIndividual m_father = null; // Optional

    private String m_surname = null; // Optional
    private String m_forenames[] = null; // Optional

    private Vector m_addresses = null; // Optional See Address class, includes dates

    private Vector m_ceremonies = null; // Optional This is attended to be a list of all
    // the civil and religious events that have occurred
    // to this person, such as birth certification,
    // baptism, marriage, divorce, death certification
    // will proving etc. see Ceremony classes.

    // Linkage data
    private Vector m_partners = null; // list of marriage or breeding partners
    private Vector m_children = null; // list of children (think about adoption)

    //
    // GedIndividual constructor
    //
    public GedIndividual(int ref) throws IllegalArgumentException {
        // A GedIndividual must have a valid reference number responsibilty
        // for uniquenes lies elsewhere

        if (ref > 0) {
            this.clear();
            this.m_reference = ref;
        }
        else {
            throw new IllegalArgumentException(
                "Illegal GedIndividual reference in new GedIndividual (" + ref + ")");
        }
    }
    public GedIndividual(String xref) throws IllegalArgumentException {
        // A GedIndividual must have a valid reference number responsibilty
        // for uniquenes lies elsewhere

        if (xref != null) {
            this.clear();
            this.m_xref = new String(xref);
        }
        else {
            throw new IllegalArgumentException(
                "Null GedIndividual reference in GedIndividual constructor");
        }
    }

    //
    // This method completely clears a GedIndividual and allocates storage to
    // the dynamic parts, normally called from the constructors.
    //
    public void clear() {
        // (re) Initialises a record, leaving garbage collection to pick up any pieces
        this.m_sex = new GedGender();
        this.m_birth_date = new GenDate(GenDate.UNDEFINED_DATE);
        this.m_death_date = new GenDate(GenDate.UNDEFINED_DATE);
        this.m_mother = null;
        this.m_father = null;
        this.m_surname = new String(GedIndividual.UNKNOWN_NAME);
        this.m_forenames = new String[MAX_FORENAMES];
        this.m_forenames[0] = new String (GedIndividual.UNKNOWN_NAME);
        this.m_addresses = new <GedAddress> Vector(0, 1);
        this.m_ceremonies = new Vector(0, 1);
        this.m_partners = new <GedIndividual> Vector(0, 1);
        this.m_children = new <GedIndividual> Vector(0, 1);
    }

    //
    // This Method allows rapid assignment of the key GedIndividual variables
    //
    public void define(GedGender sex,
                       GenDate birth_date,
                       GenDate death_date,
                       GedIndividual mother,
                       GedIndividual father,
                       String surname,
                       String forenames[]) throws CloneNotSupportedException {
        // reference is already set by the constructor
        // <ERROR> m_sex = (Gender)sex.clone();
        m_birth_date = (GenDate) birth_date.clone();
        m_death_date = (GenDate) death_date.clone();
        m_mother = mother;
        m_father = father;
        m_surname = new String(surname);
        int i = 0;
        while (forenames[i] != null) {
            m_forenames[i] = new String(forenames[i]);
            i++;
        }
        // more complex fields are set by other methods
    }

    //
    // This Method sets the mother of the GedIndividual
    //
    public void setMother(GedIndividual mother) throws IllegalArgumentException {
        if (mother == null) { // clearance operation
            m_mother = null;
            return;
        }

        // a mother must be female and different to self
        if (mother != this) {
            if (mother.m_sex.is_female()) {
                m_mother = mother;
            }
            else {
                throw new IllegalArgumentException(
                    "Invalid mother - not female !");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid mother - same person !");
        }
    }

    //
    // This Method gets the mother of the GedIndividual
    //
    public GedIndividual getMother() {
        return m_mother;
    }

    //
    // This Method sets the father of the GedIndividual
    //
    public void setFather(GedIndividual father) throws IllegalArgumentException {
        if (father == null) { // clearance operation
            m_father = null;
            return;
        }

        // a father must be male and different to self
        if (father != this) {
            if (father.m_sex.is_male()) {
                m_father = father;
            }
            else {
                throw new IllegalArgumentException(
                    "Invalid father - not male !");
            }
        }
        else {
            throw new IllegalArgumentException("Invalid father - same person !");
        }
    }

    //
    // This Method gets the father of the GedIndividual
    //
    public GedIndividual getFather() {
        return m_father;
    }

    //
    // This method determines if the GedIndividual has the specified name
    // as one of their names.
    //
    public boolean called(String name) {
        if (m_surname.equals(name)) {
            return true;
        }
        int i = 0;
        while (m_forenames[i] != null) {
            if (m_forenames[i].equals(name)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void clear_forenames() {
        int i = 0;
        for (i = 0; i < MAX_FORENAMES; i++) {
            m_forenames[i] = null;
        }
    }

    public void add_forename(String s) {
        int i = 0;
        while ( (i < MAX_FORENAMES) && (m_forenames[i] != null)) {
            i++;
        }
        if (i < MAX_FORENAMES) {
            m_forenames[i] = new String(s);
        }
    }

    public void setSurname(String s) {
        m_surname = new String(s);
    }
    public void setReference(int i){
        m_reference = i;
    }
    public void setXref(String s){
        m_xref = new String(s);
    }
}