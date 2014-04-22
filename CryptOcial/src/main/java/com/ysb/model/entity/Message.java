package com.ysb.model.entity;

import java.util.Date;

public class Message extends Entity {
    private Integer sender = null;
    private Integer recipient = null;
    private String theme = null;
    private String text = null;
    private Date mDate = null;
    private Boolean isRead = false;
    private Boolean isDeletedBySender = false;
    private Boolean isDeletedByRecipient = false;
    @Ignore
    private User user;

    public Message() {
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

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getIsDeletedBySender() {
        return isDeletedBySender;
    }

    public void setIsDeletedBySender(Boolean isDeletedBySender) {
        this.isDeletedBySender = isDeletedBySender;
    }

    public Boolean getIsDeletedByRecipient() {
        return isDeletedByRecipient;
    }

    public void setIsDeletedByRecipient(Boolean isDeletedByRecipient) {
        this.isDeletedByRecipient = isDeletedByRecipient;
    }

}
