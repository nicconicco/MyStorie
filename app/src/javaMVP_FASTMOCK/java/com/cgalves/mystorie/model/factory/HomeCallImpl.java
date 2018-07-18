package com.cgalves.mystorie.model.factory;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.abstractcalls.HomeAbstractCall;
import com.cgalves.mystorie.feature.home.model.Image;
import com.cgalves.mystorie.feature.home.model.ImageResponse;
import com.cgalves.mystorie.feature.home.model.Section;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scopus on 17/07/18.
 */

public class HomeCallImpl extends HomeAbstractCall {

    public HomeCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findImageTopHeader(String token) {

        List<Image> list = new ArrayList<>();

        Image img = new Image();
        Image img2 = new Image();
        Image img4 = new Image();

        img.setImage(ContextCompat.getDrawable(context, R.drawable.seneca1));
        img2.setImage(ContextCompat.getDrawable(context, R.drawable.seneca2));
        img4.setImage(ContextCompat.getDrawable(context, R.drawable.aristoteles));

        img.setTitle("Sêneca imagem 1 Título");
        img2.setTitle("Sêneca imagem 2 Título");
        img4.setTitle("Aristóteles imagem 3 Título");

        list.add(img);
        list.add(img2);
        list.add(img4);

        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setList(list);

        //todo: transformar em um json response
        post(imageResponse, bus);
    }

    @Override
    public void findSectionsInBody(String token) {
        List<Section> list = new ArrayList<>();

        Section section1 = new Section();
        Section section2 = new Section();
        Section section3 = new Section();

        section1.setName("Notícias");
        section1.setSubTitle("Fique ligado o que acontece no dia a dia \n" +
                "do seu candidato");
        section1.setId(10123123142L);

        section2.setName("Novidades!");
        section2.setSubTitle("Veja quais são as novidades do seu candidato");
        section2.setId(10123123143L);

        section3.setName("Contato");
        section3.setSubTitle("Caso queira receber mais informações entre\n" +
                "em contato com a gente!");
        section3.setId(10123123144L);

        list.add(section1);
        list.add(section2);
        list.add(section3);

        //todo: transformar em um json response
        post(list, bus);
    }
}
