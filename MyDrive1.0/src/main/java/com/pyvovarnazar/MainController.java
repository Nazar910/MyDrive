package com.pyvovarnazar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
@Controller
public class MainController {
    @Autowired
    private FileDAO userDAO;

    @RequestMapping("/")
    public ModelAndView welcome(){return new ModelAndView("index");}

    @RequestMapping("/add")
    public ModelAndView list(){
        User user1 = new User("qwe1","qwe@gmail.com","123");
        userDAO.add(user1);
        User user2 = new User("qwe2","qwe@gmail.com","123");
        userDAO.add(user2);
        List<User> users= new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        return new ModelAndView("add","users",users);
    }
}
