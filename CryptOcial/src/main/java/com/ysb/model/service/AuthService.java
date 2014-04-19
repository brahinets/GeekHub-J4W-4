package com.ysb.model.service;

import com.ysb.model.dao.UserDAOimpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    private static UserDAOimpl userDAOimpl = new UserDAOimpl();


    public static Integer getLogedUserID(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute("myID");
    }


    public static void setLogedUserID(Integer UserID, HttpServletRequest request) {
        request.getSession().setAttribute("myID", UserID);
    }


    public static void removeLogedUserID(HttpServletRequest request) {
        request.getSession().removeAttribute("myID");
    }


    public static void setUserOnline(Integer userID) throws Exception {
        userDAOimpl.changeUserStatus(userID, true);
    }


    public static void setUserOffline(Integer userID) throws Exception {
        userDAOimpl.changeUserStatus(userID, false);
    }

    public static boolean isLogged(HttpServletRequest request) throws Exception {
        return getLogedUserID(request) != null;
    }

    public static void logout(HttpServletRequest request) throws Exception {
        setUserOffline(getLogedUserID(request));
        removeLogedUserID(request);
    }
}
