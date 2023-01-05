package login;

import login.mange.classmange;
import login.mange.jiangchengmange;
import login.mange.schoolmange;
import login.mange.studentmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class studentcaozuoJFrame extends JFrame implements ActionListener {
    int columes=0;
    String a[][] =new String[100][6];
    String [] name={"成绩编号","成绩类型","班级编号","学生学号","课程名称","成绩"};
    private JTable table;
    JTextField jTextField=new JTextField(30);
    JButton jButton=new JButton("搜索");
    private JScrollPane pane;

    JMenuBar jMenuBar=new JMenuBar();
    JMenu function=new JMenu("功能");
    JMenuItem relogitem=new JMenuItem("重新登录");
    JMenuItem tuichuitem=new JMenuItem("退出");
    public studentcaozuoJFrame() throws ClassNotFoundException, SQLException {
        initrizhi();
        initjf();
        addaction();
        this.setVisible(true);
    }
    private void addaction() {
        relogitem.addActionListener(this);
        tuichuitem.addActionListener(this);
    }
    private void initrizhi() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String uname ="system";
        String pwd = "Lyl159377";
        Connection con = DriverManager.getConnection(url,uname,pwd);
        Statement stmt = con.createStatement();
        String sql="select * from grade";
        ResultSet rs=stmt.executeQuery(sql);
        while (rs.next()){
            a[columes][0]=rs.getString(1);
            a[columes][1]=rs.getString(2);
            a[columes][2]=rs.getString(3);
            a[columes][3]=rs.getString(4);
            a[columes][4]=rs.getString(5);
            a[columes][5]=rs.getString(6);
            columes++;
        }
        con.close();
        stmt.close();
        rs.close();
    }

    private void initjf() {
        jTextField.setBounds(10,20,200,30);
        jButton.setBounds(250,20,120,30);
        table=new JTable(a,name);
        pane=new JScrollPane(table);
        pane.setBounds(10,70,450,380);
        this.getContentPane().add(pane);
        this.getContentPane().add(jTextField);
        this.getContentPane().add(jButton);
        jButton.addActionListener(this);
        function.add(relogitem);
        function.add(tuichuitem);
        jMenuBar.add(function);
        this.setJMenuBar(jMenuBar);
        this.setSize(500,500);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==tuichuitem) {
            System.exit(0);
        } else if (e.getSource()==relogitem) {
            this.setVisible(false);
            new choicelog();
        } else if (e.getSource()==jButton) {

            JDialog jDialog=new JDialog();
            jDialog.setTitle("个人详细成绩");
            jDialog.setLayout(null);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setSize(500,400);
            jDialog.setDefaultCloseOperation(2);

            String b[][]=new String[130][6];
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String url="jdbc:oracle:thin:@localhost:1521:orcl";
            String uname ="system";
            String pwd = "Lyl159377";
            Connection con = null;
            try {
                con = DriverManager.getConnection(url,uname,pwd);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            Statement stmt = null;
            try {
                stmt = con.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String sql="select * from grade where sno='"+jTextField.getText()+"'";
            ResultSet rs= null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            while (true){
                try {
                    if (!rs.next()) break;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    b[columes][0]=rs.getString(1);
                    b[columes][1]=rs.getString(2);
                    b[columes][2]=rs.getString(3);
                    b[columes][3]=rs.getString(4);
                    b[columes][4]=rs.getString(5);
                    b[columes][5]=rs.getString(6);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                columes++;
            }
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            JTable table2=new JTable(b,name);
            JScrollPane pane2=new JScrollPane(table2);
            pane2.setBounds(10,10,450,380);
            jDialog.getContentPane().add(pane2);
            jDialog.setVisible(true);
        }
    }
}
