package com.moloong.web.bean;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Permissions {

    private int id;
    private int uid;
    private int gid;
    private int login;
    private int recharge;
    private int agentKey;
    private int mediaCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecharge() {
        return recharge;
    }

    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    public int getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(int agentKey) {
        this.agentKey = agentKey;
    }

    public int getMediaCard() {
        return mediaCard;
    }

    public void setMediaCard(int mediaCard) {
        this.mediaCard = mediaCard;
    }
}
