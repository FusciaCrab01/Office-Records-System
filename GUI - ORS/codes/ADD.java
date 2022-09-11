import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class ADD extends JFrame implements ActionListener {

    private Connection con;

    JButton adds;
    JButton addoc;
    JButton edit;
    JButton del;
    JButton historyText;
    JLabel user;
    JTextField txtf;
    JButton search;
    JTextField txtf2;
    JButton search2;
    JButton logout;
    

    public ADD(){
          
        this.setTitle(UserLoginInfo.getDepartment() +" - ADD");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("ADDING SECTION");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 40));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 500, 200);
       

        adds = new JButton("ADD USER");
        adds.setFont(new Font("Arial", Font.PLAIN,12));
        adds.setVerticalAlignment(SwingConstants.TOP);
        adds.setHorizontalAlignment(SwingConstants.CENTER);
        adds.setForeground(new Color(0, 0, 0));
        adds.setBounds(485, 170, 200, 25);
        adds.addActionListener(this);

        addoc = new JButton("ADD TYPE OF FILE");
        addoc.setFont(new Font("Arial", Font.PLAIN,12));
        addoc.setVerticalAlignment(SwingConstants.TOP);
        addoc.setHorizontalAlignment(SwingConstants.CENTER);
        addoc.setForeground(new Color(0, 0, 0));
        addoc.setBounds(695, 170, 200, 25);
        addoc.addActionListener(this);

        edit = new JButton("EDIT / DELETE USER");
        edit.setFont(new Font("Arial", Font.PLAIN,12));
        edit.setVerticalAlignment(SwingConstants.TOP);
        edit.setHorizontalAlignment(SwingConstants.CENTER);
        edit.setForeground(new Color(0, 0, 0));
        edit.setBounds(485, 200, 200, 25);
        edit.addActionListener(this);

        del = new JButton("EDIT / DELETE FILE");
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


        DefaultTableModel model = new DefaultTableModel (new String[]{"USERNAME","DEPARTMENT","NAME OF USER"},0);
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
                String d = rs.getString("Username");
                String e = rs.getString("Department");
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

        search2 = new JButton("SEARCH");
        search2.setFont(new Font("Arial", Font.PLAIN,10));
        search2.setVerticalAlignment(SwingConstants.TOP);
        search2.setHorizontalAlignment(SwingConstants.CENTER);
        search2.setForeground(new Color(0, 0, 0));
        search2.setBounds(200, 430, 100, 20);

        txtf2 = new JTextField();
        txtf2.setBounds(310, 430, 160, 20);

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
        TableRowSorter sorter2 = new TableRowSorter<>(model2);
        table2.setRowSorter(sorter2);
        this.add (scroll2);
        scroll2.setBounds(200,480,700,120);

        txtf2.getDocument().addDocumentListener(new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {
            search2(txtf2.getText());
         }
         @Override
         public void removeUpdate(DocumentEvent e) {
            search2(txtf2.getText());
         }
         @Override
         public void changedUpdate(DocumentEvent e) {
            search2(txtf2.getText());
         }
         public void search2(String str) {
            if (str.length() == 0) {
               sorter2.setRowFilter(null);
            } else {
               sorter2.setRowFilter(RowFilter.regexFilter(str));
            }
         }
      });

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



        this.add(adds);
        this.add(addoc);
        this.add(edit);
        this.add(del);
        this.add(logout);
        this.add(title);
        this.add(txtf);
        this.add(search);
        this.add(txtf2);
        this.add(search2);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == logout){
        this.dispose();
        new menu();
        }

        if (e.getSource() == adds){
        this.dispose();
        new ADDUSER();
        }

        if (e.getSource() == addoc){
        this.dispose();
        new ADDTYPE();
        }

        if (e.getSource() == edit){
        this.dispose();
        new EDITUP();
        }   

        if (e.getSource() == del){
        this.dispose();
        new EDITDELETE();
        }
    }
    
    
}

