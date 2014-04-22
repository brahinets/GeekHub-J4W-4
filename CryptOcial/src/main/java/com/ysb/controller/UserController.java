package com.ysb.controller;

import com.ysb.model.entity.User;
import com.ysb.model.service.UserService;
import com.ysb.model.utils.AuthUtils;
import com.ysb.model.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String userPage(HttpServletRequest request) {
        return "redirect:/user/" + AuthUtils.getLogedUserID(request);
    }


    @RequestMapping(value = "/user/{ID}", method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request, @PathVariable String ID) {
        ModelAndView mav = new ModelAndView();

        try {
            User user = userService.getOtherUser(Integer.parseInt(ID), AuthUtils.getLogedUserID(request));

            if (user != null) {
                mav.addObject("user", user);
                mav.setViewName("userPage");
            } else {
                mav.setViewName("redirect:/home");
            }
        } catch (Exception e) {
            mav.setViewName("redirect:/home");
        }

        return mav;
    }


    @RequestMapping(value = "/user/delete/{ID}", method = RequestMethod.GET)
    public String unregister(HttpServletRequest request, @PathVariable Integer ID) throws Exception {
        if (AuthUtils.getLogedUserID(request).equals(ID)) {
            userService.unregister(ID);
            return "redirect:/logout";
        } else {
            return "redirect:/home";
        }
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goHome(HttpServletRequest request) throws Exception {
        return "redirect:/user/" + AuthUtils.getLogedUserID(request);
    }


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/settings");
        mav.addObject("user", userService.getUser(AuthUtils.getLogedUserID(request)));

        return mav;
    }

/*
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request,
                           String name,
                           String surname,
                           String email,
                           String password ) throws Exception {

        userService.saveUser(name, surname, email, password);
        return "redirect:/login";
    }
*/


    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String settingsSave(HttpServletRequest request,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               String birthDate,
                               String whereFrom,
                               Integer gender,
                               String avatar) throws Exception {

        User user = new User();
        user.setId(AuthUtils.getLogedUserID(request));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender == 1);
        if (!birthDate.equals("")) {
            user.setBirthDate(java.sql.Date.valueOf(birthDate));
        }
        if (!whereFrom.equals("")) {
            user.setWhereFrom(whereFrom);
        }

        if (!avatar.equals("")) {
            user.setCurrentAvatar(Utils.base64toBytes(avatar));
        }
        userService.saveUser(user);

        return "redirect:/settings";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, String nameOrSurname) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.searchSimple(AuthUtils.getLogedUserID(request), nameOrSurname));

        return mav;
    }
}