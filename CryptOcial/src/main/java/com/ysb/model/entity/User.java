package com.ysb.model.entity;

import com.ysb.model.dao.MailDAOimpl;
import com.ysb.model.utils.Utils;

import java.util.Collection;
import java.util.Date;


public class User extends Entity {
    private Boolean isOnline = false;
    private String name = null;
    private String surname = null;
    private String whereFrom = null;
    private String password = null;
    private Date birthDate = null;
    private String email = null;
    private Boolean gender = null;

    private Boolean isActive = true;

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
    private Boolean isInMyBlackListWrite = false;

    @Ignore
    private Boolean isInMyBlackListPost = false;

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

    @Ignore
    private Collection<Post> posts = null;

    public User(int id, String name, String surname, byte[] currentAvatar, boolean isOnline, String from) {
        this.setId(id);
        this.name = name;
        this.surname = surname;
        this.currentAvatar = currentAvatar;
        this.isOnline = isOnline;
        this.whereFrom = from;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUnreadInputMail() {
        return new MailDAOimpl().getUnreadMailCount(this.getId());
    }

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

    public void setListOfWhoSubscribedOnMe(Collection<User> listOfWhoSubscribedOnMe) {
        this.listOfWhoSubscribedOnMe = listOfWhoSubscribedOnMe;
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

    public Boolean getIsInMyBlackListPost() {
        return isInMyBlackListPost;
    }

    public void setIsInMyBlackListPost(Boolean isInMyBlackListPost) {
        this.isInMyBlackListPost = isInMyBlackListPost;
    }

    public Boolean getIsInMyBlackListWrite() {
        return isInMyBlackListWrite;
    }

    public void setIsInMyBlackListWrite(Boolean isInMyBlackListWrite) {
        this.isInMyBlackListWrite = isInMyBlackListWrite;
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public String getAvatar() {
        if (this.currentAvatar != null)
            return Utils.bytesToBase64(this.currentAvatar);
        else
            return null;
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
}
