package org.ladbury.genealogy;

/**
 * <p>Title: JTree</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author G.J.Wood
 * @version 1.0
 */

public class GedTag {

    // GED tags

    public static final int T_BAD_TAG = -1;
    public static final int T_NULL = 0;

    public static final int T_ABBREVIATION = 1;
    public static final int T_ADDRESS = 2;
    public static final int T_ADDRESS1 = 3;
    public static final int T_ADDRESS2 = 4;
    public static final int T_ADOPTION = 5;
    public static final int T_AFN = 6;
    public static final int T_AGE = 7;
    public static final int T_AGENCY = 8;
    public static final int T_ALIAS = 9;
    public static final int T_ANCESTORS = 10;
    public static final int T_ANCES_INTEREST = 11;
    public static final int T_ANNULMENT = 12;
    public static final int T_ASSOCIATES = 13;
    public static final int T_AUTHOR = 14;
    public static final int T_BAPTISM_LDS = 15;
    public static final int T_BAPTISM = 16;
    public static final int T_BAR_MITZVAH = 17;
    public static final int T_BAS_MITZVAH = 18;
    public static final int T_BIRTH = 19;
    public static final int T_BLESSING = 20;
    public static final int T_BINARY_OBJECT = 21;
    public static final int T_BURIAL = 22;
    public static final int T_CALL_NUMBER = 23;
    public static final int T_CASTE = 24;
    public static final int T_CAUSE = 25;
    public static final int T_CENSUS = 26;
    public static final int T_CHANGE = 27;
    public static final int T_CHARACTER = 28;
    public static final int T_CHILD = 29;
    public static final int T_CHRISTENING = 30;
    public static final int T_ADULT_CHRISTENING = 31;
    public static final int T_CITY = 32;
    public static final int T_CONCATENATION = 33;
    public static final int T_CONFIRMATION = 34;
    public static final int T_CONFIRMATION_L = 35;
    public static final int T_CONTINUED = 36;
    public static final int T_COPYRIGHT = 37;
    public static final int T_CORPORATE = 38;
    public static final int T_CREMATION = 39;
    public static final int T_COUNTRY = 40;
    public static final int T_DATA = 41;
    public static final int T_DATE = 42;
    public static final int T_DEATH = 43;
    public static final int T_DESCENDANTS = 44;
    public static final int T_DESCENDANT_INT = 45;
    public static final int T_DESTINATION = 46;
    public static final int T_DIVORCE = 47;
    public static final int T_DIVORCE_FILED = 48;
    public static final int T_PHY_DESCRIPTION = 49;
    public static final int T_EDUCATION = 50;
    public static final int T_EMIGRATION = 51;
    public static final int T_ENDOWMENT = 52;
    public static final int T_ENGAGEMENT = 53;
    public static final int T_EVENT = 54;
    public static final int T_FAMILY = 55;
    public static final int T_FAMILY_CHILD = 56;
    public static final int T_FAMILY_FILE = 57;
    public static final int T_FAMILY_SPOUSE = 58;
    public static final int T_FIRST_COMMUNION = 59;
    public static final int T_FILE = 60;
    public static final int T_FORMAT = 61;
    public static final int T_GEDCOM = 62;
    public static final int T_GIVEN_NAME = 63;
    public static final int T_GRADUATION = 64;
    public static final int T_HEADER = 65;
    public static final int T_HUSBAND = 66;
    public static final int T_IDENT_NUMBER = 67;
    public static final int T_IMMIGRATION = 68;
    public static final int T_INDIVIDUAL = 69;
    public static final int T_LANGUAGE = 70;
    public static final int T_LEGATEE = 71;
    public static final int T_MARRIAGE_BANN = 72;
    public static final int T_MARR_CONTRACT = 73;
    public static final int T_MARR_LICENSE = 74;
    public static final int T_MARRIAGE = 75;
    public static final int T_MARR_SETTLEMENT = 76;
    public static final int T_MEDIA = 77;
    public static final int T_NAME = 78;
    public static final int T_NATIONALITY = 79;
    public static final int T_NATURALIZATION = 80;
    public static final int T_CHILDREN_COUNT = 81;
    public static final int T_NICKNAME = 82;
    public static final int T_MARRIAGE_COUNT = 83;
    public static final int T_NOTE = 84;
    public static final int T_NAME_PREFIX = 85;
    public static final int T_NAME_SUFFIX = 86;
    public static final int T_OBJECT = 87;
    public static final int T_OCCUPATION = 88;
    public static final int T_ORDINANCE = 89;
    public static final int T_ORDINATION = 90;
    public static final int T_PAGE = 91;
    public static final int T_PEDIGREE = 92;
    public static final int T_PHONE = 93;
    public static final int T_PLACE = 94;
    public static final int T_POSTAL_CODE = 95;
    public static final int T_PROBATE = 96;
    public static final int T_PROPERTY = 97;
    public static final int T_PUBLICATION = 98;
    public static final int T_QUALITY_OF_DATA = 99;
    public static final int T_REFERENCE = 100;
    public static final int T_RELATIONSHIP = 101;
    public static final int T_RELIGION = 102;
    public static final int T_REPOSITORY = 103;
    public static final int T_RESIDENCE = 104;
    public static final int T_RESTRICTION = 105;
    public static final int T_RETIREMENT = 106;
    public static final int T_REC_FILE_NUMBER = 107;
    public static final int T_REC_ID_NUMBER = 108;
    public static final int T_ROLE = 109;
    public static final int T_SEX = 110;
    public static final int T_SEALING_CHILD = 111;
    public static final int T_SEALING_SPOUSE = 112;
    public static final int T_SOURCE = 113;
    public static final int T_SURN_PREFIX = 114;
    public static final int T_SOC_SEC_NUMBER = 115;
    public static final int T_STATE = 116;
    public static final int T_STATUS = 117;
    public static final int T_SUBMITTER = 118;
    public static final int T_SUBMISSION = 119;
    public static final int T_SURNAME = 120;
    public static final int T_TEMPLE = 121;
    public static final int T_TEXT = 122;
    public static final int T_TIME = 123;
    public static final int T_TITLE = 124;
    public static final int T_TRAILER = 125;
    public static final int T_TYPE = 126;
    public static final int T_VERSION = 127;
    public static final int T_WIFE = 128;
    public static final int T_WILL = 129;

    private static final GedTableEntry[] GED_TABLE = {
        new GedTableEntry("", "EMPTY STRING", T_NULL, 0, 0),
        new GedTableEntry("ABBR", "ABBREVIATION", T_ABBREVIATION, 0, 0),
        new GedTableEntry("ADDR", "ADDRESS", T_ADDRESS, GedToken.GT_STRING, 0),
        new GedTableEntry("ADR1", "ADDRESS1", T_ADDRESS1, GedToken.GT_STRING, 0),
        new GedTableEntry("ADR2", "ADDRESS2", T_ADDRESS2, GedToken.GT_STRING, 0),
        new GedTableEntry("ADOP", "ADOPTION", T_ADOPTION, 0, 0),
        new GedTableEntry("AFN", "AFN", T_AFN, 0, 0),
        new GedTableEntry("AGE", "AGE", T_AGE, 0, 0),
        new GedTableEntry("AGNC", "AGENCY", T_AGENCY, 0, 0),
        new GedTableEntry("ALIA", "ALIAS", T_ALIAS, 0, 0),
        new GedTableEntry("ANCE", "ANCESTORS", T_ANCESTORS, 0, 0),
        new GedTableEntry("ANCI", "ANCES_INTEREST", T_ANCES_INTEREST, 0, 0),
        new GedTableEntry("ANUL", "ANNULMENT", T_ANNULMENT, 0, 0),
        new GedTableEntry("ASSO", "ASSOCIATES", T_ASSOCIATES, 0, 0),
        new GedTableEntry("AUTH", "AUTHOR", T_AUTHOR, 0, 0),
        new GedTableEntry("BAPL", "BAPTISM-LDS", T_BAPTISM_LDS, 0, 0),
        new GedTableEntry("BAPM", "BAPTISM", T_BAPTISM, 0, 0),
        new GedTableEntry("BARM", "BAR_MITZVAH", T_BAR_MITZVAH, 0, 0),
        new GedTableEntry("BASM", "BAS_MITZVAH", T_BAS_MITZVAH, 0, 0),
        new GedTableEntry("BIRT", "BIRTH", T_BIRTH, 0, 0),
        new GedTableEntry("BLES", "BLESSING", T_BLESSING, 0, 0),
        new GedTableEntry("BLOB", "BINARY_OBJECT", T_BINARY_OBJECT, 0, 0),
        new GedTableEntry("BURI", "BURIAL", T_BURIAL, 0, 0),
        new GedTableEntry("CALN", "CALL_NUMBER", T_CALL_NUMBER, 0, 0),
        new GedTableEntry("CAST", "CASTE", T_CASTE, 0, 0),
        new GedTableEntry("CAUS", "CAUSE", T_CAUSE, 0, 0),
        new GedTableEntry("CENS", "CENSUS", T_CENSUS, 0, 0),
        new GedTableEntry("CHAN", "CHANGE", T_CHANGE, 0, 0),
        new GedTableEntry("CHAR", "CHARACTER", T_CHARACTER, 0, 0),
        new GedTableEntry("CHIL", "CHILD", T_CHILD, GedToken.GT_REF, 0),
        new GedTableEntry("CHR", "CHRISTENING", T_CHRISTENING, 0, 0),
        new GedTableEntry("CHRA", "ADULT_CHRISTENING", T_ADULT_CHRISTENING, 0,
                          0),
        new GedTableEntry("CITY", "CITY", T_CITY, 0, 0),
        new GedTableEntry("CONC", "CONCATENATION", T_CONCATENATION,
                          GedToken.GT_STRING,
                          0),
        new GedTableEntry("CONF", "CONFIRMATION", T_CONFIRMATION, 0, 0),
        new GedTableEntry("CONL", "CONFIRMATION_L", T_CONFIRMATION_L, 0, 0),
        new GedTableEntry("CONT", "CONTINUED", T_CONTINUED, GedToken.GT_STRING,
                          0),
        new GedTableEntry("COPR", "COPYRIGHT", T_COPYRIGHT, GedToken.GT_STRING,
                          0),
        new GedTableEntry("CORP", "CORPORATE", T_CORPORATE, 0, 0),
        new GedTableEntry("CREM", "CREMATION", T_CREMATION, 0, 0),
        new GedTableEntry("CTRY", "COUNTRY", T_COUNTRY, GedToken.GT_STRING, 0),
        new GedTableEntry("DATA", "DATA", T_DATA, 0, 0),
        new GedTableEntry("DATE", "DATE", T_DATE, GedToken.GT_STRING, 0),
        new GedTableEntry("DEAT", "DEATH", T_DEATH, 0, 0),
        new GedTableEntry("DESC", "DESCENDANTS", T_DESCENDANTS, 0, 0),
        new GedTableEntry("DESI", "DESCENDANT_INT", T_DESCENDANT_INT, 0, 0),
        new GedTableEntry("DEST", "DESTINATION", T_DESTINATION, 0, 0),
        new GedTableEntry("DIV", "DIVORCE", T_DIVORCE, 0, 0),
        new GedTableEntry("DIVF", "DIVORCE_FILED", T_DIVORCE_FILED, 0, 0),
        new GedTableEntry("DSCR", "PHY_DESCRIPTION", T_PHY_DESCRIPTION, 0, 0),
        new GedTableEntry("EDUC", "EDUCATION", T_EDUCATION, GedToken.GT_STRING,
                          0),
        new GedTableEntry("EMIG", "EMIGRATION", T_EMIGRATION, 0, 0),
        new GedTableEntry("ENDL", "ENDOWMENT", T_ENDOWMENT, 0, 0),
        new GedTableEntry("ENGA", "ENGAGEMENT", T_ENGAGEMENT, 0, 0),
        new GedTableEntry("EVEN", "EVENT", T_EVENT, 0, 0),
        new GedTableEntry("FAM", "FAMILY", T_FAMILY, GedToken.GT_REF, 0),
        new GedTableEntry("FAMC", "FAMILY_CHILD", T_FAMILY_CHILD,
                          GedToken.GT_REF,
                          0),
        new GedTableEntry("FAMF", "FAMILY_FILE", T_FAMILY_FILE, 0, 0),
        new GedTableEntry("FAMS", "FAMILY_SPOUSE", T_FAMILY_SPOUSE,
                          GedToken.GT_REF,
                          0),
        new GedTableEntry("FCOM", "FIRST_COMMUNION", T_FIRST_COMMUNION, 0, 0),
        new GedTableEntry("FILE", "FILE", T_FILE, GedToken.GT_STRING, 0),
        new GedTableEntry("FORM", "FORMAT", T_FORMAT, GedToken.GT_STRING, 0),
        new GedTableEntry("GEDC", "GEDCOM", T_GEDCOM, 0, 0),
        new GedTableEntry("GIVN", "GIVEN_NAME", T_GIVEN_NAME, 0, 0),
        new GedTableEntry("GRAD", "GRADUATION", T_GRADUATION, 0, 0),
        new GedTableEntry("HEAD", "HEADER", T_HEADER, 0, 0),
        new GedTableEntry("HUSB", "HUSBAND", T_HUSBAND, GedToken.GT_REF, 0),
        new GedTableEntry("IDNO", "IDENT_NUMBER", T_IDENT_NUMBER, 0, 0),
        new GedTableEntry("IMMI", "IMMIGRATION", T_IMMIGRATION, 0, 0),
        new GedTableEntry("INDI", "INDIVIDUAL", T_INDIVIDUAL, 0, 0),
        new GedTableEntry("LANG", "LANGUAGE", T_LANGUAGE, 0, 0),
        new GedTableEntry("LEGA", "LEGATEE", T_LEGATEE, 0, 0),
        new GedTableEntry("MARB", "MARRIAGE_BANN", T_MARRIAGE_BANN, 0, 0),
        new GedTableEntry("MARC", "MARR_CONTRACT", T_MARR_CONTRACT, 0, 0),
        new GedTableEntry("MARL", "MARR_LICENSE", T_MARR_LICENSE, 0, 0),
        new GedTableEntry("MARR", "MARRIAGE", T_MARRIAGE, 0, 0),
        new GedTableEntry("MARS", "MARR-_SETTLEMENT", T_MARR_SETTLEMENT, 0, 0),
        new GedTableEntry("MEDI", "MEDIA", T_MEDIA, 0, 0),
        new GedTableEntry("NAME", "NAME", T_NAME, 0, 0),
        new GedTableEntry("NATI", "NATIONALITY", T_NATIONALITY, 0, 0),
        new GedTableEntry("NATU", "NATURALIZATION", T_NATURALIZATION, 0, 0),
        new GedTableEntry("NCHI", "CHILDREN_COUNT", T_CHILDREN_COUNT, 0, 0),
        new GedTableEntry("NICK", "NICKNAME", T_NICKNAME, 0, 0),
        new GedTableEntry("NMR", "MARRIAGE_COUNT", T_MARRIAGE_COUNT, 0, 0),
        new GedTableEntry("NOTE", "NOTE", T_NOTE, GedToken.GT_STRING, 0),
        new GedTableEntry("NPFX", "NAME_PREFIX", T_NAME_PREFIX, 0, 0),
        new GedTableEntry("NSFX", "NAME_SUFFIX", T_NAME_SUFFIX, 0, 0),
        new GedTableEntry("OBJE", "OBJECT", T_OBJECT, 0, 0),
        new GedTableEntry("OCCU", "OCCUPATION", T_OCCUPATION,
                          GedToken.GT_STRING, 0),
        new GedTableEntry("ORDI", "ORDINANCE", T_ORDINANCE, 0, 0),
        new GedTableEntry("ORDN", "ORDINATION", T_ORDINATION, 0, 0),
        new GedTableEntry("PAGE", "PAGE", T_PAGE, 0, 0),
        new GedTableEntry("PEDI", "PEDIGREE", T_PEDIGREE, 0, 0),
        new GedTableEntry("PHON", "PHONE", T_PHONE, GedToken.GT_STRING, 0),
        new GedTableEntry("PLAC", "PLACE", T_PLACE, GedToken.GT_STRING, 0),
        new GedTableEntry("POST", "POSTAL_CODE", T_POSTAL_CODE,
                          GedToken.GT_STRING, 0),
        new GedTableEntry("PROB", "PROBATE", T_PROBATE, 0, 0),
        new GedTableEntry("PROP", "PROPERTY", T_PROPERTY, 0, 0),
        new GedTableEntry("PUBL", "PUBLICATION", T_PUBLICATION, 0, 0),
        new GedTableEntry("QUAY", "QUALITY_OF_DATA", T_QUALITY_OF_DATA, 0, 0),
        new GedTableEntry("REFN", "REFERENCE", T_REFERENCE, 0, 0),
        new GedTableEntry("RELA", "RELATIONSHIP", T_RELATIONSHIP, 0, 0),
        new GedTableEntry("RELI", "RELIGION", T_RELIGION, 0, 0),
        new GedTableEntry("REPO", "REPOSITORY", T_REPOSITORY, 0, 0),
        new GedTableEntry("RESI", "RESIDENCE", T_RESIDENCE, 0, 0),
        new GedTableEntry("RESN", "RESTRICTION", T_RESTRICTION, 0, 0),
        new GedTableEntry("RETI", "RETIREMENT", T_RETIREMENT, 0, 0),
        new GedTableEntry("RFN", "REC_FILE_NUMBER", T_REC_FILE_NUMBER, 0, 0),
        new GedTableEntry("RIN", "REC_ID_NUMBER", T_REC_ID_NUMBER, 0, 0),
        new GedTableEntry("ROLE", "ROLE", T_ROLE, 0, 0),
        new GedTableEntry("SEX", "SEX", T_SEX, GedToken.GT_STRING, 0),
        new GedTableEntry("SLGC", "SEALING_CHILD", T_SEALING_CHILD, 0, 0),
        new GedTableEntry("SLGS", "SEALING_SPOUSE", T_SEALING_SPOUSE, 0, 0),
        new GedTableEntry("SOUR", "SOURCE", T_SOURCE, GedToken.GT_STRING, 0),
        new GedTableEntry("SPFX", "SURN_PREFIX", T_SURN_PREFIX, 0, 0),
        new GedTableEntry("SSN", "SOC_SEC_NUMBER", T_SOC_SEC_NUMBER, 0, 0),
        new GedTableEntry("STAE", "STATE", T_STATE, 0, 0),
        new GedTableEntry("STAT", "STATUS", T_STATUS, 0, 0),
        new GedTableEntry("SUBM", "SUBMITTER", T_SUBMITTER, GedToken.GT_STRING,
                          0),
        new GedTableEntry("SUBN", "SUBMISSION", T_SUBMISSION, 0, 0),
        new GedTableEntry("SURN", "SURNAME", T_SURNAME, 0, 0),
        new GedTableEntry("TEMP", "TEMPLE", T_TEMPLE, 0, 0),
        new GedTableEntry("TEXT", "TEXT", T_TEXT, 0, 0),
        new GedTableEntry("TIME", "TIME", T_TIME, 0, 0),
        new GedTableEntry("TITL", "TITLE", T_TITLE, 0, 0),
        new GedTableEntry("TRLR", "TRAILER", T_TRAILER, 0, 0),
        new GedTableEntry("TYPE", "TYPE", T_TYPE, 0, 0),
        new GedTableEntry("VERS", "VERSION", T_VERSION, 0, 0),
        new GedTableEntry("WIFE", "WIFE", T_WIFE, GedToken.GT_REF, 0),
        new GedTableEntry("WILL", "WILL", T_WILL, 0, 0),
        new GedTableEntry(null, null, T_NULL, 0, 0)
    };

    // Constructor
    GedTag() {

    }

    public static int tag_lookup(String tag_string) {
        int i = 0;
        do {
            if (tag_string.equals(GED_TABLE[i].m_tag_string)) {
                return GED_TABLE[i].m_tag;
            }
            i++;
        }
        while (GED_TABLE[i].m_tag_string != null);
        return T_BAD_TAG;
    }

    public static int nextStreamToken(int gt){
        return GED_TABLE[gt].m_next_token;
    }
}

class GedTableEntry {
    String m_tag_string;
    String m_tag_comment;
    int m_tag;
    int m_next_token;
    int m_next_line;

    // constructor
    GedTableEntry(String ts,
                  String tc,
                  int t,
                  int nt,
                  int nl) {
        m_tag_string = ts;
        m_tag_comment = tc;
        m_tag = t;
        m_next_token = nt;
        m_next_line = nl;
    }
}