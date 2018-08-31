package com.cgalves.mystorie.feature.home.model;

import com.cgalves.mystorie.common.model.Error;

import java.util.List;

/**
 * Created by Scopus on 18/07/18.
 */

public class SectionResponse {

    private List<Section> list;
    private Error error;

    public SectionResponse(List<Section> list) {
        this.list = list;
    }

    public SectionResponse(Error error) {
        this.error = error;
    }

    public List<Section> getList() {
        return list;
    }

    public Error getError() {
        return error;
    }

    public boolean isSuccess(){
        return error == null;
    }
}
