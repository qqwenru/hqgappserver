package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 计数
 */
@Entity
@Table(name="car_series_count")
public class CarSeriesCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer saleNum;

    private Integer concernNum;

    private Integer viewNum;

    private Integer cityId;

    private Integer carSeriesId;

    private Integer quotedPriceNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(Integer concernNum) {
        this.concernNum = concernNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public Integer getQuotedPriceNum() {
        return quotedPriceNum;
    }

    public void setQuotedPriceNum(Integer quotedPriceNum) {
        this.quotedPriceNum = quotedPriceNum;
    }
}
