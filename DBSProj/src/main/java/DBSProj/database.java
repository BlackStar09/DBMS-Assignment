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
    public class instructor{
        int ins_id;
        String ins_name;
        int mgr_id;
        int salary;
        int sal_paid;
        int d_id;
        String uname;
        String pwd;
    }
    database(){
        try{
            String PWD = "Terranova_09";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institution", "root", PWD);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public int checkLogin(String uname, String pwd, String token){
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
            //con.close();
            if(rs.next()){
                if(token=="E")
                    return rs.getInt("emp_id");
                else if(token=="S")
                    return rs.getInt("s_id");
                else
                    return rs.getInt("ins_id");
            }
            else
                return -1;        
        }catch(Exception e){
            System.out.println("Error while validating" + e);
            try{
            //con.close();
            }catch(Exception e1){
                System.out.println("Exception encountered while closing connection - " + e1);
                return -1;
            }
            return -1;
        }
    }
    public Boolean addCourse(int c_id, String cname, int credits, int sems, int min_std, int year, String days, int ins_id){
        try{
            pst = con.prepareStatement("insert into course(c_name, credits, sem, min_std, year, days, c_id) values(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement pst2 = con.prepareStatement("select cid from course where cname=?");
            pst2.setString(1, cname);
            pst.setString(1, cname);
            pst.setString(2, String.valueOf(credits));
            pst.setString(3, String.valueOf(sems));
            pst.setString(4, String.valueOf(min_std));
            pst.setString(5, String.valueOf(year));
            pst.setString(6, days);
            pst.setString(7, String.valueOf(c_id));
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
    public Boolean addStudent(int s_id, String sname, String s_role, int sc_id, int std, int fee_left, int fee_paid){
       try{
           pst = con.prepareStatement("insert into student(s_name, s_role, std, fee_left, fee_paid, s_id, sc_id) values(?, ?, ?, ?, ?, ?, ?)");
           pst.setString(1, sname);
           pst.setString(2, String.valueOf(s_role));
           pst.setString(3, String.valueOf(std));
           pst.setString(4, String.valueOf(fee_left));
           pst.setString(7,String.valueOf(sc_id));
           if(fee_paid == 0)
                pst.setString(5, String.valueOf(0));
           else
                pst.setString(5, String.valueOf(1));
           pst.setString(6, String.valueOf(s_id));
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
    public Boolean addInstructor(int ins_id, String ins_name, int salary, boolean sal_paid, int d_id){
        try{
            pst = con.prepareStatement("insert into institution(ins_name, salary, sal_paid, d_id, ins_id) values(?, ?, ?, ?, ?)");
            pst.setString(1, ins_name);
            pst.setString(2, String.valueOf(salary));
            if(sal_paid == true)
                pst.setString(3, String.valueOf(1));
            else
                pst.setString(3, String.valueOf(0));
            pst.setString(4, String.valueOf(d_id));
            pst.setString(5, String.valueOf(ins_id));
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
                stud.s_id = rs.getInt("s_id");
                stud.s_name = rs.getString("s_name");
                stud.s_role = rs.getString("s_role");
                stud.std = rs.getInt("std");
                stud.fee_left = rs.getInt("fee_left");
                stud.fee_paid = rs.getInt("fee_paid");
                students.add(stud);
            }
        }catch (Exception e){
            System.out.println("Exception encountered - " + e);
            return null;
        }
        return students;
    }
    public List<instructor> instructorList() throws SQLException{
        List<instructor> instructs = new ArrayList<instructor>();
        try{
            pst = con.prepareStatement("select * from instructor");
            rs = pst.executeQuery();
            while(rs.next()){
                instructor instruct = new instructor();
                instruct.ins_id = rs.getInt("ins_id");
                instruct.ins_name = rs.getString("ins_name");
                instruct.mgr_id = rs.getInt("mgr_id");
                instruct.salary = rs.getInt("salary");
                instruct.sal_paid = rs.getInt("sal_paid");
                instruct.sal_paid = rs.getInt("d_id");
                instructs.add(instruct);
                }
            }catch (Exception e){
                    System.out.println("Exception Encountered - " + e);
                    return null;
            }finally {
            try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
            try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
            try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
            }
        return instructs;
    }
    /*Returns -1, 1, 2, 3 depending on which error is met. If everything works fine, it returns 1*/
    public int studaddCourse(int c_id, int s_id, int year){
        try{
            pst = con.prepareStatement("select c_id, year from has_taken where s_id = ?");
            pst.setString(1, String.valueOf(s_id));
            rs = pst.executeQuery();
            PreparedStatement pst2 = con.prepareStatement("select * from courses c1, courses c2  where c1.c_id=? and c2.c_id=? and c1.year = ? and c2.year = ? and c1.st_time = c2.st_time and c1.days = c2.days");
            pst2.setString(1, String.valueOf(c_id));
            pst2.setString(3, String.valueOf(year));
            ResultSet rs2;
            while(rs.next()){
                int c_hold = rs.getInt("c_id");
                int y_hold = rs.getInt("year");
                pst2.setString(2, String.valueOf(c_hold));
                pst2.setString(4, String.valueOf(y_hold));
                if(c_id == c_hold && year == y_hold){
                    //Student is already doing course, return 2;
                    return 2;
                }
                rs2 = pst2.executeQuery();
                if(rs2.next()){
                    //Student is doing a course in that timeframe already, return 3;
                    return 3;
                }
            }
            pst = con.prepareStatement("insert into has_taken(c_id, s_id, year) values(?, ?, ?)");
            pst.setString(1, String.valueOf(c_id));
            pst.setString(2, String.valueOf(s_id));
            pst.setString(3, String.valueOf(year));
            pst.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            //Exception encountered, return -1;
            return -1;
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
    }
     /*Returns -1, 1, 2, 3 depending on which error is met. If everything works fine, it returns 1*/
    public int insaddCourse(int c_id, int ins_id, int year){
        try{
            pst = con.prepareStatement("select c_id, year from teaches where ins_id = ?");
            rs = pst.executeQuery();
            PreparedStatement pst2 = con.prepareStatement("select * from courses c1, courses c2 where c1.c_id = ? and c2.c_id=? and c1.year = ? and c2.year = ? and c1.c_id = c2.c_id and c1.year = c2.year and c1");
            pst2.setString(1, String.valueOf(c_id));
            pst2.setString(3, String.valueOf(year));
            while(rs.next()){
                int c_hold = rs.getInt("c_id");
                int y_hold = rs.getInt("year");
                pst2.setString(2, String.valueOf(c_hold));
                pst2.setString(4, String.valueOf(y_hold));
                if(c_id == c_hold && year == y_hold){
                    return 2;
                }
                ResultSet rs2;
                rs2 = pst2.executeQuery();
                if(rs2.next()){
                    return 3;
                }
            }
            pst = con.prepareStatement("insert into teaches(c_id, ins_id, year) values(?, ?, ?)");
            pst.setString(1, String.valueOf(c_id));
            pst.setString(2, String.valueOf(ins_id));
            pst.setString(3, String.valueOf(year));
            pst.executeUpdate();
            return 1;
        }catch (Exception e){
            System.out.println("Exception Encountered - " + e);
            return -1;
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }

    }
    public List<courses> studCoursesTT(int s_id, int year){
        List<courses> course_s = new ArrayList<courses>();
        try{
            pst = con.prepareStatement("select c_id, year from has_taken where s_id = ? and year = ?");
            pst.setString(1, String.valueOf(s_id));
            pst.setString(2, String.valueOf(year));
            rs = pst.executeQuery();
            while(rs.next()){
                courses course = new courses();
                PreparedStatement pst2 = con.prepareStatement("select * from courses where c_id = ?");
                int c_hold = rs.getInt("c_id");
                pst2.setString(1, String.valueOf(c_hold));
                ResultSet rs2 = pst2.executeQuery();
                course.c_id = rs2.getInt("c_id");
                course.c_name = rs2.getString("c_name");
                course.credits = rs2.getInt("credits");
                course.days = rs2.getString("days");
                course.min_std = rs2.getInt("min_std");
                course.sem = rs2.getInt("sem");
                course.st_time = rs2.getString("st_time");
                course.year = rs2.getInt("year");
                course_s.add(course);
                try{ rs2.close(); } catch (Exception e) { System.out.println("Exception Encountered - " + e);}
                try{ pst2.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e);}
            }
            
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            return null;
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
        return course_s;
    }
    public List<courses> teachesCoursesTT(int ins_id, int year){
        List<courses> course_s = new ArrayList<courses>();
        try{
            pst = con.prepareStatement("select c_id, year from teaches where ins_id = ? and year = ?");
            pst.setString(1, String.valueOf(ins_id));
            pst.setString(2, String.valueOf(year));
            rs = pst.executeQuery();
            while(rs.next()){
                courses course = new courses();
                PreparedStatement pst2 = con.prepareStatement("select * from courses where c_id = ?");
                int c_hold = rs.getInt("c_id");
                pst2.setString(1, String.valueOf(c_hold));
                ResultSet rs2 = pst2.executeQuery();
                course.c_id = rs2.getInt("c_id");
                course.c_name = rs2.getString("c_name");
                course.credits = rs2.getInt("credits");
                course.days = rs2.getString("days");
                course.min_std = rs2.getInt("min_std");
                course.sem = rs2.getInt("sem");
                course.st_time = rs2.getString("st_time");
                course.year = rs2.getInt("year");
                course_s.add(course);
                try{ rs2.close(); } catch (Exception e) { System.out.println("Exception Encountered - " + e);}
                try{ pst2.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e);}
            }
            return course_s;
            
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            return null;
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
    }
}