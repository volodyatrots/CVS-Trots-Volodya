package com.epam.lab.task3;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOM {
    private static final Logger LOG = Logger.getLogger(DOM.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            File inputFile = new File("src\\main\\java\\com\\epam\\lab\\task1\\myXML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            LOG.info(String.format("Root element :%s\n", doc.getDocumentElement().getNodeName()));
            NodeList nList = doc.getElementsByTagName("student");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                LOG.info(String.format("Current Element :%s\n", nNode.getNodeName()));
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    LOG.info(String.format("Student id :%s\n ", eElement.getAttribute("id")));
                    LOG.info(String.format("Name :%s\n", eElement.getElementsByTagName("name").item(0).getTextContent()));
                    LOG.info(String.format("Age :%s\n", eElement.getElementsByTagName("age").item(0).getTextContent()));
                    LOG.info(String.format("Major :%s\n", eElement.getElementsByTagName("major").item(0).getTextContent()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeTaken = end -start;
        LOG.info(String.format("Time taken %s ms", timeTaken));
    }
}