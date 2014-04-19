package com.ysb.controller;

import com.ysb.model.entity.User;
import com.ysb.model.service.AuthService;
import com.ysb.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();

        if(AuthService.isLogged(request)){
            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("login");
        }

        return mav;
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("action") String action,
                        String login,
                        String password,
                        String name,
                        String surname,
                        String email,
                        HttpServletRequest request) throws Exception {


        if(action.equals("login")){
            User user = UserService.getUser(login, password);
            if(user != null){
                AuthService.setLogedUserID(user.getId(), request);
                AuthService.setUserOnline(user.getId());
//            boolean rememberMe = request.getParameter("rememberMe").equals("on");

//            if(rememberMe) {
//                Cookie rememberMeCookie = new Cookie("userID", userID.toString());
//                rememberMeCookie.setHttpOnly(true);
//                rememberMeCookie.setMaxAge(7*24*60*60);
//                response.addCookie(rememberMeCookie);
//            }

                return "redirect:/";
            }
        } else {
            System.out.printf("in reg post : ");
            System.out.printf(name + " " );
            System.out.printf(surname + " " );
            System.out.printf(email + " " );
            System.out.printf(password + " " );

            boolean res = UserService.saveUser(name, surname, email, password);
        }



        return "redirect:/login";
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response
                         /*@CookieValue(value = "userID", defaultValue = "-1") String userIDCookie*/) throws Exception {
        HttpSession session = request.getSession();

//        if(!userIDCookie.equals("-1")){
//            Cookie rememberMeCookie = new Cookie("userID", "-1");
//            rememberMeCookie.setHttpOnly(true);
//            rememberMeCookie.setMaxAge(0);
//            response.addCookie(rememberMeCookie);
//        }

        if(AuthService.isLogged(request)){
            AuthService.logout(request);
        }

        return "redirect:login";
    }

}