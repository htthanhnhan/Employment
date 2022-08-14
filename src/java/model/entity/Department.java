/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.dao.DepartmentDB;

/**
 *
 * @author ACER
 */
public class Department {
    private int id;
    private String name;
    private int roomNo;
    private int managerId;

    public Department() {
    }

    public Department(int id, String name, int roomNo, int managerId) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
        this.managerId = managerId;
    }
    
    public Department(int id, String name, int roomNo) {
        this.id = id;
        this.name = name;
        this.roomNo = roomNo;
    }
    
    public Department(String name, int roomNo, int managerId) {
        this.name = name;
        this.roomNo = roomNo;
        this.managerId = managerId;
    }
    
    public Department(String name, int roomNo) {
        this.name = name;
        this.roomNo = roomNo;
    }

    public Department(Department d) {
        this(d.id, d.name, d.roomNo, d.managerId);
    }
    
    public Department(int id) {
        this(DepartmentDB.getDepartment(id));
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
    public String getManagerName(){
        try {
            return new Employee(managerId).getFullName();
        } catch (Exception e) {
            return "-";
        }
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", roomNo=" + roomNo + ", managerId=" + managerId + '}';
    }
    
    public void create() {
        DepartmentDB.create(this);
    }
    
    public void update(){
        DepartmentDB.update(this);
    }
    
    public void delete(){
        DepartmentDB.delete(this);
    }
}
