import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


public class login extends JFrame implements ActionListener {

    private Connection con;

    JTextField txtf;
    JPasswordField pwf;
    JButton loginBut;
    JComboBox Combobo;
    ImageIcon samp;
    
   
    public login() {

        //setBounds (x,y,width,height);
    
        this.setTitle("OFFICE RECORD SYSTEM - Login");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.getContentPane().setBackground(new Color(50,50,50));


        JLabel title = new JLabel("O.R.S");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 100));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(265, 0, 465, 110);

        

        JLabel usernameText = new JLabel("USERNAME:");
        usernameText.setFont(new Font("Agency FB", Font.PLAIN, 20));
        usernameText.setForeground(new Color(192, 192, 192));
        usernameText.setBounds(315, 60, 1000, 600);

        txtf = new JTextField();
        txtf.setBounds(415, 342, 180, 30);

        JLabel passwordText = new JLabel(" PASSWORD:");
        passwordText.setFont(new Font("Agency FB", Font.PLAIN, 20));
        passwordText.setForeground(new Color(192, 192, 192));
        passwordText.setBounds(310, 98, 1000, 600);

        pwf = new JPasswordField();
        pwf.setBounds(415, 382, 180, 30);
        pwf.setEchoChar('*');

        loginBut = new JButton("LOGIN");
        loginBut.setBounds(465, 425, 80, 22);
        loginBut.setFocusable(false);
        loginBut.addActionListener( this);

        // java.util.List<String> al = new ArrayList<String>();
        // try {
        //         // Class.forName("com.mysql.jdbc.Driver");
        //         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
        //         Statement stm = con.createStatement();
        //         String sql = "SELECT * FROM users";
        //         ResultSet rs = stm.executeQuery(sql);

        //         while(rs.next()){
        //         // String e = rs.getString("Department");
        //         String users = rs.getString("Department");
        //         al.add(users);
        //         }
        //         con.close();
        //     } catch (SQLException throwables) {
        //         throwables.printStackTrace();
        //     }
        // Combobo = new JComboBox(al.toArray());
        // String value = Combobo.getSelectedItem().toString();

        String[] users = {"ADMINISTRATOR", "USER"};
        Combobo = new JComboBox(users);
        Combobo.setFont(new Font("calibri", Font.PLAIN, 18));
        Combobo.setForeground(new Color(192, 192, 192));
        Combobo.setBounds(415, 240, 170, 22);
        ((JLabel) Combobo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Combobo.addActionListener(this);
        Combobo.setSelectedIndex(0);


        JLabel loginAsText = new JLabel("LOG IN AS");
        loginAsText.setHorizontalAlignment(JLabel.CENTER);
        loginAsText.setFont(new Font("thor Ragnarok", Font.PLAIN, 20));
        loginAsText.setForeground(new Color(192, 192, 192));
        loginAsText.setBounds(0, -80, 1000, 600);

        JLabel bg= new JLabel("");
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setFont(new Font("Agency FB", Font.PLAIN, 20));
        bg.setForeground(new Color(0, 0, 0));
        bg.setBounds(0,0,1100,800);
        ImageIcon wala = new ImageIcon("D:\\Documents\\PPT-DOC-PDF 1st Year\\GUI - ORS\\images\\oo.jpg");
        bg.setIcon(wala);
        
     
        
        this.getRootPane().setDefaultButton(loginBut);
        this.add(title);
        this.add(usernameText);
        this.add(passwordText);
        this.add(loginAsText);
        this.add(Combobo);
        this.add(txtf);
        this.add(pwf);
        this.add(loginBut);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == loginBut){

        if(Combobo.getSelectedItem().equals("ADMINISTRATOR")){

           try {

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                String user = txtf.getText();
                String pass = pwf.getText();
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users WHERE Username='"+user+"'AND Password='"+pass+"'AND ID = 1";
                ResultSet rs = stm.executeQuery(sql);

                if(rs.next()){
                    String depart = rs.getString("Department");
                    new UserLoginInfo(depart,pass);
                    new menu();
                    dispose();
                } else if(user.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Input Username! Try again.");
                }else if(pass.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Input Password! Try again.");
                }else {
                    JOptionPane.showMessageDialog(this, "Incorrect Username or Password! Try again.");
                    txtf.setText("");
                    pwf.setText("");
                }
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else if (Combobo.getSelectedItem().equals("USER")){

                try {

                // Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                String user = txtf.getText();
                String pass = pwf.getText();
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users WHERE Username='"+user+"'AND Password='"+pass+"'AND ID > 1";
                ResultSet rs = stm.executeQuery(sql);
                

                if(rs.next()){
                    String depart = rs.getString("Department");
                    new UserLoginInfo(depart,pass);
                    new menuuser();
                    dispose();
                }else if(user.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Input Username! Try again.");
                }else if(pass.equals("")){
                    JOptionPane.showMessageDialog(this, "Please Input Password! Try again.");
                }else {
                    JOptionPane.showMessageDialog(this, "Incorrect username or password! Try again.");
                    txtf.setText("");
                    pwf.setText("");
                }
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                
            }
        }

    }

    }

}
        
    

