/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import java.util.Date;
import java.util.LinkedList;
import static com.mycompany.enrolmentsystem.Subjects.enrolledSubjects;

/**
 *
 * @author ron.g.abadier
 */
public class User {
    private String idNum;
    private FullName fullName;
    private Gender gender;
    private Date birthdate;
    
    public User(String idNum, FullName fullName, Date birthdate, Gender gender){
        this.idNum = idNum;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        enrolledSubjects = new LinkedList<>();
    }
    
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
    
    public FullName getFullName(){
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    static void printLn(String message){
        System.out.println(message);
    }
}