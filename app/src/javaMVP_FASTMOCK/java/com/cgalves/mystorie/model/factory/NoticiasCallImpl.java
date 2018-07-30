package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.NoticiasAbstractCall;
import com.cgalves.mystorie.feature.noticias.model.NoticiasResponseList;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 27/07/18.
 */

public class NoticiasCallImpl extends NoticiasAbstractCall {
    public NoticiasCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findNoticias(String noticias) {

        String json = "    {        \"noticias\": [        {            \"name\": \"Sêneca um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        },        {            \"name\":\"Budda um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        },\n" +
                "        \n" +
                "        {            \"name\":\"Aristoteles um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        },       \n" +
                "        {            \"name\":\"Socrates um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        },      \n" +
                "        {            \"name\":\"Marco Polo um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        },        {            \"name\":\"Gengis Khan um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        }]    }";

        NoticiasResponseList responseList = new Gson().fromJson(json, NoticiasResponseList.class);

        bus.post(responseList);
    }
}
