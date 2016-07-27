package org.ladbury.genealogy;

//
//
// GedGender
//
//
public class GedGender
    extends Object
    implements Cloneable {
    // constants
    public final static char MALE = 'M';
    public final static char FEMALE = 'F';
    public final static char UNKNOWN = '?';

    private char m_gender;

    public GedGender() {
        m_gender = UNKNOWN;
    }

    public GedGender(char sex) throws IllegalArgumentException {
        set_gender(sex);
    }

    public void set_gender(char sex) throws IllegalArgumentException {
        switch (sex) {
            case MALE:
                this.m_gender = MALE;
                break;
            case FEMALE:
                this.m_gender = FEMALE;
                break;
            case UNKNOWN:
                this.m_gender = UNKNOWN;
                break;

            default:
                throw new IllegalArgumentException("Illegal gender supplied (" +
                    sex +
                    ")");
        }
    }

    public boolean is_male() {
        return (m_gender == MALE);
    }

    public boolean is_female() {
        return (m_gender == FEMALE);
    }

    public boolean is_unknown() {
        return (m_gender == UNKNOWN);
    }

    public char gender() {
        return (m_gender);
    }

}
