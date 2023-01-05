package login.mangeclass;

import login.mange.classmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class classaddDialog extends JDialog implements ActionListener {
    JButton quxiaoButton,quedingbutton;
    JLabel jLabel1,jLabel2,jLabel3,jLabel4;
    JTextField jTextField1=new JTextField(20);
    JTextField jTextField2=new JTextField(20);
    JTextField jTextField3=new JTextField(20);
    JTextField jTextField4=new JTextField(20);
    public classaddDialog() throws SQLException, ClassNotFoundException {

        initjf();
        initdia();
    }

    private void initdia() {
        quxiaoButton=new JButton("取消");
        quedingbutton=new JButton("确定");

        jLabel1=new JLabel("班级编号");
        jLabel2=new JLabel("班级名称");
        jLabel3=new JLabel("导员名字");
        jLabel4=new JLabel("班级主课程");
        jLabel1.setBounds(10,10,80,20);
        jLabel2.setBounds(10,40,80,20);
        jLabel3.setBounds(10,70,80,20);
        jLabel4.setBounds(10,100,80,20);
        jTextField1.setBounds(100,10,100,20);
        jTextField2.setBounds(100,40,100,20);
        jTextField3.setBounds(100,70,100,20);
        jTextField4.setBounds(100,100,100,20);
        quxiaoButton.setBounds(20,150,75,50);
        quedingbutton.setBounds(150,150,75,50);
        quxiaoButton.addActionListener(this);
        quedingbutton.addActionListener(this);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(jTextField1);
        this.add(jTextField2);
        this.add(jTextField3);
        this.add(jTextField4);
        this.add(quxiaoButton);
        this.add(quedingbutton);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private void initjf() {
        this.setLayout(null);
        this.setSize(350,300);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==quxiaoButton){
            this.setVisible(false);
            try {
                new classmange();
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

            String sql="insert into class values('"+jTextField1.getText()+"','"
                    +jTextField2.getText()+"','"+jTextField3.getText()+"','"+jTextField4.getText()+"')";
            try {
                stmt.executeUpdate(sql);

                con.close();
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            try {
                new classmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
