package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentUser", eager = true)

public class User {
    int id;
    String username;
    String password;
    String firstName;
    String lastName;
    String phoneNo;
    String email;
    
    ArrayList userData;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String delete(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");

        Statement stmt = con.createStatement();
        int result = stmt.executeUpdate("delete from `customers` where id=" +(id));

        return "grid.xhtml";
        
    }
    
    public String update(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        // we will execute an update sql to table user
        //sending the new values passed to you      
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");

        Statement stmt = con.createStatement();
        int result = stmt.executeUpdate("update `customers` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail()+ "\" where id=" + u.getId());

        return "grid.xhtml";
    }
    
    public String edit(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from customers where id=" +(id));
        
        if(rs.next())
        {
            User temp = new User();
            temp.id = rs.getInt("id");
            temp.username = rs.getString("username");
            temp.password = rs.getString("password");
            temp.firstName = rs.getString("first_name");
            temp.lastName = rs.getString("last_name");
            temp.phoneNo = rs.getString("phone_number");
            temp.email = rs.getString("email_address");
            sessionMap.put("editUser", temp);
            
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "edit.xhtml";
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        userData = new ArrayList();
        
        // JDBC
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from customers");
        
        while(rs.next())
        {
            User temp = new User();
            temp.id = rs.getInt("id");
            temp.username = rs.getString("username");
            temp.password = rs.getString("password");
            temp.firstName = rs.getString("first_name");
            temp.lastName = rs.getString("last_name");
            temp.phoneNo = rs.getString("phone_number");
            temp.email = rs.getString("email_address");
            userData.add(temp);
        }
        // JDBC
        return userData;
    }
    
    public boolean save() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");

        Statement stmt = con.createStatement();
        int result = stmt.executeUpdate("insert into customers (`username`,`password`, `first_name`, `last_name`, `phone_number`, `email_address`) "
                + "values ('"+ this.username +"', '"+ this.password +"', '"+ this.firstName +"', '"+ this.lastName +"', '"+ this.phoneNo +"', '"+ this.email +"');");
        if(result == 1)
            return true;
        else 
            return false;
        
    }      
    
    public String submit() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       if(this.save())
           return "response.xhtml";
       else 
           return "register.xhtml";
    }
}