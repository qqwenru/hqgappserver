package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 汽车订单
 */
@Entity
@Table(name = "car_order")
public class CarOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shopId;

    private String customerName;

    private String linkMan;

    private String tel;

    private String address;

    private String idCard;

    private String orderInfo;

    private String salesConsultantId;

    private String salesConsultantName;

    private String orderId;

    private String orderTime;

    private Integer downPayment;

    private String serialNumber;

    private String payType;

    private Integer orderPrice;

    private String remark;

    private String deliverytime;

    private Integer orderType;

    private Integer quotedpriceId;

    private String brandName;

    private String seriesName;

    private String carName;

    private String appearanceColor;

    private String interiorColor;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getSalesConsultantId() {
        return salesConsultantId;
    }

    public void setSalesConsultantId(String salesConsultantId) {
        this.salesConsultantId = salesConsultantId;
    }

    public String getSalesConsultantName() {
        return salesConsultantName;
    }

    public void setSalesConsultantName(String salesConsultantName) {
        this.salesConsultantName = salesConsultantName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Integer downPayment) {
        this.downPayment = downPayment;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getQuotedpriceId() {
        return quotedpriceId;
    }

    public void setQuotedpriceId(Integer quotedpriceId) {
        this.quotedpriceId = quotedpriceId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
