package login;

import login.mange.classmange;
import login.mange.jiangchengmange;
import login.mange.schoolmange;
import login.mange.studentmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class caozuoJFrame extends JFrame implements ActionListener {
    int columes=0;
//    JLabel jLabel=new JLabel("登录日志");
    String a[][] =new String[1000][3];
    String [] name={"账号","密码","登陆时间"};


    private JTable table;

    private JScrollPane pane;

    JMenuBar jMenuBar=new JMenuBar();
    JMenu function=new JMenu("功能");
    JMenuItem classitem=new JMenuItem("班级信息管理");
    JMenuItem schoolitem=new JMenuItem("学籍信息管理");
    JMenuItem studentitem=new JMenuItem("学生信息管理");
    JMenuItem jiangchengitem=new JMenuItem("奖惩信息管理");
    JMenuItem relogitem=new JMenuItem("重新登录");
    JMenuItem tuichuitem=new JMenuItem("退出");
    public caozuoJFrame() throws ClassNotFoundException, SQLException {
        initrizhi();
        initjf();
        addaction();
        this.setVisible(true);
    }

    private void addaction() {
        classitem.addActionListener(this);
        schoolitem.addActionListener(this);
        studentitem.addActionListener(this);
        jiangchengitem.addActionListener(this);
        relogitem.addActionListener(this);
        tuichuitem.addActionListener(this);
    }

    private void initrizhi() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String uname ="system";
        String pwd = "Lyl159377";
        Connection con = DriverManager.getConnection(url,uname,pwd);
//        System.out.println(con.getClass().getName());
        Statement stmt = con.createStatement();
        String sql="select * from rizhi";
        ResultSet rs=stmt.executeQuery(sql);


        while (rs.next()){

                a[columes][0]=rs.getString(1);
                a[columes][1]=rs.getString(2);
                a[columes][2]=rs.getString(3);
                columes++;

//            model.addRow(a[col]);

        }

        con.close();
        stmt.close();
        rs.close();

    }

    private void initjf() {
        table=new JTable(a,name);
        pane=new JScrollPane(table);
        pane.setBounds(10,70,450,380);
        this.getContentPane().add(pane);
        function.add(classitem);
        function.add(schoolitem);
        function.add(studentitem);
        function.add(jiangchengitem);
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
        if (e.getSource()==classitem){
            try {
                new classmange();
                this.setVisible(false);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==schoolitem) {
            try {
                new schoolmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);

        } else if (e.getSource()==studentitem) {
            try {
                new studentmange();
                this.setVisible(false);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==jiangchengitem) {
            try {
                this.setVisible(false);
                new jiangchengmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==tuichuitem) {
            System.exit(0);
        } else if (e.getSource()==relogitem) {
            this.setVisible(false);
            new choicelog();
        }
    }
}
