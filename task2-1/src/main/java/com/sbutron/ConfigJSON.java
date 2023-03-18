package com.sbutron;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigJSON {
    @JsonProperty("browser")
    private String browser;
    @JsonProperty("url")
    private String url;
    @JsonProperty("year")
    private String year;
    @JsonProperty("game")
    private String game;
    @JsonProperty("EXPLICIT_WAIT")
    private int explicitWait;

    public String getBrowser() {
        return this.browser;
    }
    public String getUrl() {
        return this.url;
    }
    public String getYear(){
        return this.year;
    }
    public String getGame(){
        return this.game;
    }
    public int getExplicitWait(){ return this.explicitWait; }

}

