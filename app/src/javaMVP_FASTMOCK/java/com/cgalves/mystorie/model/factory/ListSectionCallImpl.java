package com.cgalves.mystorie.model.factory;

import android.content.Context;

import com.cgalves.mystorie.R;
import com.cgalves.mystorie.common.abstractcalls.ListSectionAbstractCall;
import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.feature.home.model.Section;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Nicolau Galves on 25/07/18.
 */

public class ListSectionCallImpl extends ListSectionAbstractCall {

    public ListSectionCallImpl(EventBus bus, Context context) {
        super(bus, context);
    }

    @Override
    public void findSection(Section section) {

        switch (section.getName()) {
            case "Novidades!" :  {

                List<DetailSection> list = new ArrayList<>();

                DetailSection section1 = new DetailSection(context.getString(R.string.contemporaneo), context.getString(R.string.contemporaneo_de_jesus), "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");
                DetailSection section2 = new DetailSection("Novidade 2 de sêneca", "Texto vindo da novidade 2", "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");
                DetailSection section3 = new DetailSection("Novidade 3 de sêneca", "Texto vindo da novidade 3", "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");

                list.add(section1);
                list.add(section2);
                list.add(section3);

                post(list, bus);
                break;
            }
            case "Notícias" :  {
                List<DetailSection> list = new ArrayList<>();
                
                DetailSection section1 = new DetailSection("Noticia 1 de sêneca", "Texto vindo da novidade 1", "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");
                DetailSection section2 = new DetailSection("Noticia 2 de sêneca", "Texto vindo da novidade 2", "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");
                DetailSection section3 = new DetailSection("Noticia 3 de sêneca", "Texto vindo da novidade 3", "https://i.ytimg.com/vi/bPNTOZTXl44/maxresdefault.jpg");

                list.add(section1);
                list.add(section2);
                list.add(section3);

                post(list, bus);
                break;
            }
        }
    }
}
