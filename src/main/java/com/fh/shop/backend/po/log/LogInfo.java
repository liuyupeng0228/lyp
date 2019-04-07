package com.fh.shop.backend.po.log;

import com.fh.shop.backend.po.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class LogInfo extends Page implements Serializable {

    private static final long serialVersionUID = 5917716413449272554L;

    private Integer id;
    private String userName;
    private String logInfo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Integer excuteTime;
    private Integer status;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date minCreateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxCreateTime;

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public void setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public void setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(Integer excuteTime) {
        this.excuteTime = excuteTime;
    }
}
