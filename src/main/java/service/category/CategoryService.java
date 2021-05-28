package service.category;

import model.Category;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {

    private Connection getConnect(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/product_manager1",
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
    public List<Category> findAll() {
        Connection connection = getConnect();

        List<Category> categories = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from category;");
            ResultSet set = statement.executeQuery();
            while (set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                String description = set.getString("description");

                Category category = new Category(id, name, description);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Category p) {

    }
}
