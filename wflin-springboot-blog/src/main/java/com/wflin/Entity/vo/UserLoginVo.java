package com.wflin.Entity.vo;

import lombok.Data;

@Data
public class UserLoginVo {
    private String email;
    private Integer code;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}