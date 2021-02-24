package com.roshanaryal.mymall.model;

import java.util.List;

public class HomePageModel
{
    private int type;


    //Banner slider
    private List<SliderModal> mSliderModalList;
    public HomePageModel(int type, List<SliderModal> sliderModalList) {
        this.type = type;
        mSliderModalList = sliderModalList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModal> getSliderModalList() {
        return mSliderModalList;
    }
    public void setSliderModalList(List<SliderModal> sliderModalList) {
        mSliderModalList = sliderModalList;
    }
    //baneere slider

    //stripe
    private int resources;
    private String backgroundColor;

    public HomePageModel(int type, int resources, String backgroundColor) {
        this.type = type;
        this.resources = resources;
        this.backgroundColor = backgroundColor;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    //stripe
}
