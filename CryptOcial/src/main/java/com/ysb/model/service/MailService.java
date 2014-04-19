package com.ysb.model.service;

import com.ysb.model.dao.MessageDAOimpl;
import com.ysb.model.entity.Message;
import flexjson.JSONSerializer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yarik on 22.03.14.
 */
//@Service
public class MailService {
    static MessageDAOimpl messageDAOimpl = new MessageDAOimpl();

    public static Message getMessageByID(Integer id) throws Exception {
        Message message = messageDAOimpl.getMessageByID(id);
        message.setUser(UserService.getUserShort(message.getSender()));

        return message;
    }


    public static List<Message> getOutputMessagesByUserID(Integer id) throws Exception {
        List<Message> messages = messageDAOimpl.getOutputMessagesByUserID(id);

        for(Message message : messages){
//            message.setUser(UserService.getUserShort(message.getRecipient()));


        }

        return messages;
    }


    public static List<Message> getInputMessagesByUserID(Integer id) throws Exception {
        List<Message> messages = messageDAOimpl.getInputMessagesByUserID(id);
        List<String> messagesS = new ArrayList<>();

        for(Message message : messages){
//            message.setUser(UserService.getUserShort(message.getSender()));
        }

        return messages;
    }


    public static boolean sendMessage(Message message) throws Exception {
        return messageDAOimpl.sendMessage(message);
    }

}