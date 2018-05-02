package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 属性参数组
 */
@Entity
@Table(name = "car_info_parameter")
public class CarInfoParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer state;

    @ManyToOne
    @JoinColumn(name="groupId",  nullable = false)
    private ParameterGroup parameterGroup;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public ParameterGroup getParameterGroup() {
        return parameterGroup;
    }

    public void setParameterGroup(ParameterGroup parameterGroup) {
        this.parameterGroup = parameterGroup;
    }
}
