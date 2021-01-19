package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.dao.Category;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.repositories.CategoryRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryControllerApi {
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/get")
    public List<Category> getCategoriesAngTags() {
        return categoryRepo.findAll();
    }

    @PostMapping("/create")
    public void createCategory(@RequestBody Category category, HttpServletResponse response) throws IOException {
        try {
            System.out.println("HALLO");
            categoryRepo.save(category);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/update")
    public void updateCategory(@RequestBody Category newCategory, HttpServletResponse response) throws IOException {
        try {
            Category category = categoryRepo.findById(newCategory.getId());
            category.setName(newCategory.getName().equals("") ? category.getName() : newCategory.getName());
//            category.setIcon(newCategory.getIcon() == null ? category.getIcon() : newCategory.getIcon());
            category.setDescription(newCategory.getDescription().equals("") ? category.getDescription() : newCategory.getDescription());
            categoryRepo.save(category);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteCategory(@RequestBody Category category, HttpServletResponse response) throws IOException {
        try {
            categoryRepo.delete(category);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
}
