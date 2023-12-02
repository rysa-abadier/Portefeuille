/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enrolmentsystem;

/**
 *
 * @author ron.g.abadier
 */
public class FullName {
    public String firstName;
    public String middleName;
    public String lastName;

    public FullName(String fName, String mName, String lName) {
        firstName = fName;
        middleName = mName;
        lastName = lName;
    }
    
    public String getFullName() {
        StringBuilder ab = new StringBuilder();
        ab.append(firstName);
        ab.append(" ");
        ab.append(middleName);
        ab.append(" ");
        ab.append(lastName);
        ab.append(" ");
        return ab.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
