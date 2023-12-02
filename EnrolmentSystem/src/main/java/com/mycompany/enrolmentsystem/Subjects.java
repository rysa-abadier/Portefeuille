/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author ron.g.abadier
 */
public class Subjects {
    public static LinkedList<Subject> subjects = new LinkedList<>();
    public static LinkedList<Subject> enrolledSubjects = new LinkedList<>();
    
// Subjects
    public static LinkedList<Subject> sortCode(LinkedList<Subject> subjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectCode());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(subjects, comparator);
        return subjects;
    }
    
    public static LinkedList<Subject> sortName(LinkedList<Subject> subjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(subjects, comparator);
        return subjects;
    }
    
    public static LinkedList<Subject> sortTeacher(LinkedList<Subject> subjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectTeacher());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(subjects, comparator);
        return subjects;
    }
    
// Enrolled Subjects
    public static LinkedList<Subject> sortEnrolledCode(LinkedList<Subject> enrolledSubjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectCode());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(enrolledSubjects, comparator);
        return enrolledSubjects;
    }
    
    public static LinkedList<Subject> sortEnrolledName(LinkedList<Subject> enrolledSubjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(enrolledSubjects, comparator);
        return enrolledSubjects;
    }
    
    public static LinkedList<Subject> sortEnrolledTeacher(LinkedList<Subject> enrolledSubjects, boolean reverse) {
        Comparator<Subject> comparator = Comparator.comparing(s -> s.getSubjectTeacher());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(enrolledSubjects, comparator);
        return enrolledSubjects;
    }
}
