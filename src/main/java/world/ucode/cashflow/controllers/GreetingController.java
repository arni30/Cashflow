package world.ucode.cashflow.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.ucode.cashflow.models.Message;
import world.ucode.cashflow.repositories.MessageRepo;
import world.ucode.cashflow.utils.DataExportService;

import java.util.Map;

/**
 * The type Greeting controller.
 */
@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private DataExportService dataExportService;

    /**
     * Greeting string.
     *
     * @param name  the name
     * @param model the model
     * @return the string
     */
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    /**
     * Main string.
     *
     * @param model the model
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("/")
    public String main(Map<String, Object> model) throws Exception {
        Iterable<Message> messages = messageRepo.findAll();
        dataExportService.getDefendants();
        model.put("messages", messages);

        return "main";
    }

    /**
     * Add string.
     *
     * @param text  the text
     * @param tag   the tag
     * @param model the model
     * @return the string
     */
    @PostMapping("/")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    /**
     * Filter string.
     *
     * @param message the message
     * @param model   the model
     * @return the string
     */
    @PostMapping("filter")
        public String filter(@RequestBody Message message, Map<String, Object> model) {
        Iterable<Message> messages;

        if (message.getTag() != null && !message.getTag().isEmpty()) {
            messages = messageRepo.findByTag(message.getTag());
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);

        return "main";
    }
}