package login.mangeschool;

import login.mange.schoolmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class schoolalterDialog extends JDialog implements ActionListener {
    JButton quxiaoButton,quedingbutton;
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9;
    JTextField jTextField1=new JTextField(20);
    JTextField jTextField2=new JTextField(20);
    JTextField jTextField3=new JTextField(20);
    JTextField jTextField4=new JTextField(20);
    JTextField jTextField5=new JTextField(20);
    JTextField jTextField6=new JTextField(20);
    JTextField jTextField7=new JTextField(20);
    JTextField jTextField8=new JTextField(20);
    JTextField jTextField9=new JTextField(20);
    public schoolalterDialog() throws SQLException, ClassNotFoundException {

        initjf();
        initdia();
    }

    private void initdia() {
        quxiaoButton=new JButton("取消");
        quedingbutton=new JButton("确定");

        jLabel1=new JLabel("学号");
        jLabel2=new JLabel("修改后姓名");
        jLabel3=new JLabel("修改后性别");
        jLabel4=new JLabel("修改后出生日期");
        jLabel5=new JLabel("修改后班级编号");
        jLabel6=new JLabel("修改后联系电话");
        jLabel7=new JLabel("修改后报名日期");
        jLabel8=new JLabel("修改后家庭地址");
        jLabel9=new JLabel("修改后备注");
        jLabel1.setBounds(10,10,80,20);
        jLabel2.setBounds(10,40,80,20);
        jLabel3.setBounds(10,70,80,20);
        jLabel4.setBounds(10,100,80,20);
        jLabel5.setBounds(10,130,80,20);
        jLabel6.setBounds(10,160,80,20);
        jLabel7.setBounds(10,190,80,20);
        jLabel8.setBounds(10,220,80,20);
        jLabel9.setBounds(10,250,80,20);
        jTextField1.setBounds(100,10,100,20);
        jTextField2.setBounds(100,40,100,20);
        jTextField3.setBounds(100,70,100,20);
        jTextField4.setBounds(100,100,100,20);
        jTextField5.setBounds(100,130,100,20);
        jTextField6.setBounds(100,160,100,20);
        jTextField7.setBounds(100,190,100,20);
        jTextField8.setBounds(100,220,100,20);
        jTextField9.setBounds(100,250,100,20);
        quxiaoButton.setBounds(20,300,75,50);
        quedingbutton.setBounds(150,300,75,50);
        quxiaoButton.addActionListener(this);
        quedingbutton.addActionListener(this);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(jLabel5);
        this.add(jLabel6);
        this.add(jLabel7);
        this.add(jLabel8);
        this.add(jLabel9);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jTextField3);
        this.add(jTextField4);
        this.add(jTextField5);
        this.add(jTextField6);
        this.add(jTextField7);
        this.add(jTextField8);
        this.add(jTextField9);
        this.add(quxiaoButton);
        this.add(quedingbutton);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private void initjf() {
        this.setLayout(null);
        this.setSize(350,400);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==quxiaoButton){
            this.setVisible(false);
            try {
                new schoolmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==quedingbutton) {
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
            String sql1="delete from student where sno='"+jTextField1.getText()+"'";
            String sql2="insert into student values('"+jTextField1.getText()+"','"
                    +jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"','"+
                    jTextField5.getText()+"','"+jTextField6.getText()+"','"+jTextField7.getText()+"','"+jTextField8.getText()+"','"+jTextField9.getText()
                    +"')";
            try {
                stmt.executeUpdate(sql1);
                stmt.executeUpdate(sql2);

                con.close();
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            try {
                new schoolmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
