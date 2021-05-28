package service.product;

import model.Category;
import model.Products;
import service.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceJDBC implements IProductService {

    //tao connect
    Connection c = new ConnectionJDBC().getConnect();

    public static final String UPDATE_PRODUCT_SET_NAME_PRICE_WHERE_ID = "update product set name=?, price= ?, category_id=? where id=?";

//    private Connection getConnect(){
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/product_manager1",
//                    "root",
//                    "123456@Abc"
//            );
//            System.out.println("ket noi thanh cong");
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("ket noi khong thanh cong");
//            e.printStackTrace();
//        }
//
//        return  connection;
//    }


    @Override
    public List<Products> findAll() {

        List<Products> products = new ArrayList<>();
        try {
            //tao cau query
            PreparedStatement statement = c.prepareStatement("select p.id, p.name, p.price, c.name as category_name from product p join category c on c.id = p.category_id;");
            //thuc thi cau query
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                Products p = new Products(id, name, price);
                //lay category
                String category_name = resultSet.getString("category_name");
                Category category = new Category(category_name);
                p.setCategory(category);

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
                int category_id = set.getInt("category_id");
                Category category = new Category(category_id);
                product.setCategory(category);
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
        try {
           PreparedStatement prepareStatement=  c.prepareStatement(UPDATE_PRODUCT_SET_NAME_PRICE_WHERE_ID);
            prepareStatement.setInt(4, id);
            prepareStatement.setString(1, p.getName());
            prepareStatement.setInt(2, p.getPrice());
            prepareStatement.setInt(3, p.getCategory().getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
