import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class HISTUSER extends JFrame implements ActionListener {

    private Connection con;

    JButton date;
    JButton purp;  
    JButton sec;
    JButton search;
    JTextField txtf;
    JLabel user;
    JButton logout;
     

    public HISTUSER(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - HISTORY");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("HISTORY");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 45));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       

        // JLabel sort = new JLabel("SORT BY:");
        // sort.setFont(new Font("Agency FB", Font.PLAIN, 20));
        // sort.setForeground(new Color(0, 0, 0));
        // sort.setBounds(185, 50, 200, 200);


        // date = new JButton("DATE");
        // date.setFont(new Font("Arial", Font.PLAIN,10));
        // date.setVerticalAlignment(SwingConstants.TOP);
        // date.setHorizontalAlignment(SwingConstants.CENTER);
        // date.setForeground(new Color(0, 0, 0));
        // date.setBounds(185, 175, 100, 20);

        // purp = new JButton("PURPOSE");
        // purp.setFont(new Font("Arial", Font.PLAIN,10));
        // purp.setVerticalAlignment(SwingConstants.TOP);
        // purp.setHorizontalAlignment(SwingConstants.CENTER);
        // purp.setForeground(new Color(0, 0, 0));
        // purp.setBounds(185, 200, 100, 20);
       
        // sec = new JButton("SECTION");
        // sec.setFont(new Font("Arial", Font.PLAIN,10));
        // sec.setVerticalAlignment(SwingConstants.TOP);
        // sec.setHorizontalAlignment(SwingConstants.CENTER);
        // sec.setForeground(new Color(0, 0, 0));
        // sec.setBounds(185, 225, 100, 20);

        JPanel centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.GRAY);
        //centerPanel.setLayout(null);
        centerPanel2.setBounds(200,280,700,120);

/////////////////////////////////////////////////////////////

        search = new JButton("SEARCH");
        search.setFont(new Font("Arial", Font.PLAIN,10));
        search.setVerticalAlignment(SwingConstants.TOP);
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.setForeground(new Color(0, 0, 0));
        search.setBounds(590, 190, 90, 20);

        txtf = new JTextField();
        txtf.setBounds(400, 190, 180, 20);

        DefaultTableModel model = new DefaultTableModel (new String[]{"TYPE OF DOCUMENT","PURPOSE","OFFICE TO","OFFICE FROM","DATE FORWARDED","DATE RECEIVED"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        this.add (scroll);
        scroll.setBounds(140,230,800,350);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM documents WHERE Department_From='"+UserLoginInfo.getDepartment()+"' OR Department_To='"+UserLoginInfo.getDepartment()+"'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next())
            {
                String d = rs.getString("Department_To");
                String e = rs.getString("Department_From");
                String f = rs.getString("Document_Type");
                String g = rs.getString("Document_Purpose");
                String h = rs.getString("Date_Forwarded");
                String i = rs.getString("Date_Received");
                
                model.addRow(new Object[]{f, g, d, e, h, i});
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

/////////////////////////////////////////////////////////////

        JLabel bg= new JLabel("");
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setFont(new Font("Agency FB", Font.PLAIN, 20));
        bg.setForeground(new Color(0, 0, 0));
        bg.setBounds(0,0,1100,800);
        ImageIcon wala = new ImageIcon("D:\\Documents\\PPT-DOC-PDF 1st Year\\GUI - ORS\\images\\oo.jpg");
        bg.setIcon(wala);

        logout = new JButton("BACK");
        logout.setBounds(805,670,81,25);
        logout.setFocusable(false);
        logout.addActionListener(this);

        // this.add(sort);
        // this.add(date);
        // this.add(purp);
        this.add(search);
        this.add(txtf);
        // this.add(sec);
        this.add(logout);
        this.add(title);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        
        this.dispose();
        new menuuser();
    }
 
        }

