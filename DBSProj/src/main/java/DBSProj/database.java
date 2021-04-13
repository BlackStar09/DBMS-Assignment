/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBSProj;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanath
 */
/*
public class database {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement stmt;
    public class courses{
        int c_id;
        String c_name;
        int credits;
        int min_std;
        int sem;
        String st_time;
        int year;
        String days;
    }
    public class student{
        int s_id;
        String s_name;
        String s_role;
        int std;
        int fee_left;
        int fee_paid;
        int sc_id;
    }
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
            con.close();
            if(rs.next())
                return true;
            else
                return false;        
        }catch(Exception e){
            System.out.println("Error while validating" + e);
            try{
            con.close();
            }catch(Exception e1){
                System.out.println("Exception encountered while closing connection - " + e1);
                return false;
            }
            return false;
        }
    }
    public Boolean addCourse(String cname, int credits, int sems, int min_std, int year, String days, int ins_id){
        try{
            pst = con.prepareStatement("insert into course values(c_name = ?, credits = ?, sem = ?, min_std = ?, year = ?, days = ?)");
            PreparedStatement pst2 = con.prepareStatement("select cid from course where cname=?");
            pst2.setString(1, cname);
            pst.setString(1, cname);
            pst.setString(2, String.valueOf(credits));
            pst.setString(3, String.valueOf(sems));
            pst.setString(4, String.valueOf(min_std));
            pst.setString(5, String.valueOf(year));
            pst.setString(6, days);
            pst.executeUpdate();
            rs = pst2.executeQuery();
            int cid = 0;
            if(rs.next())
                cid = rs.getInt("cid");
            pst = con.prepareStatement("insert into teaches values(c_id = ?, ins_id = ?, year = ?)");
            pst.setString(1, String.valueOf(cid));
            pst.setString(2, String.valueOf(ins_id));
            pst.setString(3, String.valueOf(year));
            pst.executeUpdate();
            con.close();
            return true;
        }catch (Exception e){
            System.out.println("Exception Encountered - " + e);
            try{
            con.close();
            }catch(Exception e1){
                System.out.println("Exception encountered while closing connection - " + e1);
                return false;
            }
            return false;
        }
    }
    public Boolean addStudent(String sname, String s_role, int std, int fee_left, boolean fee_paid){
       try{
           pst = con.prepareStatement("insert into student values(s_name=?, s_role=?, std=?, fee_left=?, fee_paid=?)");
           pst.setString(1, sname);
           pst.setString(2, String.valueOf(s_role));
           pst.setString(3, String.valueOf(std));
           pst.setString(4, String.valueOf(fee_left));
           if(fee_paid == false)
                pst.setString(5, String.valueOf(0));
           else
                pst.setString(5, String.valueOf(1));
           pst.executeUpdate();
           con.close();
           return true;
       }catch (Exception e){
           System.out.println("Exception Encountered" + e);
           try{
            con.close();
           }catch (Exception e1){
               System.out.println("Exception Encountered while closing connection - " + e1);
               return false;
           }
           return false;
       }
    }
    public Boolean addInstructor(String ins_name, int salary, boolean sal_paid, int d_id){
        try{
            pst = con.prepareStatement("insert into institution values(ins_name = ?, salary=?, sal_paid=?, d_id=?)");
            pst.setString(1, ins_name);
            pst.setString(2, String.valueOf(salary));
            if(sal_paid == true)
                pst.setString(3, String.valueOf(1));
            else
                pst.setString(3, String.valueOf(0));
            pst.setString(4, String.valueOf(d_id));
            pst.executeUpdate();
            con.close();
            return true;
        }catch(Exception e){
            try{
            System.out.println("Exception Encountered - " + e);
            }catch(Exception e1){
                System.out.println("Exception Encountered while closing connection - " + e1);
            }
            return false;
        }
    }
    public List<courses> courseList() throws SQLException{
        List<courses> courser = new ArrayList<courses>();
        try{
            pst = con.prepareStatement("select * from courses");
            rs = pst.executeQuery();
            while(rs.next()){
                courses course = new courses();
                course.c_id = rs.getInt("c_id");
                course.c_name = rs.getString("c_name");
                course.credits = rs.getInt("credits");
                course.days = rs.getString("days");
                course.min_std = rs.getInt("min_std");
                course.sem = rs.getInt("sem");
                course.st_time = rs.getString("st_time");
                course.year = rs.getInt("year");
                courser.add(course);
            }
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
        }
        return courser;
    }
    public List<student> studentList() throws SQLException{
        List<student> students = new ArrayList<student>();
        try{
            pst = con.prepareStatement("select * from student");
            rs = pst.executeQuery();
            while(rs.next()){
                student stud = new student();
                while(rs.next()){
                    stud.s_id = rs.getInt("s_id");
                    stud.s_name = rs.getString("s_name");
                    stud.s_role = rs.getString("s_role");
                    stud.std = rs.getString("std");
                    stud.fee_left = rs.getString("fee_left");
                    stud.fee_paid = rs.getInt("fee_paid");
                }
            }
        }
    }
}
*/