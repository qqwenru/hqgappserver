package com.hqg.api.bean;

import javax.persistence.*;

/**
 * 报价
 */
@Entity
@Table(name="quoted_price")
public class QuotedPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cityId;//城市标志
    private Integer brandId;//品牌标志
    private Integer carSeriesId;//车系标志
    private Integer carId;//车型标志
    private Integer dealerId;//经销商标志
    private Integer netPrice;//净车价
    private Integer purchaseTax;//购置税
    private Integer vehicleAndVesselTax;//车船税
    private Integer licensePlatePrice;//上牌标志
    private Integer compulsoryInsurance;//交强险
    private Integer commercialInsurance;//商业保险
    private Integer otherPrice;//精品套装
    private Integer mortgagePrice;//按揭（另计）
    private Integer allPrice;//全包售价
    private Integer saleType;//销售类型
    private Integer subsidyPrice;//补贴价格
    private String subsidyRemark; //补贴说明
    private String insuranceRemark; //保险说明
    private String mortgageRemark; //按揭说明
    private  Integer insuranceAccount1;
    private  Integer insurancePrice1;
    private  Integer insuranceAccount2;
    private  Integer insurancePrice2;
    private  Integer insuranceAccount3;
    private  Integer insurancePrice3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Integer netPrice) {
        this.netPrice = netPrice;
    }

    public Integer getPurchaseTax() {
        return purchaseTax;
    }

    public void setPurchaseTax(Integer purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public Integer getVehicleAndVesselTax() {
        return vehicleAndVesselTax;
    }

    public void setVehicleAndVesselTax(Integer vehicleAndVesselTax) {
        this.vehicleAndVesselTax = vehicleAndVesselTax;
    }

    public Integer getLicensePlatePrice() {
        return licensePlatePrice;
    }

    public void setLicensePlatePrice(Integer licensePlatePrice) {
        this.licensePlatePrice = licensePlatePrice;
    }

    public Integer getCompulsoryInsurance() {
        return compulsoryInsurance;
    }

    public void setCompulsoryInsurance(Integer compulsoryInsurance) {
        this.compulsoryInsurance = compulsoryInsurance;
    }

    public Integer getCommercialInsurance() {
        return commercialInsurance;
    }

    public void setCommercialInsurance(Integer commercialInsurance) {
        this.commercialInsurance = commercialInsurance;
    }

    public Integer getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(Integer otherPrice) {
        this.otherPrice = otherPrice;
    }

    public Integer getMortgagePrice() {
        return mortgagePrice;
    }

    public void setMortgagePrice(Integer mortgagePrice) {
        this.mortgagePrice = mortgagePrice;
    }

    public Integer getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public Integer getSubsidyPrice() {
        return subsidyPrice;
    }

    public void setSubsidyPrice(Integer subsidyPrice) {
        this.subsidyPrice = subsidyPrice;
    }

    public String getSubsidyRemark() {
        return subsidyRemark;
    }

    public void setSubsidyRemark(String subsidyRemark) {
        this.subsidyRemark = subsidyRemark;
    }

    public String getInsuranceRemark() {
        return insuranceRemark;
    }

    public void setInsuranceRemark(String insuranceRemark) {
        this.insuranceRemark = insuranceRemark;
    }

    public String getMortgageRemark() {
        return mortgageRemark;
    }

    public void setMortgageRemark(String mortgageRemark) {
        this.mortgageRemark = mortgageRemark;
    }

    public Integer getInsuranceAccount1() {
        return insuranceAccount1;
    }

    public void setInsuranceAccount1(Integer insuranceAccount1) {
        this.insuranceAccount1 = insuranceAccount1;
    }

    public Integer getInsurancePrice1() {
        return insurancePrice1;
    }

    public void setInsurancePrice1(Integer insurancePrice1) {
        this.insurancePrice1 = insurancePrice1;
    }

    public Integer getInsuranceAccount2() {
        return insuranceAccount2;
    }

    public void setInsuranceAccount2(Integer insuranceAccount2) {
        this.insuranceAccount2 = insuranceAccount2;
    }

    public Integer getInsurancePrice2() {
        return insurancePrice2;
    }

    public void setInsurancePrice2(Integer insurancePrice2) {
        this.insurancePrice2 = insurancePrice2;
    }

    public Integer getInsuranceAccount3() {
        return insuranceAccount3;
    }

    public void setInsuranceAccount3(Integer insuranceAccount3) {
        this.insuranceAccount3 = insuranceAccount3;
    }

    public Integer getInsurancePrice3() {
        return insurancePrice3;
    }

    public void setInsurancePrice3(Integer insurancePrice3) {
        this.insurancePrice3 = insurancePrice3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(Integer carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }
}
