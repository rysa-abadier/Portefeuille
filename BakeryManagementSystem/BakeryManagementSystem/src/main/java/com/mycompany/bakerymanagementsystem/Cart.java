package com.mycompany.bakerymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
@ManagedBean(name = "currentCart", eager = true)

public class Cart {
    int customerId;
    int productId;
    int quantity;
    
    ArrayList cartData;
    ArrayList itemData;
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public ArrayList getCartData() {
        return cartData;
    }

    public void setCartData(ArrayList cartData) {
        this.cartData = cartData;
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
    
    public String delete(int productId)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int result = mydbconnect().executeUpdate("delete from cart where product_id=" +(productId));

        return "cdCartGrid.xhtml";
        
    }
    
    public String update(Cart c) 
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        // we will execute an update sql to table user
        //sending the new values passed to you      
        
        int result = mydbconnect().executeUpdate("update `cart` set quantity=\"" + c.getQuantity() + "\" where customer_id=\"" + c.getCustomerId() + "\" and product_id=" + c.getProductId());

        return "cdCartGrid.xhtml";
    }
    
    public String edit(int productId)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        // edit will retrieve the record from the mysql table
        
        ResultSet rs = mydbconnect().executeQuery("select * from cart where product_id=" +(productId));
        
        if(rs.next())
        {
            Cart temp = new Cart();
            temp.customerId = rs.getInt("customer_id");
            temp.productId = rs.getInt("product_id");
            temp.quantity = rs.getInt("quantity");
            sessionMap.put("editCart", temp);
            
        }
        // then, it will load the record into the edit.xhtml
        
        return "cdCartEdit.xhtml";
    }
    
    public ArrayList getAll()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        cartData = new ArrayList();
        
        ResultSet rs = mydbconnect().executeQuery("select * from cart");
        
        while(rs.next())
        {
            Cart temp = new Cart();
            temp.customerId = rs.getInt("customer_id");
            temp.productId = rs.getInt("product_id");
            temp.quantity = rs.getInt("quantity");
            cartData.add(temp);
        }
        // JDBC
        return cartData;
    }
    
    public boolean save(int productID) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        User customer = (User) sessionMap.get("logUser");
        Product product = (Product) sessionMap.get("productInfo");
        
        if (customer != null) {
            int result = mydbconnect().executeUpdate("insert into cart (`customer_id`,`product_id`,`quantity`) "
                + "values ('"+ customer.getId() +"', '"+ product.getId() +"', '"+ this.quantity +"');");
            return result == 1;
        } else {
            return false;
        }
    }      
    
    public String submit(int productID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       if(this.save(productID))
           return "cdCartResponse.xhtml";
       else 
           return "registerCart.xhtml";
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
