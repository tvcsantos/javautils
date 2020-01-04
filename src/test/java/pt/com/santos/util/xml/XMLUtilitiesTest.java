package pt.com.santos.util.xml;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

public class XMLUtilitiesTest {
    public static void main(String[] args) 
            throws XMLStreamException, IOException {
        XMLDocumentWriter db = new XMLDocumentWriter();
        db.writeStartDocument();
        db.writeStartElement("xpto");
        db.writeAttribute("baba", "yeye");
        //db.writeEndElement();
        //db.writeEndElement(); // should throw ex
        db.writeStartElement("abcd");
        db.writeStartElement("efg");
        db.writeEmptyElement("teste");
        db.writeEndElement();
        db.writeEndElement();
        db.writeEndDocument();
        XMLUtilities.emitDocument(db.getDocument(), System.out, "UTF-8");
    }
}
