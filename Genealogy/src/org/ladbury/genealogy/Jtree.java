//******************************************************************************
 // Jtree.java:	Applet
 //
 //******************************************************************************
  package org.ladbury.genealogy;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.UIManager;

public class Jtree
    extends Applet
    implements Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread m_Jtree = null; //Thread object for the applet
    public static final int IDLE = 0;
    public static final int PROCESS_FILE = 1;
    public static final int STOP = 2;
    public static final int MAX_STATE = 2;
    private boolean packFrame = false;

    private int m_state = IDLE;
    public JtreeFrame frame = null;

    // STANDALONE APPLICATION SUPPORT:
    //	m_fStandAlone will be set to true if applet is run standalone
    //--------------------------------------------------------------------------
    private boolean m_fStandAlone = true;
    public static Jtree m_main = null;
    public static JtreeFrame m_frame = null;
    String m_display_string = null;
    GedPeople m_population = null;

    // PARAMETER SUPPORT:
    //Parameters allow an HTML author to pass information to the applet;
    // the HTML author specifies them using the <PARAM> tag within the <APPLET>
    // tag.  The following variables are used to store the values of the
    // parameters.
    //--------------------------------------------------------------------------
    // Members for applet parameters
    // <type>       <MemberVar>    = <Default Value>
    //--------------------------------------------------------------------------
    private String m_gedfile = "";

    // Parameter names.  To change a name of a parameter, you need only make
    // a single change.  Simply modify the value of the parameter string below.
    //--------------------------------------------------------------------------
    private final String PARAM_gedfile = "gedfile";

    // STANDALONE APPLICATION SUPPORT
    // The GetParameter() method is a replacement for the getParameter() method
    // defined by Applet. This method returns the value of the specified parameter;
    // unlike the original getParameter() method, this method works when the applet
    // is run as a standalone application, as well as when run within an HTML page.
    // This method is called by GetParameters().
    //---------------------------------------------------------------------------
    String GetParameter(String strName, String args[]) {
        if (args == null) {
            // Running within an HTML page, so call original getParameter().
            //-------------------------------------------------------------------
            return getParameter(strName);
        }

        // Running as standalone application, so parameter values are obtained from
        // the command line. The user specifies them as follows:
        //
        //	JView Jtree param1=<val> param2=<"val with spaces"> ...
        //-----------------------------------------------------------------------
        int i;
        String strArg = strName + "=";
        String strValue = null;
        int nLength = strArg.length();

        try {
            for (i = 0; i < args.length; i++) {
                String strParam = args[i].substring(0, nLength);

                if (strArg.equalsIgnoreCase(strParam)) {
                    // Found matching parameter on command line, so extract its value.
                    // If in double quotes, remove the quotes.
                    //---------------------------------------------------------------
                    strValue = args[i].substring(nLength);
                    if (strValue.startsWith("\"")) {
                        strValue = strValue.substring(1);
                        if (strValue.endsWith("\"")) {
                            strValue = strValue.substring(0,
                                strValue.length() - 1);
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return strValue;
    }

    // STANDALONE APPLICATION SUPPORT
    // 	The GetParameters() method retrieves the values of each of the applet's
    // parameters and stores them in variables. This method works both when the
    // applet is run as a standalone application and when it's run within an HTML
    // page.  When the applet is run as a standalone application, this method is
    // called by the main() method, which passes it the command-line arguments.
    // When the applet is run within an HTML page, this method is called by the
    // init() method with args == null.
    //---------------------------------------------------------------------------
    void GetParameters(String args[]) {
        // Query values of all Parameters
        //--------------------------------------------------------------
        String param;

        // gedfile: Parameter description
        //--------------------------------------------------------------
        param = GetParameter(PARAM_gedfile, args);
        if (param != null) {
            m_gedfile = param;

        }
    }

    // STANDALONE APPLICATION SUPPORT
    // 	The main() method acts as the applet's entry point when it is run
    // as a standalone application. It is ignored if the applet is run from
    // within an HTML page.
    //----------------------------------------------------------------------
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Jtree applet_Jtree = new Jtree();
        m_main = applet_Jtree;
        //frame.add("Center", applet_Jtree);
        applet_Jtree.m_fStandAlone = true;
        applet_Jtree.GetParameters(args);
        applet_Jtree.init();
        applet_Jtree.start();
    }

    // Jtree Class Constructor
    //----------------------------------------------------------------------
    public Jtree() {
        frame = new JtreeFrame("Graham's Genealogy program");

        //Pack frames that have useful preferred size info, e.g. from their layout
        //Validate frames that have preset sizes
        if (packFrame) {
            frame.pack();
        }
        else {
            frame.validate();

            // Centre the frame
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation( (screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);

        m_display_string = new String("no line");
        init();
    }

    // APPLET INFO SUPPORT:
    //		The getAppletInfo() method returns a string describing the applet's
    // author, copyright date, or miscellaneous information.
    //--------------------------------------------------------------------------
    public String getAppletInfo() {
        return "Name: Jtree\r\n" +
            "Author: G.J.Wood Copyright 1997\r\n" +
            "Created with Microsoft Visual J++ Version 1.1";
    }

    // PARAMETER SUPPORT
    //		The getParameterInfo() method returns an array of strings describing
    // the parameters understood by this applet.
    //
    // Jtree Parameter Information:
    //  { "Name", "Type", "Description" },
    //--------------------------------------------------------------------------
    public String[][] getParameterInfo() {
        String[][] info = {
            {
            PARAM_gedfile, "String",
            "The name of the input field in GED format"}
            ,
        };
        return info;
    }

    // The init() method is called by the AWT when an applet is first loaded or
    // reloaded.  Override this method to perform whatever initialisation your
    // applet needs, such as initialising data structures, loading images or
    // fonts, creating frame windows, setting the layout manager, or adding UI
    // components.
    //--------------------------------------------------------------------------
    public void init() {
        if (!m_fStandAlone) {
            GetParameters(null);
        }
        m_population = new GedPeople(5000);
        // If you use a ResourceWizard-generated "control creator" class to
        // arrange controls in your applet, you may want to call its
        // CreateControls() method from within this method. Remove the following
        // call to resize() before adding the call to CreateControls();
        // CreateControls() does its own resizing.
        //----------------------------------------------------------------------
        //resize(320, 240);

        // TODO: Place additional initialisation code here
    }

    // Place additional applet clean up code here.  destroy() is called when
    // when you applet is terminating and being unloaded.
    //-------------------------------------------------------------------------
    public void destroy() {
        // TODO: Place applet cleanup code here
    }

    // Jtree Paint Handler
    //--------------------------------------------------------------------------
    public void paint(Graphics g) {
        // TODO: Place applet paint code here
        g.drawString("Running: " + Math.random(), 10, 20);
        g.drawString("Filename: " + m_frame.mGetFilename(), 10, 40);
        g.drawString("Directory name: " + m_frame.m_dirname, 10, 60);
        g.drawString("Filestate: " + m_frame.m_ged.state(), 10, 80);
        g.drawString(m_display_string, 10, 90);
    }

    //		The start() method is called when the page containing the applet
    // first appears on the screen. The AppletWizard's initial implementation
    // of this method starts execution of the applet's thread.
    //--------------------------------------------------------------------------
    public void start() {
        if (m_Jtree == null) {
            m_Jtree = new Thread(this);
            m_Jtree.start();
        }
        // TODO: Place additional applet start code here
    }

    //		The stop() method is called when the page containing the applet is
    // no longer on the screen. The AppletWizard's initial implementation of
    // this method stops execution of the applet's thread.
    //--------------------------------------------------------------------------
    public void stop() {
        if (m_Jtree != null) {
            this.change_state(STOP);
            //m_Jtree = null;
        }

        // TODO: Place additional applet stop code here
    }

    // THREAD SUPPORT
    //		The run() method is called when the applet's thread is started. If
    // your applet performs any ongoing activities without waiting for user
    // input, the code for implementing that behavior typically goes here. For
    // example, for an applet that performs animation, the run() method controls
    // the display of images.
    //--------------------------------------------------------------------------
    public void run() {
        while (this.get_state() != STOP) {
            try {
                switch (this.get_state()) {
                    case IDLE:
                        this.frame.displayLog(".");
                        repaint();
                        Thread.sleep(1000);
                        break;
                    case PROCESS_FILE:
                        this.frame.displayLog("\n\rRun Processing file\n\r");
                        this.frame.m_ged.process_file();

                        //Thread.sleep (5000);
                        repaint();
                        this.change_state(IDLE);
                        break;
                    default:
                        repaint();
                        Thread.sleep(1000);
                }

            }
            catch (InterruptedException e) {
                // TODO: Place exception-handling code here in case an
                //       InterruptedException is thrown by Thread.sleep(),
                //		 meaning that another thread has interrupted this one
                this.frame.displayLog("!");
                //e.printStackTrace();

            }
        }
    }

    // MOUSE SUPPORT:
    //		The mouseDown() method is called if the mouse button is pressed
    // while the mouse cursor is over the applet's portion of the screen.
    //--------------------------------------------------------------------------
    public boolean mouseDown(Event evt, int x, int y) {
        // TODO: Place applet mouseDown code here
        return true;
    }

    // MOUSE SUPPORT:
    //		The mouseUp() method is called if the mouse button is released
    // while the mouse cursor is over the applet's portion of the screen.
    //--------------------------------------------------------------------------
    public boolean mouseUp(Event evt, int x, int y) {
        // TODO: Place applet mouseUp code here
        return true;
    }

    public synchronized void change_state(int new_state) {
        if (new_state >= IDLE & new_state <= MAX_STATE) {
            m_state = new_state;
            m_Jtree.interrupt();
        }
    }

    public synchronized int get_state() {
        return (m_state);
    }

    public void display(String s) {
        m_display_string = new String(s);
        System.out.println(m_display_string);
        repaint();
    }
}
