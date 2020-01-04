/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.com.santos.util.gui.swing;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author tvcsa
 */
public class ErrorHandlerTest {
    
    // A test program to demonstrate the class
    public static class Test {
	public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ErrorHandler.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ErrorHandler.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ErrorHandler.class.getName()).log(
                        Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ErrorHandler.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
	    String url = (args.length > 0)?args[0]:null;
	    try { foo(); }
	    catch(Throwable e) {
		ErrorHandler.displayThrowable(e, "Fatal Error", url, null);
		System.exit(1);
	    }
	}
	// These methods purposely throw an exception
	public static void foo() { bar(null); }
	public static void bar(Object o) {
	    try { blah(o); }
	    catch(NullPointerException e) {
		// Catch the null pointer exception and throw a new exception
		// that has the NPE specified as its cause.
		throw (IllegalArgumentException)
		    new IllegalArgumentException("null argument", e);
	    }
	}
	public static void blah(Object o) {
	    Class c = o.getClass();  // throws NPE if o is null
	}
    }
}
