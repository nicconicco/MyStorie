package com.cgalves.mystorie.common.presenter;


import com.cgalves.mystorie.common.base.ReturnSystemVO;

public interface BaseErrorCallback {
    void onError(String error);
    void onError(ReturnSystemVO returnSystemVO);
}
