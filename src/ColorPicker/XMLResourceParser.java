package colorPicker;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


// Parser for resources in XML format
public class XMLResourceParser extends DefaultHandler {
    private String sourceFileName;
    protected String name;
    protected String version;


    public XMLResourceParser(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public XMLResourceParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

    }

    @Override
    public void endDocument() throws SAXException {


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }
}