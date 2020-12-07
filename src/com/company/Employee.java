package com.company;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {

    private int empId;
    private List<Project> projects;

    public Employee(int empId) {
        this.empId = empId;
        projects = new ArrayList<Project>();
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public Project getProjectById(int projectId) {
         for(int i = 0; i < this.projects.size(); i++) {
             if(this.projects.get(i).getProjectId() == projectId) {
                 return projects.get(i);
             }
         }
         return null;
    }

//    @Override
//    public String toString() {
//        return "Employee ID - " + this.empId + " | " + "Project ID - " + this.projectId + " | " + "Date From - " + this.dateFrom + " | " +  "Date To - " + this.dateTo;
//    }
}
