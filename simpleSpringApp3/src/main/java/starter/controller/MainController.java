package starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import starter.model.Tasks;
import starter.repos.DAO;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private DAO dao;
    private static List<Tasks> tasksList = new ArrayList<Tasks>();

    @InitBinder
    public void initBinder(WebDataBinder dataBinder, Locale locale, HttpServletRequest request){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
    }
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        String message = "Hello Spring Boot + JSP";
        Iterable<Tasks> tasks = dao.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @RequestMapping(value = { "/tasksList" }, method = RequestMethod.GET)
    public String viewTasksList(Model model) {

        Iterable<Tasks> tasks = dao.findAll();
        model.addAttribute("tasks", tasks);

        return "tasksList";
    }

//    @GetMapping
//    public String main(Map<String, Object> model){
//       Iterable<Tasks> tasks = dao.findAll();
//       model.put("tasks", tasksList);
//       return "tasksList";
//    }

    @PostMapping
    public String add(@RequestParam String summary, @RequestParam String assignee, @RequestParam Date startDate, @RequestParam Date endDate, Map<String, Object> model){
        Tasks task = new Tasks(summary, startDate, endDate,assignee);
        dao.save(task);
        Iterable<Tasks> tasks = dao.findAll();
        model.put("tasks", tasks);
        return "index";
    }
}
