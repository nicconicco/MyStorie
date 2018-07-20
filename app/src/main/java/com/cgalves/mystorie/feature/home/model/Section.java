package com.cgalves.mystorie.feature.home.model;

/**
 * Created by Scopus on 17/07/18.
 */

public class Section {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    private Long id;
    private String subTitle;
}
