import javax.swing.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.awt.Dimension;
import java.awt.BorderLayout;

class App extends JFrame{

    private JButton execute = new JButton("execute");
    private JButton help =new JButton("help");
    private JButton close = new JButton("close");
    private JPanel panelRadio = new JPanel(new GridLayout(0, 1, 0, 5));
    private JPanel panel=new JPanel(new GridLayout(1, 1, 0, 5));
    private JPanel panel1=new JPanel();
    private ButtonGroup bg = new ButtonGroup();
    private JTextField searchRequest = new JTextField();
    private JTextArea field = new JTextArea();
    private String fieldText=new String();
    private String filename = File.separator+"tmp";
    private JFileChooser fc =new JFileChooser(new File(filename));
    private File selFile;

    public App() {
        super("XML converter");
        try{
            fc.showOpenDialog(this);
            selFile=fc.getSelectedFile();
            String filename=selFile.getName();
            String temp= filename.substring(filename.length()-4);
            System.out.println(temp);
            if (!temp.equals(".xml")){
                throw new Exception();
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR"+'\n'+"Set default file name \"Timetable.xml\"");
            selFile= new File("Timetable.xml");
        }
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    IStrategy strategy;
                    if (bg.getElements().nextElement().isSelected()){
                        strategy = new SAX_Strategy();
                        fieldText="SAX"+'\n';}
                    else{
                        strategy = new DOM_Strategy();
                        fieldText="DOM"+'\n';}

                    List<Teacher> teachers = strategy.parse(searchRequest.getText(), selFile);

                    SortXML.buildSorted(teachers);

                    TransformerFactory tFactory=TransformerFactory.newInstance();
                    Source xslDoc=new StreamSource("Timetable.xsl");
                    Source xmlDoc=new StreamSource("Sorted.xml");
                    String outputFileName="Converted.html";
                    OutputStream htmlFile=new FileOutputStream(outputFileName);
                    Transformer transform=tFactory.newTransformer(xslDoc);
                    transform.transform(xmlDoc, new StreamResult(htmlFile));

                    for (Teacher teacher: teachers){
                        System.out.println(teacher.getName());
                        fieldText+=teacher.getName()+"   "+teacher.getFaculty()+"    "+teacher.getCathedra()+"    "+
                                teacher.getClassNumber()+"    "+teacher.getStudents()+'\n';
                    }

                    field.setText(fieldText);
                    teachers.clear();
                }catch(Exception ex){
                    System.out.println("Error");
                }
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Taras Korolchuk, var 5");
            }
        });

        panelRadio.setBorder(BorderFactory.createTitledBorder("Choose parser"));
        String[] names1 = { "SAX", "DOM" };
        for (int i = 0; i < names1.length; i++) {
            JRadioButton radio = new JRadioButton(names1[i]);
            panelRadio.add(radio);
            bg.add(radio);
        }
        panelRadio.add(execute);

        panel.setLayout(new BorderLayout());
        panel.add(panelRadio, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);

        panel1.setLayout(new BorderLayout());
        panel1.add(help, BorderLayout.NORTH);
        panel1.add(close, BorderLayout.CENTER);

        help.setPreferredSize(new Dimension(90,23));
        close.setPreferredSize(new Dimension(90,15));
        execute.setPreferredSize(new Dimension(90, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(panel);
        contents.add(searchRequest);
        contents.add(panel1);

        field.setEditable(false);
        setContentPane(contents);
        setSize(700, 230);
        setVisible(true);
    }
}

public class Main {
    public static void main(String[] args)  {
       new App();
    }
}