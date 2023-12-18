package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
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
    String address1;
    String address2;
    String role;
    
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Role getRole() {
        if (role != null) {
            return Role.valueOf(role);
        } else {
            return Role.SELECTED_ROLE; // default
        }
    }

    public void setRole(Role role) {
        this.role = (role != null) ? role.name() : Role.SELECTED_ROLE.name();
    }

    private Statement mydbconnect()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        Connection con = DriverManager.getConnection(
                         "jdbc:mysql://localhost:3306/bakery_db?zeroDateTimeBehavior=CONVERT_TO_NULL",
                         "root",
                         "");
        
        Statement stmt = con.createStatement();
        
        return stmt;
    }
    
    public String deleteCustomer(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        User loggedAdmin = (User) sessionMap.get("logUser");

        if (loggedAdmin != null) {
            Role adminRole = loggedAdmin.getRole();
        
            if (Role.MANAGER.equals(adminRole) || Role.SYSTEM_ADMIN.equals(adminRole)) {
                int result = mydbconnect().executeUpdate("delete from `customers` where id=" + id);

                if (Role.MANAGER.equals(adminRole)) {
                    return "mCustomerGrid.xhtml";
                } else if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                    return "adCustomerGrid.xhtml";
                } else {
                return "errorPage.xhtml";
                }
            }
        }
        
        return "adAdminEdit.xhtml";
    }
    
    public String deleteAdmin(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("delete from `admins` where id=" +(id));

        return "adAdminGrid.xhtml";
    }
    
    public String deleteManager(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("delete from `admins` where id=" +(id));

        return "adManagerGrid.xhtml";
    }
    
    public String deleteSupervisor(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("delete from `admins` where id=" +(id));

        return "adSupervisorGrid.xhtml";
    }
    
    public String updateCustomer(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `customers` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail() + "\", address1=\"" + u.getAddress1() + "\", address2=\"" + u.getAddress2() + "\" where id=" + u.getId());

        return "adCustomerGrid.xhtml";
    }
    
    public String updateCustomerD(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `customers` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail() + "\", address1=\"" + u.getAddress1() + "\", address2=\"" + u.getAddress2() + "\" where id=" + u.getId());

        return "cdAccountGrid.xhtml";
    }
    
    public String updateAdmin(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {   
        int result = mydbconnect().executeUpdate("update `admins` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail()+ "\" where id=" + u.getId());

        return "adAdminGrid.xhtml";
    }
    
    public String updateManager(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `admins` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail()+ "\", role=\"" + u.getRole() + "\" where id=" + u.getId());

        return "adManagerGrid.xhtml";
    }
    
    public String updateSupervisor(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `admins` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail()+ "\", role=\"" + u.getRole() + "\" where id=" + u.getId());

        return "adSupervisorGrid.xhtml";
    }
    
    public String updateLoggedInAdmin(User u) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `admins` set username=\"" + u.getUsername() + "\", password=\"" + u.getPassword() + "\", first_name=\"" + u.getFirstName()+ "\", last_name=\"" + u.getLastName()+ "\", phone_number=\"" + u.getPhoneNo()+ "\", email_address=\"" + u.getEmail()+ "\" where id=" + u.getId());
        
        User loggedAdmin = (User) sessionMap.get("logUser");
        if (loggedAdmin != null) {
            Role adminRole = loggedAdmin.getRole();

            // Redirect based on the role
            if (Role.MANAGER.equals(adminRole)) {
                return "mAdminGrid.xhtml"; // Manager-specific page
            } else if (Role.SUPERVISOR.equals(adminRole)) {
                return "sAdminGrid.xhtml"; // Supervisor-specific page
            } else if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                return "adAdminGrid.xhtml"; // System admin-specific page
            }
        }

        return "adAdminGrid.xhtml";
    }
    
    public String editCustomer(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from customers where id=" +(id));
        
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
            temp.address1 = rs.getString("address1");
            temp.address2 = rs.getString("address2");
            sessionMap.put("editUser", temp);
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adCustomerEdit.xhtml";
    }
    
    public String editCustomerD(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from customers where id=" +(id));
        
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
            temp.address1 = rs.getString("address1");
            temp.address2 = rs.getString("address2");
            sessionMap.put("editUser", temp);
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "cdAccountEdit.xhtml";
    }
    
    public String editAdmin(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from admins where id=" +(id));
        
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
            temp.role = rs.getString("role");
            sessionMap.put("editUser", temp);
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adAdminEdit.xhtml"; // other admins
    }
    
    public String editManager(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from admins where id=" +(id));
        
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
            temp.role = rs.getString("role");
            sessionMap.put("editUser", temp);
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adManagerEdit.xhtml"; // other admins
    }
    
    public String editSupervisor(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from admins where id=" +(id));
        
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
            temp.role = rs.getString("role");
            sessionMap.put("editUser", temp);
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adSupervisorEdit.xhtml"; // other admins
    }
    
    public String editLoggedInAdmin(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        User loggedAdmin = (User) sessionMap.get("logUser");
        
        // JDBC
        
        if (loggedAdmin != null) {
        ResultSet rs = mydbconnect().executeQuery("select * from admins where id=" +(id));

            if (rs.next()) {
                User temp = new User();
                temp.id = rs.getInt("id");
                temp.username = rs.getString("username");
                temp.password = rs.getString("password");
                temp.firstName = rs.getString("first_name");
                temp.lastName = rs.getString("last_name");
                temp.phoneNo = rs.getString("phone_number");
                temp.email = rs.getString("email_address");
                sessionMap.put("editUser", temp);
                
                Role adminRole = loggedAdmin.getRole();

            if (Role.MANAGER.equals(adminRole)) {
                return "mAdminEdit.xhtml"; 
            } else if (Role.SUPERVISOR.equals(adminRole)) {
                return "sAdminEdit.xhtml"; 
            } else if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                return "adAdminEdit.xhtml"; 
            }
            }
        }
        return "adAdminEdit.xhtml";
    }
    
    public ArrayList getAllCustomers()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        userData = new ArrayList();
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from customers");
        
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
            temp.address1 = rs.getString("address1");
            temp.address2 = rs.getString("address2");
            userData.add(temp);
        }
        // JDBC
        return userData;
    }
     
    public ArrayList getAllManagers()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        userData = new ArrayList();
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from admins where role = 'MANAGER'");
        
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
            temp.role = rs.getString("role");
            userData.add(temp);
        }
        // JDBC
        return userData;
    }
    
    public ArrayList getAllSupervisors()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        userData = new ArrayList();
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from admins where role = 'SUPERVISOR'");
        
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
            temp.role = rs.getString("role");
            userData.add(temp);
        }
        // JDBC
        return userData;
    }
    
    public ArrayList getLoggedInAdmin()
        throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        userData = new ArrayList();

        User loggedAdmin = (User) sessionMap.get("logUser");

        if (loggedAdmin != null) {
            ResultSet rs = mydbconnect().executeQuery("select * from admins where id=" + loggedAdmin.getId());

            while (rs.next()) {
                User temp = new User();
                temp.id = rs.getInt("id");
                temp.username = rs.getString("username");
                temp.password = rs.getString("password");
                temp.firstName = rs.getString("first_name");
                temp.lastName = rs.getString("last_name");
                temp.phoneNo = rs.getString("phone_number");
                temp.email = rs.getString("email_address");
                temp.role = rs.getString("role");
                userData.add(temp);
            }
        }
        return userData;
    }
    
    public ArrayList getLoggedInUser()
        throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        userData = new ArrayList();

        User loggedUser = (User) sessionMap.get("logUser");

        if (loggedUser != null) {
            ResultSet rs = mydbconnect().executeQuery("select * from customers where id=" + loggedUser.getId());

            while (rs.next()) {
                User temp = new User();
                temp.id = rs.getInt("id");
                temp.username = rs.getString("username");
                temp.password = rs.getString("password");
                temp.firstName = rs.getString("first_name");
                temp.lastName = rs.getString("last_name");
                temp.phoneNo = rs.getString("phone_number");
                temp.email = rs.getString("email_address");
                temp.address1 = rs.getString("address1");
                temp.address2 = rs.getString("address2");
                userData.add(temp);
            }
        }
        return userData;
    }
    
    @PostConstruct
    public void onPageLoad(){
         if (sessionMap.containsKey("logUser") == false)
         {
            backtologin();
         }
  
    }
    
    public String backtologin()
    {
       return "login.xhtml";
    }
    
    public String logout()
    {
        //sessionMap.clear();
        sessionMap.remove("logUser");
        return "login.xhtml";
    }        
    
    public String loginAdmin()
    throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String username, password;
        username = this.username;
        password = this.password;

        ResultSet rs = mydbconnect().executeQuery("select * from admins where username='"  + username + "' and password='" + password + "'" );

        if (rs.next()) {
            User temp = new User();
            temp.id = rs.getInt("id");
            temp.username = rs.getString("username");
            temp.password = rs.getString("password");
            temp.role = rs.getString("role");  

            Role userRole = Role.valueOf(temp.role);

            sessionMap.put("logUser", temp);

            if (Role.MANAGER.equals(userRole)) {
                return "mAdminGrid.xhtml";
            } else if (Role.SUPERVISOR.equals(userRole)) {
                return "sAdminGrid.xhtml";
            } else if (Role.SYSTEM_ADMIN.equals(userRole)) {
                return "adAdminGrid.xhtml";
            } else {
                return "adminLogin.xhtml";
            }
        } else {
            return "home.xhtml";
        }
    }
    
    public String loginUser()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        String username, password;
        username = this.username;
        password = this.password;
        
        ResultSet rs = mydbconnect().executeQuery("select * from customers where username='"  + username + "' and password='" + password + "'" );
        
        if(rs.next())
        {
            User temp = new User();
            temp.id = rs.getInt("id");
            temp.username = rs.getString("username");
            temp.password = rs.getString("password");
            sessionMap.put("logUser", temp);
            return "cdAccountGrid.xhtml";
        }
        else 
        {
           return "home.xhtml";
        }    
        // JDBC
    }       
    
    public void setLoggedInAdmin() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        User temp = new User();
        temp.setUsername(this.getUsername());
        temp.setPassword(this.getPassword());
        
        ResultSet rs = mydbconnect().executeQuery("select id from admins where username='" + this.getUsername() + "'");
        if (rs.next()) {
            temp.setId(rs.getInt("id"));
        }

        sessionMap.put("logUser", temp);
    }
    
    public void setLoggedInUser() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        User temp = new User();
        temp.setUsername(this.getUsername());
        temp.setPassword(this.getPassword());
        
        ResultSet rs = mydbconnect().executeQuery("select id from customers where username='" + this.getUsername() + "'");
        if (rs.next()) {
            temp.setId(rs.getInt("id"));
        }

        sessionMap.put("logUser", temp);
    }
    
    public boolean saveCustomer() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into customers (`username`,`password`, `first_name`, `last_name`, `phone_number`, `email_address`, `address1`, `address2`) "
                + "values ('"+ this.username +"', '"+ this.password +"', '"+ this.firstName +"', '"+ this.lastName +"', '"+ this.phoneNo +"', '"+ this.email +"', '"+ this.address1 +"', '"+ this.address2 +"');");
        if(result == 1)
            return true;
        else 
            return false;
    }   

    public boolean saveAdmin() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into admins (`username`,`password`, `first_name`, `last_name`, `phone_number`, `email_address`, `role`) "
                + "values ('"+ this.username +"', '"+ this.password +"', '"+ this.firstName +"', '"+ this.lastName +"', '"+ this.phoneNo +"', '"+ this.email +"', 'SYSTEM_ADMIN');");
        if(result == 1)
            return true;
        else 
            return false;
    }      
    
    public boolean saveManager() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into admins (`username`,`password`, `first_name`, `last_name`, `phone_number`, `email_address`, `role`) "
                + "values ('"+ this.username +"', '"+ this.password +"', '"+ this.firstName +"', '"+ this.lastName +"', '"+ this.phoneNo +"', '"+ this.email +"', 'MANAGER');");
        if(result == 1)
            return true;
        else 
            return false;
    }
    
    public boolean saveSupervisor() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into admins (`username`,`password`, `first_name`, `last_name`, `phone_number`, `email_address`, `role`) "
                + "values ('"+ this.username +"', '"+ this.password +"', '"+ this.firstName +"', '"+ this.lastName +"', '"+ this.phoneNo +"', '"+ this.email +"', 'SUPERVISOR');");
        if(result == 1)
            return true;
        else 
            return false;
    }
    
    public String submitCustomer() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        User loggedAdmin = (User) sessionMap.get("logUser");

        if (loggedAdmin != null) {
            Role adminRole = loggedAdmin.getRole();

            if (Role.MANAGER.equals(adminRole) || Role.SYSTEM_ADMIN.equals(adminRole)) {
                if (saveCustomer()) {
                    if (Role.MANAGER.equals(adminRole)) {
                        return "mCustomerResponse.xhtml";
                    } else if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                        return "adCustomerResponse.xhtml";
                    }
                }
            }
        }

        return "adRegisterCustomer.xhtml";
    }

    public String submitCustomerHome() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        
        if (this.saveCustomer()) {
            setLoggedInUser();
            return "cdCustomerResponse.xhtml";
        } else {
            return "customerLogin.xhtml";
        }
    }
    
    public String submitSupervisor() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        User loggedAdmin = (User) sessionMap.get("logUser");

        if (loggedAdmin != null) {
            Role adminRole = loggedAdmin.getRole();

            if (Role.MANAGER.equals(adminRole)) {
                if (this.saveSupervisor()) {
                    return "mSupervisorResponse.xhtml";
                }
            } else if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                if (this.saveSupervisor()) {
                    return "adSupervisorResponse.xhtml";
                }
            }
        }

        return "registerAdmin.xhtml";
    }
    
    public String submitManager() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException 
    {
        User loggedAdmin = (User) sessionMap.get("logUser");

        if (loggedAdmin != null) {
            Role adminRole = loggedAdmin.getRole();

            if (Role.SYSTEM_ADMIN.equals(adminRole)) {
                if (this.saveManager()) {
                    return "adManagerResponse.xhtml";
                } 
            }
        }

        return "registerAdmin.xhtml";
    }
    
    public String submitAdminHome() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        Role selectedRole = getRole();
        
        if (Role.MANAGER == selectedRole) {
            if (this.saveManager()) {
                setLoggedInAdmin();
                return "adminLogin.xhtml";
            }
        } else if (Role.SUPERVISOR == selectedRole) {
            if (this.saveSupervisor()) {
                setLoggedInAdmin();
                return "adminLogin.xhtml";
            }
        } else if (Role.SYSTEM_ADMIN == selectedRole) {
            if (this.saveAdmin()) {
                setLoggedInAdmin();
                return "adminLogin.xhtml";
            }
        }

        return "registerAdmin.xhtml";
    }
    
}