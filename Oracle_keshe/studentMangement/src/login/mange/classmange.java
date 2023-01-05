package login.mange;

import login.caozuoJFrame;
import login.mangeclass.classaddDialog;
import login.mangeclass.classalterDialog;
import login.mangeclass.classdeleteDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class classmange extends JFrame implements ActionListener {
    JLabel jLabel=new JLabel("请输入班级编号/班级名称/导员名字/班级主课程");
    JTextField jTextField=new JTextField(20);
    int columes=0;
    String a[][] =new String[1000][4];
    String [] name={"班级编号","班级名称","导员名字","班级主课程"};
    private JTable table;
    private JScrollPane pane;
    JButton jButton1=new JButton("查询");
    JButton jButton2=new JButton("添加");
    JButton jButton3=new JButton("删除");
    JButton jButton4=new JButton("修改");
    JButton jButton5=new JButton("返回");
    public classmange() throws SQLException, ClassNotFoundException {
        initshuju();
        initjf();
        this.setVisible(true);
    }

    private void initshuju() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String uname ="system";
        String pwd = "Lyl159377";
        Connection con = DriverManager.getConnection(url,uname,pwd);
//        System.out.println(con.getClass().getName());
        Statement stmt = con.createStatement();
        String sql="select * from class";
        ResultSet rs=stmt.executeQuery(sql);


        while (rs.next()){

            a[columes][0]=rs.getString(1);
            a[columes][1]=rs.getString(2);
            a[columes][2]=rs.getString(3);
            a[columes][3]=rs.getString(4);
            columes++;

//            model.addRow(a[col]);

        }

        con.close();
        stmt.close();
        rs.close();

    }

    private void initjf() {

        this.setLayout(null);
        this.setSize(700,500);
        jLabel.setBounds(10,10,280,30);
        jTextField.setBounds(350,10,100,30);
        this.getContentPane().add(jLabel);
        this.getContentPane().add(jTextField);
        table=new JTable(a,name);
        pane=new JScrollPane(table);
        pane.setBounds(10,70,450,380);
        this.getContentPane().add(pane);
        jButton1.setBounds(530,70,100,50);
        jButton2.setBounds(530,150,100,50);
        jButton3.setBounds(530,230,100,50);
        jButton4.setBounds(530,310,100,50);
        jButton5.setBounds(530,390,100,50);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton4.addActionListener(this);
        jButton5.addActionListener(this);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        this.getContentPane().add(jButton5);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton1){
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
//        System.out.println(con.getClass().getName());
            Statement stmt = null;
            try {
                stmt = con.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String sql="select * from class where cno="+"'"+jTextField.getText()+"'"+"or cname="
                    +"'"+jTextField.getText()+"'"
                    +"or tname="+"'"+jTextField.getText()+"'"+"or mcla="+"'"+jTextField.getText()+"'";
            ResultSet rs= null;
            try {
                rs = stmt.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            try {
                if (rs.next()){
                    JDialog jDialog=new JDialog();
                    jDialog.setLayout(null);
                    jDialog.setSize(150,200);
                    jDialog.setDefaultCloseOperation(2);
                    jDialog.setLocationRelativeTo(null);
                    JLabel jLabel1,jLabel2,jLabel3,jLabel4;
                    jLabel1=new JLabel(rs.getString(1));
                    jLabel2=new JLabel(rs.getString(2));
                    jLabel3=new JLabel(rs.getString(3));
                    jLabel4=new JLabel(rs.getString(4));
                    jLabel1.setBounds(10,10,100,20);
                    jLabel2.setBounds(10,40,100,20);
                    jLabel3.setBounds(10,70,100,20);
                    jLabel4.setBounds(10,100,100,20);
                    jDialog.add(jLabel1);
                    jDialog.add(jLabel2);
                    jDialog.add(jLabel3);
                    jDialog.add(jLabel4);
                    jDialog.setAlwaysOnTop(true);
                    jDialog.setVisible(true);
                }else {
                    JDialog jDialog=new JDialog();
                    JLabel jLabeltext=new JLabel("未查询到有关信息");
                    jDialog.setLayout(new FlowLayout());
                    jDialog.setSize(250,100);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setAlwaysOnTop(true);
                    jDialog.add(jLabeltext);
                    jDialog.setVisible(true);
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource()==jButton2) {
            this.setVisible(false);
            try {
                this.setVisible(false);
                new classaddDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==jButton3) {
            this.setVisible(false);
            new classdeleteDialog();
        }else if (e.getSource()==jButton4){
            this.setVisible(false);
            try {
                new classalterDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource()==jButton5){
            this.setVisible(false);
            try {
                new caozuoJFrame();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
