import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class menu extends JFrame implements ActionListener {

    private Connection con;

    JButton addbut;
    JButton upbut;
    JButton hisbut;
    JButton rec;
    JLabel user;
    JButton logout;


    public menu(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - DASHBOARD");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));
        
        

        JLabel title = new JLabel("DASHBOARD");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 55));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       

        addbut = new JButton("ADD");
        addbut.setFont(new Font("Arial", Font.PLAIN,15));
        addbut.setVerticalAlignment(SwingConstants.TOP);
        addbut.setHorizontalAlignment(SwingConstants.CENTER);
        addbut.setForeground(new Color(0, 0, 0));
        addbut.setBounds(235, 200, 115, 30);
        addbut.addActionListener(this);

        upbut = new JButton("UPLOAD");
        upbut.setFont(new Font("Arial", Font.PLAIN,15));
        upbut.setVerticalAlignment(SwingConstants.TOP);
        upbut.setHorizontalAlignment(SwingConstants.CENTER);
        upbut.setForeground(new Color(0, 0, 0));
        upbut.setBounds(650, 200, 115, 30);
        upbut.addActionListener(this);

        rec = new JButton("RECEIVED");
        rec.setFont(new Font("Arial", Font.PLAIN,15));
        rec.setVerticalAlignment(SwingConstants.TOP);
        rec.setHorizontalAlignment(SwingConstants.CENTER);
        rec.setForeground(new Color(0, 0, 0));
        rec.setBounds(490, 200, 115, 30);
        rec.addActionListener(this);
       
        hisbut = new JButton("HISTORY");
        hisbut.setFont(new Font("Arial", Font.PLAIN,15));
        hisbut.setVerticalAlignment(SwingConstants.TOP);
        hisbut.setHorizontalAlignment(SwingConstants.CENTER);
        hisbut.setForeground(new Color(0, 0, 0));
        hisbut.setBounds(770, 200, 115, 30);
        hisbut.addActionListener(this);
    
///////////////////////////////////////////////////////////////////

        DefaultTableModel model2 = new DefaultTableModel (new String[]{"TYPE OF DOCUMENT","PURPOSE","OFFICE FROM","DATE FORWARDED","DATE RECEIVED"}, 0);

        JTable table2 = new JTable(model2);
        JScrollPane scroll2 = new JScrollPane(table2);
        TableRowSorter sorter2 = new TableRowSorter<>(model2);
        table2.setRowSorter(sorter2);
        this.add (scroll2);
        scroll2.setBounds(200,280,700,150);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM documents WHERE Department_To='"+UserLoginInfo.getDepartment()+"'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next())
            {
                String d = rs.getString("Department_To");
                String e = rs.getString("Department_From");
                String f = rs.getString("Document_Type");
                String g = rs.getString("Document_Purpose");
                String h = rs.getString("Date_Forwarded");
                String i = rs.getString("Date_Received");
                
                model2.addRow(new Object[]{f, g, e, h, i});
            }
            stm.close();
            rs.close();
        }catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        
///////////////////////////////////////////////////////////////////

        JPanel centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.GRAY);
        //centerPanel.setLayout(null);
        centerPanel2.setBounds(200,280,700,120);

///////////////////////////////////////////////////////////////////

        DefaultTableModel model = new DefaultTableModel (new String[]{"TYPE OF DOCUMENT","PURPOSE","OFFICE TO","DATE FORWARDED","DATE RECEIVED"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        TableRowSorter sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        this.add (scroll);
        scroll.setBounds(200,450,700,150);
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM documents WHERE Department_From='"+UserLoginInfo.getDepartment()+"'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next())
            {
                String d = rs.getString("Department_To");
                String e = rs.getString("Department_From");
                String f = rs.getString("Document_Type");
                String g = rs.getString("Document_Purpose");
                String h = rs.getString("Date_Forwarded");
                String i = rs.getString("Date_Received");
                
                model.addRow(new Object[]{f, g, d, h, i});
            }
            stm.close();
            rs.close();
        }catch (SQLException throwables) {
                throwables.printStackTrace();
        }

///////////////////////////////////////////////////////////////////
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


        this.add(addbut);
        this.add(upbut);
        this.add(hisbut);
        this.add(logout);
        this.add(title);
        this.add (rec);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == addbut){
        this.dispose();
        new ADD();
        }
        
        if (e.getSource() == hisbut){
        this.dispose();
        new HISTORY();
        }

        if (e.getSource() == rec){
        this.dispose();
        new adminrec();
        }

        if (e.getSource() == upbut){
        this.dispose();
        new UPLOAD();
        
        }

        if (e.getSource() == logout){
        this.dispose();
        new login();
        }
    }

    
 
}

