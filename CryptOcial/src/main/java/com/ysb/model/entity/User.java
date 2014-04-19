package com.ysb.model.entity;

import com.ysb.model.utils.Utils;

import java.util.Collection;
import java.util.Date;


public class User extends Entity {


    private Boolean isOnline = false;
    private String name = null;
    private String surname = null;
    private String password = null;
    private Date birthdate = null;
    private String email = null;

    public User() {
    }

    public User(int id, String name, String surname, byte[] currentAvatar, boolean isOnline) {
        this.setId(id);
        this.name = name;
        this.surname = surname;
        this.currentAvatar = currentAvatar;
        this.isOnline = isOnline;
    }

    @Ignore
    private Integer countFriends = null;

    @Ignore
    private Integer unreadInputMail = null;

    @Ignore
    private Boolean isInMyBlackList = false;

    @Ignore
    private Boolean canWrite = null;

    @Ignore
    private Boolean canPost = null;

    @Ignore
    private String relation = null;

    private byte[] currentAvatar = null;

    @Ignore
    private Collection<User> listFriends = null;

    @Ignore
    private Collection<User> listOnWhoIsubscribed = null;

    @Ignore
    private Collection<User> listOfWhoSubscribedOnMe = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUnreadInputMail() {return unreadInputMail; }

    public void setUnreadInputMail(Integer unreadInputMail) { this.unreadInputMail = unreadInputMail; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<User> getListOnWhoIsubscribed() {
        return listOnWhoIsubscribed;
    }

    public void setListOnWhoIsubscribed(Collection<User> listOnWhoIsubscribed) {
        this.listOnWhoIsubscribed = listOnWhoIsubscribed;
    }

    public Collection<User> getListOfWhoSubscribedOnMe() {

        return listOfWhoSubscribedOnMe;
    }

    public void setListOfWhoSubscribedOnMe(Collection<User> listOfWhoSubscribedOnMe) { this.listOfWhoSubscribedOnMe = listOfWhoSubscribedOnMe; }

    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean isCanPost() {
        return canPost;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getCountFriends() {
        return countFriends;
    }

    public void setCountFriends(Integer countFriends) {
        this.countFriends = countFriends;
    }

    public Boolean getIsInMyBlackList() {
        return isInMyBlackList;
    }

    public void setIsInMyBlackList(Boolean isInMyBlackList) {
        this.isInMyBlackList = isInMyBlackList;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public Boolean getCanPost() {
        return canPost;
    }

    public void setCanPost(Boolean canPost) {
        this.canPost = canPost;
    }

    public byte[] getCurrentAvatar() {
        return currentAvatar;
    }

    public void setCurrentAvatar(byte[] currentAvatar) {
        this.currentAvatar = currentAvatar;
    }

    public Collection<User> getListFriends() {
        return listFriends;
    }

    public void setListFriends(Collection<User> listFriends) {
        this.listFriends = listFriends;
    }

    public String getAvatar() {
        if (this.currentAvatar != null)
            return Utils.bytesToBase64(this.currentAvatar);
        else
            return null;
    }
}
