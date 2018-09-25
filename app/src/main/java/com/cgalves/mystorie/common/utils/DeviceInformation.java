package com.cgalves.mystorie.common.utils;

/**
 * Created by Carlos Nicolau Galves on 10/07/18.
 */

public class DeviceInformation {
    DeviceInformation(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
    String manufacturer;
    String model;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  DeviceInformation) {
            DeviceInformation other = (DeviceInformation) obj;
            return this.model.equalsIgnoreCase(other.model) && this.manufacturer.equalsIgnoreCase(other.manufacturer);
        } else {
            return false;
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "DeviceInformation{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
