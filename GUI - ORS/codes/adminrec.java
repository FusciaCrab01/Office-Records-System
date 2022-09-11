import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
// ------------ //
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class adminrec extends JFrame implements ActionListener {

    private Connection con;
    int id = 0;
   
    
    JButton hisbut;
    JButton rec;
    JComboBox Combobo;
    JTextField txtf;
    JLabel user;
    JButton logout;



    public adminrec(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - RECEIVED");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));
        
        

        JLabel title = new JLabel("RECEIVED");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 55));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       
        hisbut = new JButton("RECEIVED");
        hisbut.setFont(new Font("Arial", Font.PLAIN,15));
        hisbut.setVerticalAlignment(SwingConstants.TOP);
        hisbut.setHorizontalAlignment(SwingConstants.CENTER);
        hisbut.setForeground(new Color(0, 0, 0));
        hisbut.setBounds(780, 200, 115, 30);
        hisbut.addActionListener(this);

        // txtf = new JTextField();
        // txtf.setBounds(560, 200, 200, 30);

        // String[] users = {"UNSA IBUTANG", "UNSA IBUTANG"};
        // Combobo = new JComboBox(users);
        // Combobo.setFont(new Font("calibri", Font.PLAIN, 18));
        // Combobo.setForeground(new Color(0, 0, 0));
        // Combobo.setBounds(560, 200, 200, 30);
        // ((JLabel) Combobo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        // Combobo.addActionListener(this);
        // Combobo.setSelectedItem(null);
//////////////////////////////////////////

        java.util.List<Integer> al = new ArrayList<Integer>();
        try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM documents WHERE Department_To = '"+UserLoginInfo.getDepartment()+"' AND Date_Received IS NULL";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){  
                    id = rs.getInt("Id");
                    // String users = String.valueOf(id);
                    // String users = rs.getString("Department_To");
                    al.add(id);
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
        Combobo.setBounds(560, 200, 200, 30);
        ((JLabel) Combobo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        // Combobo.addActionListener(this);
        Combobo.setSelectedItem(null);

//////////////////////////////////////////

        JPanel centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.GRAY);
        //centerPanel.setLayout(null);
        centerPanel2.setBounds(200,280,700,120);

        ///////////////////////////////////////////////////////////////////

        DefaultTableModel model2 = new DefaultTableModel (new String[]{"ID","TYPE OF DOCUMENT","PURPOSE","OFFICE FROM","DATE FORWARDED","DATE RECEIVED"}, 0);

        JTable table2 = new JTable(model2);
        JScrollPane scroll2 = new JScrollPane(table2);
        TableRowSorter sorter2 = new TableRowSorter<>(model2);
        table2.setRowSorter(sorter2);
        table2.setFocusable(false);
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
                int j = rs.getInt("id");
                
                model2.addRow(new Object[]{j, f, g, e, h, i});
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

        this.add(hisbut);
        this.add(logout);
        this.add(title);
        // this.add(txtf);
        this.add (Combobo);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
  
    
        if (e.getSource() == hisbut){

            JComboBox cb = Combobo;
            int newSelection = (int) cb.getSelectedItem();
            id = newSelection;

            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.now();

            String c = newPattern.format(date);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "UPDATE documents SET  Date_Received = '"+c+"' WHERE `documents`.`Id` ='"+id+"'";
            
                if(stm != null){
                    stm.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this,"Data received succesfully!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new menu();
                }else{
                    JOptionPane.showMessageDialog(this,"Data failed to received!","Database Message",JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (e.getSource() == logout){
        this.dispose();
        new menu();
        }
    }
 
}

