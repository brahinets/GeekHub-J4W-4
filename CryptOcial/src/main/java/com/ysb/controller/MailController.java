package com.ysb.controller;

import com.ysb.model.dao.UserDAOimpl;
import com.ysb.model.entity.Message;
import com.ysb.model.entity.User;
import com.ysb.model.service.AuthService;
import com.ysb.model.service.MailService;
import com.ysb.model.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.DatatypeConverter;

@Controller
public class MailController {

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mail(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("mail");
        mav.addObject("mailList", MailService.getInputMessagesByUserID(AuthService.getLogedUserID(request)));

        return mav;
    }

    @RequestMapping(value = "/mail/out")
    public @ResponseBody
    Collection<Message> mailOutputList(HttpServletRequest request) throws Exception {
        return MailService.getOutputMessagesByUserID(AuthService.getLogedUserID(request));
    }

    @RequestMapping(value = "/mail/in")
    public @ResponseBody
    Collection<Message> mailInputList(HttpServletRequest request) throws Exception {
        return MailService.getInputMessagesByUserID(AuthService.getLogedUserID(request));
    }


    //see mail with som id
    @RequestMapping(value = "/mail/read/{id}")
    public ModelAndView mailRead(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        Message message = MailService.getMessageByID(id);

        if(message!=null) {
            message.setUser(UserService.getUserShort(message.getSender()));
            mav.setViewName("message");
            mav.addObject("message", message);
        } else {
            mav.setViewName("redirect:/mail");
        }

        return mav;
    }



    // new mail
    @RequestMapping(value = "/mail/write/{userID}", method = RequestMethod.GET)
    public ModelAndView mailWriteAnswer(
            @PathVariable Integer userID) throws Exception {
        ModelAndView mav = new ModelAndView("message");

        Message message = new Message();
        message.setUser(UserService.getUserShort(userID));
        mav.addObject("message", message);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(UserService.getUserShort(userID));
        mav.addObject("recipients", recipients);

        return mav;
    }


    @RequestMapping(value = "/mail/write", method = RequestMethod.GET)
    public ModelAndView mailWriteNew(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("message");
        Message message = new Message();
        message.setUser(UserService.getUserShort(AuthService.getLogedUserID(request)));
        mav.addObject("message", message);
        mav.addObject("recipients", UserService.getForWhoUserCanWrite(AuthService.getLogedUserID(request)));

        return mav;
    }



    @RequestMapping(value = "/mail/write", method = RequestMethod.POST)
    public ModelAndView mailAddToDB(HttpServletRequest request,
                                    @RequestParam Integer recipient,
                                    @RequestParam String theme,
                                    @RequestParam String content) throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/mail");

        Message message = new Message();
        message.setSender(AuthService.getLogedUserID(request));
        message.setRecipient(recipient);
        message.setTheme(theme);
        message.setText(content);
        message.setmDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        MailService.sendMessage(message);

        return mav;
    }
}