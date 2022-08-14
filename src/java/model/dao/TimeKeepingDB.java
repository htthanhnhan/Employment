/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Employee;
import model.entity.TimeKeeping;

/**
 *
 * @author ACER
 */
public class TimeKeepingDB implements DBContext {
    
    public static ArrayList<TimeKeeping> getTimeKeepingByEmployee(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, startTime, endTime, punish, workingHours, startOverTime, endOverTime, overTimeHours, checkPay, validVac FROM TimeKeeping WHERE employeeId = ?\n"
                    + "ORDER by id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<TimeKeeping> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            while (rs.next()) {
                list.add(new TimeKeeping(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getTime(5), rs.getInt(6), rs.getFloat(7), rs.getTime(8), rs.getTime(9), rs.getFloat(10), rs.getBoolean(11), rs.getBoolean(12)));
                
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.getTimeKeepingByEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static ArrayList<TimeKeeping> getTimeKeepingByEmployeeUnCheckPay(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, startTime, endTime, punish, workingHours, startOverTime, endOverTime, overTimeHours, checkPay, validVac FROM TimeKeeping WHERE employeeId = ? AND checkPay = 0\n"
                    + "ORDER by id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<TimeKeeping> list = new ArrayList<>();
//            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            while (rs.next()) {
                list.add(new TimeKeeping(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getTime(5), rs.getInt(6), rs.getFloat(7), rs.getTime(8), rs.getTime(9), rs.getFloat(10), rs.getBoolean(11), rs.getBoolean(12)));
                
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.getTimeKeepingByEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static TimeKeeping getTimeKeepingByEmployeeAndCurrentDate(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, startTime, endTime, punish, workingHours, startOverTime, endOverTime, overTimeHours, checkPay, validVac FROM TimeKeeping WHERE currentDate = ? AND employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            ps.setString(1, f.format(date));
            ps.setInt(2, e.getId());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new TimeKeeping(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getTime(5), rs.getInt(6), rs.getFloat(7), rs.getTime(8), rs.getTime(9), rs.getFloat(10), rs.getBoolean(11), rs.getBoolean(12));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate()");
            throw new RuntimeException("Somthing error...");
        }
        return null;
    }
    
    public static TimeKeeping getTimeKeeping(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, employeeId, currentDate, startTime, endTime, punish, workingHours, startOverTime, endOverTime, overTimeHours, checkPay, validVac FROM TimeKeeping WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            if (rs.next()) {
                return new TimeKeeping(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getTime(4), rs.getTime(5), rs.getInt(6), rs.getFloat(7), rs.getTime(8), rs.getTime(9), rs.getFloat(10), rs.getBoolean(11), rs.getBoolean(12));
                
            }
            conn.close();
            return null;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.ContractDB.getTimeKeepingByEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void startTime(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO TimeKeeping(employeeId, punish)\n"
                    + "VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            ps.setInt(1, e.getId());
            java.time.LocalDateTime d = java.time.LocalDateTime.now();
            if (d.isAfter(java.time.LocalDateTime.of(d.getYear(), d.getMonthValue(), d.getDayOfMonth(), 8, 0, 0))) {
                ps.setInt(2, 1);
            } else {
                ps.setInt(2, 0);
            }
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.startDate()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void endTime(TimeKeeping t) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE TimeKeeping\n"
                    + "SET endTime = ?, punish = ?, workingHours = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            java.time.LocalDateTime d = java.time.LocalDateTime.now();
            Time endTime = new Time(d.getHour(), d.getMinute(), d.getSecond());
            ps.setTime(1, endTime);
            if (d.isBefore(java.time.LocalDateTime.of(d.getYear(), d.getMonthValue(), d.getDayOfMonth(), 17, 0, 0))) {
                ps.setInt(2, t.getPunish() + 1);
            } else {
                ps.setInt(2, t.getPunish());
            }
            ps.setDouble(3, (endTime.getTime() - t.getStartTime().getTime()) / 1000 / 60 / 60.0);
            ps.setInt(4, t.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.endTime()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void update(TimeKeeping t) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE TimeKeeping\n"
                    + "SET startTime = ?, endTime = ?, startOverTime = ?, endOverTime = ?, punish = ?, workingHours = ?, overTimeHours = ?  WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setTime(1, t.getStartTime());
            ps.setTime(2, t.getEndTime());
            ps.setTime(3, t.getStartOverTime());
            ps.setTime(4, t.getEndOverTime());
            if (t.getStartTime().after(Time.valueOf("08:00:00"))) {
                t.setPunish(t.getPunish() + 1);
            }
            if (t.getEndTime().before(Time.valueOf("19:00:00"))) {
                t.setPunish(t.getPunish() + 1);
            }
            ps.setInt(5, t.getPunish() + 1);
            if (t.getEndTime() != null) {
                ps.setDouble(6, (t.getEndTime().getTime() - t.getStartTime().getTime()) / 1000 / 60 / 60.0);
            } else {
                ps.setDouble(6, 0);
            }
            if (t.getEndOverTime() != null) {
                ps.setDouble(7, (t.getEndOverTime().getTime() - t.getStartOverTime().getTime()) / 1000 / 60 / 60.0);
            } else {
                ps.setDouble(7, 0);
            }
            ps.setInt(8, t.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại...");
        }
    }
    
    public static void startOverTime(TimeKeeping t) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE TimeKeeping\n"
                    + "SET startOverTime = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            java.time.LocalDateTime d = java.time.LocalDateTime.now();
            Time startOverTime = new Time(d.getHour(), d.getMinute(), d.getSecond());
            ps.setTime(1, startOverTime);
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.startOverTime()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void endOverTime(TimeKeeping t) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE TimeKeeping\n"
                    + "SET endOverTime = ?, overTimeHours = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            java.time.LocalDateTime d = java.time.LocalDateTime.now();
            Time endOverTime = new Time(d.getHour(), d.getMinute(), d.getSecond());
            ps.setTime(1, endOverTime);
            ps.setDouble(2, (endOverTime.getTime() - t.getStartOverTime().getTime()) / 1000 / 60 / 60.0);
            ps.setInt(3, t.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.endOverTime()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static float[] rateSalary(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT SUM(workingHours), SUM(punish), SUM(overTimeHours) FROM TimeKeeping\n"
                    + "WHERE employeeId = ? and checkPay=0 and currentDate < (Current_timestamp-1)\n"
                    + "GROUP BY employeeId";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                float[] res = {rs.getFloat(1), rs.getInt(2), rs.getFloat(3)};
                return res;
            }
            return null;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.ContractDB.endTime()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void paySalary(int empId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE TimeKeeping\n"
                    + "SET checkPay = 1\n"
                    + "WHERE employeeId = ? AND currentDate < (Current_timestamp-1)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, empId);
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.TimeKeepingDB.paySalary()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static void main(String[] args) {
//        for (float a : rateSalary(new Employee(1002))) {
//            System.out.println(a);
//        }
        String t = "10:10:10";
        System.out.println(Time.valueOf(t));
    }
}
