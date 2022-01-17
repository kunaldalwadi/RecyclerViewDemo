package com.dalwadibrothers.kunal.recyclerviewdemo.model.db;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dalwadibrothers.kunal.recyclerviewdemo.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "universities")
public class University extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int uni_id;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "domains")
    private List<String> domains;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "stateProvince")
    @SerializedName("state-province")
    private String stateProvince;

    @ColumnInfo(name = "web_pages")
    private List<String> web_pages;

    @ColumnInfo(name = "alpha_two_code")
    private String alpha_two_code;

    @Ignore
    public University(){

    }

    public University(String country, List<String> domains, String name, String stateProvince, List<String> web_pages, String alpha_two_code) {
        this.country = country;
        this.domains = domains;
        this.name = name;
        this.stateProvince = stateProvince;
        this.web_pages = web_pages;
        this.alpha_two_code = alpha_two_code;
    }

    @Bindable
    public int getUni_id() {
        return uni_id;
    }

    public void setUni_id(int uni_id) {
        this.uni_id = uni_id;
        notifyPropertyChanged(BR.uni_id);
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
