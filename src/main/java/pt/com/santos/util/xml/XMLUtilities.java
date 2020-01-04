package pt.com.santos.util.xml;

import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XMLUtilities {

    private XMLUtilities() {
        throw new UnsupportedOperationException();
    }

    public static void emitDocument(Document doc,
            OutputStream os, String encoding)
        throws IOException
    {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = null;
        try {
            t = tf.newTransformer();
            //t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "properties.dtd");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, encoding);
        } catch (TransformerConfigurationException tce) {
            assert(false);
        }
        DOMSource doms = new DOMSource(doc);
        StreamResult sr = new StreamResult(os);
        try {
            t.transform(doms, sr);
        } catch (TransformerException te) {
            throw new IOException(te);
        }
    }

    private static final String[] EMPTY_STR_ARR = new String[]{};

    public static void createAndAppend(Document doc,
            String name, String content, org.w3c.dom.Element location) {
        createAndAppend(doc, name, content, location,
                EMPTY_STR_ARR, EMPTY_STR_ARR);
    }

    public static void createAndAppend(Document doc,
            String name, String content, org.w3c.dom.Element location,
            String[] attrNames, String[] attrValues) {
        if (name == null || content == null) return;
        org.w3c.dom.Element node = doc.createElement(name);
        for (int i = 0; i < attrNames.length; i++) {
            org.w3c.dom.Attr attr = doc.createAttribute(attrNames[i]);
            attr.setValue(attrValues[i]);
            node.setAttributeNode(attr);
        }
        node.appendChild(doc.createTextNode(content));
        location.appendChild(node);
    }
}
