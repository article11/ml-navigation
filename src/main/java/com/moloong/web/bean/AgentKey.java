package com.moloong.web.bean;

/**
 * Created by Administrator on 2016/11/3.
 */
public class AgentKey {


    private int id;
    private String agent;
    private String agent_name;
    private String login_key;
    private String recharge_key;
    private String cdn_dns;
    private String game_dns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getLogin_key() {
        return login_key;
    }

    public void setLogin_key(String login_key) {
        this.login_key = login_key;
    }

    public String getRecharge_key() {
        return recharge_key;
    }

    public void setRecharge_key(String recharge_key) {
        this.recharge_key = recharge_key;
    }

    public String getCdn_dns() {
        return cdn_dns;
    }

    public void setCdn_dns(String cdn_dns) {
        this.cdn_dns = cdn_dns;
    }

    public String getGame_dns() {
        return game_dns;
    }

    public void setGame_dns(String game_dns) {
        this.game_dns = game_dns;
    }

    public String getOrther_key() {
        return orther_key;
    }

    public void setOrther_key(String orther_key) {
        this.orther_key = orther_key;
    }

    public String getNew_user_card() {
        return new_user_card;
    }

    public void setNew_user_card(String new_user_card) {
        this.new_user_card = new_user_card;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String orther_key;
    private String new_user_card;
    private String time;


}
