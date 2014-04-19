package com.ysb.model.entity;

import java.util.Date;

public class Message extends Entity{

    private Integer sender;
    private Integer recipient;
    private String theme;
    private String text;
    private Date mDate;
    private Boolean isRead = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Ignore
    private User user;


    public Message(){

    }

    public Message(Integer sender, Integer recipient, String theme, String text, Date mDate) {
        this.sender = sender;
        this.recipient = recipient;
        this.theme = theme;
        this.text = text;
        this.mDate = mDate;
    }

    public Message(Integer id, Integer sender, Integer recipient, String theme, String text, Date mDate) {
        this.setId(id);
        this.sender = sender;
        this.recipient = recipient;
        this.theme = theme;
        this.text = text;
        this.mDate = mDate;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isRead() {
        return isRead;
    }

    public void setRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

}
