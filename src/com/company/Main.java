package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        FileParser fileParser = new FileParser(fileName);
        List<Employee> employees = fileParser.getEmployees();

        HashMap<Integer, List<Employee>> projectsEmployees = getEmployeesGroupedByProject(employees, fileParser.getProjectsIds());
        pairEmployeesByProject(employees, projectsEmployees);
    }

    public static HashMap<Integer, List<Employee>> getEmployeesGroupedByProject(List<Employee> employees, Set<Integer> projectsIds) {
        HashMap<Integer, List<Employee>> projectsEmployees = new HashMap<Integer, List<Employee>>();

        for(Integer projectId : projectsIds) {
            projectsEmployees.put(projectId, new ArrayList<Employee>());
        }

        for(Employee emp: employees) {
            for(Project project : emp.getProjects()) {
                projectsEmployees.get(project.getProjectId()).add(emp);
            }
        }
        return projectsEmployees;
    }

    public static void pairEmployeesByProject(List<Employee> employees, HashMap<Integer, List<Employee>> employeesByProject) throws ParseException {
        for(Map.Entry<Integer, List<Employee>> entry : employeesByProject.entrySet()) {
            List<Employee> emps = entry.getValue();
            List<Pair> pairs = new ArrayList<Pair>();
            for (int i = 0 ; i < employees.size(); i++) {
                pairs.add(checkAndCalculate(employees.get(i), employees.get(i + 1), entry.getKey()));
            }
        }
    }

    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }

    public static Pair checkAndCalculate(Employee firstEmployee, Employee secondEmployee, int projectId) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String firstEmpoyeeStartDate = firstEmployee.getProjectById(projectId).getDateFrom();
        String firstEmpoyeeEndDate = firstEmployee.getProjectById(projectId).getDateTo();
        String secondEmpoyeeStartDate = firstEmployee.getProjectById(projectId).getDateFrom();
        String secondEmpoyeeEndDate = firstEmployee.getProjectById(projectId).getDateTo();


        Date firstDateStart = format.parse(firstEmpoyeeStartDate);
        Date firstDateEnd;
        Date secondDateStart = format.parse(secondEmpoyeeStartDate);
        Date secondDateEnd;

        Pair pair = new Pair();

        if(firstEmpoyeeEndDate.equals("NULL")) {
            firstDateEnd = new Date();
        } else {
            firstDateEnd = format.parse(firstEmpoyeeEndDate);
        }

        if(secondEmpoyeeEndDate.equals("NULL")) {
            secondDateEnd = new Date();
        } else {
            secondDateEnd = format.parse(secondEmpoyeeEndDate);
        }

        if(isOverlapping(firstDateStart, firstDateEnd, secondDateStart, secondDateEnd)) {
            pair.setFirstEmployee(firstEmployee);
            pair.setSecondEmployee(secondEmployee);
            pair.addTogetherWorkTime(firstDateStart, firstDateEnd, secondDateStart, secondDateEnd);
        }

        return pair;
    }
}
