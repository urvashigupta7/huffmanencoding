package huffmanCoder;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/* <applet code="MyInterface.class" width=800 height=800></applet>*/

public class AppletInterface extends Applet implements ActionListener{
    TextField t;
    Label label2;
    Button b;
    Button b2;
    Label label3;
    TextField t2=new TextField();
    Huffmancode h=new Huffmancode();
    Label actualFileSize;
    Label compressedFileSize;
    String filepath;
    Label l4;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b) {
            filepath = t.getText();
            if (filepath.equals("")) {
                label2 = new Label("Please enter file path ");
                label2.setBounds(10, 90, 300, 20);
                add(label2);
            } else {
                try {
                    label2=new Label("Compressing...");
                    label2.setBounds(10, 90, 300, 20);
                    add(label2);
                    l4=new Label("Enter the path where decompressed file is to be stored:");
                    l4.setBounds(10,110,320,15);
                    add(l4);
                    b2=new Button("Decompress");
                    b2.setBounds(300,130,90,20);
                    add(b2);
                    t2=new TextField();
                    t2.setBounds(10,130,280,20);
                    add(t2);
                    b2.addActionListener(this);
                    File file = new File(filepath);

                    actualFileSize=new Label("actual File Size-: "+file.length()+" bytes");
                    actualFileSize.setBounds(10,200,300,20);
                    add(actualFileSize);
                    String fileContents = FileOperations.readFile(file);
                    String res = "";
                    res = h.encode(fileContents);
                    FileOperations.writeEncodedFile(res, file);
                    remove(label2);
                    label2 = new Label("File Compressed");
                    label2.setBounds(10, 90, 300, 20);
                    add(label2);
                    compressedFileSize=new Label("compressed File Size-: "+file.length()+" bytes");
                    compressedFileSize.setBounds(10,230,300,20);
                    add(compressedFileSize);
                } catch (Exception err) {
                    label2 = new Label("Please Enter the valid file");
                    label2.setBounds(10, 90, 300, 20);
                    add(label2);
                }
            }
        }
        else{
            label3=new Label("Decompressing...");
            label3.setBounds(10,150,300,20);
            add(label3);
            String store=t2.getText();
            File decodedOutputFile=new File(store);
            File file=new File(filepath);
            ArrayList<Boolean> encodedfile = FileOperations.readEncodedFile(file);
            String decodedoutput=h.decode(encodedfile);
            FileOperations.writeDecodedFile(decodedOutputFile,decodedoutput);
            remove(label3);
            label3=new Label("File Decompressed");
            label3.setBounds(10,150,300,20);
            add(label3);


        }
    }


    public void init(){
      setBackground(Color.GRAY);
      Label label1=new Label("Enter Path of the File to be Compressed:");
        t=new TextField();
        t.setBounds(10,60,280,20);
        add(t);
        label1.setBounds(10,30,300,20);
        b=new Button("Compress");
        b.setBounds(300,60,90,20);
        add(label1);
        b.addActionListener(this);
        add(b);
        setLayout(null);
        setVisible(true);


    }
    public void paint(Graphics g){
        Font f1=new Font("Arial",1,20);
        g.setFont(f1);
        g.drawString("Compress/Decompress Files",80,20);

    }
}
