package com.sbutron;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigJSON {
    @JsonProperty("browser")
    private String browser;
    @JsonProperty("url")
    private String url;
    @JsonProperty("EXPLICIT_WAIT")
    private int explicitWait;

    @JsonProperty("incognito")
    private boolean incognito;
    @JsonProperty("remote-allow-origins")
    private boolean remoteAllowOrigins;

    public String getBrowser() {
        return this.browser;
    }
    public String getUrl() {
        return this.url;
    }
    public int getExplicitWait(){ return this.explicitWait; }
    public boolean getIncognito() {
        return incognito;
    }
    public boolean getRemoteAllowOrigins() {
        return remoteAllowOrigins;
    }
}

