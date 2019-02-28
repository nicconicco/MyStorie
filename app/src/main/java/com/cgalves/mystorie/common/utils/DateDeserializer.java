package com.cgalves.mystorie.common.utils;


import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author R/GA
 */

public class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    private static final String TAG = DateDeserializer.class.getSimpleName();

    private static final String[] DATE_FORMATS = new String[] {
            "dd/MM/yyyy",
            "yyyy-MM-dd'T'HH:mm:ss'z'",
            "yyyy-MM-dd",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss",
            "HH:mm:ss.SSSSSS'Z'",
            "HH:mm",
    };



    public static String convert(String dataStr, String formatoOrigem, String formatoDestino)  {
        String dataResult = null;
        try {
            Date date;
            if (dataStr == null) {
                return null;
            } else if (dataStr.matches("[\\d]+")) {
                Long value = Long.parseLong(dataStr);
                if (value == 0L) {
                    return null;
                }
            }

            if (formatoOrigem.contains("SSSSSS'Z'") && dataStr.length() > 7) {
                formatoOrigem = formatoOrigem.replace(".SSSSSS'Z'", "'Z'");
                dataStr = dataStr.substring(0, dataStr.lastIndexOf("Z") - 7) + "Z";
                //Log.debug("Ignorando milisegundos no formato de hora");
            }

            SimpleDateFormat origemSimpleDateFormat = new SimpleDateFormat(formatoOrigem,Locale.getDefault());
            SimpleDateFormat destinoSimpleDateFormat = new SimpleDateFormat(formatoDestino,Locale.getDefault());

            if (formatoOrigem.contains("'Z'")) {
                origemSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            }
            if (formatoDestino.contains("'Z'")) {
                destinoSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            }

            date = origemSimpleDateFormat.parse(dataStr);
            dataResult = destinoSimpleDateFormat.format(date);
            //log.debug(String.format("Convertendo data %s -> %s", dataStr, dataResult));
        } catch (ParseException | IllegalArgumentException e) {
            Log.d(TAG, "Falha ao converter data.");
        }
        return dataResult;
    }


    SimpleDateFormat dateFormat;


    public DateDeserializer() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
    }


    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF, JsonDeserializationContext context) throws JsonParseException {
        for (String format : DATE_FORMATS) {
            try {
                String formatOut = format.replace("'z'","").replace("'Z'","");
                String date = convert(jsonElement.getAsString(),format,formatOut);
                if (date != null)
                  return new SimpleDateFormat(formatOut,Locale.getDefault()).parse(date);
            } catch (ParseException e) {
                throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                        + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
            }
        }
        return null;
    }

    @Override public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

}