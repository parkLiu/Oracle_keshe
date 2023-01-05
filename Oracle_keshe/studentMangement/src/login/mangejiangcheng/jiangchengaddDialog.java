package login.mangejiangcheng;

import login.mange.classmange;
import login.mange.jiangchengmange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jiangchengaddDialog extends JDialog implements ActionListener {
    JButton quxiaoButton,quedingbutton;
    JLabel jLabel1,jLabel2;
    JTextField jTextField1=new JTextField(20);
    JTextArea jTextArea=new JTextArea();

    public jiangchengaddDialog() throws SQLException, ClassNotFoundException {

        initjf();
        initdia();
    }

    private void initdia() {
        quxiaoButton=new JButton("取消");
        quedingbutton=new JButton("确定");

        jLabel1=new JLabel("奖惩名称");
        jLabel2=new JLabel("备注");

        jLabel1.setBounds(10,10,80,20);
        jTextField1.setBounds(10,40,200,20);
        jLabel2.setBounds(10,70,80,20);
        jTextArea.setBounds(10,100,200,50);

        quxiaoButton.setBounds(20,180,75,50);
        quedingbutton.setBounds(150,180,75,50);
        quxiaoButton.addActionListener(this);
        quedingbutton.addActionListener(this);
        this.add(jLabel1);
        this.add(jLabel2);

        this.add(jTextField1);
        this.add(jTextArea);

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
                new jiangchengmange();
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

            String sql="insert into jiangcheng values('"+jTextField1.getText()+"','"
                    +jTextArea.getText()+"')";

            int count= 0;
            try {
                count = stmt.executeUpdate(sql);
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(count);
            this.setVisible(false);
            try {
                new jiangchengmange();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
