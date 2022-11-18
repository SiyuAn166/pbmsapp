package com.petrobest.pbmsapp.websocket;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties {
    private String[] endpoints;
    private String[] destinationPrefixes;
    private String appDestinationPrefix;
    private String userDestinationPrefix;

}
