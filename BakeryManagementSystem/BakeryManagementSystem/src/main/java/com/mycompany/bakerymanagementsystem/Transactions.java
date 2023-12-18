package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentTransactions", eager = true)

public class Transactions {
    int id;
    int paymentId;
    int customerId;
    String deliveryDate;
    String dateOrdered;
    
    ArrayList transactionsData;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
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
        int result = mydbconnect().executeUpdate("delete from `transactions` where id=" +(id));

        return "adTransactionsGrid.xhtml";
        
    }
    
    public String update(Transactions t) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        // we will execute an update sql to table user
        //sending the new values passed to you      
        
        int result = mydbconnect().executeUpdate("update `transactions` set delivery_date=\"" + t.getDeliveryDate()+ "\" where id=" + t.getId());

        return "adTransactionsGrid.xhtml";
    }
    
    public String edit(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        // JDBC
        ResultSet rs = mydbconnect().executeQuery("select * from transactions where id=" +(id));
        
        if(rs.next())
        {
            Transactions temp = new Transactions();
            temp.id = rs.getInt("id");
            temp.paymentId = rs.getInt("payment_id");
            temp.customerId = rs.getInt("customer_id");
            temp.deliveryDate = rs.getString("delivery_date");
            temp.dateOrdered = rs.getString("date_ordered");
            sessionMap.put("editTransactions", temp);
            
        }
        // JDBC
        // then, it will load the record into the edit.xhtml
        
        return "adTransactionsEdit.xhtml";
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        transactionsData = new ArrayList();
        
        // JDBC
        
        ResultSet rs = mydbconnect().executeQuery("select * from transactions");
        
        while(rs.next())
        {
            Transactions temp = new Transactions();
            temp.id = rs.getInt("id");
            temp.paymentId = rs.getInt("payment_id");
            temp.customerId = rs.getInt("customer_id");
            temp.deliveryDate = rs.getString("delivery_date");
            temp.dateOrdered = rs.getString("date_ordered");
            transactionsData.add(temp);
        }
        // JDBC
        return transactionsData;
    }
    
    public boolean save() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into transactions (`delivery_date`) "
                + "values ('"+ this.deliveryDate +"');");
        if(result == 1)
            return true;
        else 
            return false;
        
    }      
    
    public String submit() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       if(this.save())
           return "adTransactionsResponse.xhtml";
       else 
           return "adRegisterTransaction.xhtml";
    }
}