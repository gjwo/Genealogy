package org.ladbury.genealogy;

/**
 * <p>Title: JTree</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author G.J.Wood
 * @version 1.0
 */
import java.util.Vector;

public class ErrorHandler extends Vector {
class ErrorTuple{
        int m_error_number = 0;
        String m_error_string = null;
        String m_error_location = null;

        ErrorTuple(int errno, String errtype, String errloc){
           m_error_number = errno;
           m_error_string = new String(errtype);
           m_error_location = new String(errloc);
        }

    }

    //Constructor
    ErrorHandler(){
        super(0,1); //no errors initially
        this.add(ErrorTuple(1, "error handler problem", "constructor"));
    }

	private Object ErrorTuple(int i, String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

}