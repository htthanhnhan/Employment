/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Contract;
import model.entity.Department;
import model.entity.Employee;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

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

    public static ArrayList<Employee> getAllEmployeeByDepartmentId(int departmentId) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "WHERE e.departmentId = ? and e.positionId <> 1 and e.positionId <> 2\n"
                    + "ORDER BY e.id";
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
            System.out.println("Error at model.dao.EmployeeDB.getAllEmployeeByDepartmentId()");
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
        throw new RuntimeException("Nhân viên không tồn tại!");
    }

    public static Employee getEmployeeByName(String name) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT e.id,e.fullName,e.email,e.address, e.tel,e.positionId, p.name, e.managerId, e.activity, e.departmentId, e.avatar, e.sex from Employee e\n"
                    + "INNER JOIN Position p ON e.positionId = p.id\n"
                    + "WHERE upper(e.fullName) = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getInt(10), rs.getString(11), rs.getBoolean(12));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.EmployeeDB.getEmployeeByName()");
            throw new RuntimeException("Somthing error...");
        }
        throw new RuntimeException("Nhân viên không tồn tại!");
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
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }

    public static void exportEmployee(Employee e) {
        try {
            File f = new File("./");
            String file = f.getCanonicalPath() + "/build/web/danhsachnhanvien.xls";
            String path = f.getCanonicalPath() + "/build/web";
            String link = "./danhsachnhanvien.xls";
            ArrayList<Employee> list = new ArrayList<>();
            if (new Account(e.getId()).getRoleId() == 1 || e.getPositionId() == 1) {
                list = EmployeeDB.getAllEmployee();
            } else if (e.getPositionId() == 2) {
                list = EmployeeDB.getAllEmployeeByDepartmentId(e.getDepartmentId());
            } else if (e.getPositionId() == 3) {
                list = EmployeeDB.getAllEmployeeByManagerId(e.getId());
            }
            File f1 = new File(path);
            f1.mkdirs();
            HSSFWorkbook wb2003 = new HSSFWorkbook();
            HSSFSheet sheet = (HSSFSheet) wb2003.createSheet();
            HSSFRow row = sheet.createRow(0);
            row.createCell(0, CellType.STRING).setCellValue("Số thứ tự");
            row.createCell(1, CellType.STRING).setCellValue("Tên nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Account");
            row.createCell(3, CellType.STRING).setCellValue("Giới tính");
            row.createCell(4, CellType.STRING).setCellValue("Phòng ban");
            row.createCell(5, CellType.STRING).setCellValue("Chức vụ");
            int index = 1;
            int i = 0;
            while (i < list.size()) {
                Employee em = list.get(i);
                row = sheet.createRow(index);
                row.createCell(0, CellType.STRING).setCellValue(i + 1);
                row.createCell(1, CellType.STRING).setCellValue(em.getFullName());
                row.createCell(2, CellType.STRING).setCellValue(em.getUserName());
                row.createCell(3, CellType.STRING).setCellValue(em.getGen());
                row.createCell(4, CellType.STRING).setCellValue(em.getDepartmentName());
                row.createCell(5, CellType.STRING).setCellValue(em.getPositionName());
                i++;
                index++;
            }
            for (int n = 0; n < 6; n++) {
                sheet.autoSizeColumn(n);
            }
            OutPutFile.createOutputFile(wb2003, file);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        EmployeeDB.exportEmployee(new Employee(new Account("admin").getEmpId()));
    }

    public static void update(Employee e) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE Employee\n"
                    + "SET fullName = ?, email = ?, address = ?, tel = ?, positionId = ?, managerId = ?, departmentId = ?, avatar = ?\n"
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
            if (e.getManagerId() == 0) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, e.getManagerId());
            }
            ps.setInt(7, e.getDepartmentId());
            ps.setString(8, e.getAvatar());
            ps.setInt(9, e.getId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error as model.dao.EmployeeDB.update()");
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
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
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
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
            throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
