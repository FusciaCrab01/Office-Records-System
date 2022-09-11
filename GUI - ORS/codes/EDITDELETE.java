import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;


public class EDITDELETE extends JFrame implements ActionListener {

    private Connection con;
    String doc_type = "";
    int id = 0;

  
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
    JButton submit;
     

    public EDITDELETE(){
          
       
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
                String sql = "SELECT * FROM document_type";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                String users = rs.getString("Document_Type");
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

        JPanel centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.GRAY);
        //centerPanel.setLayout(null);
        centerPanel2.setBounds(200,280,700,120);


        DefaultTableModel model2 = new DefaultTableModel (new String[]{"TYPE OF DOCUMENT","PUSPOSE"}, 0);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM document_type";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next())
            {
                String d = rs.getString("Document_Type");
                String e = rs.getString("Document_Purpose");
                model2.addRow(new Object[]{d, e});
            }
            stm.close();
            rs.close();
        }catch (SQLException throwables) {
                throwables.printStackTrace();
        }
      
        JTable table2 = new JTable(model2);
        JScrollPane scroll2 = new JScrollPane(table2);
        TableRowSorter sorter = new TableRowSorter<>(model2);
        table2.setRowSorter(sorter);
        this.add (scroll2);
        scroll2.setBounds(200,280,700,120);

        txtf.getDocument().addDocumentListener(new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {
            search2(txtf.getText());
         }
         @Override
         public void removeUpdate(DocumentEvent e) {
            search2(txtf.getText());
         }
         @Override
         public void changedUpdate(DocumentEvent e) {
            search2(txtf.getText());
         }
         public void search2(String str) {
            if (str.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               sorter.setRowFilter(RowFilter.regexFilter(str));
            }
         }
      });   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        JLabel name = new JLabel("TYPE OF DOCUMENT:");
        name.setFont(new Font("Agency FB", Font.PLAIN, 20));
        name.setForeground(new Color(192, 192, 192));
        name.setBounds(290, 145, 1000, 600);

        txtf1 = new JTextField();
        txtf1.setBounds(415, 432, 250, 25);


        JLabel sec = new JLabel("PURPOSE OF DOCUMENT:");
        sec.setFont(new Font("Agency FB", Font.PLAIN, 20));
        sec.setForeground(new Color(192, 192, 192));
        sec.setBounds(265, 175, 1000, 600);

        txtf2 = new JTextField();
        txtf2.setBounds(415, 462, 250, 25);


        submit = new JButton("SUBMIT");
        submit.setBounds(500,522,81,25);
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
        this.add(logout);
        this.add(submit);
        this.add(edit);
        this.add(Combobo);
        this.add(del);
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
            String getType = (String) cb.getSelectedItem();
            doc_type = getType;
        }

        if(e.getSource() == edit){

            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM document_type WHERE Document_Type='"+doc_type+"'";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                    String a = rs.getString("Document_Type");
                    String b = rs.getString("Document_Purpose");
                    id = rs.getInt("Id");
                    txtf1.setText(a);
                    txtf2.setText(b);
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(e.getSource() == submit){

            try {
                String a = txtf1.getText();
                String b = txtf2.getText();

                if(a.equals("") || b.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in all the field!");
                }else{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                    Statement stm = con.createStatement();
                    String sql = "UPDATE document_type SET Document_Type ='"+a+"' , Document_Purpose = '"+b+"' WHERE `document_type`.`Id` ='"+id+"'";
                    
                    if(stm != null){
                        stm.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this,"Data succesfully updated!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new EDITDELETE();
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
                String sql = "SELECT * FROM document_type WHERE Document_Type='"+doc_type+"'";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                    id = rs.getInt("Id");
                }

                sql = "DELETE FROM document_type WHERE `document_type`.`Id` ='"+id+"'";

                if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if(stm != null){
                        stm.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this,"Data succesfully updated!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new EDITDELETE();
                    }else{
                        JOptionPane.showMessageDialog(this,"Data failed to update!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else {
                    remove(dialogButton);
                    this.dispose();
                    new EDITDELETE();
                }
                
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    }
 
        

