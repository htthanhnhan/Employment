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
import model.entity.Account;
import model.entity.Contract;
import model.entity.Department;
import model.entity.Employee;

/**
 *
 * @author NguyenVy
 */
public class EmployeeDB implements DBContext {

    public static ArrayList<Employee> getAllEmployee() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Account a ON e.id = a.empId\n"
                    + "INNER JOIN Position p on p.id = e.positionId\n"
                    + "WHERE a.roleId <> 1 and p.id <> 1\n"
                    + "ORDER BY e.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllEmployee()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<Employee> getAllEmployeeByManagerId(int managerId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "WHERE e.managerId = ?\n"
                    + "ORDER BY e.id";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllEmployeeByManagerId()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<Employee> getAllManager() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "INNER JOIN Account a ON e.id = a.empId\n"
                    + "WHERE e.positionId <> 4 and a.roleId <> 1\n";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllManager()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<Employee> getAllLeaderRoom(int departmentId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "INNER JOIN Account a ON e.id = a.empId\n"
                    + "WHERE e.positionId = 3 and a.roleId <> 1 and e.departmentId = ?\n";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllManager()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static ArrayList<Employee> getAllManagerLeader() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "INNER JOIN Account a ON e.id = a.empId\n"
                    + "WHERE e.positionId = 1 or e.positionId = 2\n";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Employee> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12)));

            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getAllManager()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static Employee getEmployee(int id) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "WHERE e.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getEmployee()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Nh??n vi??n kh??ng t???n t???i!");
    }

    public static Employee create(Employee e, Contract c) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Employee(fullName, email, address, tel, positionId, managerId, departmentId, sex)\n"
                    + "OUTPUT inserted.id\n"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Characters.format(e.getFullName()));
            ps.setString(2, e.getEmail());
            ps.setString(3, Characters.format(e.getAddress()));
            ps.setString(4, e.getTel());
            ps.setInt(5, e.getPositionId());
            if (e.getManagerId() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, e.getManagerId());
            }
            ps.setInt(7, e.getDepartmentId());
            ps.setBoolean(8, e.isSex());
            ResultSet rs = ps.executeQuery();
            int empId = -1;
            if (rs.next()) {
                empId = rs.getInt(1);
                Account a = new Account();
                a.setEmpId(empId);
                if (e.getPositionId() == 4) {
                    a.setRoleId(3);
                } else {
                    a.setRoleId(2);
                }
                c.setEmpID(empId);
                c.create();
                a.setUserName(Characters.abbreviation(e.getFullName()) + "" + empId);
                a.create();
                if (e.getPositionId() == 2) {
                    Department d = new Department(e.getDepartmentId());
                    d.setManagerId(empId);
                    d.update();
                }
            }
            conn.commit();
            conn.close();
            return new Employee(empId);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.create()");
            throw new RuntimeException("C?? l???i x???y ra, vui l??ng th??? l???i!");
        }
    }

    public static void update(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Employee\n"
                    + "SET fullName = ?, email = ?, address = ?, tel = ?, positionId = ?, managerId = ?, departmentId = ?, sex = ?, avatar = ?\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getTel());
            ps.setInt(5, e.getPositionId());
            Account a = new Account(e.getId());
            if (e.getPositionId() == 4) {
                a.setRoleId(3);
            } else {
                a.setRoleId(2);
            }
            a.update();
            ps.setBoolean(8, e.isSex());
            if (e.getManagerId() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, e.getManagerId());
            }
            ps.setInt(7, e.getDepartmentId());
            ps.setString(9, e.getAvatar());
            ps.setInt(10, e.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.update()");
            throw new RuntimeException("C?? l???i x???y ra, vui l??ng th??? l???i!");
        }
    }

    public static void block(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Employee\n"
                    + "SET activity = ?\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, e.isActivity());
            ps.setInt(2, e.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.update()");
            throw new RuntimeException("C?? l???i x???y ra, vui l??ng th??? l???i!");
        }
    }

    public static void delete(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE Employee\n"
                    + "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.delete()");
            throw new RuntimeException("C?? l???i x???y ra, vui l??ng th??? l???i!");
        }
    }

    public static void main(String[] args) {

        //        new Employee("Nguy???n Th??? Kim Vy", "vy@gmail.com", "???? N???ng", "0795457231", 3, 0, 1).create(new Contract(Date.valueOf("2024-10-25"), 10000000, "H??? tr??? x??ng xe"));
//        for (Employee e : getAllEmployee()) {
//            System.out.println(e.getUserName());
//        }
//        System.out.println(new Contract(Date.valueOf("2024-10-20"), 10000000, "H??? tr??? x??ng xe"));
//        new Employee(1005).paySalary();
//        System.out.println(new Employee(1005).getSalaryBasic());
//        for (Employee e : getAllEmployee()) {
//            System.out.println(e);
//        }
//        System.out.println("---");
//        System.out.println(EmployeeDB.getAllEmployee().get(0));
//        System.out.println(EmployeeDB.getAllEmployee().indexOf(EmployeeDB.getAllEmployee().get(0)));
//new Employee(1002).startTime();
//        InetAddress myIP = InetAddress.getLoopbackAddress();
//        System.out.println(myIP.getHostAddress());
    }
}
