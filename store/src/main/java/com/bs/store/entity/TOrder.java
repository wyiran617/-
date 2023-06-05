package com.bs.store.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TOrder)实体类
 *
 * @author makejava
 * @since 2023-05-09 13:47:38
 */
public class TOrder implements Serializable {
    private static final long serialVersionUID = -12746884620796976L;
    /**
     * 订单id
     */
    private String oid;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 收货人姓名
     */
    private String recvName;
    /**
     * 收货人电话
     */
    private String recvPhone;
    /**
     * 收货人所在省
     */
    private String recvProvince;
    /**
     * 收货人所在市
     */
    private String recvCity;
    /**
     * 收货人所在区
     */
    private String recvArea;
    /**
     * 收货详细地址
     */
    private String recvAddress;
    /**
     * 总价
     */
    private Long totalPrice;
    /**
     * 状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
     */
    private Integer status;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String modifiedUser;
    /**
     * 修改时间
     */
    private Date modifiedTime;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public String getRecvProvince() {
        return recvProvince;
    }

    public void setRecvProvince(String recvProvince) {
        this.recvProvince = recvProvince;
    }

    public String getRecvCity() {
        return recvCity;
    }

    public void setRecvCity(String recvCity) {
        this.recvCity = recvCity;
    }

    public String getRecvArea() {
        return recvArea;
    }

    public void setRecvArea(String recvArea) {
        this.recvArea = recvArea;
    }

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

}

