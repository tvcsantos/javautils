package pt.com.santos.util;

import java.io.File;
import pt.com.santos.util.BDRipScanner.BDFilePropertiesMovie;

public class BDRipScannerTest {
    public static void main(String[] args) {
        BDRipScanner sc = new BDRipScanner();
        BDFilePropertiesMovie bd =
                sc.executeGetBDInfo(new File("E:/Movies/Tinkerbell2_Cee"));
        System.out.println();
    }
}
