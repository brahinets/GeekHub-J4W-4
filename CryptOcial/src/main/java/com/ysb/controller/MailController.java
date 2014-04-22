package com.ysb.controller;

import com.ysb.model.entity.Message;
import com.ysb.model.entity.User;
import com.ysb.model.service.AuthService;
import com.ysb.model.service.MailService;
import com.ysb.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mail(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("mail");
        mav.addObject("mailList", mailService.getInputMessages(authService.getLogedUserID(request)));

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/mail/out")
    public Collection<Message> outputMailList(HttpServletRequest request) throws Exception {

        return mailService.getOutputMessages(authService.getLogedUserID(request));
    }


    @ResponseBody
    @RequestMapping(value = "/mail/in")
    public Collection<Message> inputMailList(HttpServletRequest request) throws Exception {
        return mailService.getInputMessages(authService.getLogedUserID(request));
    }


    @RequestMapping(value = "/mail/read/{id}")
    public ModelAndView readMessage(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        Message message = mailService.getMessageByID(id);

        if (message != null) {
            message.setUser(userService.getUserShort(message.getSender()));
            mav.setViewName("message");
            mav.addObject("message", message);
            if (message.getRecipient().equals(authService.getLogedUserID(request))) {
                mailService.markMessageAsRead(message.getId());
            }
        } else {
            mav.setViewName("redirect:/mail");
        }

        return mav;
    }


    @RequestMapping(value = "/mail/write/{userID}", method = RequestMethod.GET)
    public ModelAndView answerToMessage(@PathVariable Integer userID) {
        ModelAndView mav = new ModelAndView("message");

        Message message = new Message();
        message.setUser(userService.getUserShort(userID));
        mav.addObject("message", message);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(userService.getUserShort(userID));
        mav.addObject("recipients", recipients);

        return mav;
    }


    @RequestMapping(value = "/mail/write", method = RequestMethod.GET)
    public ModelAndView writeNewMessage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("message");
        Message message = new Message();
        message.setUser(userService.getUserShort(authService.getLogedUserID(request)));
        mav.addObject("message", message);
        mav.addObject("recipients", userService.getForWhoUserCanWrite(authService.getLogedUserID(request)));

        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/mail/write", method = RequestMethod.POST)
    public ModelAndView sendMessage(HttpServletRequest request,
                                    @RequestParam Integer recipient,
                                    @RequestParam String theme,
                                    @RequestParam String content) throws Exception {
        ModelAndView mav = new ModelAndView("redirect:/mail");

        Message message = new Message();
        message.setSender(authService.getLogedUserID(request));
        message.setRecipient(recipient);
        message.setTheme(theme);
        message.setText(content);
        message.setmDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        mailService.sendMessage(message);
        return mav;
    }


    @ResponseBody
    @RequestMapping(value = "/mail/delete")
    public void deleteMessage(@RequestParam Integer id, HttpServletRequest request) throws Exception {
        mailService.deleteMessage(authService.getLogedUserID(request), id);
    }

}