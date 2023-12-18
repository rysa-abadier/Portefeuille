/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentOrder", eager = true)

public class Order {
    int transactionId;
    int productId;
    int quantity;
    
    ArrayList orderData;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList getOrderData() {
        return orderData;
    }

    public void setOrderData(ArrayList orderData) {
        this.orderData = orderData;
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
    
    public String delete(int transactionId)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("delete from `orders` where transaction_id=" +(transactionId));

        return "adOrderGrid.xhtml";
        
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        orderData = new ArrayList();
        
        // JDBC
        
        ResultSet rs = mydbconnect().executeQuery("select * from orders");
        
        while(rs.next())
        {
            Order temp = new Order();
            temp.transactionId = rs.getInt("transaction_id");
            temp.productId = rs.getInt("product_id");
            temp.quantity = rs.getInt("quantity");
            orderData.add(temp);
        }
        // JDBC
        return orderData;
    } 
 
}
