import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
// ------------ //
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UPUSER extends JFrame implements ActionListener {

    private Connection con;
    String to = "";
    String type = "";

    JTextField txtf;
    JTextField txtf2;
    JTextField txtf3;
    JTextField txtf4;
    JTextField txtf5;
    JButton submit;
    JButton logout;
    JComboBox Combox, Combox2;


    public UPUSER(){
          
       
        this.setTitle(UserLoginInfo.getDepartment() +" - UPLOAD");
        this.setSize(1080, 1920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(50,50,50));

        JLabel title = new JLabel("UPLOAD DOCUMENT");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Thor Ragnarok", Font.BOLD, 35));
        title.setForeground(new Color(0, 0, 0));
        title.setBounds(300, 0, 465, 110);
       
        
        JLabel type = new JLabel("TYPE:");
        type.setFont(new Font("Agency FB", Font.PLAIN, 20));
        type.setForeground(new Color(0, 0, 0));
        type.setBounds(315, 5, 1000, 600);

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
        Combox = new JComboBox(al.toArray());
        Combox.addActionListener(this);
        Combox.setFont(new Font("calibri", Font.PLAIN, 18));
        Combox.setForeground(new Color(0, 0, 0));
        Combox.setBounds(415, 287, 250, 30);
        // Combox.setSelectedItem(null);
        // ((JLabel) Combox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // txtf = new JTextField();
        // txtf.setBounds(415, 287, 250, 30);


        JLabel pur = new JLabel("PURPOSE:");
        pur.setFont(new Font("Agency FB", Font.PLAIN, 20));
        pur.setForeground(new Color(0, 0, 0));
        pur.setBounds(315, 55, 1000, 600);

        txtf2 = new JTextField();
        txtf2.setEditable(false);
        txtf2.setBounds(415, 337, 250, 30);


        JLabel to = new JLabel("TO:");
        to.setFont(new Font("Agency FB", Font.PLAIN, 20));
        to.setForeground(new Color(0, 0, 0));
        to.setBounds(315, 105, 1000, 600);

        java.util.List<String> al2 = new ArrayList<String>();
        try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
                Statement stm = con.createStatement();
                String sql = "SELECT * FROM users WHERE Department !='"+UserLoginInfo.getDepartment()+"'";
                ResultSet rs = stm.executeQuery(sql);

                while(rs.next()){
                String users = rs.getString("Department");
                al2.add(users);
                }
                con.close();
                stm.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        Combox2 = new JComboBox(al2.toArray());
        Combox2.addActionListener(this);
        Combox2.setFont(new Font("calibri", Font.PLAIN, 18));
        Combox2.setForeground(new Color(0, 0, 0));
        Combox2.setBounds(415, 387, 250, 30);
        // Combox2.setSelectedItem(null);

        // txtf3 = new JTextField();
        // txtf3.setBounds(415, 387, 250, 30);


        JLabel from = new JLabel("FROM:");
        from.setFont(new Font("Agency FB", Font.PLAIN, 20));
        from.setForeground(new Color(0, 0, 0));
        from.setBounds(315, 155, 1000, 600);

        txtf4 = new JTextField();
        txtf4.setText(UserLoginInfo.getDepartment());
        txtf4.setEditable(false);
        txtf4.setBounds(415, 437, 250, 30);

        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.now();

        JLabel time = new JLabel("DATE:");
        time.setFont(new Font("Agency FB", Font.PLAIN, 20));
        time.setForeground(new Color(0,0,0));
        time.setBounds(315, 205, 1000, 600);


        txtf5 = new JTextField();
        txtf5.setText(newPattern.format(date));
        txtf5.setEditable(false);
        txtf5.setBounds(415, 487, 250, 30);


        JLabel bg= new JLabel("");
        bg.setHorizontalAlignment(JLabel.CENTER);
        bg.setFont(new Font("Agency FB", Font.PLAIN, 20));
        bg.setForeground(new Color(0, 0, 0));
        bg.setBounds(0,0,1100,800);
        ImageIcon wala = new ImageIcon("D:\\Documents\\PPT-DOC-PDF 1st Year\\GUI - ORS\\images\\oo.jpg");
        bg.setIcon(wala);

        submit = new JButton("SUBMIT");
        submit.setBounds(500,530,81,25);
        submit.setFocusable(false);
        submit.addActionListener(this);

        logout = new JButton("BACK");
        logout.setBounds(805,670,81,25);
        logout.setFocusable(false);
        logout.addActionListener(this);

        this.add(type);
        this.add(Combox);
        // this.add(txtf);
        this.add(pur);
        this.add(txtf2);
        this.add(to);
        this.add(Combox2);
        // this.add(txtf3);
        this.add(from);
        this.add(txtf4);
        this.add(time);
        this.add(txtf5);
        this.add(logout);
        this.add(submit);
        this.add(title);
        this.add(bg);
        this.setLayout(null);
        this.setVisible(true);
    }


     public void actionPerformed(ActionEvent e){
        if(e.getSource() == Combox){
            JComboBox cb = Combox;
            String newSelection = (String) cb.getSelectedItem();
            type = newSelection;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            String sql = "SELECT Document_Purpose FROM document_type WHERE Document_Type='"+type+"'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                String a = rs.getString("Document_Purpose");
                txtf2.setText(a);
            }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(e.getSource() == Combox2){
            JComboBox cb = Combox2;
            String newSelection = (String) cb.getSelectedItem();
            to = newSelection;
        }
        
        if (e.getSource() == submit){
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.now();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/orsdb","root","");
            Statement stm = con.createStatement();
            
            String txt = type;
            String txt2 = txtf2.getText(); 
            String txt3 = to; 
            String txt4 = txtf4.getText();
            String txt5 = newPattern.format(date);

            if(txt.equals("")  || txt2.equals("") || txt3.equals("") || txt4.equals("") || txt5.equals("")){
                JOptionPane.showMessageDialog(this, "Please fill in all the field!");
            }else if(txt.equals("")){
                JOptionPane.showMessageDialog(this, "Please Choose a data in the field!");
            }else if(txt3.equals("")){
                JOptionPane.showMessageDialog(this, "Please Choose a data in the field!");
            }else{
                String sql = "INSERT INTO documents (Id , Department_To , Department_From , Document_Type , Document_Purpose, Date_Forwarded, Date_Received) VALUES "
                +"(NULL, '"+txt3+"' , '"+txt4+"', '"+txt+"', '"+txt2+"', '"+txt5+"', NULL)";
                stm.executeUpdate(sql);
                this.dispose();
                new menuuser();
            }
            
        }catch (SQLException throwables){
                throwables.printStackTrace();
        }
        }

        if (e.getSource() == logout){
        this.dispose();
        new menuuser();
        }
    }

}

