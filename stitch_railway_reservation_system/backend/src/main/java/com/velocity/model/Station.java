package com.velocity.model;

import java.util.List;

public class Station {
    private String code;
    private String name;
    private String city;
    private String state;
    private String zone;
    private List<String> platforms;

    public Station() {}

    public Station(String code, String name, String city, String state, String zone, List<String> platforms) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
        this.zone = zone;
        this.platforms = platforms;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public List<String> getPlatforms() { return platforms; }
    public void setPlatforms(List<String> platforms) { this.platforms = platforms; }
}
