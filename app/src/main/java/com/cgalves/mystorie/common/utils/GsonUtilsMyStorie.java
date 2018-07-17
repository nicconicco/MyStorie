package com.cgalves.mystorie.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

public class GsonUtilsMyStorie {
    public static <T>List<T> jsonToObjectList(String json) {
        Type collectionType = new TypeToken<List<T>>(){}.getType();
        return new Gson()
                .fromJson( json , collectionType);
    }
}
