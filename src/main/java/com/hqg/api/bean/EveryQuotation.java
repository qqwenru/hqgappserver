package com.hqg.api.bean;


import javax.persistence.*;

import java.util.Date;
@Entity
@Table(name="share_bill_every_quotation")
public class EveryQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer carId;
    private Integer carPrice;
    private Date quotDate;
    private Integer offerId;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Integer carPrice) {
        this.carPrice = carPrice;
    }

    public Date getQuotDate() {
        return quotDate;
    }

    public void setQuotDate(Date quotDate) {
        this.quotDate = quotDate;
    }

    public Integer getOffererId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
