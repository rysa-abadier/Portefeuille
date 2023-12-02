/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

import static com.mycompany.enrolmentsystem.Accounts.registrars;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author ron.g.abadier
 */
public class Registrar extends Administrator{    
    public Registrar(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle) {
        super(idNum, fullName, birthdate, gender, jobTitle);
        registrars = new LinkedList<>();
    }

    public Registrar createRegistrar(String idNum, FullName fullName, Date birthdate, Gender gender, String jobTitle){
        Registrar newRegistrar = new Registrar(idNum, fullName, birthdate, gender, jobTitle);
        return newRegistrar;
    }
    
    public void addRegistrar(Registrar registrar){
        registrars.add(registrar);
    }
    
    public void editRegistrar(String idNum, String newFirstName, String newMiddleName, String newLastName, Date newBirthdate, Gender newGender,  String newJobTitle){
        for(int i = 0; i < registrars.size(); i++){
            if(registrars.get(i).getIdNum().equals(idNum)){
                registrars.get(i).getFullName().setFirstName(newFirstName);
                registrars.get(i).getFullName().setMiddleName(newMiddleName);
                registrars.get(i).getFullName().setLastName(newLastName);
                registrars.get(i).setBirthdate(newBirthdate);
                registrars.get(i).setGender(newGender);
                registrars.get(i).setJobTitle(newJobTitle);
            }
        }
    }
    
    public void viewRegistrars(){
        for(int i = 0; i < registrars.size(); i++){
            printLn("\nRegistrar Information:");
            printLn("   ID Number: " + registrars.get(i).getIdNum());
            printLn("   Name: " + registrars.get(i).getFullName());
            printLn("   Birthdate: " + registrars.get(i).getBirthdate());
            printLn("   Gender: " + registrars.get(i).getGender());
            printLn("   Job Title: " + registrars.get(i).getJobTitle());
        }
    }
}
