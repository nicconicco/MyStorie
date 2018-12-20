package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.common.abstractcalls.NoticiasAbstractCall;
import com.cgalves.mystorie.common.abstractcalls.NovidadesAbstractCall;
import com.cgalves.mystorie.feature.noticias.model.NoticiasResponseList;
import com.cgalves.mystorie.feature.novidades.model.NovidadesResponseList;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by scopus on 27/07/18.
 */

public class NovidadesCallImpl extends NovidadesAbstractCall {
    public NovidadesCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findNovidades(String novidades) {

        String json = "    {        \"novidades\": [        {            \"name\": \"Sêneca um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Seneca.jpg/240px-Seneca.jpg\"\n" +
                "        },        {            \"name\":\"Budda um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"http://secularbuddhism.org/wp-content/uploads/2011/06/budda1.jpg\"\n" +
                "        },\n" +
                "        \n" +
                "        {            \"name\":\"Aristoteles um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i1.wp.com/www.guiadafilosofia.com.br/wp-content/uploads/2018/04/12.jpg\"\n" +
                "        },       \n" +
                "        {            \"name\":\"Socrates um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://cdn.pbrd.co/images/HwRNvzU.png\"\n" +
                "        },      \n" +
                "        {            \"name\":\"Marco Polo um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"http://tabulaquadrada.com.br/wp-content/uploads/marco-polo.png\"\n" +
                "        },        {            \"name\":\"Gengis Khan um autor\",\n" +
                "            \"txt\":\"Uma estória que vai deixar você de queijo caido\",\n" +
                "            \"img\":\"https://i.pinimg.com/736x/29/6e/11/296e11f47ad927636a42a01014335103--gengis-khan-mongolia.jpg\"\n" +
                "        }]    }";

        NovidadesResponseList responseList = new Gson().fromJson(json, NovidadesResponseList.class);

        bus.post(responseList);
    }
}
