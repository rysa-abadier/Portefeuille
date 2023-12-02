/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import static com.mycompany.enrolmentsystem.Accounts.students;
import java.util.Date;

/**
 *
 * @author ron.g.abadier
 */
public class Teacher extends Administrator{
    private String subjectExpertise;
    
    public Teacher(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle){
        super(idNum, fullName, birthdate, gender, jobTitle);
    }
    
    public Teacher(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle, String subjectExpertise){
        super(idNum, fullName, birthdate, gender, jobTitle);
        this.subjectExpertise = subjectExpertise;
    }
    
    
    public String getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(String subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }
    
    public String getSubjectTeacher(){
        return subjectExpertise + " " + getJobTitle();
    }

    public void viewClassList(){
        for(int i = 0; i < students.size(); i++){
            printLn("\nSubject Information:");
            printLn("   ID Number: " + students.get(i).getIdNum());
            printLn("   Name: " + students.get(i).getFullName());
            printLn("   Year and Section: " + students.get(i).getYearAndSection());
            printLn("   Birthdate: " + students.get(i).getBirthdate());
            printLn("   Gender: " + students.get(i).getGender());
        }
    }
}