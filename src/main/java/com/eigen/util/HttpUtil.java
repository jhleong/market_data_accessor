package com.eigen.util;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpUtil {
    
    private static final Logger logger = Logger.getLogger(HttpUtil.class.getName());

    public static String getURLParamsString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
            sb.append(String.format("%s=%s", key, value));
        }
        return sb.toString();
    }

}
