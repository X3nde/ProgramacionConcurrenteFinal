package com.progcon.programacionconcurrentefinal.sensor_processing.model;

public class SensorItem {
    private String sensorData;

    public SensorItem(String sensorData) {
        this.sensorData = sensorData;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    @Override
    public String toString() {
        return "SensorItem{" +
                "sensorData='" + sensorData + '\'' +
                '}';
    }
}
