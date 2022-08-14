/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Department;

/**
 *
 * @author ACER
 */
public class DepartmentDB implements DBContext {

    public static ArrayList<Department> getAllDepartment() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, name, roomNo, managerId\n"
                    + "FROM Department";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Department> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Department(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.getAllDepartment()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static ArrayList<Department> getAllDepartmentNoManager() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, name, roomNo, managerId\n"
                    + "FROM Department WHERE managerId is null";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Department> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Department(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));

            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.getAllDepartment()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static Department getDepartment(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, name, roomNo, managerId\n"
                    + "FROM Department\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.getDepartment()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Phòng ban không tồn tại!");
    }
    
    public static Department getDepartmentByName(String name) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT id, name, roomNo, managerId\n"
                    + "FROM Department\n"
                    + "WHERE upper(name) = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.getDepartmentByName()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Phòng ban không tồn tại!");
    }

    public static void update(Department d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Department\n"
                    + "SET name = ?, roomNo = ?\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, d.getName());
            ps.setInt(2, d.getRoomNo());
            ps.setInt(3, d.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void delete(Department d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE Department\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.delete()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void create(Department d) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Department(name, roomNo, managerId)\n"
                    + "VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, d.getName());
            ps.setInt(2, d.getRoomNo());
            if(d.getManagerId() != 0) {
                ps.setInt(3, d.getManagerId());
            } else {
                ps.setString(3, null);
            }
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at model.dao.DepartmentDB.create()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
    
    public static void main(String[] args) {
//        new Department("Phòng nhân sự", 102).create();
//for(Department d : DepartmentDB.getAllDepartmentNoManager()) {
//    System.out.println(d);
//}
System.out.println(DepartmentDB.getDepartmentByName("nhân sự"));
    }
}
