
package com.example.proyecto.com.prueba.gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Player {

    private List<Datum> data = null;
    private Meta meta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Player() {
    }

    /**
     * 
     * @param data
     * @param meta
     */
    public Player(List<Datum> data, Meta meta) {
        super();
        this.data = data;
        this.meta = meta;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
