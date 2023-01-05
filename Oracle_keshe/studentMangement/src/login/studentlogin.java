package login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class studentlogin extends JFrame implements ActionListener {
    Connection con;
    PreparedStatement presql;
    ResultSet rs;
    JTextField usernameFiled=new JTextField();
    JTextField passwordFied=new JTextField();

    JButton loginButton=new JButton("登录");
//        JButton registButton=new JButton("注册");
    public studentlogin() throws SQLException {
        initJfram();
        init();
        this.setVisible(true);
    }

    private void initJfram() {
        this.setLayout(null);
        this.setSize(488,430);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
    }

    private void init() {
        JLabel username=new JLabel("用户名");
        username.setBounds(116, 135, 51, 19);
        JLabel password=new JLabel("密码");
        password.setBounds(130, 195, 35, 18);

        usernameFiled.setBounds(195, 134, 200, 30);
        passwordFied.setBounds(195, 195, 200, 30);

        loginButton.setBounds(133, 300, 90, 40);
//        registButton.setBounds(256, 300, 90, 40);
        this.getContentPane().add(password);
        this.getContentPane().add(usernameFiled);
        this.getContentPane().add(username);
        this.getContentPane().add(passwordFied);



        this.getContentPane().add(loginButton);
//        this.getContentPane().add(registButton);

        //设置鼠标监听
        loginButton.addActionListener(this);
//        registButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginButton){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String url="jdbc:oracle:thin:@localhost:1521:orcl";
            String uname ="system";
            String pwd = "Lyl159377";
            try {
                con = DriverManager.getConnection(url, uname, pwd);
            }catch (SQLException exception){
            }
            System.out.println(con.getClass().getName());
            String sno=usernameFiled.getText();
            String stupw=passwordFied.getText();
            String sql="select * from stu where sno="+"'"+sno+"'"+" and stupw="+"'"+stupw+"'";
            try {
                presql=con.prepareStatement(sql);
                rs=presql.executeQuery();
                if (rs.next()){
                    System.out.println("登录成功");
                    String rizhisql="insert into rizhi values('"+sno+"','"+stupw+"',sysdate)";
                    presql=con.prepareStatement(rizhisql);
                    presql.executeQuery();
                    new studentcaozuoJFrame();
                    this.setVisible(false);
                }
                else {
                    JDialog jDialog=new JDialog();
                    JLabel jLabel=new JLabel("密码或用户名有误请重新登录");
                    jDialog.getContentPane().add(jLabel);
                    jDialog.setSize(200,100);
                    jDialog.setAlwaysOnTop(true);
                    jDialog.setLocationRelativeTo(null);
                    jDialog.setModal(true);
                    jDialog.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
