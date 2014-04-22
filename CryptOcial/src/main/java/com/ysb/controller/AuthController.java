package com.ysb.controller;

import com.ysb.model.entity.User;
import com.ysb.model.service.UserService;
import com.ysb.model.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO use cookies to remember me

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(HttpServletRequest request) throws Exception {
        if (AuthUtils.isLogged(request)) {
            return "redirect:/";
        } else {
            return "intro";
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("action") String action,
                        String login,
                        String password,
                        String name,
                        String surname,
                        String email,
                        HttpServletRequest request) throws Exception {


        if (action.equals("login")) {
            User user = userService.getUser(login, password);
            if (user != null) {
                AuthUtils.login(user.getId(), request);
/*
            boolean rememberMe = request.getParameter("rememberMe").equals("on");
            if(rememberMe) {
                Cookie rememberMeCookie = new Cookie("userID", userID.toString());
                rememberMeCookie.setHttpOnly(true);
                rememberMeCookie.setMaxAge(7*24*60*60);
                response.addCookie(rememberMeCookie);
            }
*/
                return "redirect:/";
            }
        } else {
            if (userService.isMailFree(email)) {
                userService.saveUser(name, surname, email, password);
                AuthUtils.login(userService.getUser(email, password).getId(), request);
            }
        }

        return "redirect:/login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response
                         /*@CookieValue(value = "userID", defaultValue = "-1") String userIDCookie*/) throws Exception {

/*
        if(!userIDCookie.equals("-1")){
            Cookie rememberMeCookie = new Cookie("userID", "-1");
            rememberMeCookie.setHttpOnly(true);
            rememberMeCookie.setMaxAge(0);
            response.addCookie(rememberMeCookie);
        }
*/

        if (AuthUtils.isLogged(request)) {
            AuthUtils.logout(request);
        }

        return "redirect:/login";
    }
}