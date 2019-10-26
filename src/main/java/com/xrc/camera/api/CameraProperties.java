package com.xrc.camera.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("camera")
public class CameraProperties {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
