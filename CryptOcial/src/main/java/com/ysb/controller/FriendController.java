package com.ysb.controller;

import com.ysb.model.service.UserService;
import com.ysb.model.utils.AuthUtils;
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


    @RequestMapping(value = "/friends")
    public ModelAndView seeFriends(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getFriends(AuthUtils.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/subscribed")
    public ModelAndView seeSubscribed(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getOnWhoUserSubscribed(AuthUtils.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/subscribers")
    public ModelAndView seeSubscribers(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getWhoSubscribedOnMe(AuthUtils.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/people")
    public ModelAndView seeAllPeople(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getPeople(AuthUtils.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/friend/add/{userID}")
    public String addFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.addSubscribersByIDs(AuthUtils.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/friend/delete/{userID}")
    public String deleteFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.deleteFriendsByIDs(AuthUtils.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/friend/submit/{userID}")
    public String submitFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.submitFriendsByIDs(AuthUtils.getLogedUserID(request), userID);
        return "redirect:/";
    }


    @RequestMapping(value = "/subscriber/delete/{userID}")
    public String deleteSubscribers(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        userService.deleteSubscribersByIDs(AuthUtils.getLogedUserID(request), userID);
        return "redirect:/";
    }
}
