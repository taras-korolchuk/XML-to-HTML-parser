import javax.swing.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

class App extends JFrame{

    private JButton execute = new JButton("execute");
    private JTextField searchRequest = new JTextField();
    private JButton close = new JButton("close");
    private JRadioButton saxButton = new JRadioButton("SAX");
    private JRadioButton domButton = new JRadioButton("DOM");
    private JPanel panelRadio;
    private ButtonGroup bg = new ButtonGroup();

    public App() {
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    IStrategy strategy;
                    if (bg.getElements().nextElement().isSelected())
                        strategy = new SAX_Strategy();
                    else
                        strategy = new DOM_Strategy();

                    List<Teacher> teachers = strategy.parse(searchRequest.getText());

                    for (Teacher teacher: teachers)
                        System.out.println(teacher.getName());

                    SortXML.buildSorted(teachers);

                    TransformerFactory tFactory=TransformerFactory.newInstance();
                    Source xslDoc=new StreamSource("Timetable.xsl");
                    Source xmlDoc=new StreamSource("Sorted.xml");
                    String outputFileName="Converted.html";
                    OutputStream htmlFile=new FileOutputStream(outputFileName);
                    Transformer transform=tFactory.newTransformer(xslDoc);
                    transform.transform(xmlDoc, new StreamResult(htmlFile));

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
        panelRadio = new JPanel(new GridLayout(0, 1, 0, 5));
        panelRadio.setBorder(BorderFactory.createTitledBorder("Choose parser"));
        String[] names1 = { "SAX", "DOM" };

        for (int i = 0; i < names1.length; i++) {
            JRadioButton radio = new JRadioButton(names1[i]);
            panelRadio.add(radio);
            bg.add(radio);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(panelRadio);
        contents.add(searchRequest);
        contents.add(execute);
        contents.add(close);
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }
}

public class Main {
    public static void main(String[] args)  {
       new App();
    }
}