import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

public class EDITUP extends JFrame implements ActionListener {

    private Connection con;
    String department = "";
    int id = 0;
    String oldpass = "";

  
    JButton edit;
    JButton del;
    JComboBox Combobo;
    JButton historyText;
    JLabel user;
    JTextField txtf;
    JButton search;
    JButton logout;
    JTextField txtf1;
    JTextField txtf2;
    JTextField txtf3;
    JPasswordField txtf6;
    JPasswordField txtf4;
    JPasswordField txtf5;
    JButton submit;
     

    public EDITUP(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - EDIT AND DELETE");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("EDIT  AND  DELETE");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 40));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 500, 200);


////////////////////////////////////////////////////////////

        java.util.List<String> al = new ArrayList<String>();
        try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                String users = rs.getString("Department");
                al.add(users);
                }
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        Combobo = new JComboBox(al.toArray());
        Combobo.setFont(new Font("calibri", Font.PLAIN, 18));
        Combobo.setForeground(new Color(0, 0, 0));
        Combobo.setBounds(310, 200, 160, 25);
        ((JLabel) Combobo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        Combobo.addActionListener(this);
        Combobo.setSelectedItem(null);

////////////////////////////////////////////////////////////

        edit = new JButton("EDIT");
        edit.setFont(new Font("Arial", Font.PLAIN,12));
        edit.setVerticalAlignment(SwingConstants.TOP);
        edit.setHorizontalAlignment(SwingConstants.CENTER);
        edit.setForeground(new Color(0, 0, 0));
        edit.setBounds(485, 200, 200, 25);
        edit.addActionListener(this);

        del = new JButton("DELETE");
        del.setFont(new Font("Arial", Font.PLAIN,12));
        del.setVerticalAlignment(SwingConstants.TOP);
        del.setHorizontalAlignment(SwingConstants.CENTER);
        del.setForeground(new Color(0, 0, 0));
        del.setBounds(695, 200, 200, 25);
        del.addActionListener(this);
  
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////       

        search = new JButton("SEARCH");
        search.setFont(new Font("Arial", Font.PLAIN,10));
        search.setVerticalAlignment(SwingConstants.TOP);
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.setForeground(new Color(0, 0, 0));
        search.setBounds(200, 230, 100, 20);

        txtf = new JTextField();
        txtf.setBounds(310, 230, 160, 20);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.GRAY);
        //centerPanel.setLayout(null);
        centerPanel.setBounds(200,280,700,120);


       DefaultTableModel model = new DefaultTableModel (new String[]{"DEPARTMENT","USERNAME","NAME OF USER"},0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        this.add (scroll);
        scroll.setBounds(200,280,700,120);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next())
            {
                String d = rs.getString("Department");
                String e = rs.getString("Username");
                String f = rs.getString("Name");
                model.addRow(new Object[]{d, e, f});
            }
            stm.close();
            rs.close();
        }catch (SQLException throwables) {
                throwables.printStackTrace();
        }


        txtf.getDocument().addDocumentListener(new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {
            search(txtf.getText());
         }
         @Override
         public void removeUpdate(DocumentEvent e) {
            search(txtf.getText());
         }
         @Override
         public void changedUpdate(DocumentEvent e) {
            search(txtf.getText());
         }
         public void search(String str) {
            if (str.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               sorter.setRowFilter(RowFilter.regexFilter(str));
            }
         }
      });
   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        JLabel name = new JLabel("DEPARTMENT:");
        name.setFont(new Font("Agency FB", Font.PLAIN, 20));
        name.setForeground(new Color(192, 192, 192));
        name.setBounds(315, 145, 1000, 600);

        txtf1 = new JTextField();
        txtf1.setBounds(415, 432, 250, 25);


        JLabel sec = new JLabel("USERNAME:");
        sec.setFont(new Font("Agency FB", Font.PLAIN, 20));
        sec.setForeground(new Color(192, 192, 192));
        sec.setBounds(315, 175, 1000, 600);

        txtf2 = new JTextField();
        txtf2.setBounds(415, 462, 250, 25);


        JLabel user = new JLabel("NAME:");
        user.setFont(new Font("Agency FB", Font.PLAIN, 20));
        user.setForeground(new Color(192, 192, 192));
        user.setBounds(315, 205, 1000, 600);

        txtf3 = new JTextField();
        txtf3.setBounds(415, 492, 250, 25);

        JLabel cpass = new JLabel("OLD PASSWORD:");
        cpass.setFont(new Font("Agency FB", Font.PLAIN, 20));
        cpass.setForeground(new Color(192, 192, 192));
        cpass.setBounds(315, 235, 1000, 600);

        txtf6 = new JPasswordField();
        txtf6.setBounds(415, 522, 250, 25);

        JLabel pass = new JLabel("PASSWORD:");
        pass.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pass.setForeground(new Color(192, 192, 192));
        pass.setBounds(315, 265, 1000, 600);

        txtf4 = new JPasswordField();
        txtf4.setBounds(415, 552, 250, 25);


        JLabel pass2 = new JLabel("CONFIRM PASS:");
        pass2.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pass2.setForeground(new Color(192, 192, 192));
        pass2.setBounds(315, 295, 1000, 600);

        txtf5 = new JPasswordField();
        txtf5.setBounds(415, 582, 250, 25);


        submit = new JButton("SUBMIT");
        submit.setBounds(500,648,81,25);
        submit.setFocusable(false);
        submit.addActionListener(this);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        this.add(txtf1);
        this.add(sec);
        this.add(txtf2);
        this.add(user);
        this.add(txtf3);
        this.add(pass);
        this.add(txtf4);
        this.add(pass2);
        this.add(txtf5);
        this.add(cpass);
        this.add(txtf6);
        this.add(logout);
        this.add(submit);
        this.add(edit);
        this.add(Combobo);
        this.add(del);
        this.add(logout);
        this.add(title);
        this.add(txtf);
        this.add(search);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == logout){
            this.dispose();
            new ADD();
        }

        if(e.getSource() == Combobo){
            JComboBox cb = Combobo;
            String getDepartment = (String) cb.getSelectedItem();
            department = getDepartment;
        }

        if(e.getSource() == edit){

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users WHERE Department='"+department+"'";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                    String a = rs.getString("Department");
                    String b = rs.getString("Username");
                    String c = rs.getString("Name");
                    oldpass = rs.getString("Password");
                    id = rs.getInt("Id");
                    txtf1.setText(a);
                    txtf2.setText(b);
                    txtf3.setText(c);
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(e.getSource() == submit){

            try {
                String a = txtf1.getText();
                String b = txtf2.getText();
                String c = txtf3.getText();
                String d = txtf4.getText();
                String g = txtf5.getText();
                String f = txtf6.getText();


            if(a.equals("") || b.equals("") || c.equals("") || d.equals("") || g.equals("") || f.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in all the field!");
            }else if(d.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Password field!");
            }else if(g.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in the Confirm Password field!");
            }else if(!g.equals(d)){
                JOptionPane.showMessageDialog(this, "Password does not match!");
            }else if(!f.equals(oldpass)){
                JOptionPane.showMessageDialog(this, "Wrong Old Password!");
            }else{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                    Statement stm = con.createStatement();
                    String sql = "UPDATE users SET Department ='"+a+"' , Username = '"+b+"' , Password ='"+g+"' , Name = '"+c+"' WHERE `users`.`Id` ='"+id+"'";
                    
                    if(stm != null){
                        stm.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this,"Data succesfully updated!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new EDITUP();
                    }else{
                        JOptionPane.showMessageDialog(this,"Data failed to update!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                    }
            }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(e.getSource() == del){
            int dialogButton = 0;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users WHERE Department='"+department+"'";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                    id = rs.getInt("Id");
                }

                if(id == 1){
                    JOptionPane.showMessageDialog(this,"Administrator cannot be deleted! ","Admin Message",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    sql = "DELETE FROM users WHERE `users`.`Id` ='"+id+"'";

                    if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if(stm != null){
                            stm.executeUpdate(sql);
                            JOptionPane.showMessageDialog(this,"Data succesfully updated!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                            this.dispose();
                            new EDITUP();
                        }else{
                            JOptionPane.showMessageDialog(this,"Data failed to update!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        remove(dialogButton);
                        this.dispose();
                        new EDITUP();
                    }
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }
    }
 
        

