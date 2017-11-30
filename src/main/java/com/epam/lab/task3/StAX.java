package com.epam.lab.task3;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;


public class StAX {
    private static final Logger LOG = Logger.getLogger(StAX.class);
    public static void main(String[] args) {
        boolean name = false;
        boolean age = false;
        boolean major = false;
        long start = System.currentTimeMillis();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("src\\main\\java\\com\\epam\\lab\\task1\\myXML.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("student")) {
                            LOG.info("Start Element : student");
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String rollNo = attributes.next().getValue();
                            LOG.info("ID : " + rollNo);
                        } else if (qName.equalsIgnoreCase("name")) {
                            name = true;
                        } else if (qName.equalsIgnoreCase("age")) {
                            age = true;
                        } else if (qName.equalsIgnoreCase("major")) {
                            major = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (name) {
                            LOG.info("Name: " + characters.getData());
                            name = false;
                        }
                        if (age) {
                            LOG.info("age: " + characters.getData());
                            age = false;
                        }
                        if (major) {
                            LOG.info("major: " + characters.getData());
                            major = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
                            LOG.info("End Element : student");
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeTaken = end -start;
        LOG.info(String.format("Time taken %s ms", timeTaken));
    }
}

