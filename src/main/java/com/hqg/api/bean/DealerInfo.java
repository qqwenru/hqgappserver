package com.hqg.api.bean;

import javax.persistence.*;
/**
 * 经销商信息
*/
@Entity
@Table(name = "dealer_info")
public class DealerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dealerName;

    private Integer state;

    private Integer provinceId;

    private Integer cityId;

    private String shortName;

    private String postCode;

    private String address;

    private String website;

    private String varcharIp;

    private String dingId;

    private String tel;

    private String allDayTel;

    private String businessTel;

    private String fittingsTel;

    private String faxTel;

    private String dingKeyt;

    private String bank;

    private String accountName;

    private String accountNum;

    private String taxNumber;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVarcharIp() {
        return varcharIp;
    }

    public void setVarcharIp(String varcharIp) {
        this.varcharIp = varcharIp;
    }

    public String getDingId() {
        return dingId;
    }

    public void setDingId(String dingId) {
        this.dingId = dingId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAllDayTel() {
        return allDayTel;
    }

    public void setAllDayTel(String allDayTel) {
        this.allDayTel = allDayTel;
    }

    public String getBusinessTel() {
        return businessTel;
    }

    public void setBusinessTel(String businessTel) {
        this.businessTel = businessTel;
    }

    public String getFittingsTel() {
        return fittingsTel;
    }

    public void setFittingsTel(String fittingsTel) {
        this.fittingsTel = fittingsTel;
    }

    public String getFaxTel() {
        return faxTel;
    }

    public void setFaxTel(String faxTel) {
        this.faxTel = faxTel;
    }

    public String getDingKeyt() {
        return dingKeyt;
    }

    public void setDingKeyt(String dingKeyt) {
        this.dingKeyt = dingKeyt;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
