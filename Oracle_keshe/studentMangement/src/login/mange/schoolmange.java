package login.mange;

import login.caozuoJFrame;
import login.mangeclass.classaddDialog;
import login.mangeclass.classalterDialog;
import login.mangeclass.classdeleteDialog;
import login.mangeschool.schooladdDialog;
import login.mangeschool.schoolalterDialog;
import login.mangeschool.schooldeleteDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class schoolmange extends JFrame implements ActionListener {
    JLabel jLabel=new JLabel("请输入学生学号：");
    JTextField jTextField=new JTextField(20);
    int columes=0;
    String a[][] =new String[1000][9];
    String [] name={"学号","姓名","性别","出生日期","班级编号","联系电话","报名日期","家庭地址","备注"};
    private JTable table;
    private JScrollPane pane;
    JButton jButton1=new JButton("查询");
    JButton jButton2=new JButton("添加");
    JButton jButton3=new JButton("删除");
    JButton jButton4=new JButton("修改");
    JButton jButton5=new JButton("返回");
    public schoolmange() throws SQLException, ClassNotFoundException {
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
        Statement stmt = con.createStatement();
        String sql="select * from student";
        ResultSet rs=stmt.executeQuery(sql);
        while (rs.next()){
            a[columes][0]=rs.getString(1);
            a[columes][1]=rs.getString(2);
            a[columes][2]=rs.getString(3);
            a[columes][3]=rs.getString(4);
            a[columes][4]=rs.getString(5);
            a[columes][5]=rs.getString(6);
            a[columes][6]=rs.getString(7);
            a[columes][7]=rs.getString(8);
            a[columes][8]=rs.getString(9);
            columes++;
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
            Statement stmt = null;
            try {
                stmt = con.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String sql="select * from student where sno="+"'"+jTextField.getText()+"'";
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
                    jDialog.setSize(150,400 );
                    jDialog.setDefaultCloseOperation(2);
                    jDialog.setLocationRelativeTo(null);
                    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9;
                    jLabel1=new JLabel(rs.getString(1));
                    jLabel2=new JLabel(rs.getString(2));
                    jLabel3=new JLabel(rs.getString(3));
                    jLabel4=new JLabel(rs.getString(4));
                    jLabel5=new JLabel(rs.getString(5));
                    jLabel6=new JLabel(rs.getString(6));
                    jLabel7=new JLabel(rs.getString(7));
                    jLabel8=new JLabel(rs.getString(8));
                    jLabel9=new JLabel(rs.getString(9));
                    jLabel1.setBounds(10,10,100,20);
                    jLabel2.setBounds(10,40,100,20);
                    jLabel3.setBounds(10,70,100,20);
                    jLabel4.setBounds(10,100,100,20);
                    jLabel5.setBounds(10,130,100,20);
                    jLabel6.setBounds(10,160,100,20);
                    jLabel7.setBounds(10,190,100,20);
                    jLabel8.setBounds(10,220,100,20);
                    jLabel9.setBounds(10,250,100,20);
                    jDialog.add(jLabel1);
                    jDialog.add(jLabel2);
                    jDialog.add(jLabel3);
                    jDialog.add(jLabel4);
                    jDialog.add(jLabel5);
                    jDialog.add(jLabel6);
                    jDialog.add(jLabel7);
                    jDialog.add(jLabel8);
                    jDialog.add(jLabel9);
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
                new schooladdDialog();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==jButton3) {
            this.setVisible(false);
            new schooldeleteDialog();
        }else if (e.getSource()==jButton4){
            this.setVisible(false);
            try {
                new schoolalterDialog();
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
