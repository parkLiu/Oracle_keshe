package login.mangeschool;

import login.mange.schoolmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class schooldeleteDialog extends JDialog implements ActionListener {
    JLabel jLabel=new JLabel("删除");
    JTextField jTextField=new JTextField(10);
    JButton jButton1=new JButton("取消");
    JButton jButton2=new JButton("确定");
    public schooldeleteDialog(){
        initjf();
        init();
        this.setVisible(true);
    }

    private void init() {
        jLabel.setBounds(10,10,100,25);
        jTextField.setBounds(110,10,100,25);
        jButton1.setBounds(20,50,85,50);
        jButton2.setBounds(140,50,85,50);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        this.add(jLabel);
        this.add(jTextField);
        this.add(jButton1);
        this.add(jButton2);
    }

    private void initjf() {
        this.setLayout(null);
        this.setSize(350,170);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton1){
            this.setVisible(false);
            try {
                new schoolmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==jButton2) {
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
            String sql="delete from student where sno='"+jTextField.getText()+"'";
            String sql1="delete from stu where sno='"+jTextField.getText()+"'";
            String sql2="delete from grade where sno='"+jTextField.getText()+"'";
            try {
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql1);
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
