package com.pyvovarnazar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
@Controller
public class MainController {
    @Autowired
    private DAO userDAO;

    @RequestMapping("/")
    public ModelAndView welcome(){
//        User admin= new User("admin","admin","-","-");
//        userDAO.add(admin);
       // userDAO.deleteFile(3);
        return new ModelAndView("welcome-page");
    }

    @RequestMapping("/sign_in")
    public ModelAndView signIn(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password){
        if(username.equals("admin") && password.equals("admin")) {
            List<User> users = userDAO.userList();
            List<File> files = userDAO.fileList();
            return new ModelAndView("adminAccount","users", users);
        }
        User user=userDAO.signIn(username);
        if(user.getPassword().equals(password)) {
            ModelAndView model = new ModelAndView();
            model.setViewName("userAccount");
            model.addObject("username", username);
            if(user.getFiles()!=null)
                model.addObject("files", user.getFiles());
            return model;
        }
        else
            return new ModelAndView("welcome-page");
    }

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

    @RequestMapping("/add_file_page")
    public ModelAndView addFilePage(@RequestParam(value = "username") String username){
        return new ModelAndView("addFile", "username", username);
    }

    @RequestMapping("/add_file")
    public ModelAndView addFile(@RequestParam(value = "description") String description,
                                @RequestParam(value = "file") MultipartFile fileBuff,
                                @RequestParam(value = "username") String username,
                                HttpServletRequest request,
                                HttpServletResponse response){
        User user = userDAO.signIn(username);
        try {
            File file=new File(fileBuff.getOriginalFilename(),user, fileBuff.getBytes());
            user.addFile(file);
            userDAO.add(file);
            ModelAndView model = new ModelAndView();
            model.setViewName("userAccount");
            model.addObject("files", user.getFiles());
            model.addObject("username", username);
            return model;
            //return new ModelAndView("userAccount", "files", user.getFiles());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

    }

    @RequestMapping("/delete_file")
    public ModelAndView deleteFile(@RequestParam(value = "id") long id/*,
                                   @RequestParam(value = "username") String username*/){
        String username = "user1";
        userDAO.deleteFile(id);
        ModelAndView model = new ModelAndView();
        model.setViewName("userAccount");
        model.addObject("username", username);
        model.addObject("files", userDAO.signIn(username).getFiles());
        return model;
    }


//===============================================================================
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
