package com.ysb.controller;

import com.ysb.model.service.AuthService;
import com.ysb.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@Controller
public class FriendController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/friends")
    public ModelAndView seeFriends(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getFriends(authService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/subscribed")
    public ModelAndView seeSubscribed(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getOnWhoUserSubscribed(authService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/subscribers")
    public ModelAndView seeSubscribers(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getWhoSubscribedOnMe(authService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/people")
    public ModelAndView seeAllPeople(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getPeople(authService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/friend/add/{userID}")
    public String addFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.addSubscribersByIDs(authService.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/friend/delete/{userID}")
    public String deleteFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.deleteFriendsByIDs(authService.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/friend/submit/{userID}")
    public String submitFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.submitFriendsByIDs(authService.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/subscriber/delete/{userID}")
    public String deleteSubscribers(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.deleteSubscribersByIDs(authService.getLogedUserID(request), userID);
        return "redirect:/";
    }
}
