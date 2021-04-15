/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    C:/Users/Aswath Vinayak K/Desktop/DBS_Proj/DBSProj/src/main/java/deps  C:/Users/Sanath/Documents/Projects/DBMS-Assignment/DBSProj/src/main/java/deps/
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
    public class Employee{
        int emp_id;
        String emp_name;
        String emp_role;
        int salary;
        int sal_paid;
        int d_id;
        String uname;
        String pwd;
    }
    public class Department{
        int d_id;
        String d_name;
    }
    database(){
        try{
            String PWD = "Terranova_09"; //Parkerparticles1 //Terranova_09
            Class.forName("com.mysql.cj.jdbc.Driver");
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
            System.out.println(token+" "+uname+" "+pwd);
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
            System.out.println("Error while validating - " + e);
            return -1;
        }/*finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }*/
    }
    public Boolean addCourse(int c_id, String cname, int credits, int sems, int min_std, int year, String days, int ins_id, String stTime){
        try{
            pst = con.prepareStatement("insert into courses(c_name, credits, sem, min_std, year, days, c_id, st_time) values(?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement pst2 = con.prepareStatement("select c_id from courses where c_name=?");
            pst2.setString(1, cname);
            pst.setString(1, cname);
            pst.setString(2, String.valueOf(credits));
            pst.setString(3, String.valueOf(sems));
            pst.setString(4, String.valueOf(min_std));
            pst.setString(5, String.valueOf(year));
            pst.setString(6, days);
            pst.setString(7, String.valueOf(c_id));
            pst.setString(8, stTime);
            pst.executeUpdate();
            rs = pst2.executeQuery();
            int cid = 0;
            if(rs.next())
                cid = rs.getInt("c_id");
            pst = con.prepareStatement("insert into teaches(c_id, ins_id, year) values(?, ?, ?)");
            pst.setString(1, String.valueOf(cid));
            pst.setString(2, String.valueOf(ins_id));
            pst.setString(3, String.valueOf(year));
            pst.executeUpdate();
            //con.close();
            return true;
        }catch (Exception e){
            System.out.println("Exception Encountered - " + e);
            return false;
        }
         finally{
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered (rs) - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
            
    }
    public Boolean addStudent(int s_id, String sname, String s_role, int std, int fee_left, int fee_paid, int sc_id, String user, String pwd){
       try{
           pst = con.prepareStatement("insert into student(s_name, s_role, std, fee_left, fee_paid, s_id, uname, pwd, sc_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
           pst.setString(1, sname);
           pst.setString(2, String.valueOf(s_role));
           pst.setString(3, String.valueOf(std));
           pst.setString(4, String.valueOf(fee_left));
           if(fee_paid == 0)
                pst.setString(5, String.valueOf(0));
           else
                pst.setString(5, String.valueOf(1));
           pst.setString(6, String.valueOf(s_id));
           pst.setString(7, user);
           pst.setString(8, pwd);
           if(sc_id==0)
               pst.setNull(9, java.sql.Types.INTEGER);
           else
               pst.setString(9,String.valueOf(sc_id));
           pst.executeUpdate();
           return true;
       }catch (Exception e){
           System.out.println("Exception Encountered - " + e);
           return false;
       }finally{
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered (rs) - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
    }
    //d.addInstructor(ins_id, ins_name, salary, sal_paid, d_id, user, pwd, mgr_id);
    public Boolean addInstructor(int ins_id, String ins_name, int salary, int sal_paid, int d_id, String user, String pwd, int mgr_id){
        try{
            pst = con.prepareStatement("insert into instructor(ins_name, salary, sal_paid, d_id, ins_id, uname, pwd, mgr_id) values(?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, ins_name);
            pst.setString(2, String.valueOf(salary));
            if(sal_paid == 1)
                pst.setString(3, String.valueOf(1));
            else
                pst.setString(3, String.valueOf(0));
            pst.setString(4, String.valueOf(d_id));
            pst.setString(5, String.valueOf(ins_id));
            pst.setString(6, user);
            pst.setString(7, pwd);
            if(mgr_id == 0)
                pst.setNull(8, java.sql.Types.INTEGER);
            else
                pst.setString(8, String.valueOf(mgr_id));
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Exception Encountered - " + e);
            return false;
        }finally {
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
    }
    
    public Boolean addEmployee(int emp_id, String emp_name, String emp_role, int salary, int sal_paid, int d_id, String uname, String pwd, int mgrid){
        try{
            pst = con.prepareStatement("insert into employee(emp_id, emp_name, emp_role, salary, sal_paid, d_id, uname, pwd, mgr_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, String.valueOf(emp_id));
            pst.setString(2, emp_name);
            pst.setString(3, emp_role);
            pst.setString(4, String.valueOf(salary));
            pst.setString(5, String.valueOf(sal_paid));
            pst.setString(6, String.valueOf(d_id));
            pst.setString(7, uname);
            pst.setString(8, pwd);
            if(mgrid == 0)
                pst.setNull(9, java.sql.Types.INTEGER);
            else
                pst.setString(9, String.valueOf(mgrid));
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            return false;
        }finally {
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
    }
    
    public Boolean addDept(int d_id, String d_name){
        try{
            pst = con.prepareStatement("insert into department(d_id, d_name) values(?, ?)");
            pst.setString(1, String.valueOf(d_id));
            pst.setString(2, d_name);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            return false;
        }finally {
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
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
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
        return courser;
    }
    public List<student> studentList() {
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
                stud.sc_id = rs.getInt("sc_id");
                students.add(stud);
            }
        }catch (Exception e){
            System.out.println("Exception encountered - " + e);
            return null;
        }finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
        return students;
    }
    public List<instructor> instructorList(){
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
                instruct.d_id = rs.getInt("d_id");
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
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }
    }
     /*Returns -1, 1, 2, 3 depending on which error is met. If everything works fine, it returns 1, not req*/
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
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
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
                PreparedStatement pst2 = con.prepareStatement("select * from courses where c_id = ? and year=?");
                int c_hold = rs.getInt("c_id");
                pst2.setString(1, String.valueOf(c_hold));
                pst2.setString(2, String.valueOf(year));
                ResultSet rs2 = pst2.executeQuery();
                if(rs2.next())
                {
                    course.c_id = rs2.getInt("c_id");
                    course.c_name = rs2.getString("c_name");
                    course.credits = rs2.getInt("credits");
                    course.days = rs2.getString("days");
                    course.min_std = rs2.getInt("min_std");
                    course.sem = rs2.getInt("sem");
                    course.st_time = rs2.getString("st_time");
                    course.year = rs2.getInt("year");
                    course_s.add(course);
                    System.out.println(course.days+" "+course.c_id);
                }
                try{ rs2.close(); } catch (Exception e) { System.out.println("Exception Encountered - " + e);}
                try{ pst2.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e);}
            }
            
        }catch(Exception e){
            System.out.println("Exception encountered - " + e);
            return null;
        }/*finally {
        try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        }*/
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
                PreparedStatement pst2 = con.prepareStatement("select * from courses where c_id = ? and year=?");
                int c_hold = rs.getInt("c_id");
                pst2.setString(1, String.valueOf(c_hold));
                pst2.setString(2, String.valueOf(year));
                ResultSet rs2 = pst2.executeQuery();
                if(rs2.next())
                {
                    course.c_id = rs2.getInt("c_id");
                    course.c_name = rs2.getString("c_name");
                    course.credits = rs2.getInt("credits");
                    course.days = rs2.getString("days");
                    course.min_std = rs2.getInt("min_std");
                    course.sem = rs2.getInt("sem");
                    course.st_time = rs2.getString("st_time");
                    course.year = rs2.getInt("year");
                    course_s.add(course);
                }
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
    public Boolean payFee(int s_id, String token){
        if(token == "PAID"){
            try{
              pst = con.prepareStatement("Update student set fee_paid=1 where s_id=?");
              pst.setString(1, String.valueOf(s_id));
              pst.executeUpdate();
              return true;
            }catch(Exception e){
                System.out.println("Exception encountered - "+ e);
                return false;
            }finally {
            //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
            try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
            try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
            }
        }
        return false;
    }
    public Boolean gradeStudent(int s_id, int c_id, int year, int grade){
        try{
            pst = con.prepareStatement("Update has_taken set grade=? where s_id=? and c_id=? and year=?");
            pst.setString(1, String.valueOf(grade));
            pst.setString(2, String.valueOf(s_id));
            pst.setString(3, String.valueOf(c_id));
            pst.setString(4, String.valueOf(year));
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Exception Encountered - " + e);
            return false;
        }finally {
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
    }
    public Boolean paySal(int ins_id){
        try{
            pst = con.prepareStatement("Update instructor set sal_paid=1 where ins_id=?");
            pst.setString(1, String.valueOf(ins_id));
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Exception Encountered - " + e);
            return false;
        }finally {
        //try { rs.close(); } catch (Exception e) { System.out.println("Exception encountered - " + e); }
        try { pst.close(); } catch (Exception e) { System.out.println("Exception encountered (pst) - " + e); }
        try { con.close(); } catch (Exception e) { System.out.println("Exception encountered (con) - " + e); }
        }
    }
}