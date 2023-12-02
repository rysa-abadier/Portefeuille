/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

/**
 *
 * @author ron.g.abadier
 */
public class Subject {
    private String subjectCode, subjectName, subjectTeacher;

    public Subject(String subjectCode, String subjectName, String subjectTeacher) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
}
