package com.ysb.model.service;

import com.ysb.model.dao.MailDAOimpl;
import com.ysb.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MailService {
    @Autowired
    private MailDAOimpl mailDAOimpl;

    @Autowired
    private UserService userService;


    public Message getMessageByID(Integer id) throws Exception {
        Message message = mailDAOimpl.get(id);
        message.setUser(userService.getUserShort(message.getSender()));

        return message;
    }


    public Collection<Message> getOutputMessages(Integer id) throws Exception {
        Collection<Message> mail = mailDAOimpl.getOutputMessages(id);
        for(Message message : mail){
            message.setUser(userService.getUser(message.getRecipient()));
        }

        return mail;
    }


    public Collection<Message> getInputMessages(Integer id) throws Exception {
        Collection<Message> mail = mailDAOimpl.getInputMessages(id);
        for(Message message : mail){
            message.setUser(userService.getUser(message.getSender()));
        }

        return mail;
    }


    public void sendMessage(Message message) throws Exception {
        mailDAOimpl.save(message);
    }


    public void markMessageAsRead(Integer id) {
        mailDAOimpl.markMessageAsRead(id);
    }


    public void deleteMessage(Integer userID, Integer messageID) throws Exception {
        Message message = getMessageByID(messageID);
        if (message != null) {
            if (message.getRecipient().equals(userID)) {
                mailDAOimpl.deleteByRecipient(message.getId());
            } else {
                mailDAOimpl.deleteBySender(message.getId());
            }
        }
    }

}