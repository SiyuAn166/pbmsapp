package com.petrobest.pbmsapp.fbox.utils;

import com.petrobest.pbmsapp.fbox.config.FboxPropertiesUtils;

public class FboxUtils {
    public static String getFboxIdByTopic(String topic) {
        String prefix = FboxPropertiesUtils.getProperty("topic_prefix");
        String suffix = FboxPropertiesUtils.getProperty("topic_suffix");
        String deviceId = topic.substring(topic.indexOf(prefix) + prefix.length(), topic.indexOf(suffix));
        return deviceId;
    }

}
