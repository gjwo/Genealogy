package org.ladbury.genealogy;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class JtreeFrame
    extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// constants for menu text
    static final String FILE_MENU_NAME = "File";
    static final String FILE_MENU_ITEM_NEW = "New";
    static final String FILE_MENU_ITEM_OPEN = "Open";
    static final String FILE_MENU_ITEM_CLOSE = "Close";
    static final String FILE_MENU_ITEM_SAVE = "Save";
    static final String FILE_MENU_ITEM_EXIT = "Exit";

    static final String EDIT_MENU_NAME = "Edit";
    static final String EDIT_MENU_ITEM_CUT = "Cut";
    static final String EDIT_MENU_ITEM_COPY = "Copy";
    static final String EDIT_MENU_ITEM_PASTE = "Paste";

    static final String HELP_MENU_NAME = "Help";
    static final String HELP_MENU_ITEM_CONTENTS = "Contents";
    static final String HELP_MENU_ITEM_SEARCH = "Search for help on";
    static final String HELP_MENU_ITEM_ABOUT = "About..";

    // variables
    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    JMenuBar jMenuBar1 = new JMenuBar();

    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileNew = new JMenuItem();
    JMenuItem jMenuFileOpen = new JMenuItem();
    JMenuItem jMenuFileClose = new JMenuItem();
    JMenuItem jMenuFileSave = new JMenuItem();
    JMenuItem jMenuFileExit = new JMenuItem();

    JMenu jMenuEdit = new JMenu();
    JMenuItem jMenuEditCut = new JMenuItem();
    JMenuItem jMenuEditCopy = new JMenuItem();
    JMenuItem jMenuEditPaste = new JMenuItem();

    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();

    TextArea textArea1 = new TextArea();

    FileDialog m_filediag1 = null;
    String m_filename,
        m_dirname,
        m_windowtitle = null;

    GedFile m_ged = null;
    MenuBar m_mb1 = null;
    File m_dir = null;
    Menu m_filemenu,
        m_editmenu,
        m_helpmenu = null;

    // Construct the frame
    public JtreeFrame(String str) {
        super(str);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            m_windowtitle = str;
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Component initialization
    private void jbInit() throws Exception {
        // Create a content pane
        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(600, 400));
        this.setTitle(m_windowtitle);
        // add a menu bar
        this.setJMenuBar(jMenuBar1);

        // variable for file
        m_ged = new GedFile();
        m_filename = new String();
        m_dirname = new String();

        // initialise the menus
        // File menu
        jMenuFile.setText(FILE_MENU_NAME);
        jMenuFileNew.setText(FILE_MENU_ITEM_NEW);
        jMenuFileOpen.setText(FILE_MENU_ITEM_OPEN);
        jMenuFileClose.setText(FILE_MENU_ITEM_CLOSE);
        jMenuFileSave.setText(FILE_MENU_ITEM_SAVE);
        jMenuFileExit.setText(FILE_MENU_ITEM_EXIT);
        jMenuFile.add(jMenuFileNew);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileOpen);
        jMenuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileOpen_actionPerformed(e);
            }
        });
        jMenuFile.add(jMenuFileClose);
        jMenuFile.add(jMenuFileSave);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuFileExit);
        jMenuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileExit_actionPerformed(e);
            }
        });

        // Edit Menu
        jMenuEdit.setText(EDIT_MENU_NAME);
        jMenuEditCut.setText(EDIT_MENU_ITEM_CUT);
        jMenuEditCopy.setText(EDIT_MENU_ITEM_COPY);
        jMenuEditPaste.setText(EDIT_MENU_ITEM_PASTE);
        jMenuEdit.add(jMenuEditCut);
        jMenuEdit.add(jMenuEditCopy);
        jMenuEdit.add(jMenuEditPaste);

        // Help Menu
        jMenuHelp.setText(HELP_MENU_NAME);
        jMenuHelpAbout.setText(HELP_MENU_ITEM_ABOUT);
        jMenuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
        jMenuHelp.add(jMenuHelpAbout);

        // add text area
        textArea1.setBackground(Color.pink);
        textArea1.setColumns(25);
        textArea1.setCursor(null);
        textArea1.setEditable(false);
        //textArea1.setFont(new java.awt.Font("DialogInput", 2, 10));
        textArea1.setRows(10);
        textArea1.setText("JTree System Messages\n");

        // add the menus to the menu bar
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenuEdit);
        jMenuBar1.add(jMenuHelp);
        contentPane.add(textArea1, BorderLayout.CENTER);
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    //File | Open action performed
    public void jMenuFileOpen_actionPerformed(ActionEvent e) {
        //FilenameFilter m_filter = "*.ged";
        // create a file dialogue
        m_filediag1 = new FileDialog(this, "Open GEDCOM (.ged) file");
        //m_filediag1.setFilenameFilter((FilenameFilter)"*.GED");
        m_filediag1.setDirectory("c:/");
        m_filediag1.setVisible(true);
        // File Dialogue is modal so won't return unless file or cancel
        m_filename = m_filediag1.getFile();
        m_dirname = m_filediag1.getDirectory();
        if (! (m_filename == null | m_dirname == null)) {
            m_ged.open(m_dirname, m_filename);
            Jtree.m_main.frame.displayLog("back from open\n");
            Jtree.m_main.change_state(Jtree.PROCESS_FILE);
        }
    }

    //File | Exit action performed
    public void jMenuFileExit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //Help About action performed
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        AboutBox dlg = new AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height -
                         dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setVisible(true);
    }

    public String mGetFilename() {
        return (this.m_filename);
    }

    public void displayLog(String str) {
        textArea1.append(str);
        repaint();
    }

    public void displayLog(int i) {
        Integer intWrapper = new Integer(i);
        textArea1.append(intWrapper.toString());
        repaint();
    }
}
