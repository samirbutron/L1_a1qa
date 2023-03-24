package com.sbutron;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDataJSON {
    @JsonProperty("year")
    private String year;
    @JsonProperty("game")
    private String game;

    public String getYear(){
        return this.year;
    }
    public String getGame(){
        return this.game;
    }
}
