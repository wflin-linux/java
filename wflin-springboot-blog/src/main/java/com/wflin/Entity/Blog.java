package com.wflin.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String date;
    private String title;
    private String content;
    private String  star;
    private String share;
    private String tag;
    private  Integer view = 1;

    public Blog() {
    }

    public Blog(String author, String date, String title, String content, String star, String share, String tag, Integer view) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.title = title;
        this.content = content;
        this.star = star;
        this.share = share;
        this.tag = tag;
        this.view = view;
    }

    public Blog(String author, String date, String title, String content, String star, String share, String tag) {
        this.author = author;
        this.date = date;
        this.title = title;
        this.content = content;
        this.star = star;
        this.share = share;
        this.tag = tag;
    }

    public Integer getView() {
        return view;
    }
}