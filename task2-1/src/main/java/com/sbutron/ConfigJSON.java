package com.sbutron;

import com.fasterxml.jackson.annotation.JsonProperty;
public class ConfigJSON {
    @JsonProperty("BROWSER")
    private String browser;
    @JsonProperty("URL")
    private String url;
    @JsonProperty("IMPLICITLY_WAIT")
    private int implicitlyWait;
    @JsonProperty("PAGE_LOAD_TIMEOUT")
    private int pageLoadTimeout;

    @JsonProperty("YEAR")
    private String year;

    @JsonProperty("GAME")
    private String game;

    @JsonProperty("EXPLICIT_WAIT")
    private int explicitWait;

    public String getBrowser() {
        return this.browser;
    }
    public String getUrl() {
        return this.url;
    }
    public int getImplicitlyWait() {
        return this.implicitlyWait;
    }
    public int getPageLoadTimeout() {
        return this.pageLoadTimeout;
    }

    public String getYear(){
        return this.year;
    }

    public String getGame(){
        return this.game;
    }

    public int getExplicitWait(){ return this.explicitWait; }

}
