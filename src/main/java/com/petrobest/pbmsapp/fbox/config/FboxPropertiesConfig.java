package com.petrobest.pbmsapp.fbox.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("fbox")
public class FboxPropertiesConfig {

    private String topicPrefix;

    private String topicSuffix;

    private String pubTopic;

    private String subTopic;
}
