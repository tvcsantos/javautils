package pt.com.santos.util.io;

import junit.framework.TestCase;

public class MD5ChecksumTestCase extends TestCase {
    
    public MD5ChecksumTestCase(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testMD5Checksum() {
        byte[] byteArray = {(byte) 255, (byte) 254, (byte) 253, (byte) 252,
            (byte) 251, (byte) 250};
        String checkSum = MD5Checksum.getHexString(byteArray);
        assertEquals("FFFEFDFCFBFA", checkSum);
    }
}
