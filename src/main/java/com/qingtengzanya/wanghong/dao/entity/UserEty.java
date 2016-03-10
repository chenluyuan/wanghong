package com.qingtengzanya.wanghong.dao.entity;

import com.ac.base.dao.BaseEntity;

/**
 * @author chenluyuan (chenluyuanit@gmail.com)
 */
public class UserEty extends com.ac.base.dao.BaseEntity {

    private Long id; // 主键
    private String username; // 用户名
    private String password; // 密码
    private Boolean enabled;

    public UserEty(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEty(String username) {
        this.username = username;
    }

    public UserEty() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
