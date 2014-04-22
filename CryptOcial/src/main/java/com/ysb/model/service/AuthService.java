package com.ysb.model.service;

import com.ysb.model.dao.UserDAOimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {

    @Autowired
    private UserDAOimpl userDAOimpl;

    @Autowired
    private UserService userService;

    public Integer getLogedUserID(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute("myID");
    }


    public void setLogedUserID(Integer UserID, HttpServletRequest request) {
        request.getSession().setAttribute("myID", UserID);
    }


    public void removeLogedUserID(HttpServletRequest request) {
        request.getSession().removeAttribute("myID");
    }


    public void setUserOnline(Integer userID) {
        userDAOimpl.changeUserStatus(userID, true);
    }


    public void setUserOffline(Integer userID) {
        userDAOimpl.changeUserStatus(userID, false);
    }


    public boolean isLogged(HttpServletRequest request) {
        return getLogedUserID(request) != null;
    }


    public void logout(HttpServletRequest request) throws Exception {
        setUserOffline(getLogedUserID(request));
        removeLogedUserID(request);
    }


    public void login(Integer userID, HttpServletRequest request) throws Exception {
        setLogedUserID(userID, request);
        setUserOnline(userID);
        request.getSession().setAttribute("loggedUser", userService.getUser(userID));
    }
}
