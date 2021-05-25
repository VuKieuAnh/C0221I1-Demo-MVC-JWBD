package service;

import model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static ArrayList<Products> products;
    static {
        products = new ArrayList<>();
        products.add(new Products(1, "But thien long", 5));
        products.add(new Products(2, "But hong ha", 15));
        products.add(new Products(3, "Giay note", 8));
        products.add(new Products(4, "Khan lau", 25));
    }

    public List<Products> findAll(){
        return products;
    }

    @Override
    public Products findById(int index) {
        return products.get(index);
    }

    @Override
    public void save(Products p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Products p) {
        products.set(id, p);
    }
}
