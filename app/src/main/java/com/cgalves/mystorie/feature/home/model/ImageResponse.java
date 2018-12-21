package com.cgalves.mystorie.feature.home.model;

import com.cgalves.mystorie.common.model.Error;

import java.util.List;

/**
 * Created by Scopus on 18/07/18.
 */

public class ImageResponse {

    private List<Image> list;
    private Error error;

    public ImageResponse(List<Image> list) {
        this.list = list;
    }

    public ImageResponse(Error error) {
        this.error = error;
    }

    public ImageResponse() {
        // this is proposital and needed, dont ask why
    }

    public List<Image> getList() {
        return list;
    }

    public Error getError() {
        return error;
    }

    public void setList(List<Image> list) {
        this.list = list;
    }

    public boolean isSuccess(){
        return error == null;
    }
}
