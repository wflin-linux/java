package com.wflin.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author wflin
 */ // @Data 帮助我们自动生成了 get 和 set 的方法
@Data
// @Entity 帮我们绑定数据表与实体类
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private Integer view=1;

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Users() {
    }

    public Integer getView() {
        return view;
    }
}
