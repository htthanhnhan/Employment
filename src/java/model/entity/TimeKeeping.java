/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.TimeKeepingDB;


/**
 *
 * @author ACER
 */
public class TimeKeeping {
    private int id;
    private int employeeId;
    private Date currentDate;
    private Time startTime;
    private Time endTime;
    private int punish;
    private float workingHours;
    private Time startOverTime;
    private Time endOverTime;
    private float overTimeHours;
    private boolean checkPay;
    private boolean validVac;

    public TimeKeeping() {
    }

    public TimeKeeping(int id, int employeeId, Date currentDate, Time startTime, Time endTime, int punish, float workingHours, Time startOverTime, Time endOverTime, float overTimeHours, boolean checkPay, boolean validVac) {
        this.id = id;
        this.employeeId = employeeId;
        this.currentDate = currentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.punish = punish;
        this.workingHours = workingHours;
        this.startOverTime = startOverTime;
        this.endOverTime = endOverTime;
        this.overTimeHours = overTimeHours;
        this.checkPay = checkPay;
        this.validVac = validVac;
    }
    
    public TimeKeeping(int employeeId, Date currentDate, Time startTime, 
            Time endTime, int punish, float workingHours, Time startOverTime, 
            Time endOverTime, float overTimeHours, boolean checkPay, boolean validVac) {
        this.employeeId = employeeId;
        this.currentDate = currentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.punish = punish;
        this.workingHours = workingHours;
        this.startOverTime = startOverTime;
        this.endOverTime = endOverTime;
        this.overTimeHours = overTimeHours;
        this.checkPay = checkPay;
        this.validVac = validVac;
    }
    
    public TimeKeeping(TimeKeeping tk) {
        this(tk.id, tk.employeeId, tk.currentDate, tk.startTime, tk.endTime, tk.punish, tk.workingHours, tk.startOverTime, tk.endOverTime, tk.overTimeHours, tk.checkPay, tk.validVac);
    }
    
    public TimeKeeping(int id) {
        this(TimeKeepingDB.getTimeKeeping(id));
    }
    
    public ArrayList<TimeKeeping> listTimeKeeping(Employee e) {
        return TimeKeepingDB.getTimeKeepingByEmployee(e);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getCurrentDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(currentDate);
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getPunish() {
        return punish;
    }

    public void setPunish(int punish) {
        this.punish = punish;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public Time getStartOverTime() {
        return startOverTime;
    }

    public void setStartOverTime(Time startOverTime) {
        this.startOverTime = startOverTime;
    }

    public Time getEndOverTime() {
        return endOverTime;
    }

    public void setEndOverTime(Time endOverTime) {
        this.endOverTime = endOverTime;
    }

    public float getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(float overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public boolean isCheckPay() {
        return checkPay;
    }

    public void setCheckPay(boolean checkPay) {
        this.checkPay = checkPay;
    }

    public boolean isValidVac() {
        return validVac;
    }

    public void setValidVac(boolean validVac) {
        this.validVac = validVac;
    }

    @Override
    public String toString() {
        return "TimeKeeping{" + "id=" + id + ", employeeId=" + employeeId + ", currentDate=" + currentDate + ", startTime=" + startTime + ", endTime=" + endTime + ", punish=" + punish + ", workingHours=" + workingHours + ", startOverTime=" + startOverTime + ", endOverTime=" + endOverTime + ", overTimeHours=" + overTimeHours + ", checkPay=" + checkPay + ", validVac=" + validVac + '}';
    }
    
    public static void startTime(Employee e) {
        TimeKeepingDB.startTime(e);
    }
    
    public void endTime() {
        TimeKeepingDB.endTime(this);
    }
    
    public void startOverTime() {
        TimeKeepingDB.startOverTime(this);
    }
    
    public void endOverTime() {
        TimeKeepingDB.endOverTime(this);
    }
    
    public Employee getEmployee() {
        return new Employee(this.employeeId);
    }
    
    public void update() {
        TimeKeepingDB.update(this);
    }
}
