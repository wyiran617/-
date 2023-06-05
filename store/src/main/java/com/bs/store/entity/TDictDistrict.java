package com.bs.store.entity;

import java.io.Serializable;

/**
 * (TDictDistrict)实体类
 *
 * @author makejava
 * @since 2022-11-07 17:19:03
 */
public class TDictDistrict implements Serializable {
    private static final long serialVersionUID = 492495830181711719L;

    private Integer id;

    private String parent;

    private String code;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

