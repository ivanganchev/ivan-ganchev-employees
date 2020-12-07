package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FileParser {

    private File file;
    private Scanner fileReader;
    List<String[]> splittedLines;
    List<Employee> employees;

    public FileParser(String file) throws FileNotFoundException {
        this.file = new File(file);
        fileReader = new Scanner(this.file);
    }

    private List<String> readFromFile() throws FileNotFoundException {
        List<String> fileLines= new ArrayList<String>();

        while (fileReader.hasNextLine()) {
            fileLines.add(fileReader.nextLine());
        }
        fileReader.close();

        return fileLines;
    }

    public List<Employee> getEmployees() throws FileNotFoundException {
        List<String> fileLines = this.readFromFile();
        splittedLines = new ArrayList<String[]>();
        employees = new ArrayList<Employee>();
        Set<Integer> employeeIds = new HashSet<Integer>();

        String[] firstLine =  fileLines.get(0).split(", ");
        employees.add(new Employee(Integer.parseInt(firstLine[0])));
        employeeIds.add(Integer.parseInt(firstLine[0]));

        for(int i = 0; i < fileLines.size(); i++) {
            String[] splittedLine = fileLines.get(i).split(", ");
            splittedLines.add(splittedLine);
            int empId = Integer.parseInt(splittedLine[0]);

            if(!employeeIds.add(empId)) {
                continue;
            } else {
                employees.add(new Employee(empId));
                employeeIds.add(empId);
            }
        }

        for(String[] line : splittedLines) {
            int empId = Integer.parseInt(line[0]);
            int projectId = Integer.parseInt(line[1]);

            for(Employee emp : employees) {
                if (emp.getEmpId() == empId) {
                    emp.addProject(new Project(projectId, line[2], line[3]));
                }
            }
        }
        return employees;
    }

    public Set<Integer> getProjectsIds() {
        Set<Integer> projectsIds = new HashSet<Integer>();

        for(String[] line : splittedLines) {
            projectsIds.add(Integer.parseInt(line[1]));
        }

        return projectsIds;
    }
} 