package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentProduct", eager = true)

public class Product {
    int id;
    String name;
    String description;
    float price;
    int stockQuantity;
    
    ArrayList productData;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ArrayList getProductData() {
        return productData;
    }

    public void setProductData(ArrayList productData) {
        this.productData = productData;
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
        int result = mydbconnect().executeUpdate("delete from `products` where id=" +(id));

        return "adProductGrid.xhtml";
        
    }
    
    public String update(Product p) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        // we will execute an update sql to table user
        //sending the new values passed to you      
        
        int result = mydbconnect().executeUpdate("update `products` set name=\"" + p.getName() + "\", description=\"" + p.getDescription()+ "\", price=\"" + p.getPrice()+ "\", stock_quantity=\"" + p.getStockQuantity() + "\" where id=" + p.getId());

        return "adProductGrid.xhtml";
    }
    
    public String edit(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        ResultSet rs = mydbconnect().executeQuery("select * from products where id=" +(id));
        
        if(rs.next())
        {
            Product temp = new Product();
            temp.id = rs.getInt("id");
            temp.name = rs.getString("name");
            temp.description = rs.getString("description");
            temp.price = rs.getFloat("price");
            temp.stockQuantity = rs.getInt("stock_quantity");
            sessionMap.put("editProduct", temp);
            
        }
        // then, it will load the record into the edit.xhtml
        
        return "adProductEdit.xhtml";
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        productData = new ArrayList();
        
        ResultSet rs = mydbconnect().executeQuery("select * from products");
        
        while(rs.next())
        {
            Product temp = new Product();
            temp.id = rs.getInt("id");
            temp.name = rs.getString("name");
            temp.description = rs.getString("description");
            temp.price = rs.getFloat("price");
            temp.stockQuantity = rs.getInt("stock_quantity");
            productData.add(temp);
        }
        // JDBC
        return productData;
    }
    
    public boolean save() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("insert into products (`name`,`description`, `price`, `stock_quantity`) "
                + "values ('"+ this.name +"', '"+ this.description +"', '"+ this.price +"', '"+ this.stockQuantity +"');");
        if(result == 1)
            return true;
        else 
            return false;
    }      
    
    public String submit() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       if(this.save())
           return "adProductResponse.xhtml";
       else 
           return "adRegisterProduct.xhtml";
    }
    
    public String addToCart(int id)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        ResultSet rs = mydbconnect().executeQuery("select * from products where id=" +(id));
        
        if(rs.next())
        {
            Product temp = new Product();
            temp.id = rs.getInt("id");
            temp.name = rs.getString("name");
            temp.description = rs.getString("description");
            temp.price = rs.getFloat("price");
            temp.stockQuantity = rs.getInt("stock_quantity");
            sessionMap.put("productInfo", temp);
            
        }
        // then, it will load the record into the edit.xhtml
        
        return "cdAddProductToCart.xhtml";
    }
}
