package org.ladbury.genealogy;

import java.util.Date;

public class GenDate
    extends Date
// extended to cope with dates before 1900
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public final String MODIFIER_BEFORE = "BEF";
    static public final String MODIFIER_FROM = "FROM";
    static public final String MODIFIER_TO = "TO";
    static public final String MODIFIER_ABOUT = "ABT";
    static public final String MODIFIER_BETWEEN = "BET";
    static public final String MODIFIER_AFTER = "AFT";
    static public final String MODIFIER_CALCULATED = "CAL";
    static public final String MODIFIER_ESTIMATED = "EST";

    public static final String UNDEFINED_DATE = "Undefined Date";
    public static final String INVALID_DATE = "Invalid Date";

    static public final String MONTH_JANUARY = "JAN";
    static public final String MONTH_FEBRUARY = "FEB";
    static public final String MONTH_MARCH = "MAR";
    static public final String MONTH_APRIL = "APR";
    static public final String MONTH_MAY = "MAY";
    static public final String MONTH_JUNE = "JUN";
    static public final String MONTH_JULY = "JUL";
    static public final String MONTH_AUGUST = "AUG";
    static public final String MONTH_SEPTEMBER = "SEP";
    static public final String MONTH_OCTOBER = "OCT";
    static public final String MONTH_NOVEMBER = "NOV";
    static public final String MONTH_DECEMBER = "DEC";

    static private final String[] MONTHS = {
        MONTH_JANUARY,
        MONTH_FEBRUARY,
        MONTH_MARCH,
        MONTH_APRIL,
        MONTH_MAY,
        MONTH_JUNE,
        MONTH_JULY,
        MONTH_AUGUST,
        MONTH_SEPTEMBER,
        MONTH_OCTOBER,
        MONTH_NOVEMBER,
        MONTH_DECEMBER
    };

    private String m_date_string = null;
    private String m_year_string = null;
    private String m_month_string = null;
    private String m_day_string = null;
    private String m_modifier_string = null;
    private int m_year = 0;
    private int m_month = 0;
    private int m_day = 0;
    private boolean m_valid_date = false;

    public GenDate(String sdate) {
        this.clear();
        m_date_string = new String(sdate);
        // parse the string into the other field
    }

    public GenDate() {
        this.clear();
        m_date_string = new String(GenDate.UNDEFINED_DATE);
    }

    public void clear() {
        this.m_date_string = null;
        this.m_year_string = null;
        this.m_month_string = null;
        this.m_day_string = null;
        this.m_modifier_string = null;
        this.m_year = 0;
        this.m_month = 0;
        this.m_day = 0;
        this.m_valid_date = false;
    }
    public String getGenDate(){
    	return this.m_day_string +this.m_month_string+ this.m_year_string;
    }

    private int LookUpMonthNumber(String month) {
        int i;
        for (i = 0; i < 12; i++) {
            if (MONTHS[i].equalsIgnoreCase(month)) {
                return i+1;
            }
        }
        return 0;
    }

    private String LookUpMonth(int monthNum) {
        if ( (monthNum > 0) && (monthNum < 13)) {
            return new String(MONTHS[monthNum - 1]);
        }
        return "";
    }

}