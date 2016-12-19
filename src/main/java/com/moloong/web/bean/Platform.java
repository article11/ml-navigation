package com.moloong.web.bean;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Platform {

    /** 平台ID*/
    private Integer id;

    /** 游戏ID*/
    private Integer gameId;

    /** 平台标识*/
    private String platformAlias;


    /** 平台名字*/
    private String platformName;

    /** 平台登录key*/
    private String platformLoginKey;

    /** 内部登录key*/
    private String innerLoginKey;

    /** 充值key*/
    private String rechargeKey;

    /** 新手卡key*/
    private String newsCardKey;

    /** 登陆ip白名单*/
    private String loginAllowIp;

    /** 充值ip白名单*/
    private String rechargeAllowIp;

    /** 登陆账号白名单*/
    private String loginAllowAccount;

    /** 充值账号白名单*/
    private String rechargeAllowAccount;

    /** cdn地址*/
    private String cdn;

    /** 充值地址*/
    private String rechargeUrl;

    /** 防沉迷地址*/
    private String fcmUrl;

    /** 论坛地址地址*/
    private String bbsUrl;

    /** 新手卡地址*/
    private String newUserCradUrl;

    /** gm地址*/
    private String gmUrl;

    /** 官网地址*/
    private String websiteUrl;

    /** 客服地址*/
    private String kefuUrl;

    /** 是否关闭充值*/
    private String isCloseRecharge;

    /** 微端下载地址*/
    private String loginExeUrl;

    /** 是否删除*/
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformAlias() {
        return platformAlias;
    }

    public void setPlatformAlias(String platformAlias) {
        this.platformAlias = platformAlias;
    }

    public String getPlatformLoginKey() {
        return platformLoginKey;
    }

    public void setPlatformLoginKey(String platformLoginKey) {
        this.platformLoginKey = platformLoginKey;
    }

    public String getInnerLoginKey() {
        return innerLoginKey;
    }

    public void setInnerLoginKey(String innerLoginKey) {
        this.innerLoginKey = innerLoginKey;
    }

    public String getRechargeKey() {
        return rechargeKey;
    }

    public void setRechargeKey(String rechargeKey) {
        this.rechargeKey = rechargeKey;
    }
    public String getNewsCardKey() {
        return newsCardKey;
    }

    public void setNewsCardKey(String newsCardKey) {
        this.newsCardKey = newsCardKey;
    }
    public String getLoginAllowIp() {
        return loginAllowIp;
    }

    public void setLoginAllowIp(String loginAllowIp) {
        this.loginAllowIp = loginAllowIp;
    }

    public String getRechargeAllowIp() {
        return rechargeAllowIp;
    }

    public void setRechargeAllowIp(String rechargeAllowIp) {
        this.rechargeAllowIp = rechargeAllowIp;
    }

    public String getLoginAllowAccount() {
        return loginAllowAccount;
    }

    public void setLoginAllowAccount(String loginAllowAccount) {
        this.loginAllowAccount = loginAllowAccount;
    }

    public String getRechargeAllowAccount() {
        return rechargeAllowAccount;
    }

    public void setRechargeAllowAccount(String rechargeAllowAccount) {
        this.rechargeAllowAccount = rechargeAllowAccount;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCdn() {
        return cdn;
    }

    public void setCdn(String cdn) {
        this.cdn = cdn;
    }

    public String getRechargeUrl() {
        return rechargeUrl;
    }

    public void setRechargeUrl(String rechargeUrl) {
        this.rechargeUrl = rechargeUrl;
    }

    public String getFcmUrl() {
        return fcmUrl;
    }

    public void setFcmUrl(String fcmUrl) {
        this.fcmUrl = fcmUrl;
    }

    public String getBbsUrl() {
        return bbsUrl;
    }

    public void setBbsUrl(String bbsUrl) {
        this.bbsUrl = bbsUrl;
    }

    public String getNewUserCradUrl() {
        return newUserCradUrl;
    }

    public void setNewUserCradUrl(String newUserCradUrl) {
        this.newUserCradUrl = newUserCradUrl;
    }

    public String getGmUrl() {
        return gmUrl;
    }

    public void setGmUrl(String gmUrl) {
        this.gmUrl = gmUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getKefuUrl() {
        return kefuUrl;
    }

    public void setKefuUrl(String kefuUrl) {
        this.kefuUrl = kefuUrl;
    }

    public String getIsCloseRecharge() {
        return isCloseRecharge;
    }

    public void setIsCloseRecharge(String isCloseRecharge) {
        this.isCloseRecharge = isCloseRecharge;
    }

    public String getLoginExeUrl() {
        return loginExeUrl;
    }

    public void setLoginExeUrl(String loginExeUrl) {
        this.loginExeUrl = loginExeUrl;
    }
}
