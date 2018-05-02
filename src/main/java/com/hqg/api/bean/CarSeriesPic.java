package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 系列图片组
 */
@Entity
@Table(name = "car_series_pic")
public class CarSeriesPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer carSriesId;

    private String picPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarSriesId() {
        return carSriesId;
    }

    public void setCarSriesId(Integer carSriesId) {
        this.carSriesId = carSriesId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
