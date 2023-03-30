package com.utils;

import java.util.Map;

public class MetadataUtils {
    public static boolean existsCommonTag(Map<String, String> firstSetOfMetadata, Map<String, String> secondSetOfMetadata) {
        for (String key : firstSetOfMetadata.keySet()) {
            if (!secondSetOfMetadata.containsKey(key)) {
                continue;
            }
            Object firstValue = firstSetOfMetadata.get(key);
            Object secondValue = secondSetOfMetadata.get(key);
            if (firstValue.equals(secondValue)) {
                return true;
            }
        }
        return false;
    }
}
