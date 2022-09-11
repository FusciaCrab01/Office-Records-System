import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VIEWDOC extends JFrame implements ActionListener {

    private Connection connection;

    JTextField txtf;
    JTextField txtf2;
    JTextField txtf3;
    JTextField txtf4;
    JButton logout;
     

    public VIEWDOC(){
          
       
        this.setTitle("Administrator - UPLOAD");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("VIEW DOCUMENT");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 35));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       

        
        JLabel type = new JLabel("TYPE:");
        type.setFont(new Font("Agency FB", Font.PLAIN, 20));
        type.setForeground(new Color(0, 0, 0));
        type.setBounds(315, 5, 1000, 600);


        txtf = new JTextField();
        txtf.setBounds(415, 287, 250, 30);

        JLabel pur = new JLabel("PURPOSE:");
        pur.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pur.setForeground(new Color(0, 0, 0));
        pur.setBounds(315, 55, 1000, 600);


        txtf2 = new JTextField();
        txtf2.setBounds(415, 337, 250, 30);

        JLabel to = new JLabel("TO:");
        to.setFont(new Font("Agency FB", Font.PLAIN, 20));
        to.setForeground(new Color(0, 0, 0));
        to.setBounds(315, 105, 1000, 600);


        txtf3 = new JTextField();
        txtf3.setBounds(415, 387, 250, 30);

        JLabel from = new JLabel("FROM:");
        from.setFont(new Font("Agency FB", Font.PLAIN, 20));
        from.setForeground(new Color(0, 0, 0));
        from.setBounds(315, 155, 1000, 600);


        txtf4 = new JTextField();
        txtf4.setBounds(415, 437, 250, 30);


        logout = new JButton("BACK");
        logout.setBounds(805,670,81,25);
        logout.setFocusable(false);
        logout.addActionListener(this);

        JLabel bg= new JLabel("");
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setFont(new Font("Agency FB", Font.PLAIN, 20));
        bg.setForeground(new Color(0, 0, 0));
        bg.setBounds(0,0,1100,800);
        ImageIcon wala = new ImageIcon("D:\\Documents\\PPT-DOC-PDF 1st Year\\GUI - ORS\\images\\oo.jpg");
        bg.setIcon(wala);

        this.add(type);
        this.add(txtf);
        this.add(pur);
        this.add(txtf2);
        this.add(to);
        this.add(txtf3);
        this.add(from);
        this.add(txtf4);
        this.add(logout);
        this.add(title);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        
        this.dispose();
        new menu();
    }
 
        }

