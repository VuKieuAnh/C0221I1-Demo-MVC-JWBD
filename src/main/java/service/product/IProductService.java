package service.product;

import model.Products;

import java.util.List;

public interface IProductService {
    List<Products> findAll();
    Products findById(int id);
    void save(Products p);
    void delete(int id);
    void edit(int id, Products p);
}
