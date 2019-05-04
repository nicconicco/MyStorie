package com.cgalves.mystorie.common.utils;

public interface Service {
    void doAction(String request, Callback<Response> callback);
}
