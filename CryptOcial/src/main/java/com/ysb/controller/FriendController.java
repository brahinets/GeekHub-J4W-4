package com.ysb.controller;

import com.ysb.model.service.AuthService;
import com.ysb.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@Controller
public class FriendController {

    @RequestMapping(value = "/friends")
    public ModelAndView seeFriends(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", UserService.getFriends(AuthService.getLogedUserID(request)));

        return mav;
    }

  @RequestMapping(value = "/subscribed")
    public ModelAndView seeSubscribed(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", UserService.getOnWhoUserSubscribed(AuthService.getLogedUserID(request)));

        return mav;
    }

    @RequestMapping(value = "/subscribers")
    public ModelAndView seeSubscribers(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", UserService.getWhoSubscribedOnMe(AuthService.getLogedUserID(request)));

        return mav;
    }


    @RequestMapping(value = "/people")
    public ModelAndView addFriend(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", UserService.getPeople(AuthService.getLogedUserID(request)));

        return mav;
    }



    @RequestMapping(value = "/friend/add/{userID}")
    public ModelAndView addFriend(HttpServletRequest request,
                            @PathVariable Integer userID) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:/");
        UserService.addSubscribersByIDs(AuthService.getLogedUserID(request), userID);

        return mav;
    }


    @RequestMapping(value = "/friend/delete/{userID}")
    public ModelAndView deleteFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:/");
        UserService.deleteFriendsByIDs(AuthService.getLogedUserID(request), userID);

        return mav;
    }


    @RequestMapping(value = "/friend/submit/{userID}")
    public ModelAndView submitFriend(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:/");
        UserService.submitFriendsByIDs(AuthService.getLogedUserID(request), userID);

        return mav;
    }


    @RequestMapping(value = "/subscriber/delete/{userID}")
    public ModelAndView deleteSubscribers(HttpServletRequest request, @PathVariable Integer userID) throws SQLException {
        ModelAndView mav = new ModelAndView("redirect:/");
        UserService.deleteSubscribersByIDs(AuthService.getLogedUserID(request), userID);

        return mav;
    }

}
