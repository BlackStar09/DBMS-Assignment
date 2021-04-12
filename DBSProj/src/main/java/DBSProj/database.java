/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBSProj;
import java.sql.*;

/**
 *
 * @author sanath
 */
public class database {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    database(){
        try{
            String PWD = "Parkerparticles1";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institution", "root", PWD);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public Boolean checkLogin(String uname, String pwd, String token){
        try{
            if(token == "E")
                pst = con.prepareStatement("select * from employee where uname=? and pwd=?");
            else if(token == "S")
                pst = con.prepareStatement("select * from student where uname=? and pwd=?");
            else
                pst = con.prepareStatement("select * from instructor where uname=? and pwd=?");
            pst.setString(1, uname);
            pst.setString(2, pwd);
            rs = pst.executeQuery();
            if(rs.next())
                return true;
            else
                return false;        
        }catch(Exception e){
            System.out.println("Error while validating" + e);
            return false;
        }
    }
    public Boolean addCourse(String cname, int credits, int sems, int min_std, int year, String days){
        
        return false;
    }
}
