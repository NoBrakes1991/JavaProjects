package Controllers;

import DAO.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller  // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;


    @GetMapping(path="/all")
    public @ResponseBody String addNewUser2(@RequestParam String name, @RequestParam String email) {
        // This returns a JSON or XML with the users

        return "Saved";
    }
}