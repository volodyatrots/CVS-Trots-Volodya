package com.epam.lab.task4;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXB {
    private static final Logger LOG = Logger.getLogger(JAXB.class);

    private static final String FILE_NAME = "src\\main\\java\\com\\epam\\lab\\task1\\teacher.xml";

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setAge(25);
        teacher.setName("Susan");
        teacher.setGender("Female");
        teacher.setMajor("C#");

        ObjectToXML(teacher);

        Teacher empFromFile = XMLToObject();
        LOG.info(empFromFile.toString());
    }


    private static Teacher XMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
            Unmarshaller un = context.createUnmarshaller();
            Teacher teacher = (Teacher) un.unmarshal(new File(FILE_NAME));
            return teacher;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void ObjectToXML(Teacher emp) {
        try {
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(emp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
