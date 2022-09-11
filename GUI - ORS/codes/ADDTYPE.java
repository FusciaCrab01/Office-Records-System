import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ADDTYPE extends JFrame implements ActionListener {

    private Connection con;

    JTextField txtf;
    JTextField txtf2;
    
    JButton submit;
    JButton logout;
    

    public ADDTYPE(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - ADD TYPE OF DOCUMENT");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("ADD TYPE OF DOCUMENT");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 25));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(325, 0, 465, 110);
        
        JLabel name = new JLabel("TYPE OF DOCUMENT:");
        name.setFont(new Font("Agency FB", Font.PLAIN, 20));
        name.setForeground(new Color(0, 0, 0));
        name.setBounds(290, 5, 1000, 600);

        txtf = new JTextField();
        txtf.setBounds(415, 287, 250, 30);


        JLabel sec = new JLabel("PURPOSE OF DOCUMENT:");
        sec.setFont(new Font("Agency FB", Font.PLAIN, 20));
        sec.setForeground(new Color(0, 0, 0));
        sec.setBounds(265, 55, 1000, 600);

        txtf2 = new JTextField();
        txtf2.setBounds(415, 337, 250, 30);

        JLabel bg= new JLabel("");
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setFont(new Font("Agency FB", Font.PLAIN, 20));
        bg.setForeground(new Color(0, 0, 0));
        bg.setBounds(0,0,1100,800);
        ImageIcon wala = new ImageIcon("D:\\Documents\\PPT-DOC-PDF 1st Year\\GUI - ORS\\images\\oo.jpg");
        bg.setIcon(wala);


        submit = new JButton("SUBMIT");
        submit.setBounds(500,387,81,25);
        submit.setFocusable(false);
        submit.addActionListener(this);

        logout = new JButton("BACK");
        logout.setBounds(805,670,81,25);
        logout.setFocusable(false);
        logout.addActionListener(this);

        this.add(name);
        this.add(txtf);
        this.add(sec);
        this.add(txtf2);
        this.add(logout);
        this.add(submit);
        this.add(title);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == submit){
            try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();

            String txt = txtf.getText();
            String txt2 = txtf2.getText(); 

            if(txt.equals("") || txt2.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in all the field!");
            }else if(txt.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Type of Document field!");
            }else if(txt2.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Purpose of Document field!");
            }else{
                String sql = "INSERT INTO document_type (Id , Document_Type , Document_Purpose) VALUES (NULL, '"+txt+"' , '"+txt2+"')";
                stm.executeUpdate(sql);
                this.dispose();
                new ADD();
            }
                stm.close();
            
        }catch (SQLException throwables){
                throwables.printStackTrace();
        }
        }

        if (e.getSource() == logout){
        this.dispose();
        new ADD();
        }
    }

    
}

