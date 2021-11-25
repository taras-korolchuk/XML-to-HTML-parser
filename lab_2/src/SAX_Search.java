import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAX_Search {
    private static ArrayList<Teacher> teachers = new ArrayList<>();

 public  ArrayList<Teacher> convert(String request) throws ParserConfigurationException, SAXException, IOException{
     teachers.clear();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File("Timetable.xml"), handler);
        ArrayList<Teacher> temp=new ArrayList<>();
        for (int i=0; i<teachers.size(); i++){

            if (((teachers.get(i).getName().contains(request)) ||
                    (teachers.get(i).getFaculty().contains(request)) ||
                    (teachers.get(i).getCathedra().contains(request)) ||
                    (teachers.get(i).getClassNumber().contains(request)) ||
                    (teachers.get(i).getStudents().contains(request)))){
                temp.add(teachers.get(i));
            }
        }

    return temp;
    }


    private static class XMLHandler extends DefaultHandler {
        private String name, faculty, cathedra, classNumber, students, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String str = new String(ch, start, length);
            str = str.replace("\n", "").trim();


            if (!str.isEmpty()) {
                if (lastElementName.equals("name"))
                    name = str;
                if (lastElementName.equals("faculty"))
                    faculty = str;
                if (lastElementName.equals("cathedra"))
                    cathedra = str;
                if (lastElementName.equals("classNumber"))
                    classNumber = str;
                if (lastElementName.equals("students"))
                    students = str;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (name != null && !name.isEmpty()) &&
                    (faculty != null && !faculty.isEmpty()) &&
                    (cathedra != null && !cathedra.isEmpty()) &&
                    (classNumber != null && !classNumber.isEmpty()) &&
                    (students != null && !students.isEmpty())) {
                teachers.add(new Teacher(name, faculty, cathedra, classNumber, students));
                name = null;
                faculty = null;
                cathedra = null;
                classNumber = null;
                students = null;
            }
        }
    }
}