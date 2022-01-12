package com.dalwadibrothers.kunal.recyclerviewdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class University extends BaseObservable {

    public String country;
    public List<String> domains;
    public String name;
    @SerializedName("state-province")
    public String stateProvince;
    public List<String> web_pages;
    public String alpha_two_code;

    public University(String country, List<String> domains, String name, String stateProvince, List<String> web_pages, String alpha_two_code) {
        this.country = country;
        this.domains = domains;
        this.name = name;
        this.stateProvince = stateProvince;
        this.web_pages = web_pages;
        this.alpha_two_code = alpha_two_code;
    }

    @Bindable
    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        notifyPropertyChanged(BR.stateProvince);
    }

    @Bindable
    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
        notifyPropertyChanged(BR.alpha_two_code);
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }

    @Bindable
    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
        notifyPropertyChanged(BR.domains);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public List<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
        notifyPropertyChanged(BR.web_pages);
    }
}
