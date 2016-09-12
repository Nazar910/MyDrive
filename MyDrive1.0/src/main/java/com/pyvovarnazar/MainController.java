package com.pyvovarnazar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView welcome(){return new ModelAndView("welcome-page");}

    @RequestMapping("/sign_in")
    public ModelAndView signIn(){ return new ModelAndView("userCab");}

    @RequestMapping("/sign_up_page")
    public ModelAndView signUpPage() {
        return new ModelAndView("signUpPage");
    }

    @RequestMapping("/sign_up")
    public ModelAndView signUp(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password1") String password1,
                               @RequestParam(value = "password2") String password2,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "phone") String phone,
                               HttpServletRequest request,
                               HttpServletResponse response){
        if(password1.equals(password2)){
            User user = new User(username, password1, email,  phone);
            userDAO.add(user);
            return new ModelAndView("userAccount");
        }
        else{
            return new ModelAndView("signUpPage","passError","the second password is different");
        }
    }

    @RequestMapping("/add")
    public ModelAndView list(){
        User user1 = new User("qwe1","qwerty","qwe@gmail.com","123");
        userDAO.add(user1);
        User user2 = new User("qwe2","qwerty","qwe@gmail.com","123");
        userDAO.add(user2);
        List<User> users= new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        return new ModelAndView("add","users",users);
    }
}
