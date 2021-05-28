package service.category;

import model.Category;
import service.ConnectionJDBC;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {

    //tao connect
    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public List<Category> findAll() {

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
