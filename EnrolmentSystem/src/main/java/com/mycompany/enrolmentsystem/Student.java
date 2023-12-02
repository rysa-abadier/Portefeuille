/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import static com.mycompany.enrolmentsystem.Accounts.students;
import static com.mycompany.enrolmentsystem.Subjects.enrolledSubjects;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author ron.g.abadier
 */
public class Student extends User{
    private Section section;
    private int yearLevel;
    
    public Student(String idNum, FullName fullName, Date birthdate, Gender gender){
        super(idNum, fullName, birthdate, gender);
    }
    
    public Student(String idNum, FullName fullName, Date birthdate, Gender gender, int yearLevel){
        super(idNum, fullName, birthdate, gender);
        this.yearLevel = yearLevel;
    }
    
    public Student(String idNum, FullName fullName, Date birthdate, Gender gender, int yearLevel, Section section){
        super(idNum, fullName, birthdate, gender);
        this.section = section;
        this.yearLevel = yearLevel;
    }
    
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    
    public String getYearAndSection(){
        return yearLevel + " - " + section;
    }
    
    public Student createStudent(String idNum, FullName fullName, Date birthdate, Gender gender, int yearLevel, Section section){
        Student newStudent = new Student(idNum, fullName, birthdate, gender, yearLevel, section);
        return newStudent;
    }
    
    public void editStudent(String idNum, String newFirstName, String newMiddleName, String newLastName, Gender newGender, Date newBirthdate, int newYearLevel, Section newSection){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getIdNum().equals(idNum)){
                students.get(i).getFullName().setFirstName(newFirstName);
                students.get(i).getFullName().setMiddleName(newMiddleName);
                students.get(i).getFullName().setLastName(newLastName);
                students.get(i).setGender(newGender);
                students.get(i).setBirthdate(newBirthdate);
                students.get(i).setYearLevel(newYearLevel);
                students.get(i).setSection(newSection);
            }
        }
    }
    
// Subjects
    public void enrollSubject(Subject subject){
        enrolledSubjects.add(subject);
    }
    
    public void removeSubject(String subjectCode){
        for(int i = 0; i < enrolledSubjects.size(); i++){
            if(enrolledSubjects.get(i).getSubjectCode().equals(subjectCode)){
                enrolledSubjects.remove(i);
            }
        }
    }
    
    public void viewSubjects(){
        for(int i = 0; i < enrolledSubjects.size(); i++){
            printLn("\nSubject Information:");
            printLn("   Subject Code: " + enrolledSubjects.get(i).getSubjectCode());
            printLn("   Subject Name: " + enrolledSubjects.get(i).getSubjectName());
            printLn("   Subject Teacher: " + enrolledSubjects.get(i).getSubjectTeacher());
        }
    }    
}