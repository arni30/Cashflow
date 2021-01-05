package world.ucode.cashflow.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.ucode.cashflow.models.Category;
import world.ucode.cashflow.models.Tag;
import world.ucode.cashflow.repositories.CategoryRepo;
import world.ucode.cashflow.repositories.TagRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/tag")
public class TagControllerApi {
    @Autowired
    private TagRepo tagRepo;

    @PostMapping("/create")
    public void createTransaction(@RequestBody Tag tag, HttpServletResponse response) throws IOException {
        try {
            System.out.println("HALLO");
            tagRepo.save(tag);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/update")
    public void updateTransaction(@RequestBody Tag newTag, HttpServletResponse response) throws IOException {
        try {
            Tag tag = tagRepo.findById(newTag.getId());
            tag.setName(newTag.getName() == null ? tag.getName() : newTag.getName());
//            category.setIcon(newCategory.getIcon() == null ? category.getIcon() : newCategory.getIcon());
            tag.setDescription(newTag.getDescription() == null ? tag.getDescription() : newTag.getDescription());
            tagRepo.save(tag);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }

    @PostMapping("/delete")
    public void deleteTransaction(@RequestBody Tag tag, HttpServletResponse response) throws IOException {
        try {
            tagRepo.delete(tag);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Bad Request");
        }
    }
}
