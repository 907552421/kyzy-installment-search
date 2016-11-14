package com.kyzy.kyzyinstallment.search.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * Created by 90755 on 2016/11/14.
 */
public class SkuInfoDTO {

    private Long id;

    private Integer spuId;

    private String name;

    private String imagePath;

    private BigDecimal price;

    private String brandName;

    private Long comtCount;

    @JsonIgnore
    private Long jdSkuId;

    @JsonIgnore
    private BigDecimal jdPrice;

    private SkuInfoDTO(){}

    public SkuInfoDTO(Long id, Integer spuId, String name, String imagePath, BigDecimal price, String brandName, Long comtCount, Long jdSkuId, BigDecimal jdPrice) {
        this.id = id;
        this.spuId = spuId;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.brandName = brandName;
        this.comtCount = comtCount;
        this.jdSkuId = jdSkuId;
        this.jdPrice = jdPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getComtCount() {
        return comtCount;
    }

    public void setComtCount(Long comtCount) {
        this.comtCount = comtCount;
    }

    public Long getJdSkuId() {
        return jdSkuId;
    }

    public void setJdSkuId(Long jdSkuId) {
        this.jdSkuId = jdSkuId;
    }

    public BigDecimal getJdPrice() {
        return jdPrice;
    }

    public void setJdPrice(BigDecimal jdPrice) {
        this.jdPrice = jdPrice;
    }
}
