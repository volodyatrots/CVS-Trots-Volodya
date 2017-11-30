package com.epam.lab.task2;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Validate {
    private static final Logger LOG = Logger.getLogger(Validate.class);

    public static void main(String[] args) {
        LOG.info(String.format("myXML.xml validates XSD.xml.xsd\n Answer: %s", validateXMLSchema("src\\main\\java\\com\\epam\\lab\\task1\\XSD.xml.xsd", "src\\main\\java\\com\\epam\\lab\\task1\\myXML.xml")));
    }

    private static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
