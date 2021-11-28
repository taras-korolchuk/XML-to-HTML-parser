import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class SortXML {

    public static void buildSorted(List<Teacher> teachers){
        try {
            FileOutputStream out = new FileOutputStream("Sorted.xml");
            BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(out));
            buffWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"); buffWriter.newLine();
            buffWriter.write("<timetable>"); buffWriter.newLine();

            for (int i = 0; i < teachers.size(); i++) {
                buffWriter.write("    <teacher>"); buffWriter.newLine();
                buffWriter.write("        <name>" + teachers.get(i).getName() + "</name>"); buffWriter.newLine();
                buffWriter.write("        <faculty>" + teachers.get(i).getFaculty() + "</faculty>"); buffWriter.newLine();
                buffWriter.write("        <cathedra>" + teachers.get(i).getCathedra() + "</cathedra>"); buffWriter.newLine();
                buffWriter.write("        <classNumber>" + teachers.get(i).getClassNumber() + "</classNumber>") ;buffWriter.newLine();
                buffWriter.write("        <students>" + teachers.get(i).getStudents() + "</students>"); buffWriter.newLine();
                buffWriter.write("    </teacher>"); buffWriter.newLine();
            }
            buffWriter.write("</timetable>");
            buffWriter.close();
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}


