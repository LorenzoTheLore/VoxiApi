package it.voxibyte.voxiapi.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static Map<String, Object> toNestedMap(Map<String, Object> flatMap) {
        Map<String, Object> nestedMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : flatMap.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            Map<String, Object> currentMap = nestedMap;

            for (int i = 0; i < keys.length; i++) {
                String key = keys[i];
                if (i == keys.length - 1) {
                    currentMap.put(key, entry.getValue());
                } else {
                    if (!currentMap.containsKey(key)) {
                        currentMap.put(key, new HashMap<String, Object>());
                    }
                    currentMap = (Map<String, Object>) currentMap.get(key);
                }
            }
        }

        return nestedMap;
    }

}
