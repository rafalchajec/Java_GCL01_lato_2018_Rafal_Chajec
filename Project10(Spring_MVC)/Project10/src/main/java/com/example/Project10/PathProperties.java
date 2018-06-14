package com.example.Project10;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("img.pictures")
public class PathProperties {
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
