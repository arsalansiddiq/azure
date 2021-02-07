package com.inventa.azure.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AzureUtils {

    public static List<Map> getTabTags(Map<String, String> assetTags, String tag) {

        Map map;

        if (assetTags!=null && !assetTags.isEmpty()) {
            map = new HashMap(assetTags);
        } else {
            map = new HashMap();
        }

        map.put(Constants.ACCOUNT_TAG, tag);

        return Collections.singletonList(map);
    }

}
