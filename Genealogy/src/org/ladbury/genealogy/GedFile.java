package org.ladbury.genealogy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Vector;

//
//
// GedFile
//
//
public class GedFile
    extends Vector<Object> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String m_ged_path_name;
    private String m_ged_file_name;
    private File m_ged_file;
    private FileInputStream m_in;
    private BufferedReader m_br;
    private StreamTokenizer mStreamTokens;

    public void Gedfile() {
    	/*
    	 *  Constructor
    	 */
    	m_ged_path_name = null;
        m_ged_file_name = null;
        m_ged_file = null;
        m_in = null;
        m_br = null;
        mStreamTokens = null;
    }

    public void open(String path, String filename)
    //throws IOException
    // opens a file as a buffered, tokenised stream
    {
        try {
            Jtree.m_main.frame.displayLog("\n\rOpening file " + path +
                                          filename + "\n\r");
            this.m_ged_path_name = new String(path);
            this.m_ged_file_name = new String(filename);
            this.m_ged_file = new File(path, filename);
            this.m_in = new FileInputStream(m_ged_file);
            this.m_br = new BufferedReader(new InputStreamReader(m_in));
            mStreamTokens = new StreamTokenizer(m_br);
            // initialise behaviour of tokenizer
            this.mStreamTokens.eolIsSignificant(true);
            this.mStreamTokens.parseNumbers();
            this.mStreamTokens.slashSlashComments(false);
            this.mStreamTokens.slashStarComments(false);
            this.mStreamTokens.ordinaryChar('/');
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            Jtree.m_main.frame.displayLog(ioe.toString());
            //throw ioe;
        }
    }

    public void close()
    //throws IOException
    {
        try {
            mStreamTokens = null;
            m_br = null;
            m_in.close();
            m_in = null;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.toString());
            //throw ioe;
        }
    }

    public String state() {
        if (m_ged_file == null) {
            return ("Closed");
        }
        else {
            return ("open");
        }
    }

    //
    // process_file
    //
    // to turn each line of input tokens into Ged tokens
    // then to group these into Ged records for parsing
    //
    public void process_file() {
        StringBuffer line_buf = null;
        GedRecord ged_record = null;
        GedLine ged_line = null;
        @SuppressWarnings("unused")
		boolean line_parsed_ok,
            record_parsed_ok,
            line_added_to_record_ok = true;

        try {
            Jtree.m_main.frame.displayLog("Processing file\n\r");
            mStreamTokens.nextToken();
            ged_record = new GedRecord();
            while (mStreamTokens.ttype != StreamTokenizer.TT_EOF) { // process a files worth of records

                do { // process a records worth of lines
                    //create an empty line buffer
                    line_buf = new StringBuffer();
                    line_buf.append(mStreamTokens.lineno());
                    line_buf.append(": ");

                    ged_line = new GedLine();
                    ged_line.setLine(mStreamTokens.lineno());
                    //Jtree.m_main.frame.displayLog("+"); // show activity

                    do { // process a lines worth of tokens
                        // build the data into the line buffer as appropriate
                        switch (mStreamTokens.ttype) {
                            case StreamTokenizer.TT_NUMBER:
                                line_buf.append(mStreamTokens.nval);
                                ged_line.add(new GedToken( (int) mStreamTokens.nval));
                                break;

                            case StreamTokenizer.TT_WORD:
                                line_buf.append("<" + mStreamTokens.sval + ">");
                                ged_line.add(new GedToken(mStreamTokens.sval));
                                break;

                            case StreamTokenizer.TT_EOF:

                                //System.out.println("EOF token read: "+mStreamTokens.TT_EOF);
                                break;

                            case StreamTokenizer.TT_EOL:

                                //System.out.println("EOL token read: "+mStreamTokens.TT_EOL);
                                break;
                            default:
                                ged_line.add(new GedToken( (char) mStreamTokens.
                                    ttype)); // not sure if this is a valid type
                                //System.out.print("Ordinary token read {");
                                //System.out.print(mStreamTokens.ttype+",'"+ (char)mStreamTokens.ttype+"'}" );

                        }
                        mStreamTokens.nextToken();

                    }
                    while ( (mStreamTokens.ttype != StreamTokenizer.TT_EOF) &
                           (mStreamTokens.ttype != StreamTokenizer.TT_EOL));

                    // finish up processing the line
                    // Jtree.m_main.display(line_buf.toString());
                    line_parsed_ok = ged_line.parse();  //why no test result!
                    line_added_to_record_ok = ged_record.add(ged_line);

                }
                while (line_added_to_record_ok &
                       (mStreamTokens.ttype != StreamTokenizer.TT_EOF));

                // finish up processing record
                record_parsed_ok = ged_record.parse(); //why no test result!
                Jtree.m_main.frame.displayLog(ged_record.m_reference); // show activity
                Jtree.m_main.frame.displayLog("\r");
                this.addElement(ged_record);

                // get ready for the next one
                ged_record = new GedRecord();
                // current line was not added to last record
                line_added_to_record_ok = ged_record.add(ged_line); //why no test result!
                System.gc(); // kick off the garbage collector (could be 20,000+ lines in file
            }
            // finished as EOF reached
            this.close();
            System.gc(); // kick off the garbage collector
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.toString());
            //throw ioe;
        }
    }
}