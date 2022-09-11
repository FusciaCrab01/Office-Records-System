import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ADDUSER extends JFrame implements ActionListener {

    private Connection con;

    JTextField txtf;
    JTextField txtf2;
    JTextField txtf3;
    JPasswordField txtf4;
    JPasswordField txtf5;
    JButton submit;
    JButton logout;

    public ADDUSER(){
         
       
        this.setTitle(UserLoginInfo.getDepartment() +" - ADD USER");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("ADD USER");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 35));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       

        JLabel name = new JLabel("NAME:");
        name.setFont(new Font("Agency FB", Font.PLAIN, 20));
        name.setForeground(new Color(0, 0, 0));
        name.setBounds(315, -40, 1000, 600);

        txtf = new JTextField();
        txtf.setBounds(415, 252, 250, 30);


        JLabel sec = new JLabel("DEPARTMENT:");
        sec.setFont(new Font("Agency FB", Font.PLAIN, 20));
        sec.setForeground(new Color(0, 0, 0));
        sec.setBounds(315, 20, 1000, 600);

        txtf2 = new JTextField();
        txtf2.setBounds(415, 302, 250, 30);


        JLabel user = new JLabel("USERNAME:");
        user.setFont(new Font("Agency FB", Font.PLAIN, 20));
        user.setForeground(new Color(0, 0, 0));
        user.setBounds(315, 70, 1000, 600);

        txtf3 = new JTextField();
        txtf3.setBounds(415, 352, 250, 30);


        JLabel pass = new JLabel("PASSWORD:");
        pass.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pass.setForeground(new Color(0, 0, 0));
        pass.setBounds(315, 120, 1000, 600);

        txtf4 = new JPasswordField();
        txtf4.setBounds(415, 402, 250, 30);


        JLabel pass2 = new JLabel("CONFIRM PASS:");
        pass2.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pass2.setForeground(new Color(0, 0, 0));
        pass2.setBounds(315, 170, 1000, 600);

        txtf5 = new JPasswordField();
        txtf5.setBounds(415, 452, 250, 30);

        submit = new JButton("SUBMIT");
        submit.setBounds(500,502,81,25);
        submit.setFocusable(false);
        submit.addActionListener(this);

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


        this.add(name);
        this.add(txtf);
        this.add(sec);
        this.add(txtf2);
        this.add(user);
        this.add(txtf3);
        this.add(pass);
        this.add(txtf4);
        this.add(pass2);
        this.add(txtf5);
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
            String txt3 = txtf3.getText(); 
            String txt4 = txtf4.getText(); 
            String txt5 = txtf5.getText();

            if(txt.equals("") || txt2.equals("") || txt3.equals("") || txt4.equals("") || txt5.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in all the field!");
            }else if(txt.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Name field!");
            }else if(txt2.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Department field!");
            }else if(txt3.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Username field!");
            }else if(txt4.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Password field!");
            }else if(txt5.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Confirm Password field!");
            }else if(!txt5.equals(txt4)){
                JOptionPane.showMessageDialog(this, "Password does not match!");
            }else{
                String sql = "INSERT INTO users (Id , Department , Username , Password , Name) VALUES (NULL, '"+txt2+"' , '"+txt3+"', '"+txt4+"', '"+txt+"')";
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
            String txt = txtf.getText();
            String txt2 = txtf2.getText(); 
            String txt3 = txtf3.getText(); 
            String txt4 = txtf4.getText(); 
            String txt5 = txtf5.getText();
        if(txt.equals("") && txt2.equals("") && txt3.equals("") && txt4.equals("") && txt5.equals("")){
            this.dispose();
            new ADD();
        }else{
            
            int a=JOptionPane.showConfirmDialog(this,"Do you want to go back?");  
            if(a==JOptionPane.YES_OPTION){
            this.dispose();
            new ADD();
            }
        }
        }

    }
 
}

