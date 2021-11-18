import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMsearch {

    private static ArrayList<Teacher> teachers = new ArrayList<>();

    public static void convert(String request) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("Timetable.xml"));
        NodeList teacherElements = document.getDocumentElement().getElementsByTagName("teacher");
        for (int i = 0; i < teacherElements.getLength(); i++) {
            Node teacher = teacherElements.item(i);
            Element teacher1=(Element) teacher ;
            if(teacher1.getElementsByTagName("fullname").item(0).getTextContent().contains(request) ||
                    teacher1.getElementsByTagName("faculty").item(0).getTextContent().contains(request) ||
                    teacher1.getElementsByTagName("cathedra").item(0).getTextContent().contains(request) ||
                    teacher1.getElementsByTagName("classNumber").item(0).getTextContent().contains(request) ||
                    teacher1.getElementsByTagName("students").item(0).getTextContent().contains(request)){
                teachers.add(new Teacher(teacher1.getElementsByTagName("fullname").item(0).getTextContent(),
                        teacher1.getElementsByTagName("faculty").item(0).getTextContent(),
                        teacher1.getElementsByTagName("cathedra").item(0).getTextContent(),
                        teacher1.getElementsByTagName("classNumber").item(0).getTextContent(),
                        teacher1.getElementsByTagName("students").item(0).getTextContent()));
            }
        }
        for (Teacher teacher : teachers)
            System.out.println(teacher.getFullname()+" "+teacher.getClassNumber()+" "+teacher.getCathedra()
                    +" "+teacher.getFaculty()+" "+teacher.getStudents());

    }
}