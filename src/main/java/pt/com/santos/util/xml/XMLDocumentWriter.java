package pt.com.santos.util.xml;

import java.util.Deque;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;

public class XMLDocumentWriter {
    protected Document document;
    protected Element root;
    protected Element current;
    protected Deque<Element> stack;
    protected String encoding;

    public XMLDocumentWriter() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            document = db.newDocument();
        } catch (ParserConfigurationException ex) {
        }
        stack = new LinkedList<Element>();
    }

    public Document getDocument() {
        return document;
    }

    public void writeStartElement(String localName) throws XMLStreamException {
        stack.push(document.createElement(localName));
    }

    public void writeEmptyElement(String localName) throws XMLStreamException {
        writeStartElement(localName); writeEndElement();
    }

    public void writeEndElement() throws XMLStreamException {
        Element top = stack.poll();
        if (top != null) {
            Element pred = stack.peek();
            if (pred != null) pred.appendChild(top);
            else document.appendChild(top);
        } else throw new XMLStreamException();
    }

    public void writeEndDocument() throws XMLStreamException {
        // force end all elements
        while (!stack.isEmpty())
            writeEndElement();
    }

    public void writeAttribute(String localName, String value)
            throws XMLStreamException {
        Element top = stack.peek();
        if (top != null) {
            Attr attr = document.createAttribute(localName);
            attr.setValue(value);
            top.setAttributeNode(attr);
        } else throw new XMLStreamException();
    }

    public void writeComment(String data) throws XMLStreamException {
        Element top = stack.peek();
        Comment comment = document.createComment(data);
        if (top != null) {
            top.appendChild(comment);
        } else document.appendChild(comment);
    }

    public void writeCData(String data) throws XMLStreamException {
        Element top = stack.peek();
        if (top != null) {
            CDATASection cdata = document.createCDATASection(data);
            top.appendChild(cdata);
        } else throw new XMLStreamException();
    }

    public void writeStartDocument() throws XMLStreamException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void writeStartDocument(String version) throws XMLStreamException {
        document.setXmlVersion(version);
    }

    public void writeStartDocument(String encoding, String version)
            throws XMLStreamException {
        document.setXmlVersion(version);
        this.encoding = encoding;
    }

    public void writeCharacters(String text) throws XMLStreamException {
        Element top = stack.peek();
        Text tn = document.createTextNode(text);
        if (top != null) {
            top.appendChild(tn);
        } else document.appendChild(tn);
    }

    public void writeCharacters(char[] text, int start, int len)
            throws XMLStreamException {
        writeCharacters(new String(text, start, len));
    }
}
