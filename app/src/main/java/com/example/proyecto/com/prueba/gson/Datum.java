package com.example.proyecto.com.prueba.gson;

import java.util.HashMap;
import java.util.Map;



public class Datum {

    private Integer id;
    private String first_name;
    private Integer heightFeet;
    private Integer heightInches;
    private String last_name;
    private String position;
    private Team team;
    private Integer weightPounds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param weightPounds
     * @param heightInches
     * @param id
     * @param heightFeet
     * @param position
     * @param team
     */
    public Datum(Integer id, String firstName, Integer heightFeet, Integer heightInches, String lastName, String position, Team team, Integer weightPounds) {
        super();
        this.id = id;
        this.first_name = firstName;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.last_name = lastName;
        this.position = position;
        this.team = team;
        this.weightPounds = weightPounds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Integer weightPounds) {
        this.weightPounds = weightPounds;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
