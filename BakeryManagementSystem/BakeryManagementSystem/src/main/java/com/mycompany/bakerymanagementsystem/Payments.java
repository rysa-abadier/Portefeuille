package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentPayment", eager = true)

public class Payments {
    int id;
    String statusCode;
    Date paymentDate;
    float amount;
    
    ArrayList paymentData;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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
    
    public String delete(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {

        int result = mydbconnect().executeUpdate("delete from `payments` where id=" +(id));

        return "adPaymentsGrid.xhtml";
        
    }
    
    public String update(Payments pa) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("update `payments` set status_code=\"" + pa.getStatusCode()+ "\" where id=" + pa.getId());

        return "adPaymentsGrid.xhtml";
    }
    
    public String edit(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {   
        ResultSet rs = mydbconnect().executeQuery("select * from payments where id=" +(id));
        
        if(rs.next())
        {
            Payments temp = new Payments();
            temp.id = rs.getInt("id");
            temp.statusCode = rs.getString("status_code");
            temp.paymentDate = rs.getDate("payment_date");
            temp.amount = rs.getFloat("amount");
            sessionMap.put("editPayment", temp);
            
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adPaymentsEdit.xhtml";
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        paymentData = new ArrayList();
        
        ResultSet rs = mydbconnect().executeQuery("select * from payments");
        
        while(rs.next())
        {
            Payments temp = new Payments();
            temp.id = rs.getInt("id");
            temp.statusCode = rs.getString("status_code");
            temp.paymentDate = rs.getDate("payment_date");
            temp.amount = rs.getFloat("amount");
            paymentData.add(temp);
        }
        // JDBC
        return paymentData;
    }
    
    public boolean save() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into payments (`status_code`) "
                + "values ('"+ this.statusCode +"');");
        if(result == 1)
            return true;
        else 
            return false;
        
    }      
    
    public String submit() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       if(this.save())
           return "adPaymentsResponse.xhtml";
       else 
           return "adRegisterPayment.xhtml";
    }
}