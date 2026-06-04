package org.example.utils;

import org.example.pojo.EmpLoginLog;

public class CurrentHolder {

    //private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<EmpLoginLog> CURRENT_LOCAL = new ThreadLocal<>();

    /*public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }*/
    public static void setCurrent(EmpLoginLog empLoginLog) {
        CURRENT_LOCAL.set(empLoginLog);
    }

    /*public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }*/
    public static EmpLoginLog getCurrent() {
        return CURRENT_LOCAL.get();
    }
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
