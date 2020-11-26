package com.example.trieunv;

public class DailyWeather {
    private String dayOfWeek, dayOfYear, status, temper;
    private String weatherIcon;

    public DailyWeather(String dayOfWeek, String dayOfYear, String status, String temper, String weatherIcon) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfYear = dayOfYear;
        this.status = status;
        this.temper = temper;
        this.weatherIcon = weatherIcon;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(String dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemper() {
        return temper;
    }

    public void setTemper(String temper) {
        this.temper = temper;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
