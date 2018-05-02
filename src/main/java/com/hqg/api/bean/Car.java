package com.hqg.api.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 车型
 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer carSriesId;

    private String guidePrice;

    private Integer state;

//    private Integer carSource;

    private Date createTime;

    private Date modifyTime;

    private Integer isShow;

    private String appearanceColor;

    private String interiorColor;

    private String  isNewEnergy;

    @ManyToOne
    @JoinColumn(name="carSource",  nullable = false)
    private CarSource carsource;

//    @ManyToOne
//    @JoinColumn(name="carSriesId",  nullable = false)
//    private CarSeries carSeries;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCarSriesId() {
        return carSriesId;
    }

    public void setCarSriesId(Integer carSriesId) {
        this.carSriesId = carSriesId;
    }

    public String getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(String guidePrice) {
        this.guidePrice = guidePrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

//    public Integer getCarSource() {
//        return carSource;
//    }
//
//    public void setCarSource(Integer carSource) {
//        this.carSource = carSource;
//    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getAppearanceColor() {
        return appearanceColor;
    }

    public void setAppearanceColor(String appearanceColor) {
        this.appearanceColor = appearanceColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getIsNewEnergy() {
        return isNewEnergy;
    }

    public void setIsNewEnergy(String isNewEnergy) {
        this.isNewEnergy = isNewEnergy;
    }

    public CarSource getCarsouece() {
        return carsource;
    }

    public void setCarsouece(CarSource carsouece) {
        this.carsource = carsouece;
    }

//    public CarSeries getCarSeries() {
//        return carSeries;
//    }
//
//    public void setCarSeries(CarSeries carSeries) {
//        this.carSeries = carSeries;
//    }
}
