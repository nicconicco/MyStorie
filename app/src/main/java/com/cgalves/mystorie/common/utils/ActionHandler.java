package com.cgalves.mystorie.common.utils;


public class ActionHandler {

    private Service service;

    public ActionHandler(Service service) {
        this.service = service;
    }

    public void doAction() {
        service.doAction("our-request",  response -> handleResponse(response));
    }

    private void handleResponse(Response response) {
        if (response.isValid()) {
            response.setData(new Data("Successful data response"));
        }
    }
}