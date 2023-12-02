/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import static com.mycompany.enrolmentsystem.Accounts.admins;
import static com.mycompany.enrolmentsystem.Accounts.students;
import static com.mycompany.enrolmentsystem.Accounts.teachers;
import static com.mycompany.enrolmentsystem.Subjects.subjects;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author ron.g.abadier
 */
public class Administrator extends User {
    private String jobTitle;
    
    public Administrator(String idNum, FullName fullName, Date birthdate, Gender gender){
        super(idNum, fullName, birthdate, gender);
        students = new LinkedList<>();
        teachers = new LinkedList<>();
        admins = new LinkedList<>();
    }
    
    public Administrator(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle){
        super(idNum, fullName, birthdate, gender);
        this.jobTitle = jobTitle;
        students = new LinkedList<>();
        teachers = new LinkedList<>();
        admins = new LinkedList<>();
    }
    
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
// Student
    public Student createStudent(String idNum, FullName fullName, Date birthdate, Gender gender, int yearLevel, Section section){
        Student newStudent = new Student(idNum, fullName, birthdate, gender, yearLevel, section);
        return newStudent;
    }
    
    public void addStudent(Student student){
        students.add(student);
    }
    
    public void editStudent(String idNum, String newFirstName, String newMiddleName, String newLastName, Date newBirthdate, Gender newGender, int newYearLevel, Section newSection){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getIdNum().equals(idNum)){
                students.get(i).getFullName().setFirstName(newFirstName);
                students.get(i).getFullName().setMiddleName(newMiddleName);
                students.get(i).getFullName().setLastName(newLastName);
                students.get(i).setBirthdate(newBirthdate);
                students.get(i).setGender(newGender);
                students.get(i).setYearLevel(newYearLevel);
                students.get(i).setSection(newSection);
            }
        }
    }
    
    public void removeStudent(String idNum){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getIdNum().equals(idNum)){
                students.remove(i);
            }
        }
    }
    
    public void viewStudents(){
        for(int i = 0; i < students.size(); i++){
            printLn("\nStudent Information:");
            printLn("   ID Number: " + students.get(i).getIdNum());
            printLn("   Name: " + students.get(i).getFullName());
            printLn("   Birthdate: " + students.get(i).getBirthdate());
            printLn("   Gender: " + students.get(i).getGender());
            printLn("   Year and Section: " + students.get(i).getYearAndSection());
        }
    }
    
    
// Teacher
    public Teacher createTeacher(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle, String subjectExpertise){
        Teacher newTeacher = new Teacher(idNum, fullName, birthdate, gender, jobTitle, subjectExpertise);
        return newTeacher;
    }
    
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    
    public void editTeacher(String idNum, String newFirstName, String newMiddleName, String newLastName, Date newBirthdate, Gender newGender, String newJobTitle, String newSubjectExpertise){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getIdNum().equals(idNum)){
                teachers.get(i).getFullName().setFirstName(newFirstName);
                teachers.get(i).getFullName().setMiddleName(newMiddleName);
                teachers.get(i).getFullName().setLastName(newLastName);
                teachers.get(i).setBirthdate(newBirthdate);
                teachers.get(i).setGender(newGender);
                teachers.get(i).setJobTitle(newJobTitle);
                teachers.get(i).setSubjectExpertise(newSubjectExpertise);
            }
        }
    }
    
    public void removeTeacher(String idNum){
        for(int i = 0; i < teachers.size(); i++){
            if(teachers.get(i).getIdNum().equals(idNum)){
                teachers.remove(i);
            }
        }
    }
    
    public void viewTeachers(){
        for(int i = 0; i < teachers.size(); i++){
            printLn("\nTeacher Information:");
            printLn("   ID Number: " + teachers.get(i).getIdNum());
            printLn("   Name: " + teachers.get(i).getFullName());
            printLn("   Birthdate: " + teachers.get(i).getBirthdate());
            printLn("   Gender: " + teachers.get(i).getGender());
            printLn("   Subject Expertise and Job Title: " + teachers.get(i).getSubjectTeacher());
        }
    }
    
// Administrator
    public Administrator createAdmin(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle){
        Administrator newAdmin = new Administrator(idNum, fullName, birthdate, gender, jobTitle);
        return newAdmin;
    }
    
    public void addAdmin(Administrator admin){
        admins.add(admin);
    }
    
    public void editAdmin(String idNum, String newFirstName, String newMiddleName, String newLastName, Date newBirthdate, Gender newGender, String newJobTitle){
        for(int i = 0; i < admins.size(); i++){
            if(admins.get(i).getIdNum().equals(idNum)){
                admins.get(i).getFullName().setFirstName(newFirstName);
                admins.get(i).getFullName().setMiddleName(newMiddleName);
                admins.get(i).getFullName().setLastName(newLastName);
                admins.get(i).setBirthdate(newBirthdate);
                admins.get(i).setGender(newGender);
                admins.get(i).setJobTitle(newJobTitle);
            }
        }
    }
    
    public void removeAdmin(String idNum){
        for(int i = 0; i < admins.size(); i++){
            if(admins.get(i).getIdNum().equals(idNum)){
                admins.remove(i);
            }
        }
    }
    
    public void viewAdmins(){
        for(int i = 0; i < admins.size(); i++){
            printLn("\nAdministrator Information:");
            printLn("   ID Number: " + admins.get(i).getIdNum());
            printLn("   Name: " + admins.get(i).getFullName());
            printLn("   Birthdate: " + admins.get(i).getBirthdate());
            printLn("   Gender: " + admins.get(i).getGender());
            printLn("   Job Title: " + admins.get(i).getJobTitle());
        }
    }
    
// Subjects
    public Subject createSubject(String subjectCode, String subjectName, String subjectTeacher){
        Subject newSubject = new Subject(subjectCode, subjectName, subjectTeacher);
        return newSubject;
    }
    
    public void addSubject(Subject subject){
        subjects.add(subject);
    }
    
    public void editSubject(String subjectCode, String newSubjectName, String newSubjectTeacher){
        for(int i = 0; i < subjects.size(); i++){
            if(subjects.get(i).getSubjectCode().equals(subjectCode)){
                subjects.get(i).setSubjectName(newSubjectName);
                subjects.get(i).setSubjectTeacher(newSubjectTeacher);
            }
        }
    }
    
    public void removeSubject(String subjectCode){
        for(int i = 0; i < subjects.size(); i++){
            if(subjects.get(i).getSubjectCode().equals(subjectCode)){
                subjects.remove(i);
            }
        }
    }
    
    public void viewSubjects(){
        for(int i = 0; i < subjects.size(); i++){
            printLn("\nSubject Information:");
            printLn("   Subject Code: " + subjects.get(i).getSubjectCode());
            printLn("   Subject Name: " + subjects.get(i).getSubjectName());
            printLn("   Subject Teacher: " + subjects.get(i).getSubjectTeacher());
        }
    }    
}