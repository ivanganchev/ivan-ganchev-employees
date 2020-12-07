package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pair {
    Employee firstEmployee;
    Employee secondEmployee;
    long workTimeSum;


    public Employee getFirstEmployee() {
        return firstEmployee;
    }

    public void setFirstEmployee(Employee firstEmployee) {
        this.firstEmployee = firstEmployee;
    }

    public Employee getSecondEmployee() {
        return secondEmployee;
    }

    public void setSecondEmployee(Employee secondEmployee) {
        this.secondEmployee = secondEmployee;
    }

    public long getWorkTimeSum() {
        return workTimeSum;
    }

    public void addTogetherWorkTime (Date firstDateStart, Date firstDateEnd, Date secondDateStart, Date secondDateEnd) {
        Date overlapStart = null;
        Date overlapEnd = null;

        if(firstDateStart.before(secondDateStart)) {
            overlapStart = firstDateStart;
        } else {
            overlapStart = secondDateStart;
        }

        if(firstDateEnd.before(secondDateEnd)) {
            overlapEnd = secondDateEnd;
        } else {
            overlapEnd = firstDateEnd;
        }

        this.workTimeSum += Math.abs(overlapEnd.getTime() - overlapStart.getTime());
    }

}
