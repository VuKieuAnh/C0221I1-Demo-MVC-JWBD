package service.category;

import model.Category;
import model.Products;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int id);
    void save(Category p);
    void delete(int id);
    void edit(int id, Category p);
}
