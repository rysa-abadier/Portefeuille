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
public class Accounts {
    public static LinkedList<Student> students = new LinkedList<>();
    public static LinkedList<Teacher> teachers = new LinkedList<>();
    public static LinkedList<Administrator> admins = new LinkedList<>();
    public static LinkedList<Registrar> registrars = new LinkedList<>();
    
// Student    
    public static LinkedList<Student> sortStudentIdNum(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getIdNum());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
    
    public static LinkedList<Student> sortStudentLastName(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getFullName().getLastName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
  
    public static LinkedList<Student> sortStudentFirstName(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getFullName().getFirstName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
  
    public static LinkedList<Student> sortStudentMiddleName(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getFullName().getMiddleName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
  
    public static LinkedList<Student> sortStudentGender(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getGender());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
    
    public static LinkedList<Student> sortStudentYearLevel(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getYearLevel());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
    
    public static LinkedList<Student> sortStudentSection(LinkedList<Student> students, boolean reverse) {
        Comparator<Student> comparator = Comparator.comparing(s -> s.getSection());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(students, comparator);
        return students;
    }
   
// Teacher   
    public static LinkedList<Teacher> sortTeacherIdNum(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getIdNum());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
    
    public static LinkedList<Teacher> sortTeacherLastName(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getFullName().getLastName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
  
    public static LinkedList<Teacher> sortTeacherFirstName(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getFullName().getFirstName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
  
    public static LinkedList<Teacher> sortTeacherMiddleName(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getFullName().getMiddleName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
  
    public static LinkedList<Teacher> sortTeacherGender(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getGender());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
    
    public static LinkedList<Teacher> sortTeacherJobTitle(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getJobTitle());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
    
    public static LinkedList<Teacher> sortTeacherSubjectExpertise(LinkedList<Teacher> teachers, boolean reverse) {
        Comparator<Teacher> comparator = Comparator.comparing(s -> s.getSubjectExpertise());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(teachers, comparator);
        return teachers;
    }
   
// Administrator    
    public static LinkedList<Administrator> sortAdminIdNum(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getIdNum());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
    
    public static LinkedList<Administrator> sortAdminLastName(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getFullName().getLastName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
  
    public static LinkedList<Administrator> sortAdminFirstName(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getFullName().getFirstName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
  
    public static LinkedList<Administrator> sortAdminMiddleName(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getFullName().getMiddleName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
  
    public static LinkedList<Administrator> sortAdminGender(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getGender());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
    
    public static LinkedList<Administrator> sortAdminJobTitle(LinkedList<Administrator> admins, boolean reverse) {
        Comparator<Administrator> comparator = Comparator.comparing(s -> s.getJobTitle());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(admins, comparator);
        return admins;
    }
  
// Registrar    
    public static LinkedList<Registrar> sortRegistrarIdNum(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getIdNum());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
    
    public static LinkedList<Registrar> sortRegistrarLastName(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getFullName().getLastName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
  
    public static LinkedList<Registrar> sortRegistrarFirstName(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getFullName().getFirstName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
  
    public static LinkedList<Registrar> sortRegistrarMiddleName(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getFullName().getMiddleName());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
  
    public static LinkedList<Registrar> sortRegistrarGender(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getGender());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
    
    public static LinkedList<Registrar> sortRegistrarJobTitle(LinkedList<Registrar> registrars, boolean reverse) {
        Comparator<Registrar> comparator = Comparator.comparing(s -> s.getJobTitle());
        if (reverse) {
            comparator = comparator.reversed();
        }
        Collections.sort(registrars, comparator);
        return registrars;
    }
}
