package pt.com.santos.util.net;

import java.net.UnknownHostException;
import junit.framework.TestCase;

public class NetTestCase extends TestCase {
    
    public NetTestCase(String testName) {
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
    
    public void testgetFirstIP() {
        String firstIP = Net.getFirstIP();
        System.out.println(firstIP);
        assertNotNull(firstIP);
    }
    
    public void testgetPublicIP() {
        String publicIP = Net.getPublicIP();
        System.out.println(publicIP);
        assertNotNull(publicIP);
    }
    
    public void testgetBroadcastAddress() throws UnknownHostException {
        String broadcastAddress = Net.getBroadcastAddress().getHostAddress();
        System.out.println(broadcastAddress);
        assertNotNull(broadcastAddress);
    }
}
