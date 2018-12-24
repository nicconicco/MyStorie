package com.cgalves.mystorie.common.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

public final class GsonUtility {
    public static <T> List<T> jsonToObjectList(String json) {
        Type collectionType = new TypeToken<List<T>>() {
        }.getType();
        return new Gson()
                .fromJson(json, collectionType);
    }

    public static String objectToJson(Object object) {
        try {
            Gson gson = new Gson();
            return gson.toJson(object);
        } catch (Exception e) {
            Log.d("ERROR: ", e.getMessage());
            return "";
        }
    }
}
