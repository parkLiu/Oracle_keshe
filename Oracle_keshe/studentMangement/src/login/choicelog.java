package login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class choicelog extends JFrame implements ActionListener {
    JButton mangement=new JButton("管理员登录");
    JButton student=new JButton("学生登录");
    public choicelog(){
       initJfame();
       init();
        this.setVisible(true);
    }
    private void initJfame(){//对最大的外部方框进行初始化
        this.setTitle("学生信息管理系统");
        this.setLayout(null);
        this.setSize(600,500);
        this.setLocationRelativeTo(null);//使方框位于屏幕中间位置
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);//关闭方式，关闭一个就全部关闭
    }
    private void init(){
        mangement.addActionListener(this);
        student.addActionListener(this);
        mangement.setBounds(150,100,300,100);
        student.setBounds(150,250,300,100);
        this.getContentPane().add(mangement);
        this.getContentPane().add(student);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==mangement){
            try {
                new mangelogin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
        } else if (e.getSource()==student) {
            try {
                new studentlogin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
        }
    }
}
