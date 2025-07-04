package org.example.utils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    //  http://localhost:8001/reset-password.html?token=12345678
    public static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null || query.isEmpty()) return result;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0 && idx < pair.length() - 1) {
                String key = decode(pair.substring(0, idx));
                String value = decode(pair.substring(idx + 1));
                result.put(key, value);
            } else if (idx == pair.length() - 1) {
                String key = decode(pair.substring(0, idx));
                result.put(key, "");
            } else {
                result.put(decode(pair), "");
            }
        }
        return result;
    }

    // Decodificare URL encoded (ex: %20 => ' ' <==> 32 cod ASCII)
    private static String decode(String s) {
        try {
            return java.net.URLDecoder.decode(s, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return s;
        }
    }
}

