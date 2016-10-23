package com.pyvovarnazar.mydrive.controller;

import com.pyvovarnazar.mydrive.model.File;
import com.pyvovarnazar.mydrive.model.User;
import com.pyvovarnazar.mydrive.service.FileService;
import com.pyvovarnazar.mydrive.service.SecurityService;
import com.pyvovarnazar.mydrive.service.UserService;
import com.pyvovarnazar.mydrive.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    public User getLoggedUser(){//return logged in User
        return userService.findByUsername(                //Getting logged in user
                SecurityContextHolder.getContext().             //by getting current context
                        getAuthentication().getName());         //authentication and it`s name
    }

    public long getGlobalFileId(long id,boolean delete){
        User user = getLoggedUser();
        Set<File> list = fileService.findByUser(user);//Creating a set of user`s files to do smth we need with the needed one
        Iterator<File> iterator = list.iterator();
        int i=0;//index that shows file_id for user
        while (iterator.hasNext()){
            if(++i==id){
                id = iterator.next().getId();//get file_id from global table to variable id
                if(delete)
                    iterator.remove();//removing this file from set
                break;//end loop because don`t need to search anymore
            }
            iterator.next();//getting the next file of set
        }
        return id;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome(ModelAndView modelAndView){
        User user = getLoggedUser();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("files",fileService.findByUser(user));
        return modelAndView;
    }


    @RequestMapping(value = "add_file", method = RequestMethod.POST)
    public String addFile(@RequestParam("body") MultipartFile body){
        File file = null;
        try {
            User user = getLoggedUser();
            file = new File(body.getOriginalFilename(),user,body.getBytes());//Creating a new file object

        } catch (IOException e) {
            e.printStackTrace();            //Error message in case of problem with uploading file
        }
        if(!file.getName().equals(""))
            fileService.save(file);
        return "redirect:welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/delete")
    public String deleteFile(@RequestParam("id") long id){
        id = getGlobalFileId(id,true);
        fileService.deleteById(id);
        return "redirect:welcome";
    }

    @RequestMapping("/download")
    public String downloadFile(@RequestParam("id") long id, HttpServletResponse response){
        id=getGlobalFileId(id,false);
        File file = fileService.findFileById(id);
        response.setContentLength(file.getBody().length);
        response.setHeader("Content-Disposition","attachment; filename=\""+file.getName()+"\"");
        try {
            FileCopyUtils.copy(file.getBody(),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}