package com.ysb.controller;

import com.ysb.model.entity.User;
import com.ysb.model.service.AuthService;
import com.ysb.model.service.UserService;
import com.ysb.model.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;

@Controller
public class UserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer myID = (Integer) session.getAttribute("myID");
        ModelAndView mav;

        mav = new ModelAndView("redirect:/user/" + myID);

        return mav;
    }


    @RequestMapping(value = "/unknown")
    public ModelAndView unknownQuery(HttpServletRequest request) {
        ModelAndView mav;
        mav = new ModelAndView("redirect:/");
        System.out.println("unknown");
        return mav;
    }

    @RequestMapping(value = "/user/{ID}", method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request, @PathVariable String ID) throws SQLException {
        ModelAndView mav = new ModelAndView();

        try {
            Integer userID = Integer.parseInt(ID);
            User user = UserService.getUser(userID);

            if (user != null) {
                Integer myID = AuthService.getLogedUserID(request);
                mav.setViewName("userPage");
                user.setCanWrite(UserService.canWrite(myID, userID));
                user.setCanPost(UserService.canPost(myID, userID));
                user.setRelation(UserService.getRelationBetweenUsers(userID, myID));

                System.out.println("unread : " + user.getUnreadInputMail());

                mav.addObject("user", user);
            } else {
                mav.setViewName("redirect:/home");
            }
        } catch (Exception e) {
            mav.setViewName("redirect:/home");
            System.out.println("exc in /user/id: " + e.getMessage());
        }

        return mav;
    }




    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String goHome(HttpServletRequest request) throws Exception {
        return "/user/" + AuthService.getLogedUserID(request);
    }


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("/settings");
        mav.addObject("user", UserService.getUser(AuthService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request,
                           String name,
                           String surname,
                           String email,
                           String password ) throws Exception {

        System.out.printf("in post : ");
        System.out.printf(name + " " );
        System.out.printf(surname + " " );
        System.out.printf(email + " " );
        System.out.printf(password + " " );

        boolean res = UserService.saveUser(name, surname, email, password);


//        if(res != null){
//            HttpSession session = request.getSession();
//            session.setAttribute("userID", res);
//            return "redirect:main";
//        }

        return "redirect:/login";
    }


    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String settingsSave(HttpServletRequest request,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               String birthDate,
                               String avatar) throws Exception {
        System.out.println("come to settings post");

        User user = new User();
        user.setId(AuthService.getLogedUserID(request));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        if(!birthDate.equals("")){
            user.setBirthdate(java.sql.Date.valueOf(birthDate));
        }

        System.out.println("av : " + avatar);

        if(!avatar.equals("")){
            user.setCurrentAvatar(Utils.base64toBytes(avatar));
        }

        System.out.println("len in bytes : " + Utils.base64toBytes(avatar).length);

        UserService.saveUser(user);

        return "redirect:/settings";
    }

}