package com.ysb.controller;

import com.ysb.model.entity.User;
import com.ysb.model.service.AuthService;
import com.ysb.model.service.UserService;
import com.ysb.model.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer myID = (Integer) session.getAttribute("myID");
        ModelAndView mav;
        mav = new ModelAndView("redirect:/user/" + myID);
        return mav;
    }


    @RequestMapping(value = "/user/{ID}", method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request, @PathVariable String ID) {
        ModelAndView mav = new ModelAndView();

        try {
            User user = userService.getOtherUser(Integer.parseInt(ID), authService.getLogedUserID(request));

            if (user != null) {
                mav.setViewName("userPage");
                mav.addObject("user", user);
            } else {
                mav.setViewName("redirect:/home");
            }
        } catch (Exception e) {
            mav.setViewName("redirect:/home");
        }

        return mav;
    }


    @RequestMapping(value = "/user/delete/{ID}", method = RequestMethod.GET)
    public ModelAndView unregister(HttpServletRequest request, @PathVariable Integer ID) throws Exception {
        ModelAndView mav = new ModelAndView();

        if(authService.getLogedUserID(request).equals(ID)){
            authService.logout(request);
            userService.unregister(ID);
            mav.setViewName("redirect:/login");
        } else {
            mav.setViewName("redirect:/home");
        }

        return mav;
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goHome(HttpServletRequest request) throws Exception {
        if(authService.getLogedUserID(request) != null){
            return "redirect:/user/" + authService.getLogedUserID(request);
        } else {
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/settings");
        mav.addObject("user", userService.getUser(authService.getLogedUserID(request)));

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
        user.setId(authService.getLogedUserID(request));
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
    public ModelAndView search(HttpServletRequest request, String nameOrSurname ) throws Exception {
        ModelAndView mav = new ModelAndView ("users");

        mav.addObject("usersList", userService.searchSimple(authService.getLogedUserID(request), nameOrSurname));

        return mav;
    }
}