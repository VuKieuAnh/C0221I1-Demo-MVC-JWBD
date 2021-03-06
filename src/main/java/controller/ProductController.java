package controller;

import model.Category;
import service.product.IProductService;
import model.Products;
import service.product.ProductServiceJDBC;
import service.category.CategoryService;
import service.category.ICategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "ProductController",
        value = "/products"
)
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductServiceJDBC();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "edit":
                showFormEdit(req, resp);
                break;
            default:
                showAllProduct(req, resp);
        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/edit.jsp");
        List<Category> categories = categoryService.findAll();
        int index = Integer.parseInt(req.getParameter("id")) ;
        Products products = productService.findById(index);
        req.setAttribute("product", products);
        req.setAttribute("categories", categories);
        dispatcher.forward(req, resp);
    }

    private void showAllProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/list.jsp");
        List<Products> productsList = productService.findAll();
        req.setAttribute("dssp", productsList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "edit":
                editProduct(req, resp);
                break;
            default:
                showAllProduct(req, resp);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //lay du lieu
        int index = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int category_id = Integer.parseInt(req.getParameter("category_id"));
        //tao moi doi tuong can luu
        Products p = new Products(index,name, price);
        Category category = new Category(category_id);
        p.setCategory(category);
        //goi service
        productService.edit(index, p);
        //tro ve trang danh sach
//        showAllProduct(req, resp);
        resp.sendRedirect("/products");

    }
}
