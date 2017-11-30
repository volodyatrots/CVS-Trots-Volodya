package com.epam.lab.task3;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAX {
    private static final Logger LOG = Logger.getLogger(SAX.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            File inputFile = new File("src\\main\\java\\com\\epam\\lab\\task1\\myXML.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Handler userhandler = new Handler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        LOG.info(String.format("Time taken %s ms", timeTaken));
    }
}

class Handler extends DefaultHandler {
    private static final Logger LOG = Logger.getLogger(Handler.class);
    boolean name = false;
    boolean age = false;
    boolean major = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        LOG.info(String.format("Start Element :%s", qName));
        if (qName.equalsIgnoreCase("NAME")) {
            name = true;
        }
        if (qName.equalsIgnoreCase("AGE")) {
            age = true;
        }
        if (qName.equalsIgnoreCase("MAJOR")) {
            major = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        LOG.info(String.format("End Element :%s", qName));
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (name) {
            LOG.info(String.format("Name : %s", new String(ch, start, length)));
            name = false;
        }
        if (age) {
            LOG.info(String.format("Age : %s", new String(ch, start, length)));
            age = false;
        }
        if (major) {
            LOG.info(String.format("Major : %s", new String(ch, start, length)));
            major = false;
        }
    }
}