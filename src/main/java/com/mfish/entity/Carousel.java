package com.mfish.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 轮播图模块
 */
@ApiModel
public class Carousel implements Serializable {

    @ApiModelProperty(value = "轮播图片地址")
    String imgUrl;
    @ApiModelProperty(value = "跳转地址")
    String url;

    @Override
    public String toString() {
        return "Carousel{" +
                "imgUrl='" + imgUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
