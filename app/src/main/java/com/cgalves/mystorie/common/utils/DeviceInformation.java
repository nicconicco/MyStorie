package com.cgalves.mystorie.common.utils;

/**
 * Created by Scopus on 10/07/18.
 */

public class DeviceInformation {

    String manufacturer;
    String model;

    DeviceInformation(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  DeviceInformation) {
            DeviceInformation other = (DeviceInformation) obj;
            return this.model.equalsIgnoreCase(other.model) && this.manufacturer.equalsIgnoreCase(other.manufacturer);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 1;
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
