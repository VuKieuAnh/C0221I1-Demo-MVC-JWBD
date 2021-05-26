package service;

import com.mysql.cj.jdbc.Driver;
import model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceJDBC implements IProductService {
    private Connection getConnect(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/product_manager",
                    "root",
                    "123456@Abc"
            );
            System.out.println("ket noi thanh cong");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ket noi khong thanh cong");
            e.printStackTrace();
        }

        return  connection;
    }


    @Override
    public List<Products> findAll() {
        //tao connect
        Connection c = getConnect();

        List<Products> products = new ArrayList<>();
        try {
            //tao cau query
            PreparedStatement statement = c.prepareStatement("select * from product");
            //thuc thi cau query
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                Products p = new Products(id, name, price);
                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public Products findById(int id) {
        Products product = null;
        Connection c = getConnect();
        try {
            PreparedStatement statement = c.prepareStatement(
                    "select * from product where id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                int id1 = set.getInt("id");
                String name = set.getString("name");
                int price = set.getInt("price");
                product = new Products(id1, name, price);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return product;
    }

    @Override
    public void save(Products p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Products p) {
        Connection c = getConnect();
        try {
           PreparedStatement prepareStatement=  c.prepareStatement("update product set name=?, price= ? where id=?");
            prepareStatement.setInt(3, id);
            prepareStatement.setString(1, p.getName());
            prepareStatement.setInt(2, p.getPrice());
            prepareStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
