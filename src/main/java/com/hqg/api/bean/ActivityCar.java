package com.hqg.api.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动车型
 */
@Entity
@Table(name = "activity_car")
public class ActivityCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer carId;

    private Date time;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
