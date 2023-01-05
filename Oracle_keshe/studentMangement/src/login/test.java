package login;
import java.sql.*;
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String uname ="system";
        String pwd = "Lyl159377";
        Connection con = DriverManager.getConnection(url,uname,pwd);
//        System.out.println(con.getClass().getName());
        Statement stmt = con.createStatement();
        //"学号","姓名","性别","出生日期","班级编号","联系电话","报名日期","家庭地址","备注"
        String sql="delete from stu where sno='2010400334'";
//        ResultSet rs=stmt.executeUpdate(sql);
//        while (rs.next()){
//            System.out.println(rs.getObject(1));
//        }
        int count=stmt.executeUpdate(sql);
        System.out.println(count);
        con.close();
        stmt.close();
//        rs.close();
    }
}
