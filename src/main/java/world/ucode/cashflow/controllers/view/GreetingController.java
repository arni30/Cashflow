//package world.ucode.cashflow.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import world.ucode.cashflow.models.Message;
//import world.ucode.cashflow.models.dao.Users;
//import world.ucode.cashflow.repositories.UserRepo;
//import world.ucode.cashflow.utils.DataExportService;
//
//import java.util.Map;
//
///**
// * The type Greeting controller.
// */
//@Controller
//public class GreetingController {
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private DataExportService dataExportService;
//
//    @GetMapping("/greeting")
//    public String greeting(
//            @RequestParam(name="name", required=false, defaultValue="World") String name,
//            Map<String, Object> model
//    ) {
//        model.put("name", name);
//        return "greeting";
//    }
//
//    @GetMapping("/")
//    public String main(Map<String, Object> model) throws Exception {
//        Iterable<Users> messages = userRepo.findAll();
//        dataExportService.getDefendants();
//        model.put("user", messages);
//
//        return "main";
//    }
//
//    @PostMapping("/")
//    public String add(@RequestBody Users users, ModelMap model) {
//
//        userRepo.save(users);
//
//        Iterable<Users> messages = userRepo.findAll();
//
//        model.put("messages", messages);
//
//        return "main";
//    }
//
//    /**
//     * Filter string.
//     *
//     * @param message the message
//     * @param model   the model
//     * @return the string
//     */
//    @PostMapping("filter")
//        public String filter(@RequestBody Message message, Map<String, Object> model) {
//        Iterable<Users> messages;
//
//        if (message.getTag() != null && !message.getTag().isEmpty()) {
//            messages = userRepo.findByLogin(message.getTag());
//        } else {
//            messages = userRepo.findAll();
//        }
//        model.put("messages", messages);
//
//        return "main";
//    }
//}