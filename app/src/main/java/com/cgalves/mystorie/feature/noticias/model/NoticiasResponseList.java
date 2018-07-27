package com.cgalves.mystorie.feature.noticias.model;

import com.cgalves.mystorie.common.model.DetailSection;
import com.cgalves.mystorie.common.model.Noticia;

import java.util.List;

/**
 * Created by scopus on 27/07/18.
 */

public class NoticiasResponseList {
    public List<Noticia> getNoticiaList() {
        return noticias;
    }

    public void setNoticiaList(List<Noticia> noticiaList) {
        this.noticias = noticiaList;
    }

    public List<Noticia> noticias;
}
