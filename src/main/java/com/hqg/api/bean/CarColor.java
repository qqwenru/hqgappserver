package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 汽车颜色
 */
@Entity
@Table(name = "car_color")
public class CarColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer carSriesId;

    private String appearanceColor;

    private String interiorColor;

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

}
