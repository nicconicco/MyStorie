package com.cgalves.mystorie.feature.novidades.model;

import com.cgalves.mystorie.common.model.Noticia;
import com.cgalves.mystorie.common.model.Novidade;

import java.util.List;

/**
 * Created by scopus on 27/07/18.
 */

public class NovidadesResponseList {

    public List<Novidade> getNovidadeList() {
        return novidades;
    }
    public List<Novidade> novidades;

    public void setNovidadeList(List<Novidade> novidades) {
        this.novidades = novidades;
    }

}
