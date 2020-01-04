/*
 * Copyright (c) 2008 by Intevation GmbH
 * Authors:
 * Bernhard Herzog <bh@intevation.de>
 *
 * This program is free software under the LGPL (>=v2.1)
 * Read the file LGPL.txt coming with the software for details.
 */

package pt.com.santos.util.gui.swing;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Provides methods to show error messages and stack traces
 */
public class ErrorDialog {

    public static final String error_occurred = "An error occurred";
    public static final String show_details = "Show Details...";
    public static final String error_details_title = "Error Details";

    /**
     * Shows an error dialog with the given message.  If a throwable is
     * given a button is added that opens another dialog with a stack
     * trace.  If message is null and a throwable is given, the message
     * is taken from the throwable.  The parent and title are passed
     * through to {@code JOptionPane.showMessageDialog}.
     *
     * @param parent the parent of the dialog
     * @param title the title of the dialog
     * @param message the error message
     * @param throwable the throwable that led to the error, if any
     */
    public static void showErrorDialog( JComponent parent, String title,
                                        String message, Throwable throwable ) {
        JOptionPane.showMessageDialog( parent,
                                       createMessageObject( message,
                                                            throwable ),
                                       title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Creates the label and button widgets for the error dialog.
     *
     * @param message the error message
     * @param throwable the throwable that led to the error, if any
     * @return a Component containing the widgets
     */
    protected static Component createMessageObject(String message,
                                                   final Throwable throwable ) {
        final Box box =  new Box( BoxLayout.Y_AXIS );
        if ( message != null ) {
            box.add( new JLabel( message ) );
        }
        if ( throwable != null ) {
            String throwable_message = throwable.getLocalizedMessage();
            if ( throwable_message == null && message == null ) {
                throwable_message =
                    error_occurred;
            }
            box.add( new JLabel( throwable_message ) );
            final JButton detailsButton =
                new JButton( show_details );
            detailsButton.addActionListener( new ActionListener() {
                    public void actionPerformed( ActionEvent ae ) {
                        showStackTrace( box, throwable );
                    }
                });
            box.add( detailsButton );
        }

        return box;
    }

    /**
     * Shows a dialog with the stack trace from a throwable.
     *
     * @param parent the parent of the window
     * @param throwable the throwable whose stack trace is to be shown
     */
    public static void showStackTrace( JComponent parent,
                                       Throwable throwable ) {
        JTextArea detailsText = new JTextArea( formatThrowable( throwable ) );
        JScrollPane scroll = new JScrollPane( detailsText );
        scroll.setPreferredSize( new Dimension( 400, 300 ) );

        JOptionPane.showMessageDialog( parent, scroll,
                      error_details_title,
                                       JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Returns the stack trace of the throwable as a string.
     *
     * @param throwable a throwable
     * @return the formatted stack trace of the throwable
     */
    protected static String formatThrowable( Throwable throwable ) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace( new PrintWriter( writer ) );
        return writer.toString();
    }
}